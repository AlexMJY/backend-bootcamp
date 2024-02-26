package Ex13;

public class Ex13_11 {
    static long startTime = 0;

    public static void main(String[] args) {
        ThreadEx11_01 th1 = new ThreadEx11_01();
        ThreadEx11_02 th2 = new ThreadEx11_02();
        th1.start();
        th2.start();
        startTime = System.currentTimeMillis();

        try {
            th1.join(); // main쓰레드가 th1의 작업이 끝날 때까지 기다린다.
            th2.join(); // main쓰레드가 th2의 작업이 끝날 때까지 기다린다.
        } catch (InterruptedException e) {}

        System.out.println("소요시간 : " + (System.currentTimeMillis() -
                Ex13_11.startTime));


    }
}


class ThreadEx11_01 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.println(new String("-"));
        }
    }
}

class ThreadEx11_02 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.println(new String("|"));
        }
    }
}