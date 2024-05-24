<%@page import="kr.co.jhta.web.vo.MemberVO"%>
<%@page import="kr.co.jhta.web.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			
			out.println("<h3> id : " + id +"</h3>");
			out.println("<h3> pwd : " + pwd +"</h3>");
			
			MemberDAO dao = new MemberDAO();
			MemberDTO vo =  dao.searchUser(id, pwd);
			
			if (vo != null) {
		out.println("<h1> " + vo.getName() + "님 환영합니다. </h1>");
		
		// 내장객체 session 저장
		session.setAttribute("vo", vo);
		
		out.println("<a href='login.jsp'> 로그인 페이지로 이동 </a>");
			} else {
		// 로그인 실패
		// 다시 로그인 페이지로 redirect
		response.sendRedirect("login.jsp");
			}
	%>
</body>
</html>