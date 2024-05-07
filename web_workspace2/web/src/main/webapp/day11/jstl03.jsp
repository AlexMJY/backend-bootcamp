<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl03.jsp</title>
</head>
<body>
	<form action="jstl04.jsp">
		<input type="text" name="no1" />
		<input type="text" name="no2" />
		<input type="submit"  value="큰 값 출력" />
		
		<c:set var="num1" value="no1" scope="request"></c:set>
		<c:set var="num2" value="no2" scope="request"></c:set>
		
		<!-- 큰 값 : EL 출력 -->
	</form>
</body>
</html>