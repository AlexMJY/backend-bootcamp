package javaz.oop;

//1. 제품명name, 가격price, 보너스점수bonus를 필드로 갖는 Product 클래스 정의
class Product {
	String name;
	int price;
	int bonus;
}

class TV extends Product {} 	 //2. Product 클래스를 상속받는 TV 클래스 정의
class Computer extends Product {}//3.    "       "     "   Computer 클래스 정의
class Audio extends Product {}	 //4.    "       "     "   Audio 클래스 정의

//5. 보유금액money, 보너스점수bonus를 속성으로 갖는  Buyer 클래스 정의
class Buyer {
	int money;
	int bonus;

	void buy(TV tv) { 
		System.out.println("TV를 구매합니다."); 
	}

	void buy(Computer com) { 
		System.out.println("Computer를 구매합니다."); 
	} 
	
	void buy(Audio audio) { 
		System.out.println("Audio를 구매합니다.");
	}
}

class NewBuyer {
	int money;
	int bonus;
	
	//TV, Computer, Audio를 구매할 수 있는 buy 메서드 작성
	//"Product를 구매합니다." 출력
	void buy(Product product) { 
		System.out.println(product.getClass().getSimpleName() +  "를 구매합니다."); 
	}
}

public class ProductMain {
	public static void main(String[] args) {
		Buyer b = new Buyer();		//6. Buyer 클래스의 인스턴스 b 생성
		
		TV tv = new TV();			//7. TV 클래스의 참조변수 tv 생성
		Audio audio = new Audio();	//8. Audio 클래스의 객체 audio 생성
		Computer com = new Computer();//9. Computer 클래스 타입의 객체 com 생성
		
		//10. 7, 8, 9번의 객체를 이용하여  Buyer 클래스의 buy 메서드 호출
		b.buy(tv);
		b.buy(com);
		b.buy(audio);
		
		System.out.println("------------");
		NewBuyer nb = new NewBuyer();//11. NewBuyer 클래스의 객체 nb를 생성		
		//12. 7, 8, 9번의 객체를 이용하여 NewBuyer 클래스의 
		//    buy 메서드 호출 (필요 시 적당한 객체를 생성하여 처리)
		Product p = tv;
		nb.buy(p);
		nb.buy(com);
		nb.buy(audio);
		
	}//END main()
}//END class











