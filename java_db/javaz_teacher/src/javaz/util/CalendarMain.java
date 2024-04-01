package javaz.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalendarMain {

	public static void main(String[] args) {
//		Calendar cal = new Calendar();	//X
		Calendar cal = Calendar.getInstance();	//O
		
		System.out.print(cal.get(Calendar.YEAR) + "년 ");
		System.out.print(cal.get(Calendar.MONTH)+1 + "월 ");
		System.out.print(cal.get(Calendar.DATE) + "일 ");
		
		String[] weekdays = { "일", "월", "화", "수", "목", "금", "토"};
		String weekday = weekdays[cal.get(Calendar.DAY_OF_WEEK) - 1];		
		
		System.out.print(weekday + "요일 ");
		System.out.print(cal.get(Calendar.AM_PM) == 0 ? "오전 " : "오후 ");
		System.out.print(cal.get(Calendar.HOUR) + "시 ");
		System.out.print(cal.get(Calendar.MINUTE) + "분 ");
		System.out.print(cal.get(Calendar.SECOND) + "초 ");
		
		///////////////////////////////
		System.out.println();
		System.out.println("- 설정 가능한 TimeZone ID 리스트 - ");
		String[] ids = TimeZone.getAvailableIDs();
		for (String id : ids) {
			System.out.println(id);
		}
		
		TimeZone tz = TimeZone.getTimeZone("Europe/London");
		Calendar london = Calendar.getInstance(tz);
		cal = Calendar.getInstance();
		System.out.println("london hour : " + london.get(Calendar.HOUR));
		System.out.println("seoul hour : " + cal.get(Calendar.HOUR));
		
		System.out.println(new Date(london.getTimeInMillis()));
		System.out.println(london.getTime());
	}

}



















