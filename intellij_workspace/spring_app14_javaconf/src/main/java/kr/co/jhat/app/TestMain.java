package kr.co.jhat.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Hello h = context.getBean("h", Hello.class);
        h.sayHello("뽀로로");

    }
}
