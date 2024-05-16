package javaz.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//기본 버퍼 크기 8192 바이트
//생성자를 이용하여 버퍼 크기 지정 가능
//파일입출력 시스템과 연결하는 보조스트림
//- 버퍼의 크기만큼 한 번에 읽어서 처리 가능
public class BufferedInOutStreamMain {
	public static void main(String[] args) {
		String filename = ".classpath";
		
		try( FileInputStream fis = new FileInputStream(filename);
			 BufferedInputStream bis = new BufferedInputStream(fis)) {	
			int input = 0;
			while( (input = bis.read()) != -1 ) {
				System.out.write(input);
			}
			System.out.println();
			
			filename = "buffered.txt";  
			FileOutputStream fos = new FileOutputStream(filename);
			BufferedOutputStream bos 	//버퍼크기를 5로 지정
				= new BufferedOutputStream(fos, 5);
			
			for(int i='1' ; i<='9' ; i++) {
//				fos.write(i);
				bos.write(i);
			}
			
			System.out.println("파일 쓰기 완료!");
			bos.flush();	//출력 버퍼 비우기
			bos.close();
			fos.close();	
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

























