package javaz.oop;

public class DeskLamp {
	//field / 속성property / 멤버변수 
	private boolean power;	//켜짐: true,     꺼짐:false
	private int brightness;	//밝기: 1 | 2 | 3,    0

	//method / 기능 / 동작 / 
	public void powerOn() {	
		power = true;		//전원 켜기
		brightness = 1;		//밝기 1로 설정
		System.out.println("램프가 켜졌습니다.");
	}

	public void powerOff() {
		power = false;		//전원 끄기
		brightness = 0;		//밝기 0으로 설정
		System.out.println("램프가 꺼졌습니다.");
	}
	
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	
	public int getBrightness() {
		return brightness;
	}
	
	//객체를 문자열로 반환하는 메서드
	public String toString() {
		//전원이 켜진 경우 on, 꺼진 경우 off
		return "램프 전원 : " + (power==true?"on":"off") +   
			   " 밝기 : " + brightness;
	}
}















