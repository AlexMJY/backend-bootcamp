<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
<link rel="stylesheet" href="../css/main_2024_04_23.css" />
</head>
<body>

	<%
		String fname = "";
		String fno = request.getParameter("fno");
		out.println(fno);
		if (fno == null) {
			fname = "news.jsp";
		} else {
			fname = fno + ".jsp";
		}
	%>
	<div id="container">
		<jsp:include page="header.jsp" /> <!-- 단독 태그 -->
		<jsp:include page="nav.jsp" />
		<jsp:include page="<%= fname %>" />
		<jsp:include page="sidebar.jsp" />
		<jsp:include page="footer.jsp" /> 
	</div>
</body>
</html>