<%@page import="dao.EmpDAO"%>
<%@page import="vo.EmpVO"%>
<%@page import="vo.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// out.println("SCOTT,BLACK,KING");

	EmpDAO dao = new EmpDAO();
	ArrayList<EmpVO> list =  dao.selectAll();
	
	StringBuffer sb = new StringBuffer();
	
/* 	for (int i = 0; i <= list.size(); i++) {
		DeptVO vo = list[i];
		if (list.size)
		sb.append(vo.getDname());
		sb.append(",");
	}
	sb.deleteCharAt(sb.length());
	out.println("sb:" + sb);
	 */
	
		
	// db에서 사원명을 가져와서, 구분자 넣어서 화면 출력
	/* for (int i = 0; i <= list.size(); i++) {
		EmpVO vo = list.get(i);
		if (i <= list.size()) {
			sb.append(vo.getEname());
			sb.append(",");
		} else {
			sb.append(vo.getEname());
		}
	}
	 out.println("sb:" + sb); */
	 
	 for (int i = 0; i < list.size(); i++) {
		 EmpVO vo = list.get(i);
		 out.println(vo.getEname());
		 if (i != list.size() - 1){
			 out.println(",");
		 }
	 }
%>
    
  