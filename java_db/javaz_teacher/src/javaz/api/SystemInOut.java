package javaz.api;

import java.io.IOException;

public class SystemInOut {
	public static void main(String[] args) throws IOException {
		//5.표준 입출력 ----------------------------
		System.out.print("문자 하나를 입력해주세요. : ");
		int input = System.in.read();
		System.out.println("input data : " + input);
		System.out.println("input char : " + (char)input);

//		System.out.print("문자 하나를 입력해주세요. : ");
		input = System.in.read();	//13 - Carriage Return
		System.out.println("input data : " + input);
		System.out.println("input char : " + (char)input);

//		System.out.print("문자 하나를 입력해주세요. : ");
		input = System.in.read();	//10 - Line feed
		System.out.println("input data : " + input);
		System.out.println("input char : " + (char)input);
		
		System.in.close();
	}
}






























