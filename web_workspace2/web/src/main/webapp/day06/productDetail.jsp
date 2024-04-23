<%@page import="vo.ProductVO"%>
<%@page import="dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	img {
		width: 200px;
		height: 400px;
	}
</style>
</head>
<body>
	<%
		String p = request.getParameter("pno");
		
		if (p != null) {
			ProductDAO dao = new ProductDAO(); // ProductDAO 객체 생성 
			int pno = Integer.parseInt(p); // p라는 문자열 변수의 값을 숫자로 형변환
			ProductVO vo = dao.getOne(pno); // db에서 해당 상품 정보 가져오기
			
			out.println("<h3> 제품명 : " + vo.getPname() + "</h3><br>");
			out.println("");
			
			out.println("<img src='" + vo.getImgfile() + "'/>");
			out.println("<img src='" + vo.getBigfile() + "'/>");
			
		}
	
	%>
</body>
</html>