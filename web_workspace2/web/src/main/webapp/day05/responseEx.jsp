<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>responseEx.jsp</title>
</head>
<body>
	<%
		int status = response.getStatus();
		out.println("<h3> 상태 : " + status + "</h3>");
	
	%>
 
 
 	<h2>다른 페이지로 이동</h2>
 	
 	<!-- menu.jsp로 이동 -->
 	
 	<ol>
 		<li>forward</li>
 		<%
 			// 지정한 주소 페이지 보여주기 (url 변경 X)
 			
 			// RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
 			// rd.forward(request, response);
 		%>
 		
 		<li>redirect</li>
 		<%
 			// 지정한 주소로 이동 (url 변경 O)
 			// response.sendRedirect("menu.jsp");
 		%>
 	</ol>
 	
 	<button type="button" class="btn btn-primary active" id="btn" 
	onclick="document.location.href='http://www.naver.com'">test</button>
</body>
</html>