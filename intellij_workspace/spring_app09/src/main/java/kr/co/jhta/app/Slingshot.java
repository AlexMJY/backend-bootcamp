package kr.co.jhta.app;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Slingshot implements Weapon {
    int ball;

    public void shoot() {
        if (ball > 0) {
            System.out.println("빡");
            ball -= 1;
        } else {
            System.out.println("픽");
        }
    }

    public void reload() {
        System.out.println("새총 쇠구슬 장전 완료");
        ball = 10;
    }

    @Override
    public void use() {
        shoot();
    }

    @Override
    public void reuse() {
        reload();
    }
}
