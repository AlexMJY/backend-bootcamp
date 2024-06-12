package javaz.basic;

//command line argument
//- 명령행 매개변수
//- 프로그램 실행 시 필요한 정보를 전달
public class ArrayArgs {

	public static void main(String[] args) {
		//args 배열의 0, 1, 2번째 인덱스의 값을 화면에 출력
		System.out.println(args[0]);
		System.out.println(args[1]);
		System.out.println(args[2]);
		
		System.out.println();
		System.out.println("- for문을 이용하여 재출력 -");
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		
		System.out.println();
		System.out.println("- foreach문을 이용하여 재출력 -");
		for (String a : args) {
			System.out.println(a);
		}
	}//END main()

}//END class






















