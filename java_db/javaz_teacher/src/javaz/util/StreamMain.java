package javaz.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
//  forEach(), ...

public class StreamMain {
	public static void main(String[] args) {
		int[] arr0to4 = { 0, 1, 2, 3, 4 };
		System.out.println(Arrays.toString(arr0to4));
		System.out.println("-----------------------");
		
		//기본형 스트림
		IntStream intStrm = Arrays.stream(arr0to4);
				  intStrm = IntStream.range(0, 5);
				  intStrm = IntStream.rangeClosed(0, 4);
				  intStrm = IntStream.of(0, 1, 2, 3, 4);
				  intStrm = IntStream.of(arr0to4);
		
		intStrm.forEach(i -> System.out.print(i + " "));
		System.out.println();
		System.out.println("-----------------------");
		
//		intStrm = new Random().ints();
		intStrm = new Random().ints(5);
		intStrm = new Random().ints(5, 0, 5);
		intStrm.forEach(System.out::println);
		System.out.println("-----------------------");
		
		//실수형 범위 난수 5개를 생성하여 DoubleStream 타입의 변수 dblStrm에 저장			
		DoubleStream dblStrm = new Random().doubles(5);
		dblStrm.forEach(System.out::println);
		System.out.println("-----------------------");
		
		//객체형 스트림
		Integer[] intg5to9 = { 5, 6, 7, 8, 9 };
		List<Integer> intgList = Arrays.asList( 5, 6, 7, 8, 9 );
		
		String[] arr = { "pop", "oak", "eye", "key" };
		
		
//		Stream<Integer> strmIntg = Arrays.stream(arr0to4);
		Stream<Integer> strmIntg = Arrays.stream(intg5to9);
						strmIntg = Stream.of(intg5to9);
					 	strmIntg = intgList.stream();
					 	
		Stream<String> s1 = Arrays.stream(arr);
		
		
		strmIntg.forEach(i -> System.out.print(i + " "));
		System.out.println();
		System.out.println("-----------------------");
		
		List<String> list 
			= Arrays.asList("sky", "seA", "Art", "sun", "Sue" );
		System.out.println(list);
		
		System.out.println("오름차순 정렬 ------------------");
		Collections.sort(list);	//대소문자 구분 O
		System.out.print("i  :: " );
		list.forEach(str -> {
			if(str.toLowerCase().startsWith("s")) {
				System.out.print(str + " | ");
			}
		});
		System.out.println();
		Collections.shuffle(list);
		
		Collections.sort(list, 	//대소문자 구분 X
						 String::compareToIgnoreCase);	
		System.out.print("ii :: " );
		list.forEach(str -> {
			if(str.toLowerCase().startsWith("s")) {							
				System.out.print(str + " | ");
			}
		});
		System.out.println();
		Collections.shuffle(list);
		System.out.println();
		
		///////////////////////////////////////////
		System.out.println(list);
		System.out.println("내림차순 정렬 ------------------");
		System.out.print("i  :: " );
		list.stream()
			.filter( str -> str.toLowerCase().startsWith("s"))				
//			.map( str -> str.toLowerCase().startsWith("s"))
			.sorted(Collections.reverseOrder())
			.forEach(str -> System.out.print(str + " > "));
		System.out.println();
		Collections.shuffle(list);
		
		System.out.print("ii :: " );
		list.stream()
			.filter( str -> str.toLowerCase().startsWith("s"))
			.sorted(String.CASE_INSENSITIVE_ORDER.reversed())
			.forEach(str -> System.out.print(str + " > "));
		System.out.println();
		System.out.println("----------------");
		
		////////////////////////////////////////////////
		//list에서 A가 포함된 문자열들 필터링
		s1 = list.stream()
				 .filter( str -> str.contains("A"));

		//list의 문자열들을 모두 대문자로 변경한 새로운 데이터 생성
		s1 = list.stream()
				 .map(str -> str.toUpperCase());
		

		//list의 문자열들을 모두 소문자로 변경
		s1 = list.stream()
				 .map(String::toLowerCase);
		
		System.out.println(s1);
		System.out.println(s1.collect(Collectors.toList()));
		System.out.println();
		
		//-----------------------------------------------
		List<Integer> nums = Arrays.asList(1, 3, 5, 7, 9);
		IntStream intNums = IntStream.of(10, 30, 50, 70, 90);
		
//		System.out.println( intNums.sum());
		System.out.println( intNums.count());
		System.out.println( nums.stream()
								.reduce(0, (x, y) -> x + y));	
		System.out.println( nums.stream()
								.reduce(10, (x, y) -> x + y));	
		System.out.println( nums.stream()
								.reduce(100, (x, y) -> x + y));	
		System.out.println();
		

		//-----------------------------------------------
		
		List<String> upperList = list.stream()
				 					 .map(str -> str.toUpperCase())
				 					 .collect(Collectors.toList());		
		System.out.println(list);
		System.out.println(upperList);
		System.out.println();
		
		List<Integer> sams = nums.stream()
								 .filter(n -> n%3 == 0)
								 .map(n -> n * 2)
			 					 .collect(Collectors.toList());		
		System.out.println(nums);
		System.out.println(sams);
		System.out.println();
		
		//3. list의 각 단어의 길이를 화면에 출력
		list.forEach(str 
				-> System.out.println(str + " :" + str.length()));
		
	}
}



































