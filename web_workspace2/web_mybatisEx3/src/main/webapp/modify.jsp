<%@page import="kr.co.jhta.web.vo.DeptVO"%>
<%@page import="kr.co.jhta.web.dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<form action="modifyOk.jsp">
			<table class="table table-hover">
				<%
					String d = request.getParameter("deptno");
					
					if (d != null) {
						int deptno = Integer.parseInt(d);
						
						DeptDAO dao = new DeptDAO();
						DeptVO vo = dao.queryDeptOne(deptno);
					
				%>
				<tr>
					<th>부서명</th>
					<td>
						<input type="text" name="dname" id="dname" class="form-control" value="<%= vo.getDname() %>" />
						<input type="hidden" name="deptno" value="<%= vo.getDeptno() %>"/>
					</td>
					
				</tr>
				<tr>
					<th>지역</th>
					<td><input type="text" name="loc" id="loc" class="form-control" value="<%= vo.getLoc() %>"/></td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input type="submit" value="수정완료" class="btn btn-success" />
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</form>
	</div>
	
</body>
</html>