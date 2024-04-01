package Ex12;

class Parent {
    void parentMethod() {}
}

class Child extends  Parent {
    @Override
    void parentMethod() {}
}


public class AnnotationEx01 {
    public static void main(String[] args) {
        System.out.println("hello");
    }
}
