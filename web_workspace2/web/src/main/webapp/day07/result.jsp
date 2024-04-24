<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result.jsp</title>
</head>
<body>
	<%
	
		// upload 디렉토리의 실제 경로를 얻어오기
		String saveDir = application.getRealPath("/upload");
		out.println(saveDir);
		
		/* String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		String file = request.getParameter("filename");
		
		out.println("<h3> title : " + title + "</h3>");
		out.println("<h3> writer : " + writer+ "</h3>");
		out.println("<h3> contents : " + contents + "</h3>");
		out.println("<h3> file : " + file + "</h3>"); */
	%>
</body>
</html>