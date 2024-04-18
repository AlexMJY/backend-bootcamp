package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DB에 연결하는 객체

public class DeptDao {
	// 1. 멤버필드 (클래스 변수)
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "scott";
	String password = "tiger";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 생성자  (2. 드라이버 로딩 3. Connection)
	public DeptDao() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("conn : " + conn);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	}  // constructor end
	
	// 1건 전체 데이터 가져오기 메서드 selectAll()
//	public DeptVO selectAll(int deptno) {
//		// 부서번호로 부서정보 가져오기
//		// 부서정보 : 부서번호, 부서명, 부서위치
//		
//	}
//	
	public static void main(String[] args) {
		DeptDao dao = new DeptDao();
	}
}

