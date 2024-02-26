package javaz.api;

//Wrapper 클래스
//- 기본형을 참조형으로 표현하는 클래스들
//  - 기본 데이터 형을 값이 아닌 객체로 사용 가능
//- 메서드의 매개변수로 기본형이 참조형을 전달하거나,
//  클래스가 제공하는 상수 또는 메서드 사용 가능
//- Boolean, Character
//- Byte, Short, Integer, Long, Float, Double
//  - 추상 클래스 Number의 하위 클래스

//Boxing 	: 기본형 -> 참조형
//Unboxing	: 참조형 -> 기본형

public class Wrapper {
	public static void main(String[] args) 
				throws ArithmeticException{
		//1. boxing & unboxing -----------------
		double d = 3.14;
//		d.~~~  //X
		Double dd = 3.14;	   //auto boxing
		dd = Double.valueOf(3.14);	//boxing
		d = dd.doubleValue();		//unboxing
		
		System.out.println(d);
		System.out.println(dd);
		
		Integer i = 123;
		int ii = Integer.valueOf(123);
		
		//2. String > int ----------------------
//		ii = "456";	//X
		try {
			ii = Integer.valueOf("456");
			ii = Integer.parseInt("567");
			ii = Integer.parseInt("오륙칠");
		} catch (NumberFormatException e) {
			System.out.println("한글은 숫자로 파싱 불가");
		}
		
		//3. int > String
		String s1 = String.valueOf(100);  //100;
		String s2 = 200 + ""; 			  //200;
		String s3 = Integer.toString(300);//300;
		
		//int 범위의 가장 큰 값, 작은 값
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		
//		System.err.println(5/0);
		
		System.out.println( 5/0.0 );
		System.out.println( Double.isInfinite( 5/0.0 ));
		System.out.println();
		
		System.out.println( 5%0.0 );
		System.out.println( Double.isNaN( 5%0.0 ));
		
		////////////////////////////////////
		System.out.println();
		//명령행 매개변수로 단수를 입력받아 
		//구구단을 출력하는 메서드 호출
		new Wrapper().gugudan(args[0]);
	}//END main()
	
	public void gugudan(String arg) {
		try {
			//숫자가 아닌 값이 입력된 경우 - 예외 처리 메시지 출력				
			int dan = Integer.parseInt(arg);
			
			//2 ~ 9 사이가 아닌 경우 - 안내 메시지 출력
			if( dan >= 2 && dan <= 9) {
				//정상적으로 2 ~ 9 사이의 숫자가 입력된 경우 
				System.out.println("----- 구구단 ------");
				System.out.println(dan + "단을 출력합니다.");
				for(int i = 1 ; i <= 9 ; i++ ) {
					System.out.println(dan + " * " + i + " = " + dan*i);
				}
			} else {
				System.out.println("구구단은 2 ~ 9 사이를 입력해주세요.");
			}
		} catch (NumberFormatException e) {
			System.out.println("구구단은 숫자로 입력해주세요.");
		}
	}
}



















