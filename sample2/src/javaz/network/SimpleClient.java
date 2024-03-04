package javaz.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleClient {
    private static final int SERVER_PORT = 5000;
    private static final String DATE_PTN = "--- yyyy년 MM월 dd일 ---";
    private static final String TIME_PTN = "[a hh:mm:ss]";

    private BufferedReader br;  // read server message
    private PrintWriter pw; // send message to server

    public SimpleClient() {
        System.out.println("***** This is a Client *****");
        String nickname = "aaa";
        String msg = "hello my name is Alex";

        try (Socket clientSocket = new Socket("localhost", SERVER_PORT)) {
            // 서버로 메시지를 보낼 스트림 생성
            pw = new PrintWriter(clientSocket.getOutputStream(), true);

            // 서버로 닉네임 보내기
            pw.println(nickname);
            pw.println(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    } // END 기본 생성자

    public static String timeDateStamp(String ptn) {
        return new SimpleDateFormat(ptn).format(new Date());

    }

    public static void main(String[] args) throws IOException {
        new SimpleClient();
    }
}
