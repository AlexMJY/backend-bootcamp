package javaz.util;


import java.util.*;
import java.util.function.*;

// java.util.functional
//- 람다식을 사용할 때마다 함수형 인터페이스를 작성하지 않도록 제공되는 패키지
//- Consumer, Supplier, Function, Operator, Predicate 등이 주로 사용됨
public class FunctionalInterfaceMain {
    public static void main(String[] args) {
        // 반환값 X, 매개변수 1
        Consumer<Integer> consumer = (i) -> {
            System.out.println(i + " * 2 = " + i * 2);
        };
        consumer.accept(5);
        System.out.println();

        // 반환값 O, 매개변수 2
        BiFunction<Integer, Integer, Double> bf = (x, y) -> Math.pow(x, y);
        System.out.println(bf.apply(5, 3));
        System.out.println();

        Function<Integer, Double> fun = (x) -> x + 100.0;
        System.out.println(fun.apply(200));
        System.out.println();

        Supplier<Boolean> sup = () -> new Random().nextBoolean();
        System.out.println(sup.get());
        System.out.println();

        // 반환값 O, 매개변수 1
        Predicate<Integer> pr = (x) -> x > 100;
        System.out.println(pr.test(10000));
        System.out.println();


        // java.util.function의 Function을 이용하여
        // 원의 반지름을 매개변수로 전달하면 넓이 반환하는 람다식 fn1
        Function<Integer, Double> fn1 = r -> Math.PI * r * r;
        System.out.println(fn1.apply(5));

        // 문자열을 매개변수로 전달하면 길이를 반환하도록 람다식을 정의해서 fn2에 저장
//        Function<String, Integer> fn2 = s -> s.length();
        Function<String, Integer> fn2 = String::length;
        System.out.println(fn2.apply("hello"));
        System.out.println();


        // ace, bus, sky를 List 타입의 객체 list에 저장한 후
        // Consumer 인터페이스를 람다식으로 구현하여 한 줄씩 출력
//        List<String> list = new ArrayList<String>();
//        list.add("ace");
//        list.add("bus");
//        list.add("sky");

//        Consumer<List> con = (i) -> {
//            i.forEach(System.out::println);
//        };
//        con.accept(list);
        List<String> list = Arrays.asList("ace", "bus", "sky");
        list.forEach(i -> System.out.println(i));


        System.out.println();
        // ant, bee, top을 각각 a, b, t를 키로
        // Map 타입의 객체 map에 저장한 후
        // Biconsumer 인터페이스를 람다식으로 구현하여 '키:값'의 형태로 출력
        Map<String, String> map = new HashMap<>();
        map.put("a", "ant");
        map.put("b", "bee");
        map.put("c", "top");
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
