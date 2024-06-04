<%@page import="java.sql.SQLException"%>
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
<title>sqldb.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</head>
<body>
	<div class="container">
					<table class="table">
						<thead>
							<tr>
								<th>Deptno</th>
								<th>Dname</th>
								<th>Loc</th>
							</tr>
						</thead>
						<tbody>
							
	<%
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/sqldb";
		// String url = "jdbc:mysql://192.168.99.30:3306/sqldb";
		String username = "smith";
		String password = "oracle";
		
		// 밑단 finally에서 불러서 close()할 수 있게 try문 밖에서 선언
		Connection conn = null;   
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		
		// 2. 드라이버 로딩
		try {
			Class.forName(driver);
			// 3. Connection
			conn =  DriverManager.getConnection(url, username, password);
			System.out.println("conn : " + conn);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 4. sql 문장
		// String sql = "SELECT * FROM DEPT";  String은 불변하기 때문에 명령어를 넣기 부적합
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM dept");
		
		// 5. sql 문장 객체
		try {
			pstmt= conn.prepareStatement(sb.toString());
			
			// 6. 실행 (ResultSet)
			rs =  pstmt.executeQuery();

			// 7. 레코드별 로직 처리
			while (rs.next()) {
				// rs.next();  // 전달받은 ResultSet의 첫 번째 레코드로 이동
				int deptno = rs.getInt("deptno");
				String dname =  rs.getString("dname");
				String loc = rs.getString("loc");
				System.out.println(deptno + " : " + dname + " : " + loc);
				
				out.println("<tr>");
				out.println("<td>" + deptno + "</td>");
				out.println("<td>" + dname + "</td>");
				out.println("<td>" + loc + "</td>");
				out.println("</tr>");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 8. 자원 반납
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	%>
		
			</tbody>
					</table>
	</div>
</body>
</html>