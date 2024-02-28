package javaz.io;

import java.io.*;

public class BufferedReaderWriterMain {
    public static void main(String[] args) {
        String filename = "C:\\Users\\jhta\\Downloads\\classpath.txt";


        try (Reader fr = new FileReader(filename);
             BufferedReader br = new BufferedReader(fr)) {
//            int input = 0;
            String input = "";
//            while ( (input = br.read()) != -1) {
            while ( (input = br.readLine()) != null) {
                System.out.println(input);
            }
            System.out.println();

            filename = "C:\\Users\\jhta\\Downloads\\buffered.txt";
            // for문을 이용하여 buffered.txt 파일에 1~9까지 쓰기
            Writer fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw, 5);

            for (int i = '1'; i <= '9'; i++) {
//                fos.write(i);
                bw.write(i);
            }
            System.out.println("파일 쓰기 완료");
//            fos.flush();  // 출력 버퍼 비우기
//            fos.close();
            bw.flush();
            bw.close();
            fw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }
}
