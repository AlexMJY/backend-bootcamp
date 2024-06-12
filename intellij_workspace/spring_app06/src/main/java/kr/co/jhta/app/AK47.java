package kr.co.jhta.app;

import lombok.Setter;

//@Setter
public class AK47 implements WeaponInter {
    int bullet;  // int의 기본생성자는 0
    CheckWeapon cw;



//	public void setBullet(int bullet) {
//		this.bullet = bullet;
//	}
//
//	public void setCw(CheckWeapon cw) {
//		this.cw = cw;
//	}

    public AK47(int bullet, CheckWeapon cw) { // 생성자
        super();
        this.bullet = bullet;
        this.cw = cw;
    }

    @Override
    public void fire() {
        if (bullet > 0) {
            System.out.println("빵!");
            bullet -= 1;
            cw.check();
        } else {
            System.out.println("틱~");
        }
    }

    @Override
    public void reload() {
        System.out.println("재장전");
        bullet = 30;
    }

}
