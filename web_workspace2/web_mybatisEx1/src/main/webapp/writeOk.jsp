<%@page import="kr.co.jhta.vo.DeptVO"%>
<%@page import="kr.co.jhta.dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String dname = request.getParameter("dname");
	String loc = request.getParameter("loc");
	
	if (dname != null && loc != null) {
		DeptVO vo = new DeptVO(1, dname, loc);
		
		DeptDAO dao = new DeptDAO();
		dao.addOne(vo);
		
		response.sendRedirect("list.jsp");	
	}
%>