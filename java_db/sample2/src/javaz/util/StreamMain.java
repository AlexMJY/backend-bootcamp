package javaz.util;

//java.util.stream 패키지
//- I/O 스트림이 아닌 컬렉션에서 시작되는 스트림 api
//- 다양한 데이터 소스(배열 및 컬렉션-List, Set, Map 등)를
//  표준화된 방법으로 처리
//- 컬렉션의 저장 요소를 하나씩 참조하여 람다식으로 처리 가능
//- 한 메서드의 출력 스트림이 다른 메서드의 입력 스트림이 될 수 있음

//스트림 연산 : 생성 > 중간 처리 > 최종 처리

//스트림 생성 연산
//- 배열 또는 컬렉션으로 생성

//스트림 중간처리 연산
//- 입력 데이터를 출력 데이터로 가공
//- map(), filter(), sorted(), mapToInt(), ...

//스트림 최종 연산
//- 처리된 데이터를 모아서 결과 작성
//- sum(), average(), count(), reduce(), collect(),
//  forEach(), .

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamMain {
    public static void main(String[] args) {
        int[] arr0to4 = {0, 1, 2, 3, 4};
//        System.out.println(Arrays.toString(arr));
//        for (int i : arr)
//            System.out.print(i + " ");

        // 기본형 스트림
        IntStream intStrm = Arrays.stream(arr0to4);
                  intStrm = IntStream.range(0, 5);
                  intStrm = IntStream.rangeClosed(0, 4);
                  intStrm = IntStream.of(0, 1, 2, 3, 4, 5, 6);
                  intStrm = IntStream.of(arr0to4);


        intStrm.forEach(i -> System.out.print(i + " "));
        System.out.println();
        System.out.println("-------------------------------------------------");
        ///////////////////////////////////////////
//        intStrm = new Random().ints();
        intStrm = new Random().ints(5);
        intStrm = new Random().ints(5,0, 9);
        intStrm.forEach(System.out::print);
        System.out.println();
        System.out.println("-------------------------------------------------");


        // 객체형 스트림
        Integer[] intg5g9 = {5, 6, 7, 8, 9};
        List<Integer> intgList = Arrays.asList(5, 6, 7, 8, 9);
//        Stream<Integer> strmIntg = Arrays.stream(arr0to4);  기본형을 스트림으로 닮을 수 없다.
        Stream<Integer> strmIntg = Arrays.stream(intg5g9);
                        strmIntg = intgList.stream();
        strmIntg.forEach(System.out::print);


    }
}
