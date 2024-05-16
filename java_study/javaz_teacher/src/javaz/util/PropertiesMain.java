package javaz.util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;

//Properties
//- 속성들을 모아서 하나의 객체로 만들기 위해 제공되는 클래스
//- 프로그램 시작 전에 필요한 속성들을 미리 인식시켜서
//  키와 값을 String 타입으로 제한
//- 프로퍼티 파일(~~~.properties)을 읽어들일 때 사용
public class PropertiesMain {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("USER", "dev");
		props.setProperty("PASSWORD", "1111");
		System.out.println(props);
		System.out.println("------------------------");
		
		System.out.println(props.get("USER"));
		System.out.println(props.getProperty("PASSWORD"));
		System.out.println("------------------------");	
		
		Enumeration<?> keyz = props.propertyNames();
		while( keyz.hasMoreElements() ) {
			String key =  (String) keyz.nextElement();
			System.out.println(key + " : " + props.get(key));	
		}
		System.out.println("------------------------");	
		
		System.out.println(System.getProperty("file.separator"));
		System.out.println(System.getProperty("path.separator"));
//		props = System.getProperties();
//		props.list(System.out));
		System.out.println("------------------------");	
		
		Scanner sc = new Scanner(System.in);
		System.out.print("시스템 프로퍼티의 이름을 입력하세요\n(종료는 q) : " );
		String propName = sc.nextLine();
		
		props = System.getProperties();
		if(props.containsKey(propName)) {
			System.out.println(propName + "의 값 : " + 
							   props.getProperty(propName));
		} else {
			System.out.println("입력하신 프로퍼티가 존재하지 않습니다.");            
		}
	}
}
















