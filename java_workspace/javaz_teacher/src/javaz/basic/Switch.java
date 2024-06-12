package javaz.basic;

//switch문
//-조건이 여러 개이거나
// 특정 경우의 수가 정해져 있는 경우에 유용
//-switch의 조건은 int 이하의 정수 또는 String 값
//-조건에 맞는 case 문을 수행
// - 조건에 일치하더라도 break(생략 가능) 문이 없으면 계속 수행
// - 모든 case와 일치하지 않는 경우 default(생략 가능) 문 수행

//switch(조건) {
//case 값1 :	//수행문1;
			//수행문2;
			//...
			//수행문n;
//			[break;]
//case 값2 : //수행문1;
//	...
//case 값n : //수행문1;
//[default : //수행문1;]
//}

public class Switch {

	public static void main(String[] args) {
		System.out.println("--- SWITCH PIZZA MALL ---");
		String size = "small"; //small/medium/large;
		int price = 0;          //10000  20000  30000
		
		//사이즈 별로 가격 확인
		switch (size) {
		case "small"  : price = 10000;	break;
		case "medium" : price = 20000;	break;
		case "large"  : price = 30000;	break;
		default:
			System.out.println("피자 사이즈 선택 오류!!");
			System.out.println("small, medium, large " + 
							   "중에 하나를 선택해주세요.");			
		//	break;	//switch문 중단
			return;	//프로그램 실행을 중단하고 호출한 곳으로 반환
		}
		
		System.out.println("사이즈  : " + size);
		System.out.println("가격(원): " + price);
		

		System.out.println();
		//특정 연월의 마지막 날 알아보기 - 윤년 고려
		int year = 2000;
		int month = 2;
		int lastDate = 0;
		
		switch (month) {
		case 2  : //lastDate = 28;	break;
		  lastDate = (year%4 == 0 && year%100 != 0) ||			
			 		 (year%400 == 0) ? 29 : 28;
					break;
		case 4  :  
		case 6  :  
		case 9  : 
		case 11 : lastDate = 30;	break;
		
		default : lastDate = 31;	break;
		}
		
		System.out.println(year + "년 " +
		                   month + "월의 마지막 날 : " +
						   lastDate);
		
	}//END main()

}//END class















