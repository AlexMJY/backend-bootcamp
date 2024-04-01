package javaz.oop;

//Car 클래스를 상속받는 클래스
public class ElectricCar extends Car {
	private int battery;
	private Tire tire1, tire2, tire3, tire4;
	private Tire[] tires;
	
	public ElectricCar(int battery, Tire tire1, Tire tire2, Tire tire3, Tire tire4) {
		this.battery = battery;
		this.tire1 = tire1;
		this.tire2 = tire2;
		this.tire3 = tire3;
		this.tire4 = tire4;
		
		tire1.setTireInfo();
		tire2.setTireInfo();
		tire3.setTireInfo();
		tire4.setTireInfo();
	}
	
	public ElectricCar(int battery, Tire ...tires) {
		this.battery = battery;
		this.tires = tires;
		for (Tire tire : tires) {
			tire.setTireInfo();
		}
	}
	
	public ElectricCar() { }

	public void setBattery(int battery) {
		this.battery = battery;
	}
	
	public int getBattery() {
		return battery;
	}
	
	//부모 클래스의 start() 오버라이딩하여 재정의 ----------
	public void start() {
		super.start();//부모 클래스의 start() 호출
		
		if(battery < 1){  //배터리가 1미만인 경우
			System.out.println("배터리를 충전해주세요.");
			stop();//자동차 정지시키기
		}
	}
	
	//부모 클래스의 final로 선언된 stop() 오버라이딩 X
//	public    void stop() {	//O 접근 제한자 동일
//	protected void stop() {	//X 부모 보다 좁은 범위로
//			  void stop() { //X 접근을 제한할 수 
//	private   void stop() {	//X 없음
//	}
}
















