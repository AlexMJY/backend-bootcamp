package javaz.oop.rpg;

public class Princess extends Player {

	public Princess(String name) {
		super(name);
	}

	//부모 클래스의 추상 메서드 오버라이딩
	@Override
	public void move(int x, int y) {
		setX(x);
		setY(y);
		System.out.println(this);
		setPower(1);
		setHeart(1);
	}

	public void heal(){   //프린세스 전용 메서드 작성
		System.out.println("[" + getName() + "] heal~~!!!!!");
	}

	@Override
	public void attack() {
		System.out.println("[" + getName() + "] 공격!!!!!");
		setPower(-1);
		setHeart(-1);
	}

	@Override
	public void helpSkill() {
		System.out.println("[공주 도움말] 치료 스킬 사용 가능");
	}
	
	
}

















