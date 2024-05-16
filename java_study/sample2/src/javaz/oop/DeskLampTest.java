package javaz.oop;

public class DeskLampTest {

	public static void main(String[] args) {
		//1. DeskLamp 클래스의 객체 myLamp 생성
		DeskLamp myLamp = new DeskLamp();
		myLamp.powerOn();	//2. myLamp 켜기
		
		myLamp.setBrightness(3);  //4. myLamp의 밝기를 3으로 설정
		System.out.println("램프의 밝기 : " + 
					 	   myLamp.getBrightness()); //5. myLamp의 현재 밝기를 화면에 출력

		myLamp.powerOff();  //3. myLamp 끄기
		
		///////////////////////////////////
		//6. DeskLamp 클래스의 인스턴스 redLamp 생성
		DeskLamp redLamp = new DeskLamp();
		redLamp.powerOn();  	 //7. redLamp의 전원 켜기
		redLamp.setBrightness(2);//8. redLamp의 밝기를 2로 설정
		
		//9. redLamp의 밝기와 전원 상태 출력
		//10. myLamp의 밝기와 전원 상태 출력
		System.out.println(myLamp);
		System.out.println(redLamp.toString());
	}

}




















