package kr.co.jhta.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("app.xml");
        CollectionInter collection = ac.getBean("collection", CollectionInter.class);
        collection.printList();
        collection.printMap();
    }
}
