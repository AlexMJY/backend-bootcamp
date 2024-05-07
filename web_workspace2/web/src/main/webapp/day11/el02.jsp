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
		// String no = request.getParameter("no");
	%>
	<%-- <h3>전달받은 값 : <%= no %></h3> --%>
	<h3>전달받은 값 : ${param.no}</h3>
	
	<%
		Object id = request.getParameter("id");
	%>
	<h3>전달받은 값2 : <%= id %></h3> <!-- parameter 없으면 null 반환 -->
	<h3>전달받은 값3 : ${param.id}</h3> <!-- parameter 없어도 null 반환 안함 -->
	<h3>간단한 연산 가능4 : ${100 + 200}</h3> 
	
</body>
</html>