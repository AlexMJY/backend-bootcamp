package javaz.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class FileInputOutputMain {
	//파일입력스트림
	public static void fileInputStream() throws IOException {
		String filename = ".classpath";
		
		FileInputStream fis = new FileInputStream(filename);
		
		int input = 0;
		while( (input = fis.read()) != -1 ) {
//			System.out.print((char)input);
			System.out.write(input);
		}
		fis.close();
	}
	
	//파일출력스트림
	public static void fileOutputStream() throws IOException {
		String filename = ".classpath";
		
		FileInputStream fis = new FileInputStream(filename);
		FileOutputStream fos = new FileOutputStream(filename + ".bak");
		
		int input = 0;
		while( (input = fis.read()) != -1 ) {
			fos.write(input);
		}
		System.out.println("-- 백업 파일 생성 완료 --");
		fos.close();
		fis.close();
	}
	
	//키보드 입력을 파일로 저장한 후 저장된 내용을 화면에 출력
	public static void makeTextFile() throws IOException {
		String filename, path;			//파일이름, 파일경로
		byte[] inputBytes = new byte[100];	//입력 데이터 저장 배열
		int input = 0;						//키보드 입력 저장
		InputStream is = System.in;			//키보드 입력 스트림
		
		System.out.println("> JAVA NOTEPAD - making text file");
		
		//파일 저장 경로 입력 받기 ----------------------------------
		System.out.print("> 파일 저장 경로(생략 시 현재 디렉토리) : ");
		is.read(inputBytes);
		path = new String(inputBytes).trim();
		
		if(path == null || path.length() < 1 || path.equals("")) {
			path = ".";	//경로명 생략 시 현재 디렉토리 "."을 path에 저장
		}
		
		//파일 이름 입력 받기 --------------------------------------
		System.out.print("> 파일명 : ");
		inputBytes = new byte[100];	//바이트 배열 초기화
		is.read(inputBytes);
		filename = new String(inputBytes).trim();

		//키보드 입력을 파일에 쓰기 ----------------------------------
		FileOutputStream fos 
			= new FileOutputStream(
					path + 
					System.getProperty("file.separator") + 
					filename);
		
		System.out.println("> 파일 내용을 입력해 주세요(종료는 Ctrl + z).");
		while( (input = is.read()) != -1 ) {
			fos.write(input);
		}
		fos.write(input);
		System.out.println("> 파일이 저장되었습니다.");
		fos.close();
	}
	
	public static void main(String[] args) throws IOException {
		makeTextFile() ;
		//fileOutputStream();
		//fileInputStream();
	}
}













