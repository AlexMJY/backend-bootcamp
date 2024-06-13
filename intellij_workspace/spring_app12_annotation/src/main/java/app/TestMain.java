package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        // BeanFactory : 빈 관리 기능

        // ApplicationContext : 빈 관리 기능 + 자원처리 추상화 + 메시지 지원 + 국제화 지원 + 이벤트 처리
        ApplicationContext ac = new ClassPathXmlApplicationContext("app.xml");
        Character ch = ac.getBean("ch", Character.class);

        ch.eat("donut");
        ch.walk();
        ch.attack("monster");
        ch.get("rock");
    }
}
