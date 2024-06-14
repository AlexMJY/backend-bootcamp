package kr.co.jhta.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("app.xml");
        CustomerService cs = ac.getBean("cs", CustomerService.class);

        // 공통 관심사 업무 지정
        // 지금 시간 : 12:40:10

        cs.printName(); // 당신의 이름은 : ~~
        cs.printEmail(); // 당신의 이메일은 : ~~

    }
}
