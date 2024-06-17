package kr.co.jhta.app.dao;

import kr.co.jhta.app.dto.EmpDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpOracleDAO implements CommonDAO {
    // member field
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "scott";
    private static final String PASSWORD = "tiger";

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    StringBuffer sb = new StringBuffer();


    @Override
    public void connect() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EmpDTO> selectAll() {
        sb.setLength(0);
        sb.append("SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp");
        ArrayList<EmpDTO> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                String hiredate = rs.getString("hiredate");
                int sal = rs.getInt("sal");
                int comm = rs.getInt("comm");
                int deptno = rs.getInt("deptno");

                EmpDTO dto = new EmpDTO(empno, ename, job, mgr, hiredate, sal, comm, deptno);
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public EmpDTO selectOne(int empno) {
        sb.setLength(0);
        sb.append("SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp WHERE empno = ?");
        EmpDTO dto = null;
        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, empno);
            rs = pstmt.executeQuery();
            while (rs.next()) {
//                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("job");
                String hiredate = rs.getString("hiredate");
                int sal = rs.getInt("sal");
                int comm = rs.getInt("comm");
                int deptno = rs.getInt("deptno");

                dto = new EmpDTO(empno, ename, job, mgr, hiredate, sal, comm, deptno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public void insertOne(EmpDTO dto) {
        sb.setLength(0);
        sb.append("INSERT INTO emp VALUES(?, ?, ?, ?, ?, ?, ? ,?)");
        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, dto.getEmpno());
            pstmt.setString(2, dto.getEname());
            pstmt.setString(3, dto.getJob());
            pstmt.setInt(4, dto.getMgr());
            pstmt.setString(5, dto.getHiredate());
            pstmt.setInt(6, dto.getSal());
            pstmt.setInt(7, dto.getComm());
            pstmt.setInt(8, dto.getDeptno());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOne(EmpDTO dto) {
        sb.setLength(0);
        sb.append("UPDATE emp SET ename = ?, job = ?, mgr = ?, hiredate = ?, sal = ?, comm = ?, deptno = ? WHERE empno = ?" );
        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, dto.getEname());
            pstmt.setString(2, dto.getJob());
            pstmt.setInt(3, dto.getMgr());
            pstmt.setString(4, dto.getHiredate());
            pstmt.setInt(5, dto.getSal());
            pstmt.setInt(6, dto.getComm());
            pstmt.setInt(7, dto.getDeptno());
            pstmt.setInt(8, dto.getEmpno());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deleteOne(int empno) {
        sb.setLength(0);
        sb.append("DELETE FROM emp WHERE empno = ?");
        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, empno);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (rs != null) { rs.close(); }
            if (pstmt != null) { pstmt.close(); }
            if (conn != null) { conn.close(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
