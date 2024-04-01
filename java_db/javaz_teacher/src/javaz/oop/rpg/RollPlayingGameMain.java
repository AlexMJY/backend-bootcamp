package javaz.oop.rpg;

public class RollPlayingGameMain {

	public static void main(String[] args) {
		Princess princess = new Princess("princess");						
		Shooter shooter = new Shooter("shooter");
		
		shooter.move(140, 200);
		shooter.attack();
		shooter.helpSkill();
		shooter.shoot();        //슈터 전용 메서드 호출
		
		System.out.println();
		princess.move(50, 50);
		princess.attack();
//		princess.helpItem();	//인터페이스의 공유 메서드는 인스턴스로 접근 불가
		Item.helpItem();		//이렇게 접근 가능
		princess.helpSkill();
		princess.heal();	   //공주 전용 메서드 호출

	}

}







