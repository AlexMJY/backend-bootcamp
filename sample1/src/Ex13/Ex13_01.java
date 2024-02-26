package Ex13;

public class Ex13_01 {
    public static void main(String[] args) {
        ThreadEx_01_1 t1 = new ThreadEx_01_1();

//        Runnable r = new ThreadEx_02_2();
//        Thread t2 = new Thread(r);  // 생성자 Thread(Runnable target)
        Thread t2 = new Thread(new ThreadEx01_2());

        t1.start();
        t2.start();
        // 한 번 실행이 종료된 쓰레드는 다시 실행할 수 없다. 즉 start()는 한 번만 가능
    }
}

class ThreadEx_01_1 extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName()); // 조상인 Thread의 getName 호출
        }
    }
}

class ThreadEx_02_2 implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            // Thread.currentThread() - 현재 실행중인 Thread 반환
            System.out.println(Thread.currentThread().getName());
        }
    }
}