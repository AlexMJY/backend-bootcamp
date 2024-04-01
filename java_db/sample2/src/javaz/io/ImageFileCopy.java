package javaz.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImageFileCopy {
    public static void main(String[] args) throws IOException {
        String filename;
        int input = 0;
        filename = "C:\\dev\\hello.png";

        FileInputStream fis = new FileInputStream(filename);
        FileOutputStream fos = new FileOutputStream(filename.replace(".png", "-복사본.png"));

        fos.write(input);
        System.out.println("-- 복사본 생성 완료 --");
        fis.close();
        fos.close();

//        String source = "c:\\dev\\hello.png";
//        String copied = "c:\\dev\\hello.png - 복사본";
    }

}
