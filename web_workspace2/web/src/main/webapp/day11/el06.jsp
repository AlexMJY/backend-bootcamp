<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el06.jsp</title>
</head>
<body>
	<%
		String i1 = (String) pageContext.getAttribute("id1");
		String i2 = (String) request.getAttribute("id2");
		String i3 = (String) session.getAttribute("id3");
		String i4 = (String) application.getAttribute("id4");
	%>
	<h3>id1 : <%= i1 %></h3>
	<h3>id2 : <%= i2 %></h3>
	<h3>id3 : <%= i3 %></h3>
	<h3>id4 : <%= i4 %></h3>
	
	
	<h3>id1 : ${id1}</h3>
	<h3>id2 : ${id2}</h3>
	<h3>id3 : ${sessionScope.id3}</h3>
	<h3>id4 : ${applicationScope.id4}</h3>
</body>
</html>