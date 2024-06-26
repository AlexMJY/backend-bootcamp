<%@page import="kr.co.jhta.web.vo.DeptVO"%>
<%@page import="kr.co.jhta.web.dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>actionTag02.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<%
	// 부서번호 객체를 하나 생성하고 부서정보 입력
			// 99 AI Pusan
			
			// DeptVO vo = new DeptVO(99, "AI", "BUSAN");
		
			// vo (Value Object), javaBean, DTO (Data Transfer Object)
			// POJO (Plain Old Java Object)
	%>
	
	<!-- DeptVO vo = new DeptVO()와 똑같은 역할 -->
	<jsp:useBean id="vo" class="kr.co.jhta.web.vo.DeptVO" scope="session"></jsp:useBean>
	
	<jsp:setProperty property="deptno" name="vo" value="99" />
	<jsp:setProperty property="dname" name="vo" value="AI" />
	<jsp:setProperty property="loc" name="vo" value="BUSAN" />
	
	<div class="container">
		<h3>actionTag02.jsp</h3>
		<table class="table table-striped">
			<tr>
				<th>부서번호</th>
				<th>부서명</th>
				<th>부서위치</th>
			</tr>
			<tr>
				<td><%= vo.getDeptno() %></td>
				<td><%= vo.getDname() %></td>
				<td><%= vo.getLoc() %></td>
			</tr>
		</table>
	</div>
	
	<jsp:forward page="actionTag03.jsp"></jsp:forward>
</body>
</html>