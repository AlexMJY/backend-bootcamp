package javaz.oop;

abstract class Animal {		//추상 클래스 선언
	String name;
	String place;
	String food;
	
	public abstract void move();	//추상 메서드 선언
	
	public void eat() {
		System.out.println(name + "이(가) " +
						   food + "를(을) 먹습니다.");
	}
}//END Animal class

class Human extends Animal {
	public Human(String name, String place, String food) {
		this.name = name;
		this.place = place;
		this.food = food;
	}

	@Override
	public void move() {
		System.out.println(name + "이(가) " + place + "에서 걷습니다.");
	}
	
	public void speak() {
		System.out.println(name + "이(가) 말합니다.");
	}
}

class Dolphin extends Animal {
	public Dolphin(String name, String place, String food) {
		this.name = name;
		this.place = place;
		this.food = food;
	}
	
	@Override
	public void move() {
		System.out.println(name + "이(가) " + place + "에서 헤엄칩니다.");
	}
	
	public void sound() {
		System.out.println(name + "이(가) 초음파 소리를 냅니다.");
	}
}

public class AbstractMain {
	public static void main(String[] args) {
		Human boy = new Human("소년A", "도시", "시리얼"); //사람 객체 생성
		Dolphin dol = new Dolphin("남방돌고래 삼돌이", "제주 앞바다", "작은 물고기");//돌고래 객체 생성
		
		boy.move();	//사람 움직이기
		boy.eat(); 	//사람 먹이기
		boy.speak();//사람 말하게 하기
		
		dol.move();	//돌고래 움직이기
		dol.eat();  //돌고래 먹이기
		dol.sound();//돌고래 초음파 소리 내기
	}//END main()
}//END class





