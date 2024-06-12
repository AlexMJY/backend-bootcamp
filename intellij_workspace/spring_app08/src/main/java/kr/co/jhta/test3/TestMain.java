package kr.co.jhta.test3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("app03.xml");
        Printer p = ac.getBean("p", Printer.class);
        p.print();
    }

}
