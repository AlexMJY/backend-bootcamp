<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="vo.ProductVO"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		
		String saveDir = application.getRealPath("/upload");
		


		int maxFileSize = 1024 * 1024 * 30;
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxFileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		
		
		String pname = mr.getParameter("pname");
		String pfile1 = "../upload/" + mr.getOriginalFileName("pfile1");
		String pfile2 = "../upload/" + mr.getOriginalFileName("pfile2");
		int qty =  Integer.parseInt(mr.getParameter("pcount"));
		int price = Integer.parseInt(mr.getParameter("pprice"));
		int sale = Integer.parseInt(mr.getParameter("psale"));
		String contents = mr.getParameter("pcontent");
		
		ProductDAO pdao = new ProductDAO();
		ProductVO vo = new ProductVO(pname, price, sale, contents, qty, pfile1, pfile2);
		
		pdao.insertOne(vo);
		response.sendRedirect("productReg.jsp");
		
	%>