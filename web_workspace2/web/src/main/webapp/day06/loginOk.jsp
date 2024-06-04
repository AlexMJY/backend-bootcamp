<%@page import="kr.co.jhta.web.vo.MemberVO"%>
<%@page import="kr.co.jhta.web.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginOk.jsp</title>
</head>
<body>
	<%
	String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberDAO dao = new MemberDAO();
			MemberVO vo =  dao.searchUser(id, pw);
			
			if (vo != null) {
		session.setAttribute("vo", vo);
		session.setAttribute("name", vo.getName());
		response.sendRedirect("layout.jsp");
		
			} else {
		response.sendRedirect("layout.jsp");
			}
	%>
</body>
</html>