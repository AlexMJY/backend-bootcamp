<%@page import="kr.co.jhta.web.vo.DeptVO"%>
<%@page import="kr.co.jhta.web.dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String d = request.getParameter("deptno");
	String dname = request.getParameter("dname");
	String loc = request.getParameter("loc");
	
	if (d != null && dname != null && loc != null) {
		int deptno = Integer.parseInt(d);
		
		DeptDAO dao = new DeptDAO();
		DeptVO vo = new DeptVO();
		vo.setDeptno(deptno);
		vo.setDname(dname);
		vo.setLoc(loc);
		
		dao.updateDeptOne(vo);
		response.sendRedirect("list.jsp");
	}
%>