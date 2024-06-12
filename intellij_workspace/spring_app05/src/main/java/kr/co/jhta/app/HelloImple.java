package kr.co.jhta.app;

import lombok.Setter;

@Setter
public class HelloImple implements HelloInter {
    NewYorkNowTime nt;

    @Override
    public void sayHello() {
        int hour = nt.getHour();
        System.out.println("지금 시간은 " + hour + "입니다.");
        if (hour >= 6 && hour <= 10) {
            System.out.println("안녕하세요.");
        } else if (hour >= 10 && hour <= 15) {
            System.out.println("점심 식사하셨나요?");
        } else {
            System.out.println("좋은 저녁되세요.");
        }
    }
}
