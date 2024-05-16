package javaz.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class Capital {
	private Map<String, String> map;
	
	public Capital() {
		map = new HashMap<String, String>();
		map.put("대한민국", "서울");
		map.put("가나", "아크라");
		map.put("나이지리아", "아부자");
		map.put("아르헨티아", "부에노스아이레스");
	}
	
	public void getCapital(String country) {
		if(map.containsKey(country)) {
			System.out.println(country + "의 수도 : " + 
							   map.get(country));
		} else {
			System.out.println("입력하신 나라가 존재하지 않습니다.");            
		}
		System.out.println();
	}
}

public class MapCapitalQuiz {
	public static void main(String[] args) {
		System.out.println("--- 각 나라의 수도 알아보기 ---");
		Scanner sc = new Scanner(System.in);
		Capital c = new Capital();
		
		while(true) {
			System.out.print("나라 이름(종료는 q) : " );
			String input = sc.nextLine();
			
			if(input.equalsIgnoreCase("q")) {
				break;
			}
			c.getCapital(input);
		}
		
		System.out.println("--- 프로그램이 종료되었습니다. ---");
		sc.close();
	}
}







