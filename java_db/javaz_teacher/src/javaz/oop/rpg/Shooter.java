package javaz.oop.rpg;

public class Shooter extends Player {

	public Shooter(String name) {
		super(name);
	}

	//슈퍼 클래스의 추상 메서드 재정의
	@Override
	public void move(int x, int y) {
		setX(x);
		setY(y);
		System.out.println(this);
		setPower(1);
		setHeart(1);
	}
	
	public void shoot() {	//슈터 전용 메서드 작성
		System.out.println("[" + getName() + "] shoot!!!!!");
		setPower(-1);
		setHeart(-1);
	}

	@Override
	public void attack() {
		System.out.println("[" + getName() + "] 공격!!!!!");
		setPower(-3);
		setHeart(-1);
	}

}

















