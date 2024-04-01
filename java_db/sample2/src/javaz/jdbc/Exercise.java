package javaz.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Exercise { // t_emp 테이블에 데이터를 추가, 변경, 삭제, 조회, 목록
    private String query;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private boolean result;

    public boolean insert(ExerciseVO evo) {
        query = "INSERT INTO t_emp VALUES (?, ?, ?, ?, SYSDATE)";
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setInt(1, evo.getEmpno());
            pstmt.setString(2, evo.getEname());
            pstmt.setInt(3, evo.getDeptno());
            pstmt.setInt(4, evo.getSal());

            result =  pstmt.executeUpdate() == 1;
            DBConn.close(pstmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean update(ExerciseVO evo) {  // deptno, sal only
        query = "UPDATE t_emp SET deptno = ?, sal = ? WHERE emptno = ?";
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setInt(1, evo.getDeptno());
            pstmt.setInt(2, evo.getSal());
            pstmt.setInt(3, evo.getEmpno());

            result =  pstmt.executeUpdate() == 1;
            DBConn.close(pstmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean delete(int empno) {
        query = "DELETE FROM t_emp WHERE empno = ?";
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setInt(1, empno);

            result =  pstmt.executeUpdate() == 1;
            DBConn.close(pstmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ExerciseVO select(int empno) {
        query = "SELECT * FROM t_emp WHERE empno = " + empno;
        ExerciseVO evo = null;
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs.next()) {  // 결과값이 있으면 ExerciseVO에서 가져옴
                evo = new ExerciseVO();
                evo.setEmpno(rs.getInt("empno"));
                evo.setEname(rs.getString("ename"));
                evo.setDeptno(rs.getInt("deptno"));
                evo.setSal(rs.getInt("sal"));
                evo.setHireDate(rs.getDate("hireDate"));
            }
            DBConn.close(pstmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 쿼리 실행 및 실행 결과 받기
        return evo;
    }

    public List<ExerciseVO> select() {
        query = "SELECT * FROM t_emp ORDER BY empno";
        List<ExerciseVO> evoList = new ArrayList<ExerciseVO>();

        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {  // 결과값이 있으면 ExerciseVO에서 가져옴
                ExerciseVO evo = new ExerciseVO();
                evo = new ExerciseVO();
                evo.setEmpno(rs.getInt("empno"));
                evo.setEname(rs.getString("ename"));
                evo.setDeptno(rs.getInt("deptno"));
                evo.setSal(rs.getInt("sal"));
                evo.setHireDate(rs.getDate("hireDate"));
                evoList.add(evo);
            }
            DBConn.close(pstmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 쿼리 실행 및 실행 결과 받기
        return evoList;
    }

    public static void main(String[] args) {
        Exercise e = new Exercise();
        ExerciseVO evo = new ExerciseVO();


        //1. t_emp 테이블에 다음 레코드 추가
        // empno: 9992, ename: Goo2, deptno: 99, sal: 9000, hiredate: today

//        evo.setEmpno(9002);
//        evo.setEname("Goo2");
//        evo.setDeptno(99);
//        evo.setSal(9000);
//        if (e.insert(evo) == true) {
//            System.out.println(">> 레코드 추가 성공");
//        } else {
//            System.out.println(">> 레코드 추가 실패");
//        }



        //2. t_emp 테이블의 전체 목록 조회

//        List<ExerciseVO> evoList = e.select();
//        if (!evoList.isEmpty()) {
//            System.out.println("사원번호\t사원이름\t부서번호\t급여\t입사일자");
//            System.out.println("-----------------------------------------");
//            evoList.forEach( ev -> {
//                System.out.println(ev.getEmpno() + "\t" +
//                                   ev.getEname() + "\t" +
//                                   ev.getDeptno() + "\t" +
//                                   ev.getSal() + "\t" +
//                                   ev.getHireDate());
//            });
//        } else {
//            System.out.println(">> 등록된 레코드가 없습니다.");
//        }


        //3. 1에서 추가한 레코드의 sal을 900으로, deptno는 90으로 변경

//        evo = new ExerciseVO();
//        evo.setEmpno(9002);
//        evo.setDeptno(99);
//        evo.setSal(9000);
//        if (e.insert(evo) == true) {
//            System.out.println(">> 레코드 추가 성공");
//        } else {
//            System.out.println(">> 레코드 추가 실패");
//        }

        //4. t_emp  테이블의 empno가 9002인 레코드 조회

//        evo = e.select(9002);
//        if (evo != null) {
//            System.out.println("----- 사원 정보 -----");
//            System.out.println("사원번호 : " + evo.getEname());
//            System.out.println("사원이름 : " + evo.getEmpno());
//            System.out.println("부서번호 : " + evo.getDeptno());
//            System.out.println("급여 : " + evo.getSal());
//            System.out.println("입사일자 : " + evo.getHireDate());
//        } else {
//            System.out.println(">> 레코드 조회 실패");
//        }


        //5. t_emp  테이블의 empno가 9002인 레코드 삭제

//        if (e.delete(9002) == true) {
//            System.out.println(">> 레코드 삭제 성공");
//        } else {
//            System.out.println(">> 레코드 삭제 실패");
//        }

    }
}
