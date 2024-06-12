package javaz.api.thread;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

class AutoSaveDaemon extends Thread {							
	@Override
	public void run() {
		while(ThreadDaemon.autoSave) {
			try {
				Thread.sleep(ThreadDaemon.interval);
				fileAutoSave();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void fileAutoSave() {
		SimpleDateFormat dateFmt 
			= new SimpleDateFormat("[hh:mm:ss] ");
		
		System.out.println(dateFmt.format(new Date()) + 
						   "파일이 자동 저장되었습니다.");
	}
}

public class ThreadDaemon {											
	static boolean autoSave;	//파일 자동 저장 여부
	static int interval;		//파일 자동 저장 간격 1/1000초
	
	public static void main(String[] args) 
					throws InterruptedException, IOException  {
		System.out.println(">> 파일 작업 설정");
		System.out.print("   자동 저장은 y, 그외는 아무거나 입력하세요 : ");
		autoSave = System.in.read() == 'y' ? true : false;
		System.in.skip(2);
		
		if(autoSave) {
			System.out.print("   저장 간격(10초 미만) : ");
			interval = (System.in.read() - 48) * 1000;
			System.in.skip(2);
		}
		
		AutoSaveDaemon asd  = new AutoSaveDaemon();
		asd.setDaemon(true);	//데몬 스레드로 설정
		asd.start();
		
		System.out.println("------------------------------------");
		System.out.println(">> 파일 작업 시작 - 제한 시간 10초");
		for(int i=1 ; i<=10 ; i++) {
			Thread.sleep(1000);
			System.out.println(i);
		}
		System.out.println(">> 파일 작업 종료 ");
	}
}













