package kr.co.jhta.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assassin  implements Character {
    int hp;
    Weapon w;

    @Override
    public void walk() {
        System.out.println("!! walk fast and quiet");
    }

    @Override
    public void eat(String it) {
        System.out.println("!! eat " + it);
    }

    @Override
    public void attack(Object obj) {
        System.out.println("!!! attack -> " + obj);
        w.use();
    }

    @Override
    public void get(Object obj) {
        System.out.println("!! get -> " + obj);
    }
}
