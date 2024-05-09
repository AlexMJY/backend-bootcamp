<%@page import="java.sql.Connection"%>
<%@page import="kr.co.jhta.web.dao.DBConnection"%>
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
		Connection conn = DBConnection.getInstance().getConnection();
		out.println("conn : " + conn);
		
		conn.close();
	%>
</body>
</html>