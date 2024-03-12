package javaz.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.*;

public class BufferedReaderWriterMain {
	public static void main(String[] args) {
		String filename = ".classpath";
		
		try( FileReader fr = new FileReader(filename);
			 BufferedReader br = new BufferedReader(fr)) {	
			String input = "";
			
			while( (input = br.readLine()) != null ) {
				System.out.println(input);
			}
			System.out.println();
			
			filename = "buffered.txt";  
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw, 5); //버퍼크기를 5로 지정
			
			for(int i='1' ; i<='9' ; i++) {
				bw.write(i);
			}
			System.out.println("파일 쓰기 완료!");
			bw.flush();	//출력 버퍼 비우기
			
			bw.newLine();	//라인 추가
			bw.append("appended 덧붙이기");	
			
			bw.close();
			fw.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

















