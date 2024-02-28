package javaz.io;


// 발생한 예외의 날짜와 시간 정보 및
// 발생한 예외 객체 클래스 이름과 예외 메시지를 파일로 저장
// - 파일명은 파일 생성 시점의 long타입의 시간정보.log로 저장
//  중복되지 않도록 처리

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

class ExceptionLog {
    public static void writeLog(Exception e, Date now) {
        // 파일에 예외 정보 저장
        String fileName = now.getTime() + ".log";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            // 예외 발생 시간 정보 저장
            writer.println("날짜 및 시간: " + now.toString());
            // 예외 객체 클래스 이름 저장
            writer.println("예외 클래스: " + e.getClass().getName());
            // 예외 메시지 저장
            writer.println("예외 메시지: " + e.getMessage());
            System.out.println("예외 정보가 파일에 저장되었습니다: " + fileName);
        } catch (IOException ex) {
            System.err.println("파일 쓰기 오류: " + ex.getMessage());
            ex.printStackTrace();
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
