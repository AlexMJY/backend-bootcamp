package javaz.util;


// 1. Thread를 상속받는 ThreadA 클래스 정의
class ThreadA extends Thread {
    public void run() {
        System.out.println("This is ThreadA");
    }
}

// 2. Runnable을 구현하는 RunnableB 클래스 정의
class RunnableB implements Runnable {
    @Override
    public void run() {
        System.out.println("This is RunnableB");
    };
}

@FunctionalInterface
// 추상 메서드 하나만 갖는 인터페이스
interface InterfaceA {  // parameter X, return X
    public void method();
}

// 정수형 매개변수 하나를 받는 추상 메서드 method를
// 함수적 인터페이스 InterfaseB에 선언
@FunctionalInterface
interface InterfaceB {  // parameter O, return X
    public void method(int x);
}

@FunctionalInterface
interface InterfaceC {
    public double method();  // parameter X, return O
}

@FunctionalInterface
interface InterfaceD {
    public int method(int x, int y);  // two parameter, return O
}


public class LambdaMain {
    public static int sum(int x, int y) {
        return x + y;
    }


    public static void main(String[] args) {
        InterfaceD d = (arg1, arg2) -> {
            int result = arg1 + arg2;
            return result;
        };
        System.out.println("5 + 5 = " + d.method(5, 5));

        d = (arg1, arg2) -> arg1 + arg2;
        System.out.println("6 + 7 = " + d.method(6, 7));

        d = (arg1, arg2) -> sum(7,8);


        ////////////////////////////////////////
        System.out.println();
        InterfaceC c = () -> {
            double pi = Math.PI;
            return pi;
        };
        System.out.println("return 1 : " + c.method());

        c = () -> {
            return Math.PI;
        };
        System.out.println("return 2 : " + c.method());


        ////////////////////////////////////////
        System.out.println();
        InterfaceB b = (ax) -> {
            int result = ax * 5;
            System.out.println(ax + " * 5 = " + result);
        };
        b.method(2);

        b = ax -> {  // 매개변수가 하나인 경우 () 생략 가능
            int result = ax * 5;
            System.out.println(ax + " * 5 = " + result);
        };
        b.method(3);

        ////////////////////////////////////////
        System.out.println();
        InterfaceA a = () -> {
            String str = "Hi! lambda1";
            System.out.println(str);
        };
        a.method();

        a = () -> {
            System.out.println("Hi! lambda2");
        };
        a.method();

        a = () -> System.out.println("Hi! lambda3");
        a.method();


        ////////////////////////////////////////
        System.out.println();
        // 3. 참조변수 t3에 Thread를 익명의 객체로 구현하여 저장
        Thread t3 = new Thread() {
            public void run() {
                System.out.println("This is Thread t3 anony");
            }
        };


        ThreadA ta = new ThreadA();
        ta.start();

        Thread rb = new Thread(new RunnableB());
        rb.start();

        t3.start();


        // 4. 참조변수가 없는 익명의 객체로 Runnable을 구현
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is Runnable anony");
            }
        }).start();

        new Thread(
//                () -> {System.out.println("This is Runnable Lambda"); }
                () -> System.out.println("This is Runnable Lambda")
        ).start();
    }
}
