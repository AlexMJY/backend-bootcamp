package javaz.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MultiServer {
	private static final int PORT = 5000;
	private static final String DATE_PTN = "--- yyyy년 MM월 dd일 ---";
	private static final String TIME_PTN = "[a hh:mm:ss] ";
	
	private Socket clientSocket;
	private Map<String, PrintWriter> clientMap;  
	           //닉네임,  출력스트림
	
	public MultiServer() {
		try(ServerSocket serverSocket = new ServerSocket(PORT)){	//1
			System.out.println(timeDateStamp(DATE_PTN));
			System.out.println(timeDateStamp(TIME_PTN) +
							   "***** MULTI SERVER STARTED *****");
		
			clientMap = Collections.synchronizedMap(new HashMap<>()); //2	
			
			while(true) {	//3.클라이언트 접속 무한 대기 	
				System.out.println(timeDateStamp(TIME_PTN) + 
								   "클라이언트 요청 대기 중...");
				clientSocket = serverSocket.accept();	//4
				System.out.println(timeDateStamp(TIME_PTN) + 			
						   		   "클라이언트 접속!!");	
				System.out.println("> 접속 IP \t: " + clientSocket.getInetAddress());
				System.out.println("> 접속 PORT\t: " + clientSocket.getLocalPort());
				System.out.println("> 원격 PORT\t: " + clientSocket.getPort());								
				
				new ChatServerThread().start();	//5.클라이언트 처리를 스레드로 시작 시키기	
			}//END while
		} catch(BindException e) { 
			System.err.println(">> 서버가 이미 가동 중입니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//END 기본 생성자
	
	class ChatServerThread extends Thread {
		private BufferedReader br;				//7
		private PrintWriter pw;			 
		private String nickname, msg;
		
		ChatServerThread(){
			try {
				br = new BufferedReader(		//8
						new InputStreamReader(
								clientSocket.getInputStream()));
				
				String[] input = br.readLine().split("#");	//9
				nickname = input[0];
				msg = input[1];
				System.out.println("> 클라이언트 닉네임\t: " + input[0]);
				System.out.println("> 클라이언트 메시지\t: " + input[1]);				
				System.out.println();
				
				
				pw = new PrintWriter(					//10
						clientSocket.getOutputStream(), true); 
				pw.println(timeDateStamp(DATE_PTN));	//11			
				pw.println(timeDateStamp(TIME_PTN) + "서버 접속 성공!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//END ChatServerThread 기본 생성자
		
		public void run() {							//12
			clientMap.put(nickname, pw);			//12.1
			broadcast(nickname + "님이 들어오셨습니다.");//12.2
			
			try {
				while(br != null) {					//12.3 
					msg = br.readLine();
					if(msg != null && !msg.equals("-1")) {
						broadcast(nickname + " > " + msg);  //	   전체에게 브로드 캐스팅 반복하고
					} else { 						//12.4 
						broadcast(nickname + "님이 나가셨습니다."); 
						clientMap.remove(nickname);	//	   해당 클라이언트를 clientMap에서 삭제
					}
				}//END while
			} catch(SocketException e) { 
				System.err.println("> " + nickname + " 퇴장");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//END run()			
	}//END ChatServerThread 클래스
	
	public void broadcast(String msg) {	//모든 사용자에게 시간과 메시지 전송
		clientMap.forEach((nickname, pw) -> {
			pw.println(timeDateStamp(TIME_PTN) + msg);
		});
	}//END broadcast()
	
	public String timeDateStamp(String ptn) {
		return new SimpleDateFormat(ptn).format(new Date());
	}//END timeDateStamp()
	
	public static void main(String[] args) {
		new MultiServer();
	}//END main()
}//ENd class























