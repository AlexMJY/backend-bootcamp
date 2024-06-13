package kr.co.jhta.app.dao;

import kr.co.jhta.app.dto.EmpDTO;

import java.sql.*;
import java.util.List;

public class EmpMySQLDAO implements CommonDAO {
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EmpDTO> selectAll() {
        return List.of();
    }

    @Override
    public EmpDTO selectOne(int empno) {
        return null;
    }

    @Override
    public void insertOne(EmpDTO dto) {

    }

    @Override
    public void updateOne(EmpDTO dto) {

    }

    @Override
    public void deleteOne(int deptno) {

    }

    @Override
    public void close() {

    }
}
