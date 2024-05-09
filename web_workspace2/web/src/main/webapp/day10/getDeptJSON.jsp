<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="kr.co.jhta.web.vo.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.jhta.web.dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
	<%
	// 부서 테이블의 데이터를 가져와서 JSON 형식으로 변환해서 화면 출력
	// { deptno : 10, dname : ACCOUNTING, loc : DALLAS }
	
		JSONArray deptArray = new JSONArray();
	
		DeptDAO dao = new DeptDAO();
		ArrayList<DeptVO> list = dao.selectAll();
		
		for (DeptVO vo : list) {
			JSONObject dept = new JSONObject();  // map type (key, value)
			dept.put("deptno", vo.getDeptno());
			dept.put("dname", vo.getDname());
			dept.put("loc", vo.getLoc());
			
			// JSON 배열에 객체 json 객체를 담는다
			deptArray.add(dept);
		}
		
		out.println(deptArray.toJSONString());
	%>
	
