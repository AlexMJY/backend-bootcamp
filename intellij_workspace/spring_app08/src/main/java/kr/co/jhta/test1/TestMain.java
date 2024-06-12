package kr.co.jhta.test1;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class TestMain {
    public static void main(String[] args) {
        BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/main/resources/app01.xml"));
        Data d = factory.getBean("d", Data.class);
        System.out.println("출력값 : " + d.getNumber());

    }
}
