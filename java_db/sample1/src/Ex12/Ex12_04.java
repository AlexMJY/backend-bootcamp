package Ex12;

import java.util.ArrayList;

class Fruit2 { public String toString() { return "Fruit"; }}
class Apple2 extends Fruit2 { public String toString() { return "Apple"; }}
class Grape2 extends Fruit2 { public String toString() { return "Grape"; }}

class Juice {
    String name;
    Juice(String name) { this.name = name + "Juice"; }
    public String toString() { return name; }
}

class Jucier {
    static Juice makeJuice(FruitBox2<? extends Fruit2> box) {
        String tmp = "";

        for (Fruit2 f : box.getList())
            tmp += f + " ";
        return new Juice(tmp);
    }
}


public class Ex12_04 {
    public static void main(String[] args) {

    }
}

class FruitBox2<T extends Fruit2> extends Box2<T> {}

class Box2<T> {
    ArrayList<T> list = new ArrayList<T>();
    void add(T item) { list.add(item); }
    T get(int i) { return list.get(i); }
    ArrayList<T> getList() { return list; }
    int size() { return list.size(); }
    public String toString() { return list.toString(); }
}