package kr.co.jhta.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CheckWeapon {
    public void check() {
        // 1. 현재 시간을 가져옴
        // Date 객체를 사용하여 현재 시간을 가져오고,
        // SimpleDateFormat을 사용하여 파일 이름과 로그 내용에 필요한 형식으로 포맷합니다.
        String sdfFile = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String sdfContent = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
//        SimpleDateFormat sdfFile = new SimpleDateFormat("yyyyMMddHHmm");
//        SimpleDateFormat sdfContent = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");

//        String sdfForFileName = sdfFile.format(now); // 파일 이름용 포맷
//        String sdfForContent = sdfContent.format(now); // 로그 내용용 포맷

        // 2. d:\dev\log\현재시간명.log 파일 생성
        // 로그 파일이 저장될 디렉토리 경로 설정
        String directoryPath = "d:\\dev\\log";
        // 로그 파일의 전체 경로 설정 (디렉토리 경로 + 파일 이름)
        String filePath = directoryPath + "\\" + sdfFile + ".log";

        // 로그 파일이 저장될 디렉토리가 존재하지 않으면 생성합니다.
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir(); // 디렉토리 생성
        }

        // 지정된 경로에 로그 파일을 생성하고, "무기사용시간"과 현재 시간을 기록합니다.
        File logFile = new File(filePath);
        try {
            FileWriter fw = new FileWriter(logFile, true);
            PrintWriter pw = new PrintWriter(fw);  // 이전 기록 유지하고 다음줄에 작성 가능
//        	BufferedWriter writer = new BufferedWriter(new PrintWriter(fw));

            // 로그 파일에 "무기사용시간 : yyyy년 MM월 dd일 HH시 mm분" 형식으로 현재 시간을 작성
            pw.println("무기사용시간 : " + sdfContent);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            // 예외가 발생하면 스택 트레이스를 출력합니다.
            e.printStackTrace();
        }

    }
}
