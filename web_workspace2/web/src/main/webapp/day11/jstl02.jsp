<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl02.jsp</title>
</head>
<body>
	
	<h1><c:out value="${num1 + num2}"/></h1>
	
	<h1>${num1 + num2}</h1>
	
	<!-- 두 수 중 큰 값 출력 -->
	<h1>${num1 > num2 ? num1 : num2}</h1>
	
	<c:if test="${num1 > num2 }">
		<h1>num1 bigger</h1>
	</c:if>
</body>
</html>