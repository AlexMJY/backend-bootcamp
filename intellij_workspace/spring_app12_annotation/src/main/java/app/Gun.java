package app;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gun implements Weapon {
    @Value("5")
    int bullet;


    public void fire() {
        if (bullet > 0) {
            System.out.println("발사가능 : 빵~~~");
            bullet--;
        } else {
            System.out.println("발사불가능 : 틱!~~");
        }
    }

    public void reload() {
        System.out.println("장전 중");
        bullet = 8;
    }

    @Override
    public void use() {
        fire();
    }

    @Override
    public void reuse() {
        reload();
    }
}
