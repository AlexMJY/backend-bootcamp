package Ex13;

public class Ex13_08 {
    public static void main(String[] args) {
        ThreadEx08_1 th1 = new ThreadEx08_1();
        ThreadEx08_2 th2 = new ThreadEx08_2();
        th1.start(); th2.start();

        try {
            th1.sleep(2000);
        } catch (InterruptedException e) {}

        System.out.println("<<main 종료>>");
    }
}



class ThreadEx08_1 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) System.out.println("-");
        System.out.println("<<th1 종료>>");
    } // run()
}

class ThreadEx08_2 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) System.out.println("|");
        System.out.println("<<th2 종료>>");
    } // run()
}