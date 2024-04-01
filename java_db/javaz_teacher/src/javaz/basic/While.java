package javaz.basic;

//while, do - while 반복문
//- 특정 조건에 따른 단순 반복 처리에 주로 사용
//- do - while문은 최소 1회 실행 보장
//- 조건을 true로 지정하여 무한 반복 처리

//while(조건식) {
//		수행문
//}

//do { 
//		수행문
//} while(조건식);

public class While {

	public static void main(String[] args) {
		//1 ~ 5 출력
		int i = 1;			//초기화
		while( i <= 5 ) {	//조건식
			System.out.println(i++); //증감식
		}
		System.out.println();
		
		//6 ~ 10까지 출력
		do {
			System.out.println(i++);
		} while( i <= 10);

		System.out.println();

		System.out.println();
		System.out.println("- PLAY GAME while - ");
		char yesNo = 'n';
		//yesNo 변수의 값이 y이면 반복
		//그렇지 않으면 반복 중단
		while( yesNo == 'y' ) {
			System.out.println(">> 게임을 시작합니다.");
			System.out.println(">> 게임이 끝났습니다.");
			System.out.println(">> 계속하시겠습니까? " + yesNo);
		}	
		

		System.out.println();
		System.out.println("- PLAY GAME do while - ");
		do {
			System.out.println(">> 게임을 시작합니다.");
			System.out.println(">> 게임이 끝났습니다.");
			System.out.println(">> 계속하시겠습니까? " + yesNo);
		} while(yesNo == 'y');

		System.out.println();
		System.out.println("- COUNTDOWN while - ");					
		i = 10;
		while( i >= 1 ) {
			System.out.println(i--);
			
			if(i == 0) {
				System.out.println("fire!!");
			}
		}	
		
//		while(true) {	}	//무한 루프
	}//END main()

}//END class























