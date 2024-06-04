<%@page import="kr.co.jhta.web.vo.EmpVO"%>
<%@page import="kr.co.jhta.web.dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	
	String empno = request.getParameter("txt");
	
	if (empno != null ) {
		EmpDAO dao = new EmpDAO();
		EmpVO vo = dao.selectOne(Integer.parseInt(empno));
		
		if (vo != null) {
			out.println("<h6> 사원번호 : " + vo.getEmpno() + "</h6>");
			out.println("<h6> 사원명 : " + vo.getEname() + "</h6>");
			out.println("<h6> 급여 : " + vo.getSal() + "</h6>");
			out.println("<h6> 직무 : " + vo.getJob() + "</h6>");
		} else {
			System.out.println("vo가 존재하지 않습니다.");
		}
	}

%>