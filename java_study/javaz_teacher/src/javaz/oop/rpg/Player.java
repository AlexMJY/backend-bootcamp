package javaz.oop.rpg;

public abstract class Player implements Skill, Item {
	private int x;
	private int y;
	private int heart;
	private int power;
	private int gold;
	private String name;
	
	public Player(String name) {
		this.name = name;
		heart = Character.HEART;
		power = Character.POWER;
		
		System.out.println("=== MY RPG Character ===");
		System.out.println("캐릭터 : " + name);
		System.out.print("HEART : ");
		for(int i = 0 ; i < heart ; i++) {
			System.out.print("♥");
		}
		System.out.println();
		
		System.out.print("POWER : ");
		for(int i = 0 ; i < power ; i++) {
			System.out.print("O");
		}
		System.out.println();
		System.out.println();
	}
	
		
	//추상 메서드 - 이동하기
	public abstract void move(int x, int y);
	
	//인스턴스 메서드 - 하트 설정하기
	public void setHeart(int heart) {
		this.heart = this.heart + heart;
		System.out.print("[" + name + "] HEART " + heart +
						 (heart > 0 ? " 증가" : " 감소") + "\t| ");
		for(int i = 0 ; i < this.heart ; i++) {
			System.out.print("♥");
		}
		System.out.println();
	}
	
	//인스턴스 메서드 - 파워 설정하기
	public void setPower(int power) {
		this.power += power;
		System.out.print("[" + name + "] POWER " + power +
				 (power > 0 ? " 증가" : " 감소") + "\t| ");
		for(int i = 0 ; i < this.power ; i++) {
			System.out.print("O");
		}
		System.out.println();
	}
	
	//각 플레이어 객체를 문자열로 반환하는 메서드를 이용하여 이동 좌표를 표시하도록 처리
	@Override
	public String toString() {
		return "[" + name + "] 이동!!! " +
			   " x좌표:" + x + ", y좌표:" + y ;
	}


	//필요에 따라 추가 기능 정의 --------------------
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}
	
}


















