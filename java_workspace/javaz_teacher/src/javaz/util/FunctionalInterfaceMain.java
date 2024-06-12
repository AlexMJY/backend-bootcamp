package javaz.util;

import java.util.*;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

//java.util.function
//- 람다식을 사용할 때마다 함수형 인터페이스를 
//  작성하지 않도록 하기 위해 제공되는 패키지
//- Consumer, Supplier, Function, Operator, Predicate 등이
//  많이 사용되는 함수형 인터페이스들
public class FunctionalInterfaceMain {
	public static void main(String[] args) {
		//반환값 X, 매개변수 1
		Consumer<Integer> consumer = (i) -> {
			System.out.println(i + " * 2 = " + i*2);
		};
		consumer.accept(3);
		
		System.out.println();
		//반환값 O, 매개변수 2
//		BiFunction<Integer, Integer, Double> bf 
//				= (x, y) -> Math.pow(x, y);
		BiFunction<Integer, Integer, Integer> bf 
				= (x, y) -> x + y;
			
		System.out.println(bf.apply(10, 2));
		System.out.println();
		
		//반환값 O, 매개변수 X
		Supplier<Boolean> sup = () -> new Random().nextBoolean();
		System.out.println(sup.get());
		System.out.println();
		
		//반환값 O, 매개변수 1
		Predicate<Integer> prep = (x) -> x > 100;
		System.out.println(prep.test(100));
		System.out.println();
		
		Function<Integer, Double> fn1 
				= r -> r * r * Math.PI;
		
		Function<String, Integer> fn2 
				= str -> str.length();
		
		System.out.println(fn1.apply(3));
		System.out.println(fn2.apply("Hello"));
		System.out.println();
		
		List<String> list 
			= Arrays.asList("ace", "bus", "sky");
		list.forEach(i -> System.out.println(i));
		
		
		
		Map<String, String> map = new HashMap<String, String>();	
		map.put("a", "ant");	
		map.put("b", "bee");	
		map.put("t", "top");
		map.forEach((k,v) -> System.out.println(k + ":" + k));
		
	}
}

































