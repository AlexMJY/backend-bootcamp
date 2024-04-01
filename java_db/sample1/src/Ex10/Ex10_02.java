package Ex10;

import java.util.Calendar;

public class Ex10_02 {
    public static void main(String[] args) {
        final String[] DAY_OF_WEEK = {"","일","월","화","수","목","금","토"};

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        // month의 경우 0부터 시작하기 때문에 4월인 경우, 3로 지정해야한다.
        // date1.set(2019, Calendar.APRIL, 29);와 같이 할 수도 있다.
        date1.set(2019, 3, 29);
        System.out.println("date1은 "+ toString(date1)
                + DAY_OF_WEEK[date1.get(Calendar.DAY_OF_WEEK)]+"요일이고,");
        System.out.println("오늘(date2)은 " + toString(date2)
                + DAY_OF_WEEK[date2.get(Calendar.DAY_OF_WEEK)]+"요일입니다.");

        // 두 날짜간의 차이를 얻으려면, getTimeInMillis() 천분의 일초 단위로 변환해야한다.
        long difference = (date2.getTimeInMillis() - date1.getTimeInMillis()) / 1000;
        System.out.println("date2 - date1 : " + difference);
        System.out.println("date2 - date1 (days) : " + (difference / (24*60*60)));
    }

    public static String toString(Calendar date) {
        return date.get(Calendar.YEAR) + "년 " + (date.get(Calendar.MONTH) + 1) + "월 " +
                date.get(Calendar.DATE) + "일 ";
    }
}
