package dao;

public class TestMain2 {
	public static void main(String[] args) {
		
		int cnt = 0;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 5; j++) {
				cnt++;
				if (cnt == 10) break;
				System.out.println("i = " + i + ", j = " + j);
			}
		}
	}
}