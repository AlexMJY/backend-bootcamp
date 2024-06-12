package javaz.io;

import java.io.*;

public class ReaderWriterMain {
    public static void main(String[] args) throws IOException {
        String file = "src\\user.txt";
        try {
            // 파일과 연결된 파일 입력 스트림 생성
            FileInputStream fis = new FileInputStream(file);

            // InputStream 타입의 객체에 파일 입력 스트림 저장
            InputStream is = fis;

            // 파일의 내용을 읽어서 화면에 출력
            int input = 0;
            while ((input = is.read()) != -1) {
                System.out.print((char) input);
            }

            // 스트림 닫기
            fis.close();
        } catch (IOException e) {
            System.err.println("파일 읽기 오류: " + e.getMessage());
            e.printStackTrace();
        }

        int input = 0;
        Reader reader = new FileReader(file);
        while ( (input = reader.read()) != -1) {
            System.out.println((char) input);
        }
        reader.close();
        System.out.println();
        System.out.println("-----------------------");

        Writer writer = new FileWriter("write.txt");
        writer.write("Let's write! 파일에 써 보아요");
        System.out.println("파일 쓰기 완료");
        writer.close();
    }
}
