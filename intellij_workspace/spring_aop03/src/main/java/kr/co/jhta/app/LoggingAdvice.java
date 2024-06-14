package kr.co.jhta.app;

// transfer 메서드가 호출되면 기록 남기기
// d:\dev\log\log20240615.txt 파일생성
// 2024년 6월 14일 15시 30분 이체함

import org.springframework.aop.AfterReturningAdvice;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LoggingAdvice  implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        makeLog();
    }

    private void makeLog() {
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String fileContent = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

        File f = new File("d:\\dev\\log\\log"+fileName+".txt");
        System.out.println("파일 절대 경로 : " + f.getAbsolutePath());

        try {
            FileWriter fw = new FileWriter(f, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(fileContent);
            pw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        LoggingAdvice la = new LoggingAdvice();
        la.makeLog();
    }
}
