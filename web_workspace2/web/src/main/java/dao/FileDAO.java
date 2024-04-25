package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.FileVO;

public class FileDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "scott";
	String password = "tiger";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	
	public FileDAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("conn : " + conn);
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
			System.out.println("드라이버를 찾을 수 없습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 연결 실패");
		}
	} // constructor end
	
	public ArrayList<FileVO> selectAll() {
		sb.setLength(0);
		sb.append("SELECT no, title, writer, contents FROM fileuploadtest");
		ArrayList<FileVO> list = new ArrayList<FileVO>();
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String contents = rs.getString("contents");
//				String filename = rs.getString("filename");
				
				FileVO vo = new FileVO(no, title, writer, contents);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	} // selectAll() end
	
	public void addOne(int fileNumber, String title, String writer, String contents) {
		sb.setLength(0);
		sb.append("INSERT INTO fileuploadtest (no, title, writer, contents) VALUES (?, ?, ?, ?)");
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, fileNumber);
			pstmt.setString(2, title);
			pstmt.setString(3, writer);
			pstmt.setString(4, contents);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // addOne() end
	
	
	public int getNumber() {
		sb.setLength(0);
		sb.append("SELECT fileup_no_seq.nextval x FROM dual");
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("x");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	} // getNumber() end
	
	
	
	public void close() {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // close() end
}
