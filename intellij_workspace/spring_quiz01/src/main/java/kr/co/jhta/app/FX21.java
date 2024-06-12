package kr.co.jhta.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FX21 implements Airplain {
    private Missile m;

    @Override
    public void takeOff() {
        System.out.println("전투기가 미사일을 발사합니다.");
        m.fire();
    }

    @Override
    public void landing() {
        System.out.println("전투기가 착륙합니다.");

    }

    @Override
    public void fly() {

        System.out.println("전투기가 이륙합니다.");
    }
}
