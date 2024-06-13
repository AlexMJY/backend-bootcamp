package kr.co.jhta.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StunGun implements Weapon {
    int battery;

    public void shoot() {
        if (battery > 30) {
            System.out.println("발사가능 : 찌리릿");
            battery -= 5;
        } else {
            System.out.println("발사불가능 : 뾱");
        }
    }

    public void charge() {
        System.out.println("충전 중");
        battery = 100;
    }

    @Override
    public void use() {
        shoot();
    }

    @Override
    public void reuse() {
        charge();
    }
}
