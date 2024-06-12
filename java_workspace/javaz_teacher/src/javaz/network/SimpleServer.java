package javaz.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

//TCP ; Transmission Control Protocol
//- 신뢰성 있게 통신하기 위해 서로 먼저 연결 설정 후
//  데이터 전송
//- 전화와 유사

//UDP ; User Datagram Protocol
//- 연결 X
//- 데이터를 고정 패킷으로 분할하여 패킷 앞에 주소를 붙여서 전송
//- 편지와 유사

//Socket
//- TCP를 위한 구조
//- 응용 프로그램끼리 연결을 위한 연결 끝점end point
//- port를 이용하여 만들어짐

//ServerSocket 클래스
//- 클라이언트에 대한 연결을 기다리고 수락

//Socket 클래스
//- 서버에 연결 요청

public class SimpleServer {
	private static final int PORT = 5000;
	private static final String DATE_PTN = "--- yyyy년 MM월 dd일 ---";
	private static final String TIME_PTN = "[a hh:mm:ss] ";
	
	private Socket clientSocket;
	private BufferedReader br;		//클라이언트의 메시지 읽기
	private PrintWriter pw;			//클라이언트에게 메시지 보내기
	
	public SimpleServer() {
		//서버 소켓 생성
		try(ServerSocket serverSocket = new ServerSocket(PORT)){
			System.out.println(timeDateStamp(DATE_PTN));
			System.out.println(timeDateStamp(TIME_PTN) +
							   "***** SERVER STARTED *****");
			
			while(true) {	//클라이언트 요청 대기 
				System.out.println(timeDateStamp(TIME_PTN) + 
								   "클라이언트 요청 대기 중...");
				//클라이언트 요청 수락
				clientSocket = serverSocket.accept();
				System.out.println(timeDateStamp(TIME_PTN) + 			
						   		   "클라이언트 접속!!");
				
				//클라이언트의 메시지를 읽을 스트림 생성
				br = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				
				//클라이언트 접속 정보 출력 ------------------------
				System.out.println("> 접속 IP \t: " + clientSocket.getInetAddress());
				System.out.println("> 접속 PORT\t: " + clientSocket.getLocalPort());
				System.out.println("> 원격 PORT\t: " + clientSocket.getPort());								
				
				String[] msg = br.readLine().split("#");
				System.out.println("> 클라이언트 닉네임\t: " + msg[0]);
				System.out.println("> 클라이언트 메시지\t: " + msg[1]);
				
				//1.클라이언트에게 보낼 메시지 출력 스트림 생성 
				pw = new PrintWriter(clientSocket.getOutputStream(), true); 

				//2.메시지 "~~~님 서버에 접속되었습니다." 전송
				pw.println(msg[0] + "님 서버에 접속되었습니다.");			

				System.out.println(timeDateStamp(TIME_PTN) + 			
				   		   		   "데이터 전송 완료!!");
				System.out.println();
			}//END while
		} catch(BindException e) { 
			System.err.println(">> 서버가 이미 가동 중입니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally { 
			try {
				if(pw != null) 				pw.close();
				if(br != null) 				br.close();
				if(clientSocket != null) 	clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}//END 기본 생성자
	
	public String timeDateStamp(String ptn) {
		return new SimpleDateFormat(ptn).format(new Date());
	}
	
	public static void main(String[] args) {
		new SimpleServer();
	}
}






















