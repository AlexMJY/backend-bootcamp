package spring_app03;

public class DotPrinter implements Printer {
    @Override
    public void print(String msg) {
        System.out.println("찌징 찌징 찌징 " + msg);
    }
}
