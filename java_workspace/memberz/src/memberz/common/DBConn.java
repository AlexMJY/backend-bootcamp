package memberz.common;


import java.sql.*;

public class DBConn {
    //1. 공유 Connection 객체 con을 클래스 외부에서 접근할 수 없도록 선언
    //2. 기본 생성자를 외부에서 접근할 수 없도록 정의
    //3. con 객체가 null 아닌 경우 반환하고, 그렇지 않으면
    //   생성하여 반환하는 공유 메서드 getConnection 정의
    //4. con 객체가 null 아닌 경우 닫기 처리하는 공유 메서드 close 정의



    private static Connection con; // 1

    private DBConn() {} // 2

    public static Connection getConnection() { // 3
        if (con == null) {
            String driver = "oracle.jdbc.OracleDriver";
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "scott";
            String password = "tiger";

            try {
                Class.forName(driver);
//                System.out.println("driver ok");
                con = DriverManager.getConnection(url, username, password);
//                System.out.println("connection ok");


//                con.setAutoCommit(false); // 자동 커밋 X
//                con.commit();             // DB에 반영하기
//                con.rollback();           // DB에 "  " 되돌리기
//                con.setAutoCommit(true);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    public static void close() {
        try {
            if (con != null) con.close();
//            System.out.println("con closed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //PreparedStatement 객체를 매개변수로 받아서
    //null이 아닌 경우 닫기 처리를 하는 공유 메서드 close를 오버로딩
    public static void close(PreparedStatement pstmt) {
        try {
            if (pstmt != null)  pstmt.close();
//            System.out.println("con closed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Statement 객체를 매개변수로 받아서
    //null이 아닌 경우 닫기 처리를 하는 공유 메서드 close를 오버로딩
    public static void close(Statement stmt) {
        try {
            if (stmt != null)  stmt.close();
//            System.out.println("con closed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //PreparedStatement와 ResultSet 객체를 받아서 null이 아닌 경우 닫기 처리
    // clost 오버로딩
    public static void close(PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
//            System.out.println("rs closed");
            close(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}