package javaz.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SimpleClient {
    private static final int SERVER_PORT = 5000;
    private static final String DATE_PTN = "--- yyyy년 MM월 dd일 ---";
    private static final String TIME_PTN = "[a hh:mm:ss]";

    private BufferedReader br;  // read server message
    private PrintWriter pw; // send message to server

    public SimpleClient() throws IOException {
        System.out.println("***** This is a Client *****");
        String nickname = "";
        String msg = "";

        try (Socket clientSocket = new Socket("localhost", SERVER_PORT)) {
            // 서버로 메시지를 보낼 스트림 생성
            pw = new PrintWriter(clientSocket.getOutputStream(), true);

            // 서버로 닉네임 보내기
            Scanner scanner = new Scanner(System.in);

            System.out.print("nickname : ");
            nickname = scanner.nextLine();

            System.out.print("msg : ");
            msg = scanner.nextLine();

            pw.println(nickname + "#" + msg);


            // 연결 완료메시지 읽어오기
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String serverMsg = br.readLine();
            System.out.println(serverMsg);



        } catch (ConnectException e) {
            System.err.println("서버 가동상태를 확인해주세요.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pw != null) pw.close();
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    } // END 기본 생성자

    public static String timeDateStamp(String ptn) {
        return new SimpleDateFormat(ptn).format(new Date());

    }

    public static void main(String[] args) throws IOException {
        new SimpleClient();
    }
}
