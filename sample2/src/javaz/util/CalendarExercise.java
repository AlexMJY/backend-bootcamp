package javaz.util;

import java.util.Calendar;

public class CalendarExercise {
	private static Calendar cal;
	
	//1.기본생성자에서 cal 객체 생성 
	public CalendarExercise() {	
		cal = Calendar.getInstance();
	}
	
	//2.오늘이 속한 달의 달력을 출력하는 printMonth 공유 메서드
	public static void printMonth() {
		System.out.print(cal.get(Calendar.YEAR) + "년 ");
		System.out.print(cal.get(Calendar.MONTH)+1 + "월 ");
		System.out.print(cal.get(Calendar.DATE) + "일 ");
		
		String[] weekdays = { "일", "월", "화", "수", "목", "금", "토"};
		String weekday = weekdays[cal.get(Calendar.DAY_OF_WEEK) - 1];	

		cal.set(Calendar.DATE, 1);
		int start = cal.get(Calendar.DAY_OF_WEEK);
		int last  = cal.getActualMaximum(Calendar.DATE);
		
		System.out.println(weekday + "요일 ");
		System.out.println();
		System.out.println(" Sun Mon Tue Wed Thr Fri Sat");
		System.out.println(" ---------------------------");
		
		for(int i = 1 ; i<start ; i++) {	//시작 요일까지 공백찍기
			System.out.print("    ");		
		}
		
		for(int i = 1 ; i<=last ; i++) {	
			System.out.printf("%4d", i);	//날짜 찍기
			if(start++ % 7 == 0) { System.out.println(); }
		}
		System.out.println();
	}

	//3.지금 시간을 출력하는 printNow 정적 메서드
	public static void printNow() {
		System.out.print(cal.get(Calendar.AM_PM) == 0 ? "오전 " : "오후 ");
		System.out.print(cal.get(Calendar.HOUR) + ":");
		System.out.print(cal.get(Calendar.MINUTE) + ":");
		System.out.print(cal.get(Calendar.SECOND));
		System.out.println();
	}
	
	//4.명령행 매개변수로 특정 연도와 월을 입력받아 달력을 표시하는 printCalendar 클래스 메서드
	public static void printCalendar(String[] args) {
//		cal.set(Calendar.YEAR, args[0]);	//X
		cal.set(Calendar.YEAR, Integer.parseInt(args[0]));
		cal.set(Calendar.MONTH, Integer.parseInt(args[1]) - 1);
		printMonth();
	}
	
	public static void main(String[] args) {
		new CalendarExercise();
		printMonth();	
		System.out.println("----------------------------");
		printNow();
		System.out.println("----------------------------");
		printCalendar(args);
	}
}

















