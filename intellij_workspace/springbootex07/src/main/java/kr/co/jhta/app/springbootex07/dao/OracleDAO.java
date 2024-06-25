package kr.co.jhta.app.springbootex07.dao;


import kr.co.jhta.app.springbootex07.dto.DeptDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OracleDAO {
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String user = "scott";
    String password = "tiger";

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    StringBuffer sb = new StringBuffer();

    public OracleDAO() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("conn : " + conn);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } // constructor end


    public boolean findByIdAndPw(String id, String pw) {
        sb.setLength(0);
        sb.append("SELECT id, pw FROM member WHERE id = ? and pw = ?");
        boolean result = false;
        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();
            result= rs.next(); // 다음 값이 존재하면 true, 없으면 false
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    } // findByIdAndPw() end


    public void insert(DeptDTO dto) {
        sb.setLength(0);
        sb.append("INSERT INTO dept(deptno, dname, loc) VALUES(?, ?, ?)");
        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, dto.getDeptno());
            pstmt.setString(2, dto.getDname());
            pstmt.setString(3, dto.getLoc());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } // insert() end


    public List<DeptDTO> selectDeptList() {
        sb.setLength(0);
        sb.append("SELECT deptno, dname, loc FROM dept");
        List<DeptDTO> list = new ArrayList<DeptDTO>();
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
            throw new RuntimeException(e);
        }
        return list;
    } // deptList() end

    public DeptDTO selectOne(int deptno) {
        sb.setLength(0);
        sb.append("SELECT deptno, dname, loc FROM dept WHERE deptno = ?");
        DeptDTO dto = null;
        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, deptno);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                dto = new DeptDTO(deptno, dname, loc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }



}
