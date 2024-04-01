package javaz.oop;

class Parent{
	Parent(){ System.out.println("1.Parent()"); }
	
	//매개변수, 반환타입, 접근 제한이 없는 method 메서드 작성
	//"Parent's method"를 화면에 출력
	public void method() {
		System.out.println("Parent's method");
	}
}

final class Child extends Parent {  
	Child(){ System.out.println("2.Child()"); }
	
	//부모 클래스의 인스턴스 메서드를 오버라이딩하여
	//"Child's method"를 화면에 출력
	public void method() {
		System.out.println("Child's method");
	}
}

public class ParentChildMain {
	public static void main(String[] args) {
		Parent p = new Parent();
		Child c = new Child();	 
		
		p.method();	//p 객체의 인스턴스 메서드 호출
		c.method(); //c 객체의 인스턴스 메서드 호출
		
		System.out.println();
		System.out.println("- up casting -");
		p = c;
		p.method();

		System.out.println();
		System.out.println("- down casting -");
		c = (Child) new Parent();
		c.method();
		
		
		GrandChild gc = new GrandChild(); 
	}
}

class GrandChild /* extends Child */ { } //final로 선언된 클래스 상속 불가










