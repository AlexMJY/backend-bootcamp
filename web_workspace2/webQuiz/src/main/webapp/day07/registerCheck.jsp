<%@page import="vo.MemberVO"%>
<%@page import="dao.MemberDAO"%>
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
		String gender = request.getParameter("gender");
		String pw = request.getParameter("pw");
		int zipcode = Integer.parseInt(request.getParameter("zipcode")) ;
		String address = request.getParameter("addr");
		
		
		// String phone = request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-" + request.getParameter("phone3");
		// String socialNum = request.getParameter("socialNum1") + "-" + request.getParameter("socialNum2");
	
		/* String email;
		if (!request.getParameter("email2").isEmpty()) {
			email = request.getParameter("email1") + "@" + request.getParameter("email2");
		} else {
			email = request.getParameter("email1") + "@" + request.getParameter("domain-list");
		} */

		
		//String[] hobby = request.getParameterValues("hobby");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = new MemberVO(id, pw, name, gender, zipcode, address);

		dao.insertOne(vo);
		
	%>
</body>
</html>