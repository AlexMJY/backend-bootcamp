package kr.co.jhta.app;

public class LaserPrinter implements Printer {

    @Override
    public void print(String msg) {
        System.out.println(msg + " user Laser Printer");
    }
}
