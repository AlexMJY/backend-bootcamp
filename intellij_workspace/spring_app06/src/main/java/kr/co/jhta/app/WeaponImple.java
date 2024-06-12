package kr.co.jhta.app;

public class WeaponImple implements WeaponInter {

    @Override
    public void fire() {
        System.out.println("빵빵");
    }

    @Override
    public void reload() {
        System.out.println("철커덕");
    }
}
