package javaz.jdbc;


// JDBC ; Java DataBase Connectivity
// 자바 어플리케이션과 데이터베이스를 연결하는 라이브러리
// 자바 프로그램에서 DB에 접근하여 검색 및 저장, 변경, 삭제 등이 가능
// 2계층과 3계층 처리 모델 모두 지원

// 2계층 Two-tier
// 클라이언트/서버 구성
// 자바 어플리케이션이 직접 DB 서버와 연결
// JDBC 드라이버가 데이터소스와 직접 통신
// 어플리케이션 로직은 사용자 인터페이스 내부 또는 서버 DB에 위치

// 3계층 Three-tier
// 어플리케이션 로직이 데이터 및 사용자 인터페이스와 분리되어 중간계층에 위치
// 트랜잭션 처리 모니터, 메시지 서버, 응용 프로그램 서버 등
// 중간계층을 이용하여 사용자의 접근과 비즈니스 데이터 업데이트 제어 가능
// 웹 기반 시스템

import java.sql.*;

public class CRUDTest {
    public static void main(String[] args) {
        String driver = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "scott";
        String password = "tiger";

        try {
            Class.forName(driver);
            
            Connection con = DriverManager.getConnection(url, username, password);

            Statement stmt = con.createStatement();  // 쿼리문에 변수가 없을 때


            //1. t_emp 테이블에 레코드를 추가하는 쿼리문 작성
            // empno: 7777, ename: Chil, deptno: 77, sal: 7000, hiredate: 오늘 sysdate
            String query = "INSERT INTO t_emp (empno, ename, deptno, sal, hiredate) VALUES (7777, 'Chil', 77, 7000, SYSDATE)";

            //2. t_emp 테이블에 레코드를 추가하는 쿼리문 작성
            // empno: 88, ename: Pal, deptno: 88, sal: 8000, hiredate: 어제
            query = "INSERT INTO t_emp (empno, ename, deptno, sal, hiredate) VALUES (88, 'Pal', 88, 8000, SYSDATE - 1)";

            //3. t_emp 테이블에 레코드를 변경하는 쿼리문 작성
            // empno: 88, ename: Pal, deptno: 88, sal: 8000, hiredate: 어제
            query = "UPDATE t_emp SET (empno, deptno, sal) VALUES (88, 77, 7000)";


            //4. t_emp 테이블에 empno가 88인 레코드를 삭제하는 쿼리문 작성
            query = "DELETE FROM t_emp WHERE empno = 88";


            //7. 입력받은 값을 이용하여 레코드 추가 쿼리문 작성
            int empno = 9001;
            String ename = "Goo";
            int deptno = 99;
            int sal = 900;

            query = "INSERT INTO t_emp (empno, ename, deptno, sal, hiredate) VALUES ("
                    + empno + ", '" + ename + "', " + deptno + ", " + sal + ", SYSDATE)";

            query = "INSERT INTO t_emp (empno, ename, deptno, sal, hiredate) " +
                    "VALUES (?, ?, ?, ?, SYSDATE)";  // ? 에 변수 담기
            

            PreparedStatement pstmt = con.prepareCall(query); // 쿼리문에 변수가 있을 때
            pstmt.setInt(1, empno);  // ?와 변수값을 바인딩
            pstmt.setString(2, ename);
            pstmt.setInt(3, deptno);
            pstmt.setInt(4, sal);

//
////            int result = stmt.executeUpdate(query);
//            int result = pstmt.executeUpdate();  // 미리 쿼리를 보냈기 때문에 파라미터로 넣지 않는다
//
//            if (result == 1) {
//                System.out.println("9001 레코드 추가 성공");
//            } else {
//                System.out.println("9001 레코드 추가 실패");
//            }

            //5. t_emp 테이블의 모든 레코드를 조회하는 쿼리문, deptno가 작은 값부터 조회되도록 처리
            query ="SELECT * FROM t_emp ORDER BY deptno ASC";


            // SELECT 쿼리 실행 결과를 rs객체에 저장
            ResultSet rs = stmt.executeQuery(query);
//            System.out.println("사원번호\t사원이름\t부서번호\t급여\t입사일자");
//            System.out.println("-----------------------------------------");
//            while (rs.next()) { // 다음 값이 없을 때까지 반복하여
//                // 각 컬럼의 값을 읽어와서 화면에 표시
//                System.out.println(rs.getInt("empno") + "   " +
//                                   rs.getString("ename") + "   " +
//                                   rs.getInt("deptno") + "   " +
//                                   rs.getInt("sal") + "   " +
//                                   rs.getDate("hiredate"));
//            }

            //6. t_emp empno가 7777인 레코드를 조회하는 쿼리문 작성
            query = "SELECT * FROM t_emp WHERE empno = 7777";
//
//            ResultSet rs = stmt.executeQuery(query);
//            if (rs != null) {
//                rs.next();
//                System.out.println("----- 사원 정보 -----");
//                System.out.println("사원이름 : " + rs.getInt("empno"));
//                System.out.println("사원번호 : " + rs.getString("ename"));
//                System.out.println("부서번호 : " + rs.getInt("deptno"));
//                System.out.println("급여 : " + rs.getInt("sal"));
//                System.out.println("입사일자 : " + rs.getDate("hiredate"));
//            }


            pstmt.close();
            stmt.close();
            con.close();



        } catch (ClassNotFoundException e) {
            System.err.println(">> 드라이버를 찾을 수 없습니다");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println(">> DB 연결 실패");
            e.printStackTrace();
        }


    }
}
