package Ex12;

import java.util.ArrayList;
import java.util.List;

class Product {}
class Tv extends Product {}
class Audio extends Product {}

public class Ex12_01 {
    public static void main(String[] args) {
        ArrayList<Product> productList = new ArrayList<Product>();
        ArrayList<Tv> tvList = new ArrayList<Tv>();
        List<Tv> tvList2 = new ArrayList<Tv>();  // 다형성

        productList.add(new Tv());
        productList.add(new Audio());

        tvList.add(new Tv());
        tvList.add(new Tv());

        printAll(productList);

    }

    public static void printAll(ArrayList<Product> list) {
        for (Product p : list)
            System.out.println(p);
    }
}
