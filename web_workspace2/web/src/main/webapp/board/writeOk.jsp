<%@page import="dao.BoardDAO"%>
<%@page import="vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String contents = request.getParameter("contents");
	String ip = request.getRemoteAddr();

	BoardDAO dao = new BoardDAO();
	BoardVO vo = new BoardVO();

	vo.setWriter(writer);
	vo.setTitle(title);
	vo.setContents(contents);
	vo.setIp(ip);
	
	dao.addOne(vo);
	response.sendRedirect("list.jsp");
	
	
	
%>