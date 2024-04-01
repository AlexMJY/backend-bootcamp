package javaz.basic;

import java.util.Iterator;

//for 반복문
//- 반복 횟수가 명확한 경우에 주로 사용
//- 초기화 : 가장 먼저 1회만 수행
//    	    반복 제어 변수의 선언 또는 초기화
//- 조건식 : 반복 계속 여부 검사 - false이면 반복 종료
//- 증감식 : 반복 제어 변수의 값을 증감 처리한 후
//			조건식으로 이동

//for( 초기화 ; 조건식 ; 증감식 ) {
//		수행문1;
//		수행문2;
//		  ...
//		수행문n;
//}

//for( 초기화 ; 조건식 ; 증감식 )  수행문1;

//for( 초기화 ; 조건식 ; 증감식 )  
//		수행문1;


//중첩 for문
//for( 초기화 ; 조건식 ; 증감식 )  {		//outer loop
//	  for( 초기화 ; 조건식 ; 증감식 )  { //inner loop
//			...
//	  }//END inner loop
//}//END outer loop

public class For {

	public static void main(String[] args) {
		System.out.println(0);
		System.out.println(1);
		System.out.println(2);
		System.out.println(3);
		System.out.println(4);
		
		System.out.println();
		for(int i=0 ; i<=4 ; i++) {
			System.out.println(i);
		}
		
//		for( ; ; ) {			}	//무한루프 
//		for(int i=0 ; ; ) {		}	// "
//		for(int i=0 ; i<10 ; ) {}	// "
//		for(int i=0 ; ; i++) {	}	// "
		

		System.out.println();
		System.out.println("2 ~ 10 사이의 2의 배수");
		System.out.println("2 4 6 8 10");
		for (int i = 2; i < 11 ; i += 2) {
			System.out.print(i + " ");
		}

		System.out.println();
		System.out.println();
		System.out.println("1 ~ 10 사이의 3의 배수");
		System.out.println("3 6 9 ");
		for (int i = 1; i <= 10 ; i++) {
			if( i%3 == 0 ) {
				System.out.print(i + " ");
			}
		}
		

		System.out.println();
		System.out.println();
		System.out.println("1 ~ 10 사이의 모든 정수의 합");
//		for (int i = 1, sum = 0 ; i <= 10 ; i++) {
		int sum = 0;
		for (int i = 1 ; i <= 10 ; i++) {
			sum = sum + i;
//			sum += i;
			System.out.println(i + "까지의 합 : " + sum);				
		}
		System.out.println("--------------------");
		System.out.println("1 ~ 10 사이의 모든 정수의 합 : " + 
						   sum);

		System.out.println();
		System.out.println("구구단 - 2단");
		System.out.println("2 * 1 = 2");
		System.out.println("2 * 2 = 4");
		System.out.println("2 * 3 = 6");
		System.out.println("2 * 4 = 8");
		System.out.println("   ...   ");
		System.out.println("2 * 9 = 18");

		System.out.println();
		for (int i = 1 ; i <= 9 ; i++) {
			System.out.println("2 * " + i + " = " + i * 2);				
		}

		////////////////////////////////
		System.out.println();
		System.out.println("중첩 for문");
		System.out.println("1 : *");
		System.out.println("2 : **");
		System.out.println("3 : ***");
		System.out.println("4 : ****");
		System.out.println("5 : *****");

		System.out.println();
		for(int i = 1 ; i <= 5 ; i++) {
			System.out.print(i + " : ");
			
			//*을 i의 개수만큼 반복 출력할 for 문
			for(int j = 1 ; j <= i ; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		

		System.out.println();
		System.out.println("가로 5 * 세로 5 별표 출력");
		for(int i = 1 ; i <= 5 ; i++) {
			//*을 5개씩 반복 출력할 for 문
			for(int j = 1 ; j <= 5 ; j++) {
				System.out.print("*");
			}
			System.out.println();
		}


		System.out.println();
		System.out.println("구구단 2단 ~ 9단 세로 방향 출력 ");
		//2단 ~ 9단을 반복하는 for문
		for(int dan = 2 ; dan <= 9 ; dan++) {
			//각 단의 1 ~ 9를 반복하는 for문
			for (int i = 1 ; i <= 9 ; i++) {
//				System.out.println("2 * " + i + " = " + i * 2);	
				System.out.println(dan + " * " + i + 
								   " = " + i * dan);				
			}//END 안쪽 for문
			System.out.println();
		}//END 바깥쪽 for문
		

		System.out.println();
		System.out.println("구구단 2단 ~ 9단 가로 방향 두 블럭 출력 ");
		//가로 방향 두 블럭을 반복하는 for문 ---------------------
		for(int row = 2 ; row <= 6 ; row += 4) {
			//각 단의 1 ~ 9를 반복하는 for문
			for (int i = 1 ; i <= 9 ; i++) {
				//각 단을 반복하는 for문
				for(int dan = 0 ; dan <= 3 ; dan++) {
					System.out.print(row + dan + " * " + i + 
									   " = " + i * (row+dan) + 
									   "\t");				
				}//END 안쪽 for문
				System.out.println();
			}//END 바깥쪽 for문
			System.out.println();
		}//END가로 방향 2단을 반복하는 for문
	}//END main()

}//ENd class













