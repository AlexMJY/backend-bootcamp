package kr.co.jhta.app;

import java.time.LocalTime;

public class NewYorkNowTime {
    LocalTime now = LocalTime.now();

    public int getHour() {
        int usHour = now.getHour();
        if (usHour < 0) {
            usHour += 24;
        }
        return usHour;
    }
}
