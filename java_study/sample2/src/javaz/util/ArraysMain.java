package javaz.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//Arrays 클래스
//- 배열 객체에서 필요한 기능을 메서드로 제공
//- 배열의 비교, 정렬, 특정 값으로 배열 채우기 등 수행
//- 주로 static으로 선언되어 있어서 인스턴스 없이 사용 가능

public class ArraysMain {
	public static void main(String[] args) {
		System.out.println("1.배열 복사 ----------------------");				
		String[] arr = { "bee", "air", "sky", "Sea", "Ace" };
		String[] clone1 = Arrays.copyOf(arr, arr.length);
		String[] clone2 = Arrays.copyOfRange(arr, 0, arr.length);
		String[] clone3 = new String[arr.length];
		System.arraycopy(arr, 0, clone3, 0, arr.length);
		
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(clone1));
		System.out.println(Arrays.toString(clone2));
		System.out.println(Arrays.toString(clone3));
		System.out.println();
		
		System.out.println("2.배열 채우기 ----------------------");
		String[] level = new String[10];
		System.out.println(Arrays.toString(level));
		
		Arrays.fill(level, "A");
		System.out.println(Arrays.toString(level));

		Arrays.fill(level, 5, level.length, "B");
		System.out.println(Arrays.toString(level));
		System.out.println();
		
		System.out.println("3.배열 정렬 -------------------------");		
		//sort()
		//- 기본 데이터 타입 또는 String을 오름차순으로 정렬
		//- 내림차순 정렬은 Collections.reverseOrder() 이용
		//- 사용자 정의 정렬은 
		//  java.lang.Comparable 
		//  또는 java.util.Comparator 인터페이스 이용
		Integer[] i = { 20, 3, 9, 6, 33 };
		System.out.println(Arrays.toString(i));					
		
		Arrays.sort(i);	//오름차순 정렬
		System.out.println(Arrays.toString(i));
		
		Arrays.sort(i, 	//내림차순 정렬
					Collections.reverseOrder());
		System.out.println(Arrays.toString(i));
		System.out.println();
		
		String[] hangul = { "벌", "공기", "하늘", "바다", "에이스" };		
		System.out.println(Arrays.toString(hangul));		

		Arrays.sort(hangul);	//오름차순 정렬
		System.out.println(Arrays.toString(hangul));
		
		Arrays.sort(hangul, 	//내림차순 정렬
					Collections.reverseOrder());
		System.out.println(Arrays.toString(hangul));
		System.out.println();

		System.out.println(Arrays.toString(arr));
		Arrays.sort(arr);	//오름차순 정렬
		System.out.println(Arrays.toString(arr));
		
		Arrays.sort(arr, 	//대소문자 구분X 정렬
					String::compareToIgnoreCase);
		System.out.println(Arrays.toString(arr));
		
		System.out.println("sky 인덱스 : " + 
						   Arrays.binarySearch(arr, "sky"));
		System.out.println("sea 인덱스 : " + 
				   		   Arrays.binarySearch(arr, "sea"));
		System.out.println("see 인덱스 : " + 
		   		   		   Arrays.binarySearch(arr, "see"));
		System.out.println();
		
		System.out.println("4.배열 정렬 기준 정의 ------------------");
		String a = "A";	//65
		String b = "B";	//66
		System.out.println(a.compareTo(b));
		System.out.println(b.compareTo(a));
		System.out.println(a.compareTo(a));
		System.out.println();
		
		Member[] members = new Member[5];
		members[0] = new Member("Kim", 20);
		members[1] = new Member("Lee", 30);
		members[2] = new Member("Han", 10);
		members[3] = new Member("Ann", 50);
		members[4] = new Member("San", 40);
		
		printMember(members);
		
		Arrays.sort(members);	//나이 기준  정렬
		printMember(members);
		
		Arrays.sort(members, 	//이름 기준 정렬
					new MemberNameComparator());
		printMember(members);

	}//END main()
	
	public static void printMember(Member[] members) {
		for (Member member : members) {
			System.out.println(member);
		}
		System.out.println();
	}
}//END ArraysMain class

//이름을 기준으로 정렬하도록 java.util.Comparator 인터페이스 구현
class MemberNameComparator implements Comparator<Member>{

	@Override
	public int compare(Member o1, Member o2) {
		String name1 = o1.getName();
		String name2 = o2.getName();
		
		return name1.compareToIgnoreCase(name2);	//오름차순
	}
	
}//END class

//나이를 기준으로 정렬하도록 java.lang.Comparable 인터페이스 구현
class Member  implements Comparable<Member> {
	private String name;
	private int age;
	
	public Member(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	@Override
	public String toString() {
		return name + " | " + age;
	}

	@Override
	public int compareTo(Member member) {
		//나이로 정렬
		//					오름차순	내림차순
		//매개변수가 더 크면		음수		양수
		//   "       작으면	양수		음수
		//   "  와 같으면     0		0

//		return member.age - this.age;	//내림차순
		return this.age - member.age;	//오름차순
	}
}





























