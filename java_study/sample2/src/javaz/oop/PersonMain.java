package javaz.oop;

//이름과 전화번호를 저장하는 클래스
//필드들은 외부 클래스에서 접근 불가
class Person {
	private String name;	//이름 필드 name
	private String tel;		//전화번호 멤버 변수 tel
	
	//기본 생성자
	public Person() {}
	
	//인스턴스의 속성들을 초기화하는 생성자
	public Person(String name, String tel) {
		this.name = name;
		this.tel = tel;
	}
	
	public Person(String name) {
		this.name = name;
	}

	//필드의 값을 설정하고 반환하는 setter/getter
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getTel() {
		return tel;
	}
}//END class Person

public class PersonMain {
	//1. Person 클래스 타입의 배열 객체를 매개변수로 전달받아
	//   화면에 표시하는 공유 메서드 showList
	public static void showList(Person[] personArr) {
		System.out.println("====== 연락처 정보 ======");
		System.out.println("이름\t전화번호");
		for (Person person : personArr) {
			System.out.println(person.getName() + "\t" +
							   person.getTel());
		}
	}
	
	public static void main(String[] args) {
		//2. 1번에서 필요한 객체를 생성하여 
		int total = 0;
		Person[] personArr = new Person[3];
		personArr[total++] = new Person("Kim", "010-1111-2222");
		personArr[total++] = new Person("Lee", "010-2222-3333");
		personArr[total++] = new Person("Ann", "010-3333-4444");
		
		//   1번 메서드 호출
//		PersonMain.showList(null);
		showList(personArr);	//자신의 클래스의 공유메서드인 경우
						//클래스 이름을 생략하고 접근 가능
		
	}//END main()
}//END class

























