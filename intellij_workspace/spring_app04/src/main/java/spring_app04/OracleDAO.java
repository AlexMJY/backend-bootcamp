package spring_app04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleDAO implements CommonDAO {
    // 멤버변수
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String username = "scott";
    String password = "tiger";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sb = new StringBuffer();

    // connect()
    @Override
    public Connection connect() {

        try {
            Class.forName(driver);  // try catch 잡아야됨
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }

    // 전체조회
    @Override
    public List<DeptDTO> selectAll() {
        System.out.println("전체 조회");
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
}
