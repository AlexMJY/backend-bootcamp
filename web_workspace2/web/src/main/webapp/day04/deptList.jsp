<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DeptVO"%>
<%@page import="dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deptList</title>
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
 .deptno {
 	width: 20%;
 }
 .dname {
 	width: 45%;
 }
 .loc {
  width: 45%;
 }
</style>

<body>
	<%
		// 부서 전체 정보 가져오기
		DeptDAO dao = new DeptDAO();
		ArrayList<DeptVO> list = dao.selectAll();
		
/* 		out.println(list);
		out.println("<br><br>");
		
		out.println(list.toString());
		out.println("<br><br>");
		
		out.println(Arrays.asList(list)); */
	
	%>
	
	<!-- table>(tr>th*3)+(tr>td*3)*4 -->
	<table>
		<tr>
			<th>부서번호</th>
			<th>부서명</th>
			<th>부서위치</th>
		</tr>
		
		<%
			for (DeptVO vo : list) {	
		%>
		<tr>
			<td class="deptno"><%= vo.getDeptno() %></td>
			<td class="dname"><%= vo.getDname() %></td>
			<td class="loc"><%= vo.getLoc() %></td>
		</tr>		
		<%
			}
		%>
		
	</table>
</body>
</html>