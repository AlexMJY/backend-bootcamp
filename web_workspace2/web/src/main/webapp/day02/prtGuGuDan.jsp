<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- <form action="showGuGuDan.jsp" method="get"> -->
	<!--  action값을 지정하지 않으면 자기 자신에게 리턴 -->
	<form action="" method="get">
		<input type="text" name="dan" id="" />
		<input type="submit" value="출력" />
	</form>
	
	
	<%		
		String d = request.getParameter("dan");
	
		if (d != null) {
			int dan = Integer.parseInt(d);
			for (int i = 1; i <= 9; i++) {
				out.println("<h2>" +  dan + " * " + i + " = " + dan * i + "</h2>");
			}			
		} 
	%>
</body>
</html>