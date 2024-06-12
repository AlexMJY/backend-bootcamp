package spring_app03;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class TestMain {
    public static void main(String[] args) {
        BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/application.xml"));
        Printer p =  factory.getBean("printer", Printer.class);
        p.print("Hello Printer??!!");
    }
}
