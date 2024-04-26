<%@page import="vo.ProductVO"%>
<%@page import="dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	String txt = request.getParameter("txt");

	if (txt != null) {
		ProductDAO dao = new ProductDAO();
		ProductVO vo = dao.getImg(txt);
		// out.println("<img src='" + vo.getImgfile() + "'/>");
%>

	<img src="<%= vo.getImgfile() %>" alt=뽀로로 />

<% 
	}
%>

	