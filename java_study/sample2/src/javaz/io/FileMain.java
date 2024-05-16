package javaz.io;

import java.io.File;

// File
//- 파일과 디렉토리 경로를 관리하는 클래스
// 파일 크기, 생성, 삭세, 변경 및 마지막 수정 날 짜 등을 알 수 있는 메서드 제공
// 자바에서 모든 데이터의 입출력은 스트림 기반으로 수행
// 파일, 경로 구분자 공유 필드

public class FileMain {
    public static void main(String[] args) {
        System.out.println(System.getProperty("file.separator"));
        System.out.println(System.getProperty("path.separator"));
        System.out.println(File.separator);
        System.out.println(File.pathSeparator);
        System.out.println();

        // src 디렉토리 객체 생성
        File src = new File("src");

        //1. src 디렉토리의 파일 목록을 문자열 배열 fileList로 받기
        //1.1 fileList 배열의 값 화면에 출력
        String[] fileList = src.list();
        for (String file : fileList) {
            System.out.println(file);
        }


        // src 디렉토리의 파일 목록을 파일 배열 files로 받기
        File[] files = src.listFiles();
        for (File file : files) {
            System.out.println(file);
            System.out.println("이름 : " + file.getName());
            System.out.println("부모 : " + file.getParent());
            System.out.println("경로 : " + file.getParent());
            System.out.println("절대경로 : " + file.getAbsolutePath());
            System.out.println("디렉토리 : "+ file.isDirectory());
            System.out.println("파일 : "+ file.isFile());
            System.out.println("마지막 수정 : "+ file.lastModified());
        }
    }
}
