package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	} // searchUser End
	
	public void addMember(MemberVO vo) {
		sb.setLength(0);
		sb.append("INSERT INTO member(no, id, pw, name, gender, motive) VALUES (MEMBER_NO_SEQ.nextval, ?, ?, ?, ?, ?)");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getMotive());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // addMember End
	
	public MemberVO getOne(String id) {
		sb.setLength(0);
		sb.append("SELECT id, pw, name FROM member WHERE id = ?");
		MemberVO vo = null;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String name = rs.getString("name");
				vo = new MemberVO();
				vo.setId(id);
				vo.setName(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public ArrayList<MemberVO> selectAll() {
		sb.setLength(0);  // 문자 길이 0으로 ( 있는 내용 삭제)
		sb.append("SELECT no, id, pw, name, gender, motive  FROM member");
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("no");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String motive = rs.getString("motive");
				
				MemberVO vo = new MemberVO(no, id, pw, name, gender, motive);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	public void close() {
		try {
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
			if (conn != null) { conn.close(); } 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // close() End
	
	
}
