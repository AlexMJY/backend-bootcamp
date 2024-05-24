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
			MemberDTO vo = dao.searchUser(id, pwd);
			
			if (vo != null) {
		
		// 내장객체 session 저장
		session.setAttribute("vo", vo);
		response.sendRedirect("productList.jsp");
		System.out.println("로그인 성공");
		
		if (id.equals("admin")) {
			session.setAttribute("admin", id);
		}
		

			} else {
		// 로그인 실패
		// 다시 로그인 페이지로 redirect
		System.out.println("로그인 실패");
		response.sendRedirect("login.jsp");
			}
	%>
</body>
</html>