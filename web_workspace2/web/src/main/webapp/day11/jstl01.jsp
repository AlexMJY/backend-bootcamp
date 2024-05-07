<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl01.jsp</title>
</head>
<body>
<!-- 
	태그로 자바의 문법적 기능 수행 가능
	JSTL (Java Standard Tag Library) 
	https://mvnrepository.com/artifact/org.glassfish.web/jakarta.servlet.jsp.jstl/3.0.0
	
	1. 간단한 프로그램 로직 구사
	2. for문 if문 태그 지원
	3. 날짜, 시간, 숫자 형식 
	4. 데이터베이스 입출력
	5. xml 문서 처리
	6. 문자열 처리 함수 호출
 -->
	
	<!-- request.setAttribute("num1", "300") 과 같은 내용-->
	<c:set var="num1" value="300" scope="request"></c:set>
	<c:set var="num2" value="200" scope="request"></c:set>
	
	<jsp:forward page="jstl02.jsp"></jsp:forward>
	
	<!-- 
		jstl02.jsp에서 두 수의 합을 h1 태그로 출력
	 -->
</body>
</html>