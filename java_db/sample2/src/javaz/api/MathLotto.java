package javaz.api;

public class MathLotto {
	private int[] lottoWin;
	private int bonusNum;
	
	public MathLotto() {
		lottoWin = new int[]{ 1, 3, 4, 29, 42, 45 }; 
		bonusNum = 36;
		showResult();
	}
	
	public int[] makeLottoNum() {
		int[] myLottoNum = { 1, 3, 4, 29, 36, 42 };
		return myLottoNum;
	}
	
	//나의 당첨번호		: ~~ ~~ ~~ ~~ ~~ ~~   1등 ... 5등 | 다음 기회에
	private void showResult() {
		int[] myLottoNum = makeLottoNum(); 
		
		System.out.println("- 1106회차 로또 당첨 결과 - ");
		System.out.print("이번 주 당첨 번호\t: ");
		for (int i : lottoWin) {
			System.out.printf("%3d", i);
		}
		System.out.print(" + " + bonusNum);
		System.out.println();
		System.out.print("나의 로또 번호\t: ");
		for (int i : myLottoNum) {
			System.out.printf("%3d", i);
		}

		//당첨 개수 확인 -------------------
		int count = 0;
		for (int myNum : myLottoNum) {
			for (int i : lottoWin) {
				if(myNum == i)	count++;
			}
		}
		
		//당첨 여부 및 등수 확인----------------
		String result = "";
		switch (count) {
		case 6 : result = "1등";	break;
		case 5 : result = "3등";
				for (int myNum : myLottoNum) {
					if(myNum == bonusNum) {
						result = "2등";
					}
				}
				break;
		case 4 : result = "4등";	break;
		case 3 : result = "5등";	break;
		default: result = "다음 기회에";
		}
		
		//당첨 결과 표시 ----------------------
		System.out.print("   " + result);
		
	}

	public static void main(String[] args) {
		new MathLotto();
	}
}



















