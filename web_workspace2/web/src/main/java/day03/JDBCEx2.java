package day03;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DAO (Data Access Object)


public class JDBCEx2 {
	public static void main(String[] args) {
		// 목표 : 사원정보를 출력, 부서번호 10인 사원의 사번, 이름, 급여, 부서번호
		
		// 1. 환경변수
		// 2. 드라이버 로딩
		// 3. Connection
		// 4. sql 문장
		// 5. sql 문장 객체
		// 6. 실행 (ResultSet)
		// 7. 레코드별 로직 처리
		// 8. 자원 반납
		
		// 1
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "scott";
		String password = "tiger";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		// 2
		try {
			Class.forName(driver);
			// 3
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("conn : " + conn);
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} 
		
		// 4
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT EMPNO, ENAME, SAL, DEPTNO, HIREDATE FROM EMP WHERE DEPTNO=10");
		
		// 5
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			// 6
			rs = pstmt.executeQuery();
			
			// 7
			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				int sal = rs.getInt("sal");
				int deptno = rs.getInt("deptno");
				Date hireDate = rs.getDate("hiredate");
				System.out.println(empno + " : " + ename + " : " + sal + " : " + deptno + " : " + hireDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 8
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
















