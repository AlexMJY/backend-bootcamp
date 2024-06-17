package kr.co.jhta.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        // AspectJ
        // PARC에서 개발한 자바 프로그래밍 언어용 
        // 관점지향 프로그래밍 확장 기능

        // 당신의 이름 : 나띠
        // 당신의 이메일 : 나띠@gamil.com

        ApplicationContext ac = new ClassPathXmlApplicationContext("app.xml");
        CustomerService cs =  ac.getBean("cs", CustomerService.class);
        cs.printName();
        cs.printEmail();

    }
}