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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MultiServer {
    private static final int PORT = 5000;
    private static final String DATE_PTN = "--- yyyy년 MM월 dd일 ---";
    private static final String TIME_PTN = "[a hh:mm:ss]";

    private Socket clientSocket;
    private Map<String, PrintWriter> clientMap; // <nickname, 출력스트림>

    public MultiServer() {
        //1. 서버 소켓 생성
        //2. clientMap 객체 생성 및 동기화 처리. Collections.synchronizedMap 이용.
        //3. 클라이언트 접속 무한 대기
        //4. 클라이언트 접속 승인. accept
        //5. 클라이언트 처리를 스레드로 시작
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println(timeDateStamp(DATE_PTN));
            System.out.println(timeDateStamp(TIME_PTN) + "******* SERVER STARTED *******");

            clientMap = Collections.synchronizedMap(new HashMap<>());

            while (true) {
                System.out.println(timeDateStamp(TIME_PTN) + " 클라이언트 요청 대기 중!!!");
                clientSocket = serverSocket.accept(); // 클라이언트 요청 수락
                System.out.println(timeDateStamp(TIME_PTN) + " 클라이언트 접속 완료");
                new ChatServerThread().start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //6. 클라이언트를 처리할 스레드 클래스
    class ChatServerThread extends Thread{
        //7. 각 클라이언트에서 사용할 입출력 스트림을 필드로 선언
        private String nickname, msg;
        private BufferedReader br;
        private PrintWriter pw;

        //8. 입력 스트림 객체 생성
        //9. 입력 스트림을 읽어서 닉네임과 메시지 저장
        //10. 출력 스트림 객체 생성
        //11. 출력 스트림으로 접속 일시 및 접속 성공 메시지 쓰기
        ChatServerThread() {
            try {
                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String[] arr = br.readLine().split("#");
                nickname = arr[0];
                msg = arr[1];
                System.out.println("> 클라이언트 닉네임 : " + nickname);
                System.out.println("> 클라이언트 메시지 : " + msg);

                pw = new PrintWriter(clientSocket.getOutputStream(), true);
                pw.println(timeDateStamp(TIME_PTN) + nickname + "님 서버에 접속되었습니다.");

            } catch (BindException e) {
                System.err.println("서버가 이미 가동 중입니다.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //12. 스레드로 처리할 내용
        //12.1 닉네임과 출력 스트림을 clientMap에 저장
        //12.2 입장 메시지 전체에게 브로드 캐스팅
        //12.3 클라이언트가 퇴장 전까지 입력 스트림을 읽어서 전체에게 브로드 캐스팅
        //12.4 클라이언트가 퇴장한 경우 퇴장 메시지를 전체에게 브로드 캐스팅한 후 해당 클라이언트 clientMap에서 삭제
        @Override
        public void run() { //12
            if (clientMap.containsKey(nickname)) {
                System.out.println("동일한 닉네임이 존재합니다.");
            } else {
                clientMap.put(nickname, pw); //12.1
            }
            broadcast(nickname + "님이 들어오셨습니다."); //12.2

            try {
                while (br != null) { //12.3
                    msg = br.readLine();
                    if (msg != null && !msg.equals("-1")) {
                        broadcast(nickname + " > " + msg);
                    } else { //12.4
                        broadcast(nickname + "님이 나가셨습니다.");
                        clientMap.remove(nickname);
                    }
                    System.out.println(msg);



                }
            } catch (SocketException e) {
                System.err.println(nickname + "퇴장");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            try {
                br.close();
                pw.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        }

    }

    public void broadcast(String msg) {
        // 모든 사용자에게 메시지 전송 (시간 포함)
        clientMap.forEach((nickname, pw) -> {
            pw.println(timeDateStamp(TIME_PTN) + msg);
        });


    }

    public static String timeDateStamp(String ptn) {
        return new SimpleDateFormat(ptn).format(new Date());
    }

    public static void main(String[] args) {
        new MultiServer();

    }
}
