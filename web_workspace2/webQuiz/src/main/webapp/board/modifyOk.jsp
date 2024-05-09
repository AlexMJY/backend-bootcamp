<%@page import="kr.co.jhta.web.vo.BoardVO"%>
<%@page import="kr.co.jhta.web.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String contents = request.getParameter("contents");
	String b = request.getParameter("b");
	
	
	if (b != null) {
		int bno = Integer.parseInt(b);
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.getOne(bno);
		
		vo.setWriter(writer);
		vo.setTitle(title);
		vo.setContents(contents);
		
		dao = new BoardDAO();
		dao.updateOne(vo);
	}
	response.sendRedirect("list.jsp");

%>