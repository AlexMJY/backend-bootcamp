package javaz.api;

public class StringExercise {
	public static final String BIRTHINFO_HEADLINE 
									= "주민등록번호|출생년도|월|일|성별";
	
	public void printBirthInfo(String[] idNos) {
		System.out.println(BIRTHINFO_HEADLINE);
		for (String idNo : idNos) {
			String year  = idNo.substring(0, 2);
			String month = idNo.substring(2, 4);
			String date  = idNo.substring(4, 6);
			char gender  = idNo.charAt(idNo.indexOf("-") + 1);
			
			if(gender <= '2') year = "19" + year;
			else			  year = "20" + year;
			
			System.out.println(idNo + "|" + year + "|" +					
					           month + "|" + date + "|" +
							   (gender%2 == 0 ? "여성" : "남성") );
		}
	}
	
	public static void printFileInfo(String filename) {
		String name = filename.substring(0, filename.lastIndexOf("."));
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		System.out.println("파일정보 : " + filename);
		System.out.println("파일명 : " + name);
		System.out.println("확장자 : " + ext);
		System.out.println();
	}
	
	public static void main(String[] args) {
		StringExercise se = new StringExercise();
		se.printBirthInfo(args);
		
		String file1 = "my.music.best.one.mp3";
		String file2 = "long_long_file_two.mpeg";
		printFileInfo(file1);
		printFileInfo(file2);
	}

}













