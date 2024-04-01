package javaz.oop;

public class SingletonMain {
	public static void main(String[] args) {
		//Singleton 클래스의 객체 s1 생성
//		Singleton s1 = new Singleton(); // X
//		Singleton s11 = new Singleton();
		
		//Singleton 클래스의 인스턴스 s2 반환받기
		Singleton s2 = Singleton.getInstance();
		Singleton s3 = Singleton.getInstance();
		
		System.out.println(s2);
		System.out.println(s3);
//		System.out.println(s1);
//		System.out.println(s11);
	}

}









