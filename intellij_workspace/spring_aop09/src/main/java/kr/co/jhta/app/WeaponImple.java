package kr.co.jhta.app;

public class WeaponImple implements Weapon {
    String type;

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void fire() {
        System.out.println(type + " 총으로 빵하고 쏩니다.");
    }

    @Override
    public void reload() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(type + " 총으로 재장전 합니다.");
    }
}
