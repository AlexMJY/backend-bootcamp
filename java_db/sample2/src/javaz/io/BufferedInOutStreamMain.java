package javaz.io;


import java.io.*;

// 기본 버퍼 크기 8192 바이트
// 생성자를 이용하여 버퍼 크기 지정 가능
// 파일입출력 시스템과 연결하는 보조스트림
// - 버퍼의 크기만큼 한 번에 읽어서 처리 가능
public class BufferedInOutStreamMain {
    public static void main(String[] args) throws IOException {
        String filename = "C:\\Users\\jhta\\Downloads\\classpath.txt";


        try (FileInputStream fis = new FileInputStream(filename);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            int input = 0;
            while ( (input = bis.read()) != -1) {
                System.out.write(input);
            }
            System.out.println();

            filename = "C:\\Users\\jhta\\Downloads\\buffered.txt";
            // for문을 이용하여 buffered.txt 파일에 1~9까지 쓰기
            FileOutputStream fos = new FileOutputStream(filename);
            BufferedOutputStream bos = new BufferedOutputStream(fos, 5);

            for (int i = '1'; i <= '9'; i++) {
//                fos.write(i);
                bos.write(i);
            }
            System.out.println("파일 쓰기 완료");
//            fos.flush();  // 출력 버퍼 비우기
//            fos.close();
            bos.flush();
            bos.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        };


    }
}
