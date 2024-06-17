package kr.co.jhta.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("app.xml");
        Weapon w = ac.getBean("w", Weapon.class);
        w.fire();
        w.reload();

        // 1. 다음 코드가 실행되도록 app.xml 작성
        // 2. reload() 느려진듯 합니다
        // 3. reload()의 실행시간을 측정하는 CheckTime 클래스 작성
        //  - ProxyfactoryBean
        //  - <aop:config> ~~~ </aop:config>
        //  - @Aspect
    }
}
