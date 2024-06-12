package javaz.api;

//enum 열거형
//- 미리 정의된 상수값을 만들기 위한 자료형

enum Season {
	SPRING, SUMMER, FALL, WINTER, ALL
}

enum Baseball2023{
	LG(86, 56), KT(79, 62), SSG(76, 65), NC(75, 67);

	private final int win;
	private final int lose;
	
	Baseball2023(int win, int lose) {
		this.win = win;
		this.lose = lose;
	}
	
	public double winsRate() {
		return (win*100.0) / (win+lose)/100.0;
	}

	public int getWin() {
		return win;
	}

	public int getLose() {
		return lose;
	}
}

public class EnumMain {
	public static void main(String[] args) {
		Baseball2023 lg = Baseball2023.LG;
		Baseball2023 kt = Baseball2023.KT;
		
		System.out.println("team LG : " + 
						   lg.getWin() + "승 " +
						   lg.getLose() + "패 ");

		System.out.println("team KT : " + 
						   kt.getWin() + "승 " +
						   kt.getLose() + "패 ");
		System.out.println();
		System.out.println("- 2023 프로야구팀 승률 - ");						
		Baseball2023[] teams = Baseball2023.values();
		for (Baseball2023 t : teams) {
			System.out.printf("%s\t : %.3f\n", 
					           t, t.winsRate());
		}
		
		
		//////////////////////////////////////////
		System.out.println();
		Season season = Season.SPRING;
		
		switch (season) {
		case SPRING: System.out.println("계절 : 봄");		break;
		case SUMMER: System.out.println("계절 : 여름");	break;
		case FALL:	 System.out.println("계절 : 가을");	break;
		case WINTER: System.out.println("계절 : 겨울");	break;
		default: 	 System.out.println("계절 : 사계절");	break;
		}
		
		System.out.println();
		Season[] seasons = Season.values();
		for (Season s : seasons) {
			System.out.println(s);
		}
	}
}
























