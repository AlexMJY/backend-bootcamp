<%@page import="java.util.Arrays"%>
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
		String name = request.getParameter("name");
		String socialNum = request.getParameter("socialNum1") + "-" + request.getParameter("socialNum2");
		String pw = request.getParameter("pw");
		String phone = request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-" + request.getParameter("phone3");
		String addr = request.getParameter("addr");
	
		String email;
		if (!request.getParameter("email2").isEmpty()) {
			email = request.getParameter("email1") + "@" + request.getParameter("email2");
		} else {
			email = request.getParameter("email1") + "@" + request.getParameter("domain-list");
		}

		
		String gender = request.getParameter("gender");
		String[] hobby = request.getParameterValues("hobby");
	
		out.println("<h3> ID : " + id + "</h3>");
		out.println("<h3> NAME : " + name + "</h3>");
		out.println("<h3> SOCIAL ID : " + socialNum + "</h3>");
		out.println("<h3> PW : " + pw + "</h3>");
		out.println("<h3> PHONE : " + phone + "</h3>");
		out.println("<h3> ADRRESS : " + addr + "</h3>");
		out.println("<h3> EMAIL : " + email +  "</h3>");
		out.println("<h3> GENDER : " + gender + "</h3>");
		out.println("<h3> HOBBY : " + Arrays.asList(hobby) + "</h3><br>");
		
	%>
</body>
</html>