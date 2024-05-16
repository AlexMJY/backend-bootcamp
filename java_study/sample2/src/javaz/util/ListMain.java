package javaz.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

//List<E> 인터페이스
//- 순차적 데이터 처리를 위한 컬렉션
//- 가변길이의 배열과 같은 구조 
//  - 크기 조절 가능
//- 입력 순서 유지 및 데이터 중복 가능
//- 특정 위치 요소 접근 가능

public class ListMain {
	public static void main(String[] args) {
		//List 객체 list 생성
//		List<Object> list = new List();		//X
		List<Object> list = new ArrayList();
		ArrayList<Object> arrList = new ArrayList<Object>();
		
		System.out.println(list.size());
		
		list.add(1);
		list.add('2');
		list.add("3");
		list.add(4.0);
		list.add(true);
		list.add(true);
		
		System.out.println(list);
		System.out.println(list.size());	
		System.out.println();
		
		//list에  1이 들어 있다면 지우기
		if(list.contains(1)) {
//			list.remove(1);	//1번째 인덱스 값이 지워짐
			list.remove(list.indexOf(1));	//1의 인덱스를 지정하여 지움
			list.remove("3");
		}
		System.out.println(list);
		System.out.println("1번째 인덱스의 데이터 : " + list.get(1));	
		list.set(1, "사");
		System.out.println(list);
		System.out.println(list.size());	
		System.out.println();
		
		System.out.println();
		System.out.println("list with for");
		for(int i = 0 ; i<list.size() ; i++) {
			System.out.println(list.get(i));	
		}
		
		System.out.println();
		System.out.println("list with foreach");						
		for (Object o : list) {
			System.out.println(o);
		}
		

		System.out.println();
		System.out.println("list with Iterator");	
		Iterator<Object> iterator = list.iterator();
		while(iterator.hasNext()) {		//다음 요소가 있으면
			Object obj = iterator.next();	//다음 요소 가져오기			
			System.out.println(obj);
		}

		System.out.println();
		System.out.println("list with Consumer 인터페이스 i");	
		Consumer<Object> consumer = new Consumer<Object>() {				
			@Override
			public void accept(Object o) {
				System.out.println(o);
			}
		};
		list.forEach(consumer);
		
		System.out.println();
		System.out.println("list with Consumer 인터페이스 ii - lambda");	
		list.forEach(o -> System.out.println(o));
		System.out.println();
		
		System.out.println("---------------------------");
		System.out.println("배열을 List로");
		String[] animals = {"dog", "cat", "ant"};
		List<String> animalList = Arrays.asList(animals);
//		animalList = animals;	//X
		System.out.println(animals);
		System.out.println(Arrays.toString(animals));
		System.out.println(animalList);
		
//		animalList.add("bee");	//X
		animalList.set(1, "bee");
		System.out.println(Arrays.toString(animals));
		System.out.println(animalList);
		
		System.out.println();
		System.out.println("List를 배열로 ------------------");
//		String[] newAnimals = (String[]) animalList.toArray();  //X
//		String[] newAnimals = animalList.toArray(new String[0]);//O
		String[] newAnimals = animalList.toArray(String[]::new);//O
		System.out.println(Arrays.toString(newAnimals));					
	}
}






























