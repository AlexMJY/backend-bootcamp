<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	// 이 부분은 사용자에게 보여줄 내용이 없음
	// 즉, HTML 부분 작성 불필요
	session.invalidate(); // 무효화
	
	// 로그인 페이지로 리다이렉트
	response.sendRedirect("layout.jsp");
%>