package kr.co.jhta.app;

import java.time.LocalTime;

public class NowTime {
    LocalTime now = LocalTime.now();

    public int getHour() {
        return now.getHour();
    }
}
