package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.DeptVO;

// DB에 연결하는 객체

public class DeptDAO {
	// 1. 멤버필드 (클래스 변수)
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "scott";
	String password = "tiger";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	// 생성자  (2. 드라이버 로딩 3. Connection)
	public DeptDAO() {
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
	
	// 1건 조회 
	public DeptVO selectOne(int deptno) {
		// 4 ~ 7
		// 4
//		StringBuffer sb = new StringBuffer();
		sb.setLength(0);
		sb.append("SELECT * FROM dept WHERE deptno = ?");  // ? binding variable
		DeptVO vo = null;
		
		try {
			// 5
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, deptno);  // 첫번째 ?에 값 할당
			
			// 6
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int dno= rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				vo = new DeptVO();
				vo.setDeptno(dno);
				vo.setDname(dname);
				vo.setLoc(loc);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
		
		// 부서번호로 부서정보 가져오기
		// 부서정보 : 부서번호, 부서명, 부서위치
		
	}
	
	// 전체 조회
	public ArrayList<DeptVO> selectAll() {
		sb.setLength(0);  // 문자 길이 0으로 ( 있는 내용 삭제)
		sb.append("SELECT deptno, dname, loc FROM dept");
		ArrayList<DeptVO> list = new ArrayList<DeptVO>();
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				DeptVO vo = new DeptVO(deptno, dname, loc);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	// 데이터 1건 추가
	public void insertOne(DeptVO vo) {
		sb.setLength(0);
		sb.append("INSERT INTO dept ");
		sb.append("VALUES (DEPT_DEPTNO.nextval, ?, ?)");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getDname());
			pstmt.setString(2, vo.getLoc());
			
			int result = pstmt.executeUpdate();
			System.out.println("result : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// 수정
	public void updateOne(DeptVO vo) {
		sb.setLength(0);
		sb.append("UPDATE dept ");
		sb.append("SET dname = ?, loc = ? ");
		sb.append("WHERE deptno = ?");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getDname());
			pstmt.setString(2, vo.getLoc());
			pstmt.setInt(3, vo.getDeptno());
			
			int result = pstmt.executeUpdate();
			System.out.println("수정완료 : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// 삭제
	public void deleteOne(int deptno) {
		sb.setLength(0);
		sb.append("DELETE FROM dept ");
		sb.append("WHERE deptno = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, deptno);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}

