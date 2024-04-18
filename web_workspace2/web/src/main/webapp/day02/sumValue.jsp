<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  
		스크립트 요소
		1. 스크립트릿 scriptlet
		2. 표현식 expression
		3. 선언 declare
	 -->


	 
	 
	 <% 
	 	// 실행부 : _jspService() 안쪽으로
	 	int k = 0; // _jspServeice() 지역변수
	 	for (int i = 1; i <= 100; i++) {
	 		sum += i;
	 	}
	 %>
	 
	 	 <%!
	 	// 변수 선언부
	 	
	 	// 선언부는 서블릿 변환 시 멤버필드(전역변수)가 되기 때문에 아래에 선언되도 상관 없음 
	 	int sum = 0;	 
	 %>
	 
	 
	 <h1>1부터 100까지 누적합계 : <%= sum %> </h1>
</body>
</html>