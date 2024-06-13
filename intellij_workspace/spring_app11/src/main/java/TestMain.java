import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) {
//		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/application.xml"));
		ApplicationContext ac = new ClassPathXmlApplicationContext("app.xml");
		HelloInter hi = ac.getBean("h", HelloInter.class);
		hi.sayHello();
	}
}
