package javaz.oop;

public class Tire {
	private String maker;
	private String position;
	
	public Tire(String maker, String position) {
		this.maker = maker;
		this.position = position;
	}
	
	public void setTireInfo() {
		System.out.println(maker + "를 " + 
	                       position + "에 장착했습니다.");
	}
}





