package kr.co.jhta.app;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        BeanFactory factory;


        ApplicationContext ac = new ClassPathXmlApplicationContext("app.xml");
//        MessageImple m = ac.getBean("m", MessageImple.class);
//        m.printMsg();

        MessageImple2 m2 = ac.getBean("m2", MessageImple2.class);
        m2.printMsg();

        AbstractApplicationContext aac = (AbstractApplicationContext) ac;
        aac.close();
    }
}
