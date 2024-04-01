package javaz.io;

import java.io.File;
import java.text.SimpleDateFormat;

public class FileDirExercise {
	public static void main(String[] args) {
		File src = new File("src");
		File[] files = src.listFiles();
		int dirCnt = 2;
		int fileCnt = 0;
		int fileSize = 0;

		SimpleDateFormat dateFmt 
			= new SimpleDateFormat("yyyy-MM-dd a hh:mm \t");
		
		System.out.println(src.getAbsolutePath() + " 디렉터리");				
		System.out.println();
		System.out.println(dateFmt.format(src.lastModified()) +"<DIR>\t   .");
		System.out.println(dateFmt.format(src.lastModified()) +"<DIR>\t   ..");						
		for (File file : files) {
			System.out.print(dateFmt.format(file.lastModified()));
			
			if(file.isDirectory()) {
				dirCnt++;
				System.out.printf("%-9s", "<DIR>\t");
			}
			
			if(file.isFile()) {
				fileCnt++;
				fileSize += file.length();
				System.out.printf("%10d ", file.length());
			}
			System.out.println(file.getName());
		}//END for
		System.out.printf("%15d개 파일 %15d 바이트\n ", 
				   		  fileCnt,  fileSize);
		System.out.printf("%14d개 디렉터리 %,d 바이트 남음\n ", 
						  dirCnt,  src.getFreeSpace());
		System.out.println(src.getAbsolutePath() + "> ");
		
	}
}





















