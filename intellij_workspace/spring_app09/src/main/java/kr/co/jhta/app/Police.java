package kr.co.jhta.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Police implements Character {
    int hp;
    // Gun g;  // 'is a' 관계가 아니라 'has a'관계라면 상속이 아니라 멤버변수로 만들어줘야한다
    Weapon w;

//    public void setHp(int hp) {
//        this.hp = hp;
//    }
//    public void setW(Weapon w) {
//        this.w = w;
//    }

    @Override
    public void walk() {
        System.out.println("뚜벅뚜벅 걸어요");
    }

    @Override
    public void eat(String it) {
        System.out.println(it + "을 먹어요");
    }

    @Override
    public void attack(Object obj) {
        System.out.println(obj + "를 공격합니다.");
        w.use();
    }

    @Override
    public void get(Object obj) {
        System.out.println(obj + "를 획득합니다.");
    }
}
