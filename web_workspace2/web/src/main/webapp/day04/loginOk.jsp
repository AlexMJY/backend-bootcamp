<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		out.println("id : " + id + "<br>");
		out.println("pw : " + pwd + "<br>");
	%>
	
	<%
		// 1. 환경변수
		String driver = "oracle.jdbc.driver.OracleDriver";
		/* String url = "jdbc:oracle:thin:@localhost:1521:xe"; */
		String url = "jdbc:oracle:thin:@192.168.99.32:1521:xe";
		String username = "scott";
		String password = "tiger";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 2. 드라이버 로딩
		Class.forName(driver);
		
		// 3. Connection
		conn = DriverManager.getConnection(url, username, password);
		
		// 4. sql 문장
		StringBuffer sb = new StringBuffer();
		/* sb.append("SELECT id, pw, name FROM member WHERE id = '" + id + "' and pw = '" + pwd + "'"); */
		sb.append("SELECT id, pw, name FROM member WHERE id = ? and pw = ?");
		
		System.out.println("SQL : " + sb.toString());
		
		// 5. sql 문장 객체
		/* Statement stmt = conn.createStatement(); */
		pstmt = conn.prepareStatement(sb.toString());
		
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		
		
		// 6. 실행 (ResultSet)
		rs = pstmt.executeQuery();
		
		// 7. 레코드별 로직 처리
		if (rs.next()) {
			String name = rs.getString("name");
			// 8. 자원 반납
			out.println("<h3> name : " + name + "</h3>");
		} else {
			out.println("<h3> 아이디 또는 패스워드를 확인해주세요 </h3>");
		}
		
		
		
		
		
		
		
	%>
</body>
</html>