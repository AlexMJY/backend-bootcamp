package javaz.basic;

public class Array2DExercise {

	public static void main(String[] args) {
		//성적들을 저장하는 2차원 배열 변수 scores
		//각 과목들의 총점을 저장하는 1차원 배열 변수 subjectSum
		
		int[][] scores = { {100, 95, 98},
						   {27, 48, 22},
						   {69, 77, 80}   };
		int[] subjectSum = new int[3];
		
		System.out.println("번호\t국어\t영어\t수학\t총점\t평균");
		System.out.println("--------------------------------------------");
		for(int i=0 ; i<scores.length ; i++) {
			System.out.print( (i+1) + "번\t" );
			
			//각 학생의 과목별 총점 저장 변수 선언 및 초기화
			int studentSum = 0;	
			
			for(int j = 0 ; j < scores[i].length ; j++) {
				//각 학생의 과목별 점수 출력
				System.out.print(scores[i][j] + "\t");
				
				//각 학생의 과목별 총점 계산
				studentSum = studentSum + scores[i][j];
				
				//각 과목의 총점 계산
				subjectSum[j] += scores[i][j];	
			}//END inner for
			
			System.out.print(studentSum + "\t");//각 학생의 총점 출력
			
			//각 학생의 평균 계산 출력
			System.out.printf("%.2f ", 
							 (float)studentSum/scores.length);  

			System.out.println();
		}//END outer for
		System.out.println("--------------------------------------------");

		//과목별 총점 출력
		System.out.print("총점\t");
		for (int s : subjectSum) {
			System.out.print(s + "\t");
		}
		
		//과목별 평균 출력
		System.out.println();
		System.out.print("평균\t");
		for (int s : subjectSum) {
			System.out.printf("%.2f \t", 
					 (float)s/scores.length);  
		}
		
		
	}//END main()

}//END class




















