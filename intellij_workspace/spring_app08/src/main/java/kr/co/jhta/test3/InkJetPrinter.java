package kr.co.jhta.test3;

import lombok.Setter;

@Setter
public class InkJetPrinter implements Printer {
    int red, green, blue; // 0 ~ 100

    @Override
    public void print() {
        if (red > 0 && green > 0 && blue > 0) {
            System.out.println("프린팅");
            System.out.println("red : " + red);
            System.out.println("green: " + green);
            System.out.println("blue : " + blue);

            red -= 10;
            green -= 10;
            blue -= 10;

            System.out.println("-----------------------------");
            System.out.println("red : " + red);
            System.out.println("green: " + green);
            System.out.println("blue : " + blue);
        } else {
            System.out.println("잉크가 부족합니다.");
        }
    }


}
