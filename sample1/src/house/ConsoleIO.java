package house;//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.BufferedReader;
//
//
//public class ConsoleInput {
//    public static void main(String[] args) throws IOException{
//        InputStream in = System.in;
//        InputStreamReader reader = new InputStreamReader(in);
//        BufferedReader br = new BufferedReader(reader);
//
////        char[] a = new char[3];
////        reader.read(a);
//        String a = br.readLine();
//
//        System.out.println(a);
//    }
//}


import java.util.Scanner;

public class ConsoleIO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(sc.next());
    }
}