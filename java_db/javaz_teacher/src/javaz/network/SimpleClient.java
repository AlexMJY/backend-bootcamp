package javaz.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleClient {
	private static final int SERVER_PORT = 5000;
	private static final String DATE_PTN = "--- yyyy년 MM월 dd일 ---";
	private static final String TIME_PTN = "[a hh:mm:ss] ";
	
	private BufferedReader br;		//서버의 메시지 읽기
	private PrintWriter pw;			//서버에게 메시지 보내기
	
	public SimpleClient() {
		//클라이언트 소켓 생성
		System.out.println("***** THIS IS a CLIENT *****");
		String nickname = "aaa";
		String msg = "hello!";
		
		try(Socket clientSocket = new Socket("localhost", SERVER_PORT)){	
			//서버로 메시지를 보낼 스트림 생성
			pw = new PrintWriter(clientSocket.getOutputStream(), true);
			
			//서버로 닉네임 보내기
			pw.println(nickname + "#" + msg);			
			
			//3.서버에서 보낸 메시지를 읽어올 스트림 생성
			br = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
			
			//4.서버에서 보낸 메시지 출력
			System.out.println(timeDateStamp(TIME_PTN) + 			
	   		   		  		   br.readLine());
		} catch(ConnectException e) {
			System.err.println(">> 서버에 연결하지 못했습니다.");
			System.err.println(">> 서버 가동 상태를 확인해 주세요.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally { 
			try {
				if(pw != null) 				pw.close();
				if(br != null) 				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}//END 기본 생성자
	
	public String timeDateStamp(String ptn) {
		return new SimpleDateFormat(ptn).format(new Date());
	}
	
	public static void main(String[] args) {
		new SimpleClient();
	}
}





























