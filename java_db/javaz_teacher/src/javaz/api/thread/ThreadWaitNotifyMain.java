package javaz.api.thread;

class Bakery {
	private int cake;
	private boolean empty = true;
	
	public synchronized int get()   {
		while(empty) {
			try {	wait();
			} catch (InterruptedException e) {	e.printStackTrace();		}
		}
		empty = true;
		notifyAll();
		return cake;
	}
	
	public synchronized void put(int cake) {
		while(!empty) {
			try {	wait();
			} catch (InterruptedException e) {	e.printStackTrace();	}
		}
		empty = false;
		this.cake = cake;
		notifyAll();
	}
}

class Patissier implements Runnable {
	private Bakery bakery;
	
	public Patissier(Bakery bakery) {
		this.bakery = bakery;
	}

	public void run() { //케익을 10개 집어넣기
		for(int i = 1 ; i<=10 ; i++) { 
			bakery.put(i);
			System.out.println("[Patissier] 만든 케익 수 : " + i);
			try {
				Thread.sleep((int)(Math.random() * 100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Customer implements Runnable {
	private Bakery bakery;
	
	public Customer(Bakery bakery) {
		this.bakery = bakery;
	}
	
	public void run() { //케익 10개 가져가기
		for(int i = 1 ; i<=10 ; i++) { 
			int cake = bakery.get();
			System.out.println("[Customer ] 구매 케익 수 : " + cake);
			try {
				Thread.sleep((int)(Math.random() * 100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ThreadWaitNotifyMain {
	public static void main(String[] args) {
		Bakery bakery = new Bakery();
		Patissier p = new Patissier(bakery);  //Patissier 스레드 시작 시키기
//		p.start();
		new Thread(p).start();
		new Thread(new Customer(bakery)).start();//Customer 스레드 시작 시키기
	}
}

















