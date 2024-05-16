package javaz.basic;

//break : 가까운  반복문 실행 중단
//continue : 반복문의 증감식으로 이동
public class BreakContinue {

	public static void main(String[] args) {
		System.out.println("1 ~ 100 사이의 모든 7의 배수");
		for(int i = 1 ; i <= 100; i++) {
			if( i%7 == 0) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
		System.out.println();
		System.out.println("1 ~ 100 사이에서 가장 큰 7의 배수");
		for(int i = 100 ; i >= 1; i--) {
			if( i%7 == 0) {
				System.out.print(i + " ");
				break;	//반복 중단
			}
		}
		
		System.out.println();
		System.out.println();
		System.out.println("1 ~ 100 사이의 모든 7의 배수");			
		System.out.println("단, 3의 공배수는 제외");
		for(int i = 1 ; i <= 100; i++) {
			if( i%3 == 0 ){  //3의 배수인지 확인
				continue;	//3의 배수이면 이하를 실행하지 않고
							//다음 반복으로 이동
			}
			
			if( i%7 == 0) {	//7의 배수인지 확인
				System.out.print(i + " ");
			}
		}

		System.out.println();
		System.out.println();
		System.out.println("중첩 for문 벗어나기");
		outer:	//반복문 레이블
		for(int i = 5 ; i >= 1 ; i--) {
			for(int j = 1 ; j <= i ; j++) {
//				if( i == 3 ) 	break;	//가까운 for문 중단
				if( i == 3 ) 	break outer;  //중단할 반복문 레이블 명시
				System.out.print("ㅁ ");
			}
			System.out.println();
		}
	}//END main()

}//END class






















