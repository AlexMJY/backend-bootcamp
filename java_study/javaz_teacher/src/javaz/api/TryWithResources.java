package javaz.api;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TryWithResources {
	
	public void fileRead(String filename) {
		try(FileReader fr = new FileReader(filename)){
			char[] ch = new char[1000];  
			fr.read(ch);	 
			
			for (char c : ch) {
				System.out.print(c);	 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//파일명을 매개변수로 받아서 읽어온 후
	//"파일명.bak" 파일로 저장하는 클래스 메서드 fileReadWrite
	public static void fileReadWrite(String filename) {
		    //사용하려는 리소스가 여러 개인 경우 세미콜론으로 구분
		try(FileReader fr = new FileReader(filename);
			FileWriter fw = new FileWriter(filename + ".bak")){
			char[] ch = new char[1000];  
			fr.read(ch);	 
			
			for (char c : ch) {
				System.out.print(c);	 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String filename = ".classpath";
		
		TryWithResources twr = new TryWithResources();
		twr.fileRead(filename);
		
		//fileReadWrite 메서드 호출
		twr.fileReadWrite(filename);
		TryWithResources.fileReadWrite(filename);
		fileReadWrite(filename);
		
		System.out.println("-----------------------");
		
		FileReader fr = null;
		try {
			fr = new FileReader(filename);
			char[] ch = new char[1000]; //파일에서 읽은 내용 저장하는 배열
			fr.read(ch);	//파일에서 읽기
			
			for (char c : ch) {
				System.out.print(c);	//ch 배열의 값을 화면에 출력
			}
		} catch (FileNotFoundException e) {
			System.out.println("파일 읽기 오류 발생!!!");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {//예외 발생 여부와 관계없이 닫기 처리!!
			try {
				if(fr != null) { 
					fr.close();	//fr이 null이 아닌 경우에만 닫기!!
				}
			} catch (IOException e) {
				e.printStackTrace();
			}     
		}
		
	}
}





























