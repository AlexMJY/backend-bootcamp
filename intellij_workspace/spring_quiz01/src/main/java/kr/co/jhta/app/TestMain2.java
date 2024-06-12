package kr.co.jhta.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain2 {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("app2.xml");
        Airplain a =  ac.getBean("a", Airplain.class);
        a.fly();
        a.takeOff();
        a.landing();
    }
}
