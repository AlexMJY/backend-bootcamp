package day04;

import kr.co.jhta.web.dao.MemberDAO;
import kr.co.jhta.web.vo.MemberVO;

public class TestMain2 {
	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo =  dao.searchUser("aaa", "bbb");
		
		System.out.println("vo : " + vo);
		
		System.out.println("id : " + vo.getId());
		System.out.println("pw : " + vo.getPw());
		System.out.println("name : " + vo.getName());
		
	}
}

