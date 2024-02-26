package Ex13;

public class Ex13_09 {
    public static void main(String[] args) {
        RunImpEx10 r = new RunImpEx10();
        Thread th1 = new Thread(r, "*");
        Thread th2 = new Thread(r, "**");
        Thread th3 = new Thread(r, "***");
        th1.start();
        th2.start();
        th3.start();

        try {
            Thread.sleep(2000);
            th1.suspend();  // 잠시 중단
            Thread.sleep(2000);
            th2.suspend();
            Thread.sleep(3000);
            th1.resume();  // 다시 동작
            Thread.sleep(3000);
            th1.stop();  // 강제종료
            th2.stop();
            Thread.sleep(2000);
            th3.start();
        } catch (InterruptedException e) {}
    }
}


class RunImpEx10 implements Runnable {
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}