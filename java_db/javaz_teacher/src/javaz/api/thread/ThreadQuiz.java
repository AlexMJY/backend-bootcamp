package javaz.api.thread;

import javax.swing.JOptionPane;

class Quiz extends Thread{
	static boolean inputFlag;
	
	@Override
	public void run() {
		String input = JOptionPane.showInputDialog(
							"question?\n(제한시간 : " +
							Countdown.remainTime + "초)");
		String msg = "실행이 취소되었습니다.";
		String answer = "Object";
		
		do {
			if(input != null) { //확인 버튼이 눌린 경우
				if(input.trim().isEmpty()) {	//값 X
					input = JOptionPane.showInputDialog(
							"RE question?\n(제한시간 : " +
							Countdown.remainTime + "초)");
				} else { 	//값 O
					inputFlag = true;
					msg = input.equals(answer)?"정답입니다!":"오답입니다.";		
					msg += "\n입력: " + input + "\n정답: " + answer;
					showResult(msg);
				}
			} else {
				showResult(msg); //취소 또는 X 버튼이 눌린 경우
			}
		} while(Countdown.remainTime > 0);
	}
	
	public static void showResult(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		System.exit(0); 	//시스템 정상 종료
	}
}

class Countdown implements Runnable {
	static int remainTime = 10;
	
	@Override
	public void run() {
		for(remainTime = 10 ; remainTime > 0 ; remainTime--) {
			System.out.println(remainTime + "초");
			
			if(Quiz.inputFlag == true) {
				return; 
			}
			
			try { 
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("- 제한 시간 종료 -");
		Quiz.showResult("- 제한 시간 종료 -\n"+
				   	    "정답 : 자바의 최상위 클래스는 Object입니다.");
	}
}


public class ThreadQuiz {
	public static void main(String[] args) {
		Quiz quiz = new Quiz();  
		Thread c = new Thread(new Countdown());
		
		quiz.start();
		c.start();
	}
}


















