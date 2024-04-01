package javaz.basic;

public class Array1DExercise {

	public static void main(String[] args) {
		System.out.println("=== JAVA TEST SCORE ===");
		//이름 5개를 저장할 문자열 배열 names를 선언하면서
		//Kim, Lee, Han, Ann, Ben으로 초기화
		
		//99, 88, 77, 66, 50을 javas 배열 변수에 저장하여 생성

		
		//저장된 데이터를 다음 형태로 출력
		//순위	이름		점수
		//1	 	~~~ 	~~
		//2		~~~ 	~~
		//3	 	~~~ 	~~
		//4		~~~ 	~~
		//5	 	~~~ 	~~
		//-----------------
		//총점 : ~~~
		//평균 : ~~.~~
		
		String[] names = { "Kim", "Lee", "Han",
						   "Ann", "Ben"	    	};					
		int[] javas = { 99, 88, 77, 66, 50 };
		int total = 0;

		//버블 정렬 - 서로 이웃한 데이터들을 비교하여 맨 뒤로 보냄
		for(int i = 0 ; i < javas.length ; i++) {
			for(int j = 0 ; j < i ; j++) {
				if(javas[j] > javas[i]) {	//오름차순 정렬
//				if(javas[j] < javas[i]) {	//내림차순 정렬
					int temp = javas[i];
					javas[i] = javas[j];
					javas[j] = temp;
				}
			}
		}
		
		System.out.println("순위\t이름\t점수");
		for(int i = 0 ; i < names.length ; i++) {
			System.out.println( (i+1) + "\t" + 	//순위, 이름, 점수 출력
								names[i] + "\t" +
								javas[i]);
			total = total + javas[i];	//total에 각 점수를 누적 합산
		} //total += javas[i];
		
		System.out.println("----------------");
		System.out.println("총점 : " + total);//총점 출력
		System.out.printf("평균 : %.2f", (float)total/javas.length); //평균 출력
	}//END main()

}//END class













