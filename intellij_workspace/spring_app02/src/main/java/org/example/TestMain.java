package org.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("Test Intellij IDEA");

        BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/app.xml"));
//        Object coffee =  factory.getBean("coffee", Beverage.class);
//        Beverage b = (Beverage) coffee;
        Beverage b1 = factory.getBean("coffee", Beverage.class);
        Beverage b2 = factory.getBean("coffee", Beverage.class);

        System.out.println("b1 : " + b1);
        System.out.println("b2 : " + b2);

        // 스프링이 관리하는 모든 객체는 싱글톤 객체(getBean을 여러번 사용해도 객체는 하나)

        b1.drink("홍길동");
        b2.drink("박길동");

        MachineCoffee mc1 = (MachineCoffee) b1;
        mc1.clean("바닥");
    }
}