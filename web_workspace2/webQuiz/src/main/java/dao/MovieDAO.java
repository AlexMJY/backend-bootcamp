package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.MovieVO;

public class MovieDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "scott";
	String password = "tiger";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public MovieDAO() {
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
	
	
	public MovieVO selectOne(String mname) {
		sb.setLength(0);
		sb.append("SELECT mname, mpath FROM movie WHERE mname = ?");  
		MovieVO vo = null;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, mname); 
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String path = rs.getString("mpath");
				
				vo = new MovieVO();
				vo.setMname(mname);
				vo.setMpath(path);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	} // selectOne End
	
	
	public void insertOne(MovieVO vo) {
		sb.setLength(0);
		sb.append("INSERT INTO movie ");
		sb.append("VALUES (?, ?)");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getMname());
			pstmt.setString(2, vo.getMpath());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // insertOne End
}
