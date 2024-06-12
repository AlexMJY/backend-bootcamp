package kr.co.jhta.app;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class TestMain {
    public static void main(String[] args) {
        BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/application.xml"));
        WeaponInter w = factory.getBean("w", WeaponInter.class);
        w.fire();
        w.reload();
        w.fire();
    }
}
