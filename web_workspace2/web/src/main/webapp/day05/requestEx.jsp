<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>requestEx.jsp</title>
</head>
<body>
	<%
		// .jsp ==> _jsp.java (_jspService(HttpServletReuquest request, HttpServletResponse response)) ==> _jsp.class
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// GET, POST
		String method = request.getMethod();
		out.println("<h2> method : " + method + "</h2>");

		String query = request.getQueryString(); // GET 방식에서 가져올 수 있음, url에서 ? 이후의 것들 가져옴
		out.println("<h2> getQueryString : " + query  + "</h2>");
		
		String ip = request.getRemoteAddr();
		out.println("<h2> ip : " + ip  + "</h2>");
		
	%>
</body>
</html>