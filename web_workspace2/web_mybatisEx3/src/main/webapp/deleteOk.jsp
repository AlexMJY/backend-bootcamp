<%@page import="kr.co.jhta.web.dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%
		String d = request.getParameter("deptno");
		
		if (d != null) {
			int deptno = Integer.parseInt(d);
			DeptDAO dao = new DeptDAO();
			dao.deleteDeptOne(deptno);
			response.sendRedirect("list.jsp");
		}
	%>
