<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%! 
		int result;
		int nn1;
		int nn2;
	%>
	
	<%
		String n1 = request.getParameter("num1");
		String n2 = request.getParameter("num2");
		
		if( n1 != null &&  n2 != null) {
			nn1 = Integer.parseInt(n1);
			nn2 = Integer.parseInt(n2);
			result = nn1 + nn2;
		}
	
	%>
	
	<form action="">
		<input type="text" name="num1" id="" / value="<%= nn1 %>">
		+
		<input type="text" name="num2" id="" / value="<%= nn2 %>">
		
		<input type="submit" value="=" />
		<input type="text" name="result" id=""  value="<%= result %>" />
	</form>
	

	
</body>
</html>