package javaz.io;


// 발생한 예외의 날짜와 시간 정보 및
// 발생한 예외 객체 클래스 이름과 예외 메시지를 파일로 저장
// - 파일명은 파일 생성 시점의 long타입의 시간정보.log로 저장
//  중복되지 않도록 처리

import java.io.*;
import java.util.Date;

class ExceptionLog {
    public static void writeLog(Exception e, Date now) {
        // 파일에 예외 정보 저장
        String fileName = "C:\\dev\\" + now.getTime() + ".log";

        try (FileWriter fw = new FileWriter(fileName);
             BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write("날짜 및 시간 : " + now.toString() + "\n");

                bw.write("예외 클래스 : " + e.getClass().getName() + "\n");

                bw.write("예외 메시지 : " + e.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}


public class ExceptionLogSaveFileExercise {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // 예외 발생
        } catch (Exception e) {
            Date now = new Date(); // 예외 발생 시간
            ExceptionLog.writeLog(e, now);
        }
    }
}
