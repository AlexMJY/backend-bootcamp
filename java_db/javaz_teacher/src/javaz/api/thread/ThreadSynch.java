package javaz.api.thread;

class MoneyBox {
	int money;
	
	public MoneyBox(int money) {
		this.money = money;
	}
	
	public  void withdraw() {
		this.money -=1;
	}
}

class MoneyMoa extends Thread {
	private MoneyBox moneyBox ;
	
	public MoneyMoa(String name, MoneyBox moneyBox) {
		super(name);
		this.moneyBox = moneyBox;
	}
	
	public void run() {
		for(int i=1 ; i<=5 ; i++) {
//			synchronized (moneyBox) {
//				moneyBox.money++;
//				System.out.println(getName() + " : " + 
//								   moneyBox.money);
//			}
			
			synchronized (moneyBox) {
				System.out.print(getName() + " 출금 -1 : ");
				moneyBox.withdraw();
				System.out.println(moneyBox.money);
			}
		}
	}
}

public class ThreadSynch {
	public static void main(String[] args) {
		MoneyBox mBox = new MoneyBox(0);
				 mBox = new MoneyBox(10);
				 
		MoneyMoa ann = new MoneyMoa("Ann", mBox);
		MoneyMoa ben = new MoneyMoa("Ben", mBox);
		
		ann.start();
		ben.start();
	}
}





















