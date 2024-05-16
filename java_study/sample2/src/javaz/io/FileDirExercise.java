package javaz.io;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class FileDirExercise {
    public static void fileDir() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd   a HH-mm");
        File src = new File("src");


        System.out.println(src.getAbsolutePath() + " 디렉터리");

        int file_num = 0;
        int dir_num = 0;
        int file_size = 0;

        File[] files = src.listFiles();

        System.out.println(sdf.format(src.lastModified()) + "   <DIR>\t     .");
        System.out.println(sdf.format(src.lastModified()) + "   <DIR>\t     ..");
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.print(sdf.format(file.lastModified()) + "   <DIR>\t   ");
                System.out.println(file.getName());
                dir_num += 1;

            } else {
                System.out.print(sdf.format(file.lastModified()) + "\t           ");
                System.out.println(file.getName());
                file_num += 1;
                file_size += file.length();
            }
        }
        System.out.println("            " + file_num + "개 파일" + "       " + file_size + "바이트");
        System.out.println("            " + dir_num + "개 디렉터리");

    }

    public static void main(String[] args) {
        fileDir();

    }
}
