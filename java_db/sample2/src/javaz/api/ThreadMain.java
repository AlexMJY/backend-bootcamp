package javaz.api;


//1. Thread 클래스 상속 받기
class ThreadExtends extends Thread{
	public ThreadExtends(String name) {
		super(name);
	}

	public ThreadExtends() { }

	@Override
	public void run() {
		 for( int i=1 ; i<=3 ; i++) {
			try {
				Thread.yield();
				System.out.println(getName() + " " + i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
	}
}//END ThreadExtends

//a. Runnable 인터페이스 구현
class RunnableImplements implements  Runnable{
	@Override
	public void run() {
		for( int i=4 ; i<=6 ; i++) {
			try {
				System.out.println(Thread.currentThread()
									     .getName() + " " + i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
	}
}//END RunnableImplements

public class ThreadMain {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("---- main() begin ----");
		Thread t = Thread.currentThread();
		
		RunnableImplements t4 = new RunnableImplements();
//		t4.setName("티사");	//X
//		t4.start();	//X  
		Thread t44 = new Thread(t4);
		t44.setName("티사");
		
		Thread t5 = new Thread(new RunnableImplements(), "티오");				
		
		//스레드 우선 순위 확인
		System.out.println("티사 : " + t44.getPriority());
		System.out.println("티오 : " + t5.getPriority());
		t44.setPriority(Thread.MAX_PRIORITY);	//우선 순위 최대 설정
		t5.setPriority(1);	//우선 순위 최저 설정
		System.out.println("----------------");
		System.out.println("티사 : " + t44.getPriority());
		System.out.println("티오 : " + t5.getPriority());
		

//		t44.start();
//		t5.start();
		
		ThreadExtends t2 = new ThreadExtends();  
		t2.setName("티투");  
//		t2.run();	//X
		t2.start();	//O  
		
		Thread t3 = new ThreadExtends("티삼");  
		t3.start();						   
		
		t2.join();	 
		t3.join();	
		t44.join();
		t5.join();
		
		System.out.println("실행 중인 스레드 이름 : " + t.getName());
		System.out.println("---- main() end ----");
	}
}
  





























