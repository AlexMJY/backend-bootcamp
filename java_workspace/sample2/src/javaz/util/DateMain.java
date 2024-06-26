package javaz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateMain {

	public static void main(String[] args) throws ParseException {
		Date date = new Date();
		Date current = new Date(System.currentTimeMillis());
		Date before1hour = new Date(System.currentTimeMillis() 
									- 1000 * 60 * 60);

		System.out.println(date);
		System.out.println(current);
		System.out.println(current.toLocaleString());
		System.out.println(before1hour);
		System.out.println();
		
		//Date -> String
		SimpleDateFormat dateFmt 
			= new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//		String str = date; // X
		String str = dateFmt.format(date);	//O
		System.out.println(str);
		
		//String -> Date
//		current = "2024/02/16";	//X
//		current = dateFmt.parse("2024/02/16");	//X
		current = dateFmt.parse("2024/02/16 11:22:33");	//O
		System.out.println(current);
		
		//Date -> Calendar
		date = new Date();
		Calendar cal = Calendar.getInstance();
//		cal = date;	//X
		cal.setTime(date);	//O
		
		//Calendar -> Date
//		date = cal;	//X
		date = new Date(cal.getTimeInMillis()); //O
		
	}

}

















