<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>hello.jsp</h3>
	
	<!-- EL 방식 : jsp 내에 자바 코드 삽입 최소화 -->
	<h1>${msg}</h1>	
	
	<%-- <%
		Object obj = request.getAttribute("msg");
		String msg = (String) obj;
		out.println("<h2>" + msg  + "</msg>");
	%> --%>
</body>
</html>