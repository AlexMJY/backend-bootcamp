<%@page import="java.util.List"%>
<%@page import="kr.co.jhta.vo.EmpVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.jhta.dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<table class="table table-stripe">
			<tr>
				<th>사원번호</th>
				<th>사원명</th>
				<th>직업</th>
				<th>연봉</th>
			</tr>
			
			<%
				EmpDAO dao = new EmpDAO();
				List<EmpVO> list =  dao.queryAllEmp();
				
				for (EmpVO vo :  list) {
					
			%>
				<tr>
					<td><a href="detail.jsp?empno=<%= vo.getEmpno() %>"><%= vo.getEmpno() %></a></td>
					<td><%= vo.getEname() %></td>
					<td><%= vo.getJob() %></td>
					<td><%= vo.getSal() %></td>
				</tr>
			
			<%
				}
			%>
			<tr>
				<td colspan="3">
					<a href="write.jsp" class="btn btn-primary">사원추가</a>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>