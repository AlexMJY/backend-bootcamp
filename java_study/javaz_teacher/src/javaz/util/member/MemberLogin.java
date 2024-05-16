package javaz.util.member;

import java.util.HashMap;
import java.util.Map;

public class MemberLogin {
	public static Map<String, String> memberMap;

	public MemberLogin() {
		memberMap = new HashMap<String, String>();
		memberMap.put("admin", "1234");
		memberMap.put("aaa", "1111");
		memberMap.put("bbb", "2222");
		memberMap.put("ccc", "3333");
	}
	
	//로그인 체크
	public boolean loginChk(String id, String pw) {
		if(memberMap.containsKey(id)    
		   && pw.equals(memberMap.get(id))) { 
			return true;
		} else {
			return false;
		}
	}
	
	//로그아웃 
	public void logout(String id) {
		
	}
}
















