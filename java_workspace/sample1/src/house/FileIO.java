package house;

import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
    public static void main(String[] args) throws IOException {
//        FileOutputStream output = new FileOutputStream("c:/out.txt");
//        for (int i = 1; i < 11; i++) {
//            String data = i + " 번째 줄입니다. \r\n";
//            output.write(data.getBytes());
//        }
        FileWriter fw = new FileWriter("c:/out.txt");
        for (int i = 1; i < 11; i++) {
            String data = i + " 번째 줄입니다. \r\n";
            fw.write(data);
        }


//        output.close();
        fw.close();
    }
}
