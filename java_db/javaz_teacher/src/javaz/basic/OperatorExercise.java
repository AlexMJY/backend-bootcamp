package javaz.basic;

public class OperatorExercise {

	public static void main(String[] args) {
		//피자 주문하기 -----------------------
		int m = 20;	//- 미디엄피자 : 반지름 20cm, 10000원
		int l = 40;	//- 라지피자   : 반지름 40cm, 20000원
		final double PI = 3.14; 	//예산은 20000원
		
		double medium = m * m * PI * 2;		//자동형변환
		int large     = (int)(l * l * PI);	//강제형변환
		String order  = medium > large ? 
					    "미디엄 2판" : "라지 1판"; 
				
		System.out.println("미디엄피자 2판 : " + medium);
		System.out.println("라지피자 1판   : " + large);	
		System.out.println("가성비 피자 주문: " + order);			
		
		//가성비가 높은 주문은?
		//1. 미디엄피자 2판
		//2. 라지피자 1판
	
		

		System.out.println();
		System.out.println();
		//동전 출금하기 ---------------------------
		int money = 7777;	//출금액은 7777원
		int c500, c100, c50, c10;
		
		c500 = money / 500;	   	money = money % 500;
		c100 = money / 100;		money %= 100;
		c50  = money / 50;		money %= 50;
		c10  = money / 10;		money %= 10;
		c10 += money >= 5 ? 1 : 0 ;	//십원 미만 반올림
		
		System.out.println("오백원 : " + c500 + "개 " +
				           (c500 * 500) + "원" );						
		System.out.println("　백원 : " + c100 + "개");
		System.out.println("오십원 : " + c50 + "개");
		System.out.println("  십원 : " + c10 + "개");
		System.out.println();

		System.out.printf("오백원 : %2d개 %,5d원\n",
						  c500, c500 * 500);
		System.out.printf("　백원 : %2d개 %,5d원\n",
				  		  c100, c100 * 100);
		System.out.printf("오십원 : %2d개 %,5d원\n",
		  		  		  c50, c50 * 50);
		System.out.printf("　십원 : %2d개 %,5d원\n",
						  c10, c10 * 10);
		
		
		//10원 미만은 10원으로 반올림하여 
		//다음과 같이 출력
		//오백원 : ~~개 ~~~~원
		//　백원 : ~~개     원
		//오십원 : ~~개     원
		//　십원 : ~~개     원
		//
		
	}//END main()

}//END class











