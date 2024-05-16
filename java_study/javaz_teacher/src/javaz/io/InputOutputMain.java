package javaz.io;

import java.io.*;
import java.util.Arrays;

public class InputOutputMain {
	//표준 입력 
	public static void standardInput() throws IOException {
		System.out.print("문자 하나를 입력해주세요. : ");
		int input = System.in.read();	//1바이트만 읽기 가능
		System.out.println("input data : " + input);
		System.out.println("input char : " + (char)input);
		System.out.println();
	}
	
	//InputStream 이용
	public static void inputStream() throws IOException{
		System.out.print("단어 하나를 입력해주세요. : ");
		InputStream is = System.in;
		byte[] inputBytes = new byte[10];//읽은 바이트를 저장할 배열
		int input = is.read(inputBytes); //읽은 값을 inputBytes에 저장
		System.out.println("input data : " + input);
		System.out.println("input char : " + (char)input);
		System.out.println(Arrays.toString(inputBytes));
		System.out.print("input word : [" );
		for(int i=0 ; i < (input-2) ; i++) {	//13, 10번 제외하기
			System.out.print( (char)inputBytes[i]);
		}
		System.out.println("]");	
		System.out.println(new String(inputBytes));//한글 처리
		is.close();
		System.out.println();
	}
	
	//InputStream을 try with resources로 열고 자동으로 닫기
	public static void inputStream2(){
		try(InputStream is = System.in) {
			System.out.println("--- 단어의 아스키코드 알아보기 ---");
			System.out.print("단어를 입력해주세요(종료는 Ctrl + z) : ");
			while(true) {	
				int input = is.read();
				
				switch (input) {
				case 13 : continue;	//'\r'   
				case 10 : System.out.print("단어를 입력해주세요"+ 
										   "(종료는 Ctrl + z) : ");
						  continue;	//'\n'
				case -1 : System.out.println();	
						  System.out.println("- 프로그램이 종료되었습니다. -");
						  System.exit(0);						  
				default : System.out.println((char)input + " : " +  input);			
				}
			}//END while
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//표준 출력
	public static void standardOutput() {
		System.out.write('A');
		System.out.write(66);
		System.out.write('c');	//기본 버퍼 512바이트가 다 채워져야 출력
		System.out.write('\n');	//또는 13, 10을 쓰면 버퍼를 비우고 출력
		System.out.write(10);	//
	}
	
	//OutputStream 이용
	public static void outputStream() throws IOException{
		OutputStream os = System.out;
		os.write(68);
		os.write('E');
		os.flush();		//출력 버퍼 비우기
		
		os.write('F');
		os.write('G');
		os.write('\n');
		
		byte[] outputBytes = { 72, 101, 108, 108, 111 };
		os.write(outputBytes);
		os.write('\n');
		
		String hangul = "가나다라마바사아자차카타파하";
		byte[] hangulBytes = hangul.getBytes();
		os.write(hangulBytes);
		os.write('\n');
		
		os.close();		//스트림 닫기 - 채워진 버퍼를 비움
		
		System.err.write(outputBytes);
	}
	
	public static void main(String[] args) throws IOException {
		outputStream();
		//standardOutput();
		//standardInput()
		//inputStream();
		//inputStream2()
	}
}
































