package spring_app01;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class TestMain {
	public static void main(String[] args) {
//		HelloKr h = new HelloKr();
//		h.sayHello("홍길동");
//		
//		HelloEn h2 = new HelloEn();
//		h2.sayHello("홍길동");
//		
//		HelloJp h3 = new HelloJp();
//		h3.sayHello("홍길동");
		
		// xml에 설정정보를 담아 놓고 필요할 때 객체를 꺼내서 사용
		
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/app.xml"));
		
		Object obj = factory.getBean("h");
		
		System.out.println(obj);
		
		Hello h = (Hello) obj;
		h.sayHello("홍길동");
	}
	
}
