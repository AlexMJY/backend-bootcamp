package javaz.oop;

public class Initialization {
	int instanceField = 1;		//인스턴스 변수
	static int staticField = 2;	//클래스 변수
	
	//인스턴스 초기화 블럭
	{  	System.out.println("3.instance init block");
		System.out.println("  instanceField : " + 
					        instanceField);
		System.out.println("  staticField : " + 
							staticField);
	}
	
	//클래스 초기화 블럭
	static {
		System.out.println("1.class init block");
//		System.out.println("  instanceField : " + 
//		        			instanceField);
		System.out.println("  staticField : " + 
							staticField);
	}
	
	//1.기본생성자 정의 - "constructor block" 출력
	public Initialization() {
		System.out.println("4.constructor block");
	}

	public static void main(String[] args) {
		System.out.println("2.main() start");
		new Initialization(); //2.현재 클래스의 생성자 호출
		System.out.println("5.main() end");
	}//END main()

}//END class








