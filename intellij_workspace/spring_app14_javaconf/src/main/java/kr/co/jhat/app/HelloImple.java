package kr.co.jhat.app;

public class HelloImple implements Hello {
    @Override
    public void sayHello(String name) {
        System.out.println(name + "!!! hello :)");
    }
}
