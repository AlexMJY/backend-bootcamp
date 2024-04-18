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
		for (int i = 1; i <= 9; i++) {
		
		// 표현식 (Expression)
	%>
			<!-- HTML 주석 (브라우저 페이지 소스에 노출됨)/ id: aaa -->
			<%-- JSP 주석 (브라우저 페이지 소스에 노출 안됨)/ pw : bbb --%>
			<h1> 3 *  <%= i %> =  <%= i * 3 %> </h1>
	<%
		}
	%>
					
	
	
	
	
</body>
</html>