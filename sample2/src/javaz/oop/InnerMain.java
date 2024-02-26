package javaz.oop;

class Outer {
	class Member {
		public void method() {
			System.out.println("Member inner method()");
		}
	}
	
	static class Static {
		public static void method() {
			System.out.println("Static inner method()");
		}
	}

	public void method() {
		class Local {
			public void method() {
				System.out.println("Local inner method()");
			}
		}
		new Local().method();
	}
}//END Outer class

abstract class Abstract {
	public abstract void methodA();
}

interface Interface {
	void methodB();
}

class AnonyClass extends Abstract implements Interface {
	@Override
	public void methodB() {
		System.out.println("methodB");
	}

	@Override
	public void methodA() {
		System.out.println("methodA");
	}
}

public class InnerMain {
	public static void main(String[] args) {
//		Abstract a = new Abstract();	//추상 클래스는 객체 생성 불가
//		Interface i = new Interface();	//인터페이스도 객체 생성 불가
		
		Abstract a = new AnonyClass();	//자식 타입으로는 가능
		Interface i = new AnonyClass(); //      "
		
		AnonyClass ac = new AnonyClass();   
		ac.methodA();
		ac.methodB();	
		
		///////////////////////////////////
		Abstract aa = new Abstract() {	//참조변수 O
			@Override
			public void methodA() {
				System.out.println("추상 클래스를 구현한 익명의 이너 클래스");
			}
		};
		aa.methodA();
		
		new Interface() {				//참조변수 X
			@Override
			public void methodB() {
				System.out.println("인터페이스를 구현한 익명의 이너 클래스");		
			}
		}.methodB();
		
		Interface ii = new Interface() {//참조변수 O
			@Override
			public void methodB() {
				System.out.println("인터페이스를 구현한 익명의 이너 클래스 ii");		
			}
		};
		ii.methodB();
		
		
		Interface iii = () -> {   		//람다식 
			System.out.println("인터페이스를 람다식으로 구현");		
		};
		iii.methodB();
		
		///////////////////////////////////
		
		Outer o = new Outer();
//		Member m = new Member();
		Outer.Member m = o.new Member();
		m.method();
		
		Outer.Static.method();
		
		o.method();
	}
}












