package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.MemberVO;

public class MemberDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "scott";
	String password = "tiger";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	public MemberDAO() {
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
	}
	
	public MemberVO searchUser(String id, String pw) {
		sb.setLength(0);
		sb.append("SELECT id, pw, name FROM member WHERE id = ? AND pw = ? ");
		
		MemberVO vo = null;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
//				String gender = rs.getString("gender");
//				String motive = rs.getString("motive");
				vo = new MemberVO();
				
				vo.setId(id);
				vo.setPw(pw);
				vo.setName(name);
//				vo.setGender(gender);
//				vo.setMotive(motive);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return vo;	
	}
	
	public void insertOne(MemberVO vo) {
		sb.setLength(0);
		sb.append("INSERT INTO member(no, id, pw, name, gender) VALUES (MEMBER_NO.nextval, ?, ?, ?, ?)");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
