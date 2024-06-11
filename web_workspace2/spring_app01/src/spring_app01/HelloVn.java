package spring_app01;

public class HelloVn implements Hello {
	@Override
	public void sayHello(String name) {
		System.out.println(name + "  신 짜오~!~!");
	}
}
