<%@page import="vo.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>searchDept.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<script>
	/* function search() {
		let txt = document.getElementById("txt").value;
		if (txt == '') {
			console.log("nothing")			
		} else {
			console.log("exits")
		}
		
	} */

</script>
</head>
<body>
	<form action="">
		<input type="text" name="deptno" id="txt" />
		<input type="submit" value="검색" onclick="search();"/>
	</form>
	
	<%
		String deptno = request.getParameter("deptno");
	
		if (deptno == null) {
			DeptDAO dao = new DeptDAO();
			ArrayList<DeptVO> list = dao.selectAll();
			
			
			for (DeptVO vo : list) {
				
				
			}
		}
	
	
	%>
	
	
	<%-- <%
		
		String deptno = request.getParameter("deptno");
		
		if (deptno == null) {
	%>
			<div class="container">
			<table class="table">
				<tr>
					<td>부서번호</td>
					<td>부서명</td>
					<td>부서위치</td>
				</tr>
				
	<%
		DeptDAO dao = new DeptDAO();
		ArrayList<DeptVO> list = dao.selectAll();
		for (DeptVO vo : list) {
			
		
	%>
			<tr>
				<td><%= vo.getDeptno() %></td>
				<td class="dname"><%= vo.getDname() %></td>
				<td class="loc"><%= vo.getLoc() %></td>
			</tr>
	<% 
		} 
	%>
			</table>
		</div> 
	
						
	<%
		} else {
			
    %>			
		
	
	<%		
		}
	%>
	 --%>
	
	
	
	
	
	
</body>
</html>