package javaz.io;

import java.io.*;

public class FileInputOutputMain {

    // 파일을 읽기
    public static void fileInputStream() throws IOException {
        String filename = "C:\\Users\\jhta\\Downloads\\classpath.txt";

        // 읽어올 파일과 연결된 파일입력스트림 객체 생성
        FileInputStream fis = new FileInputStream(filename);

        // 파일에서 1바이트씩 읽어서
        // 더 이상 읽을 값이 없을 때까지 (EOF, End of File :: -1)
        // 화면에 표시
        // 입력스트림 닫기
        int input = 0;
        while ( (input = fis.read()) != -1) {
//            System.out.print((char)input);
            System.out.write(input);  // byte 출력이라 type casting을 안해도 된다.
        }
        fis.close();

//        while(true) {
//            int input = fis.read();
//
//            switch (input) {
//                case -1 : System.out.println();
//                    fis.close();
//                    System.out.println("- 프로그램이 종료되었습니다. -");
//                    System.exit(0);
//                default : System.out.print((char)input);
//            }
//        }//END while




    }

    // 파일 출력 스트림
    public static void fileOutputStream() throws IOException {
        String filename = "C:\\Users\\jhta\\Downloads\\classpath.txt";

        // 파일을 읽어서 "원본파일명.bak" 파일로 저장
        String backupFile = filename + ".bak";


        FileInputStream fis = new FileInputStream(filename);
        FileOutputStream fos = new FileOutputStream(backupFile);

        int input = 0;
        while ( (input = fis.read()) != -1) {
            fos.write(input);
        }
        fos.close();
        fis.close();
        System.out.println("Backup created Succenssfully");
    }


    // 키보드 입력을 파일로 저장한 후 저장된 내용을 화면에 출력
    public static void makeTextFile() throws IOException {
        String filename, path; // 파일이름, 파일경로
        byte[] inputBytes = new byte[100]; // 입력 데이터 저장 배열
        int input = 0; // 키보드 입력 저장
        InputStream is = System.in; // 키보드 입력 스트림

        System.out.println("> JAVA NOTEPAD - making text file");
        System.out.print("> 파일 저장 경로(생략 시 현재 디렉토리) : ");
        is.read(inputBytes);
        path = new String(inputBytes).trim();
        if (path == null || path.length() < 1 || path.equals("")) {
            path = ".";
        }

        System.out.print("> 파일명 : ");
        inputBytes = new byte[100]; // 바이트 배열 초기화
        is.read(inputBytes);
        filename = new String(inputBytes).trim();

//        String makeFile = path + filename;
//        FileOutputStream fos = new FileOutputStream(makeFile);
        FileOutputStream fos = new FileOutputStream(path + System.getProperty("file.separator") + filename);


        System.out.println("> 파일 내용을 입력해 주세요 (종료는 Ctrl + z).");
        is.read(inputBytes);
        String ft = new String(inputBytes);

        FileInputStream fis = new FileInputStream(path + System.getProperty("file.separator") + filename);


        while (true) {
            fos.write(ft.getBytes());
            System.out.println("> 파일 저장이 완료되었습니다.");
            System.out.println("> 파일 내용 : ");
            System.out.print((char)input);
            is.close();
            fos.close();
        }



        // 파일에서 1바이트씩 읽어서
        // 더 이상 읽을 값이 없을 때까지 (EOF, End of File :: -1)
        // 화면에 표시
        // 입력스트림 닫기




    }

    public static void main(String[] args) throws IOException {
    //        fileInputStream();
    //        fileOutputStream();
        makeTextFile();
    }

}

