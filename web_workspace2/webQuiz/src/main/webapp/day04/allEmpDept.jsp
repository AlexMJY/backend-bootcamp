<%@page import="vo.EmpDeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DeptDAO"%>
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
		DeptDAO dao = new DeptDAO();
		ArrayList<EmpDeptVO> list = dao.selectEmpDept();
	%>
	
	<table>
		<tr>
			<th>deptno</th>
			<th>dname</th>
			<th>loc</th>
			<th>empno</th>
			<th>ename</th>
			<th>job</th>
			<th>mgr</th>
			<th>hiredate</th>
			<th>sal</th>
			<th>comm</th>
		</tr>
		
		<%
			for (EmpDeptVO vo : list) {
		%>
		<tr>
			<td><%= vo.getDeptno() %></td>
			<td><%= vo.getDname() %></td>
			<td><%= vo.getLoc() %></td>
			<td><%= vo.getEmpno() %></td>
			<td><%= vo.getEname() %></td>
			<td><%= vo.getJob() %></td>
			<td><%= vo.getMgr() %></td>
			<td><%= vo.getHiredate() %></td>
			<td><%= vo.getSal() %></td>
			<td><%= vo.getComm() %></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>