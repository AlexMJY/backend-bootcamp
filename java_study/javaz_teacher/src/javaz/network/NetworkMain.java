package javaz.network;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

//InetAddress 클래스
//- IP 주소를 표현
//- 로컬 컴퓨터의 IP 주소 및 도메인 이름을 DNS에서 
//  검색한 후 IP 주소를 가져옴

//DNS server
//- 도메인 이름을 IP 주소로 변환해주는 서버
public class NetworkMain {
	public static void main(String[] args) throws IOException {
		InetAddress ia = InetAddress.getLocalHost();
		
		System.out.println("로컬 호스트 이름 : " + ia.getHostName());
		System.out.println("로컬 호스트 IP 주소 : " + ia.getHostAddress());		
		System.out.println();
		
		ia = InetAddress.getByName("www.naver.com");
		System.out.println("호스트 이름 : " + ia.getHostName());
		System.out.println("호스트 IP 주소 : " + ia.getHostAddress());		
		System.out.println();
		
		InetAddress[] ias = InetAddress.getAllByName("www.naver.com");
		for (InetAddress i : ias) {
			System.out.println("호스트 이름 : " + i.getHostName());
			System.out.println("호스트 IP 주소 : " + i.getHostAddress());		
		}
		System.out.println("---------------------");
		
		//URL 객체 생성 - 특정 웹 페이지 읽어오기
		String address = "https://docs.oracle.com/en/java/javase/11/docs/api/";
		URL url = new URL(address);
		
		//1byte 스트림으로 읽기
		InputStream is = url.openStream();
		FileOutputStream fos = new FileOutputStream("api.html");
		int input = 0;
		while( (input = is.read()) != -1) {						
			System.out.write(input);
			fos.write(input);
		}
		fos.close();
		is.close();
		System.out.println("-------------------");
		
		//2byte 스트림으로 읽기
		BufferedReader br = new BufferedReader(
					new InputStreamReader(url.openStream()));				
		FileWriter fw = new FileWriter("api2.html");
		String inputs = "";
		while( (inputs = br.readLine()) != null) {
			//api.html로 저장하기
			System.out.println(inputs);
			fw.write(inputs);
		}
		fw.close();
		br.close();

		System.out.println("-------------------");
		URLConnection urlCon = url.openConnection();
		System.out.println("URL : " + address);
		
		//지정된 URL의 헤더 정보 보기
		Map<String, List<String>> headerMap 
						= urlCon.getHeaderFields();
		headerMap.forEach((key, value) -> {
			System.out.println(key  + " : " + value);
		});
		
		System.out.println("마지막 수정 일자 : " + 
							urlCon.getLastModified());	
		System.out.println("문서의 인코딩 : " + 
							urlCon.getContentEncoding());
		
	}
}


















