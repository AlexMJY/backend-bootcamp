<%@page import="kr.co.jhta.vo.EmpVO"%>
<%@page import="kr.co.jhta.dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String e = request.getParameter("empno");
	String ename = request.getParameter("ename");
	String job = request.getParameter("job");
	String s = request.getParameter("sal");
	
	if (e != null && ename != null && job != null && s != null) {
		int empno = Integer.parseInt(e);
		int sal = Integer.parseInt(s);
		
		EmpDAO dao = new EmpDAO();
		EmpVO vo = new EmpVO();
		
		vo.setEmpno(empno);
		vo.setEname(ename);
		vo.setJob(job);
		vo.setSal(sal);
		
		dao.insertEmpOne(vo);
		response.sendRedirect("list.jsp");
	}
%>
