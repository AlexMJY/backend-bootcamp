package javaz.network;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

public class NetworkMain {
    public static void main(String[] args) throws IOException {
        InetAddress ia = InetAddress.getLocalHost();

        System.out.println("로컬 호스트 이름 : " + ia.getHostName());
        System.out.println("로컬 호스트 IP 주소 : " + ia.getHostAddress());
        System.out.println();

        ia = InetAddress.getByName("www.naver.com");
        System.out.println("로컬 호스트 이름 : " + ia.getHostName());
        System.out.println("로컬 호스트 IP 주소 : " + ia.getHostAddress());
        System.out.println();

        InetAddress[] ias = InetAddress.getAllByName("www.naver.com");
//        System.out.println(Arrays.toString(ias));
        for (InetAddress i : ias) {
            System.out.println("로컬 호스트 이름 : " + i.getHostName());
            System.out.println("로컬 호스트 IP 주소 : " + i.getHostAddress());
            System.out.println();
        }
        System.out.println("-----------------------------------------");


        // URL 객체 생성 - 특정 웹 페이지 읽어오기
        String address = "https://docs.oracle.com/en/java/javase/11/docs/api/";
        URL url = new URL(address);

//         1byte stream
//        InputStream is = url.openStream();
//        FileOutputStream fos = new FileOutputStream("api.html");
//        int input = 0;
//        while ( (input = is.read()) != -1) {
//            System.out.write(input);
//            fos.write(input);
//        }
//        fos.close();
//        is.close();
//        System.out.println("--------------------");

//         2byte stream (주로 사용)
//        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
//        FileWriter fw = new FileWriter("api2.html");
//        while (true) {
//            String line = br.readLine();
//            if (line == null)
//                break;
//            fw.write(line);
//            System.out.println(line);
//        }
//        fw.close();
//        br.close();
//        System.out.println();


        URLConnection urlCon = url.openConnection();
        System.out.println("URL : " + address);

        // 지정된 URL의 헤더 정보 보기
        Map<String, List<String>> headerMap = urlCon.getHeaderFields();
//        System.out.println(map.toString());
        headerMap.forEach((strKey, strVal) -> {
            System.out.println(strKey + " : " + strVal);
        });
        System.out.println();


        System.out.println("마지막 수정 일자 : " + urlCon.getLastModified());
        System.out.println("문서의 인코딩 : " + urlCon.getContentEncoding());
        System.out.println("콘텐트 길이 : " + urlCon.getContentLength());





    }
}
