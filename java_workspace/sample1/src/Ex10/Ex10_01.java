package Ex10;

import java.util.Calendar;
public class Ex10_01 {
    public static void main(String[] args) {
        Calendar today = Calendar.getInstance();
        System.out.println("이 해의 년도 : " + today.get(Calendar.YEAR));
        System.out.println("월(0~11) : " +  (today.get(Calendar.MONTH) + 1));
        System.out.println("이 해의 몇 째 주 : "  + today.get(Calendar.WEEK_OF_YEAR));
        System.out.println("이 달의 몇 째 주 : " + today.get(Calendar.WEEK_OF_MONTH));

        System.out.println("이 달의 몇 일 : " + today.get(Calendar.DATE));
        System.out.println("이 달의 몇 일 : " + today.get(Calendar.DAY_OF_MONTH));
        System.out.println("이 해의 몇 일 : " + today.get(Calendar.DAY_OF_YEAR));

        System.out.println("시간(0~11) : " + today.get(Calendar.HOUR));
        System.out.println("시간(0~23_ : " + today.get(Calendar.HOUR_OF_DAY));
        System.out.println("분 : " + today.get(Calendar.MINUTE));
        System.out.println("초 : " + today.get(Calendar.SECOND));
        System.out.println("밀리초(1000분의 1초) : " + today.get(Calendar.MILLISECOND));

        System.out.println("이 달의 마지막 날 : " + today.getMaximum((Calendar.DATE)));
    }
}
