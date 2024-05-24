<%@page import="org.json.simple.JSONObject"%>
<%@page import="kr.co.jhta.web.vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.jhta.web.dao.MemberDAO"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
	<%
	// 부서 테이블의 데이터를 가져와서 JSON 형식으로 변환해서 화면 출력
		// { deptno : 10, dname : ACCOUNTING, loc : DALLAS }
		
			JSONArray memberArray = new JSONArray();
		
			MemberDAO dao = new MemberDAO();
			ArrayList<MemberDTO> list = dao.selectAll();

			
			for (MemberDTO vo : list) {
		JSONObject member = new JSONObject();  // map type (key, value)
		
		member.put("id", vo.getId());
		member.put("pw", vo.getPw());
		member.put("name", vo.getName());
		member.put("gender", vo.getGender());
		member.put("motive", vo.getMotive());
		
		// JSON 배열에 객체 json 객체를 담는다
		memberArray.add(member);
			}
			
			out.println(memberArray.toJSONString());
	%>