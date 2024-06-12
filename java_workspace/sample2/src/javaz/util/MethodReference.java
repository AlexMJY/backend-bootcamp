package javaz.util;

// 메서드 참조

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

//1. Consumer 인터페이스를 구현하는 ConsumerImpl 클래스 정의
//  매개변수로 받은 값을 화면에 출력
class ConsumerImpl implements Consumer<String> {
    public void accept(String str) {
        System.out.println(str);
    }
}

public class MethodReference {
    public static void main(String[] args) {
        Consumer<String> cs = new ConsumerImpl();
        cs.accept("1. named class");

        cs = new Consumer<String>() {
            public void accept(String str) {
                System.out.println(str);
            }
        };
        cs.accept("2. anony class");

        //3. 람다식
//        cs = (str) -> { System.out.println(str);};
        cs = str -> System.out.println(str);
        cs.accept("3. lambda");

        //4. 메서드 참조
        cs = System.out::println;
        cs.accept("4. method reference");

        //정적 메서드 참조
        ToIntFunction<String> toInt = str -> Integer.parseInt(str);
        ToIntFunction<String> toIntM = Integer::parseInt;

        int i = toInt.applyAsInt("123");
        int iM = toIntM.applyAsInt("456");
        System.out.println(i + iM);


        Cal c = new Cal();
        IntBinaryOperator intB = (x, y) -> c.add(x, y);
        System.out.println(intB.applyAsInt(1, 2));

        IntBinaryOperator intB2 =  c::add; // method reference
        System.out.println(intB2.applyAsInt(1, 2));

        InterfaceCal ic = () -> new Cal();
        InterfaceCal icc = Cal::new;
        ic.get();
        icc.get();

        //1. List 타입의 변수 list에 bee, air, sky, Sea, Ace 저장
        List<String> list = Arrays.asList("bee", "air", "sky", "Sea", "Ace");
        System.out.println(list);

        //2. 기본 정렬. 대소문자 구분 0
        Collections.sort(list);
//        list.sort(Comparator.naturalOrder());
        System.out.println(list);

        //3, 대소문자 구분 X 정렬.
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
//        list.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println(list);

        //3-1. 익명의 객체로 Comparator 인터페이스의 compare() 재정의 구현
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });

        //3-2. 람다식 구현
        Collections.sort(list, (s1, s2) -> s1.compareToIgnoreCase(s2));
        list.sort((o1, o2) -> o1.compareToIgnoreCase(o2));

        //3-3. 메서드 참조 구현
        list.sort(String::compareToIgnoreCase);


    } // END main()
} // END MethodReference

interface  InterfaceCal {
    public Cal get();
}

class Cal {
    public Cal() {
        System.out.println("Cal's default constructor");
    }
    public Cal(String msg) {
        System.out.println("Cal's msg : " + msg);
    }

    public int add(int x, int y) { return x + y; }
    public static int minus(int x, int y) { return x - y; }
}