package kr.co.jhta.control;

import java.time.LocalTime;

public class NowTime {
    LocalTime now = LocalTime.now();

    public int getHour() {
        return now.getHour();
    }
}
