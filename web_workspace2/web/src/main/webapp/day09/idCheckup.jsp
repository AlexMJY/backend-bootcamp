<%@page import="kr.co.jhta.web.vo.MemberVO"%>
<%@page import="kr.co.jhta.web.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// idCheckup.jsp?id=aaa
	// 1. 파라미터 값 가져오기
	String id = request.getParameter("id");
	
	// 2. id가 널이 아니라면
	if (id != null) {
		MemberDAO dao = new MemberDAO();  // 3. dao 객체
		MemberVO vo = dao.getOne(id);  // 4. dao.getOne(id) ==> vo 리턴
		
		// 5. vo가 널이라면 존재하지 않는 아이디 : 화면에 true 출력
		if (vo == null) {
	out.println("true");
	System.out.println("중복된 값이 없습니다.");
		// 6. vo가 널이 아니라면 존재하는 아이디 : 화면에 false 출력
		} else {
	out.println("false");
	System.out.println("중복된 값이 있습니다.");
		}
	}
%>