package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("app.xml");
        CustomerService cs = ac.getBean("cs", CustomerService.class);

        cs.printName(); // 당신의 이름은 : ~~
        // 메서드가 호출된 후에 실행되는 메서드

        cs.printEmail(); // 당신의 이메일은 : ~~
        // 메서드가 호출된 후에 실행되는 메서드
    }
}
