package spring_app01;

public class HelloEn implements Hello {
	@Override
	public void sayHello(String name) {
		System.out.println("Welcome " + name + ".");
	}
}
