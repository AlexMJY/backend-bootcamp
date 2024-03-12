package javaz.api;

import java.io.IOException;

public class SystemGawiBawiBo {
	private int input;
	
	public void menu() {
		while(true) {
			try {
				System.out.println("----- JAVA GAME CENTER -----");
				System.out.println("\t1.가위 바위 보"); 
				System.out.println("\t2.야구 게임"); 
				System.out.println("\t3.종료"); 
				System.out.println("----------------------------");
				System.out.print(">> 선택 : " );
				input = System.in.read();
	//			System.in.read();
	//			System.in.read();
				System.in.skip(2);
				
				switch (input - 48) {	 
				case 1:	gawiBawiBo();	break;
				case 2:	baseball();		break; 
				case 3:	System.out.println("시스템을 종료합니다.");
						System.in.close();
						System.exit(0);
				default:System.out.println(">> 1 ~ 3을 입력해주세요."); 
				}
				System.out.println();
			} catch (IOException e) {
				e.printStackTrace();
			}//END catch
		}//END while
	}//END menu()
	
	public void baseball() {
		System.out.println(">> 컴퓨터와 야구 게임!");
		System.out.println(">> 준비 중입니다....");
		System.out.println();
	}
	
	public void gawiBawiBo() {
		
		try {
			char yesNo = 'y';	
			
			do {
				System.out.println();
				System.out.println(">> 컴퓨터와 가위 바위 보!");
				System.out.println(">> 가위(0), 바위(1), 보(2) 중 하나를 선택해주세요.");
				System.out.print(">> 선택 : ");
				int you = System.in.read() - 48;
				System.in.skip(2);
				
				//0, 1, 2가 아닌 값이 입력된 경우 ---------
				if(you < 0 || you > 2) {
					System.out.println(">> 0 ~ 2 중 하나를 입력해주세요.");
					continue;
				}
					
				int com = (int) (Math.random() * 3); //컴퓨터 입력 값
				String youStr = you == 0 ? "가위" : you == 1 ? "바위" : "보";
				String comStr = com == 0 ? "가위" : com == 1 ? "바위" : "보";
				String result = ""; //대결 결과
				switch (you - com) {
				case -2:
				case 1:
					result = "U WIN!";
					break;
				case 0:
					result = "DRAW!";
					break;
				default:
					result = "U LOSE!";
				}
				System.out.println("-----------------------------");
				System.out.println(" you : " + youStr + "  vs.  com : " + comStr);
				System.out.println("-----------------------------");
				System.out.println("         " + result);
				System.out.print(">> 계속하려면 y, 그만하려면 아무거나 입력해주세요. : ");
				yesNo = (char) System.in.read();
							   System.in.skip(2);
			} while (yesNo == 'y' || yesNo == 'Y');
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		SystemGawiBawiBo sgb = new SystemGawiBawiBo();
		sgb.menu();
	}
}
