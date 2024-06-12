package javaz.api;

public class ExceptionMain {
	public static void main(String[] args) {
		//1.직접 예외 처리 ------------------------------
		try { //예외 발생 가능 영역
			System.out.println("1.예외 발생 전");
			System.out.println(args[2]);	//2.예외 발생
			System.out.println("3.예외 발생 후");
		} catch(NullPointerException e) { 
			System.out.println("4.관련없는 예외 객체 i");
		} catch(NumberFormatException | ArithmeticException e) {
			    //여러 예외를 처리하고 싶은 경우
			System.out.println("5.관련없는 예외 객체 ii");
//		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("6.예외 잡기 성공!");
		} catch (Exception e) {
			System.out.println("7.최상위 예외 객체");
			e.printStackTrace();
			System.out.println("예외 메시지 : " + e.getMessage());
		} finally {
			System.out.println("8.예외 발생 여부 관계없이 실행");
		}
		System.out.println("-- END main() --");
		
		Cal c = new Cal();
		c.eeja(10000);
		Cal.eeja(20000, "Lee");
		
		try { 
			Cal.eeja(30000, null);
		} catch (NullPointerException e) {
			System.out.println("NullPointerException 발생!!");
		}
		
		ExceptionMain em = new ExceptionMain();
		try {
			em.countdown(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (SamchoException e) {
			System.out.println("사용자정의 예외 발생!!");
			System.out.println(e.getMessage());
		}
	}//END main()
	
	public void countdown(int num) throws InterruptedException, SamchoException {
		if(num < 3) {
			throw new SamchoException("카운트 다운은 3초 이상으로 지정해주세요.");
		}  
		
		System.out.println("-- 카운트 다운 --");
		for(int i = num ; i >= 0 ; i--) {
			System.out.println(i);
			Thread.sleep(1000);
		}
	}
	
}//END class//////////////////////////////////////////////

class Cal {
	public static final double RATE = 0.05;
	
	//예외 상황이 발생하는 경우 메서드를 호출한 쪽으로 예외 처리 위임
	public static void eeja(int wongum, String name) 
			                  throws NullPointerException {
		System.out.println("고객명 : " + name);
		System.out.println("고객명 : " + name.toUpperCase());
		System.out.println("원금 : " + wongum);
		System.out.println("이자 : " + wongum * RATE);
	}
	
	public void eeja(int wongum) {
		try { 
			System.out.println(wongum / 0);
		} catch(ArithmeticException e) {
			System.out.println("예외 발생!");
			System.out.println("예외 메시지 : " + e.getMessage());
		}
	}
}



































