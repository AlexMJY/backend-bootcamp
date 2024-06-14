package kr.co.jhta.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("app.xml");

        CustomerService cs = ac.getBean("cs", CustomerService.class);
        
        // 메서드 타임 테스트
        cs.printName();
        cs.printEmail();
    }
}
