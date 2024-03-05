package javaz.network;


// TCP : Transmission Control Protocol
// 신뢰성 있는 통신, 선연결 후 데이터 전송, 전화와 유사

// UDP : User Datagram Protocol
// 연결x, 데이터를 고정 패킷으로 분할하여 패킷 앞에 주소 붙여서 전송, 속도 빠름, 편지와 유사

// ServerSocket 클래스
// TCP를 위한 구조, 응용 프로그램끼리 연결을 위한 연결 끝점 end point, port를 이용하여 만들어짐

// Socket 클래스
// 서버에 연결 요청,

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SimpleServer {
    private static final int PORT = 5000;
    private static final String DATE_PTN = "--- yyyy년 MM월 dd일 ---";
    private static final String TIME_PTN = "[a hh:mm:ss]";

    private Socket clientSocket;
    private BufferedReader br;  // read client message
    private PrintWriter pw; // send message to client


    // 기본 생성자 ServerSocket 객체 serverSocket 생성
    public SimpleServer() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println(timeDateStamp(DATE_PTN));
            System.out.println(timeDateStamp(TIME_PTN) + "******* SERVER STARTED *******");

            // 클라이언트 요청 대기
            while (true) {
                System.out.println(timeDateStamp(TIME_PTN) + " 클라이언트 요청 대기 중");
                clientSocket = serverSocket.accept(); // 클라이언트 요청 수락
                System.out.println(timeDateStamp(TIME_PTN) + " 클라이언트 접속 완료");

                // 클라이언트의 메시지 읽을 스트림 생성
                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


                // 클라이언트 접속 정보 출력
                System.out.println("> 접속 IP\t" + clientSocket.getInetAddress());
                System.out.println("> 접속 PORT\t" + clientSocket.getLocalPort());
                System.out.println("> 원격 PORT\t" + clientSocket.getPort());

                String msg = br.readLine();
                String[] arr = msg.split("#");
                System.out.println("> 클라이언트 닉네임 : " + arr[0]);
                System.out.println("> 클라이언트 메시지 : " + arr[1]);

                // 클라이언트에게 보낼 메시시 출력 스트림 생성
                // "~~님 서버에 접속되었습니다."
//                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                PrintWriter sw = new PrintWriter(clientSocket.getOutputStream(), true);
                sw.println(timeDateStamp(TIME_PTN) + arr[0] + "님 서버에 접속되었습니다.");
                System.out.println(timeDateStamp(TIME_PTN + " 데이터 전송 완료!"));
            }  // END while


        } catch (BindException e) {
            System.err.println("서버가 현재 연결되어있습니다.");

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (pw != null) pw.close();
                if (br != null) br.close();
                if (clientSocket != null) clientSocket.close();
            } catch (BindException e) {
                e.printStackTrace();
                System.err.println("서버가 이미 가동중입니다.");
            }

        }
    } // END 기본 생성자


    // 날짜 또는 시간 패턴을 매개변수로 받아서
    // 지정된 패턴의 문자열을 반환하는 timeDateStamp 메서드
    public static String timeDateStamp(String ptn) {
        return new SimpleDateFormat(ptn).format(new Date());
    }

    public static void main(String[] args) throws IOException {
        new SimpleServer();
    }
}
