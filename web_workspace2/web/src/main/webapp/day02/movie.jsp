<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- page directive-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	img {
		width: 300px;
		height: 300px;
	}
</style>
</head>
<body>
	<h1>영화 포스터</h1>
	
	<%
		/* scriptlet */
		for (int i = 1; i <= 5; i++) {		
	%>
		<img src="../images/movie_image0<%= i %>.jpg" alt="" />
	<%
		}
	%>
	
	<!-- 
	<img src="../images/movie_image01.jpg" alt="" />
	<img src="../images/movie_image02.jpg" alt="" />
	<img src="../images/movie_image03.jpg" alt="" />
	<img src="../images/movie_image04.jpg" alt="" />
	<img src="../images/movie_image05.jpg" alt="" />
	 -->
	
</body>
</html>