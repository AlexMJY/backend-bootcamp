<%@page import="kr.co.jhta.vo.EmpVO"%>
<%@page import="kr.co.jhta.dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		String en = request.getParameter("empno");
		// System.out.println("empno : " + empno);
		
		if (en != null) {
			int empno = Integer.parseInt(en);
			EmpDAO dao = new EmpDAO();
			EmpVO vo = dao.queryEmpOne(empno);
	%>
		<tr>
			<td><%= vo.getEmpno() %></td>
			<td><%= vo.getEname() %></td>
			<td><%= vo.getJob() %></td>
			<td><%= vo.getSal() %></td>
		</tr>
	
	<%
		}
	%>
		</table>
	</div>
</body>
</html>