package kr.co.jhta.app.dao;

import kr.co.jhta.app.dto.DeptDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySQLDAO implements CommonDAO {
	// 멤버변수
	final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	final static String URL = "jdbc:mysql://localhost:3306/xe";
	final static String USERNAME = "scott";
	final static String PASSWORD = "tiger";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	StringBuffer sb = new StringBuffer();

	@Override
	public void connect() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<DeptDTO> selectAll() {
		System.out.println("<selectAll>");
		sb.setLength(0);
		sb.append("SELECT deptno, dname, loc FROM dept");
		ArrayList<DeptDTO> list = new ArrayList<DeptDTO>();
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");

				DeptDTO dto = new DeptDTO(deptno, dname, loc);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public DeptDTO selectOne(int deptno) {
		System.out.println("<selectOne>");
		sb.setLength(0);
		sb.append("SELECT deptno, dname, loc FROM dept WHERE deptno = ?");
		DeptDTO dto = null;

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int dn = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				dto = new DeptDTO(dn, dname, loc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void insertOne(DeptDTO dto) {
		System.out.println("<insertOne>");
		sb.setLength(0);
		sb.append("INSERT INTO dept VALUES (?, ?, ?)");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getDname());
			pstmt.setString(3, dto.getLoc());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOne(DeptDTO dto) {
		System.out.println("<updateOne>");
		sb.setLength(0);
		sb.append("UPDATE dept SET dname = ?, loc = ? WHERE deptno = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getDname());
			pstmt.setString(2, dto.getLoc());
			pstmt.setInt(3, dto.getDeptno());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOne(int deptno) {
		System.out.println("<deleteOne>");
		sb.setLength(0);
		sb.append("DELETE FROM dept WHERE deptno = ?");
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, deptno);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}