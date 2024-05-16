package javaz.oop;

public class MethodOverLoading {
	//1.
	//메서드 이름 : sumAll
	//매개변수 : X
	//반환타입 : X
	//기능 : "-- 모두 더하기 with var args --" 출력하는
	//      공유 메서드
	public static void sumAll() {
		System.out.println("-- 모두 더하기 with var args --");
	}
	
	//2.정수형 매개변수 2개를 받아서 더한 후 반환하는 sumAll 메서드
	public int sumAll(int num1, int num2) {
		return num1 + num2;
	}
	
	//3.정수형 매개변수 3개를 받아서 더한 후 반환하는   "      "
	public int sumAll(int num1, int num2, int num3) {
		return num1 + num2 + num3;
	}
	
	//4.정수형 배열 하나를 매개변수로 받아서
	//  배열의 값을 모두 더한 후 반환하는 sumAll 메서드
	public int sumAll(int[] nums) {
		int sum = 0;
		
		for (int num : nums) {
			sum += num;
//			sum = sum + num;
		}
		
		return sum;
	}
	
	//5.실수형 데이터들을 매개변수로 받아서
	//  모두 더한 후 반환하는 sumAll 메서드
	public double sumAll(double ... nums) {
		double sum = 0.0;	//var args
		for (double num : nums) {
			sum += num;
		}
		return sum;
	}
	
	public static void main(String[] args) {
//		sumAll();
		MethodOverLoading mol = new MethodOverLoading();
		mol.sumAll();	//static이 아닌 접근 방법
		MethodOverLoading.sumAll();	//static 접근 방법
		sumAll();	//동일 클래스 내의 경우 이름만 명시 가능
		
		//2. 3과 4를 매개변수로 sumAll 메서드를 호출하여
		//   반환되는 값을 화면에 출력
		System.out.println("3 + 4 : " + 
						   mol.sumAll(3, 4));
		
		//3. 3과 4와 5를 매개변수로 sumAll 메서드를 호출하여
		//   반환되는 값을 화면에 출력
		System.out.println("3 + 4 + 5 : " + 
						   mol.sumAll(3, 4, 5));
		
		//4. 3, 4, 5, 6을 정수형 배열에 저장한 후
		//   sumAll 메서드를 호출하여 반환되는 값을 화면에 출력
		int[] nums = { 3, 4, 5, 6 };
		System.out.println("3 + 4 + 5 + 6 : " + 
				   		   mol.sumAll(nums));
		
		//6. 0.1, 0.2를 매개변수로 전달하여 
		//   반환되는 값을 화면에 출력
		System.out.println(mol.sumAll(0.1, 0.2));
		
		//7. 0.1, 0.2, 0.3을 매개변수로 전달하여
		//	 반환되는 값을 화면에 출력
		System.out.println(mol.sumAll(0.1, 0.2, 0.3));

		//8. 0.1, 0.2, 0.3, 0.4을 매개변수로 전달하여
		//	 반환되는 값을 화면에 출력
		System.out.println(mol.sumAll(0.1, 0.2, 0.3, 0.4));			
		
		
		//9.  Method 클래스 타입의 변수 m 선언
		Method m;
		
		m = new Method();		//9.1 m을 생성
		System.out.println(m);	//9.2 m을 문자열로 출력
		
		m.setType("네모");		//9.3 m의 type을 "네모"로 설정
		System.out.println(m);	//9.4 m을 문자열로 출력
		
		System.out.println(m.getType());//9.5 m의 type을 화면에 출력
		System.out.println(m.PI);		//9.6 m의 PI를 화면에 출력
//		Method 클래스의 PI는 static으로 선언된
//		공유 상수이므로 객체를 사용하지 않고
//			클래스의이름.필드이름
//		으로 접근해서 쓰는 것이 좋다
		System.out.println(Method.PI);	
		
		
		
		
	}
	
}






	






