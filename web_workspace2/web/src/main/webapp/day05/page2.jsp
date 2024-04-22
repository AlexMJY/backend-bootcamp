<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>page2.jsp</h2>
	<%
		Object obj1 = pageContext.getAttribute("id1");
		Object obj2 = request.getAttribute("id2");
		Object obj3 = session.getAttribute("id3");
		Object obj4 = application.getAttribute("id4");
		
		out.println("obj1 : " + obj1 + "<br>");
		out.println("obj2 : " + obj2 + "<br>");
		out.println("obj3 : " + obj3 + "<br>");
		out.println("obj4 : " + obj4 + "<br>");
		
		out.println("<br><br><br>");
	
		
	
	%>
</body>
</html>