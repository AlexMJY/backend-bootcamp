<%@page import="kr.co.jhta.web.vo.EmpVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.jhta.web.dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
 table {
 	width: 80%;
 	margin: 50px auto;
 	text-align: center;
 }
 table, td, th {
 	border: 1px solid red;
 	border-collapse: collapse; 	
 }
</style>
<body>
	<%
		// 부서 전체 정보 가져오기
		EmpDAO dao = new EmpDAO();
		ArrayList<EmpVO> list = dao.selectAll();
		
	
	%>
	
	
	<table>
		<tr>
			<th>사원번호</th>
			<th>사원명</th>
			<th>직업</th>
			<th>매니저번호</th>
			<th>취업일</th>
			<th>커미션</th>
			<th>부서번호</th>
		</tr>
		
		<%
			for (EmpVO vo : list) {	
		%>
		<tr>
			<td><%= vo.getEmpno() %></td>
			<td><%= vo.getEname() %></td>
			<td><%= vo.getJob() %></td>
			<td><%= vo.getMgr() %></td>
			<td><%= vo.getHiredate() %></td>
			<td><%= vo.getSal() %></td>
			<td><%= vo.getComm() %></td>
			<td><%= vo.getDeptno() %></td>
			
		</tr>		
		<%
			}
		%>
		
	</table>
</body>
</html>