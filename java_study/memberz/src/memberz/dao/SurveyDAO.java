package memberz.dao;

import memberz.common.DBConn;
import memberz.vo.SurveyAttendVO;
import memberz.vo.SurveyVO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyDAO {
    private String query;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private boolean result;
    private SurveyVO svo;

    // 설문 목록 가져오기
    public List<SurveyVO> selectSurvey() {
        query = "SELECT * FROM t_survey WHERE endDate > SYSDATE AND startDate <= SYSDATE";
        List<SurveyVO> surveyArr = new ArrayList<>();

        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                svo = new SurveyVO();
                svo.setSno(rs.getInt("sno"));
                svo.setTitle(rs.getString("title"));
                svo.setNum1(rs.getString("num1"));
                svo.setNum2(rs.getString("num2"));
                svo.setStartDate(rs.getString("startDate"));
                svo.setEndDate(rs.getString("endDate"));
                svo.setNum1Cnt(rs.getInt("num1Cnt"));
                svo.setNum2Cnt(rs.getInt("num2Cnt"));
                surveyArr.add(svo);
            }
            DBConn.close(pstmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surveyArr;
    }

    // 설문 하나 가져오기
    public SurveyVO selectSurvey(int sno) {
        query = "SELECT * FROM t_survey WHERE sno = " + sno;
        svo = null;
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                svo = new SurveyVO();
                svo.setSno(rs.getInt("sno"));
                svo.setTitle(rs.getString("title"));
                svo.setNum1(rs.getString("num1"));
                svo.setNum2(rs.getString("num2"));
                svo.setStartDate(rs.getString("startDate"));
                svo.setEndDate(rs.getString("endDate"));
                svo.setNum1Cnt(rs.getInt("num1Cnt"));
                svo.setNum2Cnt(rs.getInt("num2Cnt"));
            }
            DBConn.close(pstmt, rs);
        } catch (SQLException e) {
            System.err.println("selectSurvey error");
            e.printStackTrace();
        }
        return svo;
    }

    // 설문 만들기
    public boolean insertSurvey(SurveyVO svo) {
//        query = "INSERT INTO t_survey VALUES (?, ?, ?, ?, TO_DATE(?, 'yy-MM-dd'), TO_DATE(?, 'yy-MM-dd'), ?, ?)";
        query = "INSERT INTO t_survey VALUES (T_SURVEY_ATTEND_SEQ.NEXTVAL, ?, ?, ?, TO_DATE(?, 'yy-MM-dd'), TO_DATE(?, 'yy-MM-dd'), 0, 0)";
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
//            pstmt.setInt(1, svo.getSno());
            pstmt.setString(1, svo.getTitle());
            pstmt.setString(2, svo.getNum1());
            pstmt.setString(3, svo.getNum2());
            pstmt.setString(4, svo.getStartDate());
            pstmt.setString(5, svo.getEndDate());

            result = pstmt.executeUpdate() == 1;
            DBConn.close(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean doSurveyDAO(SurveyAttendVO savo) {
        // 설문 참여 - 트랜잭션 처리
        // 설문 참여 테이블에 데이터 추가에 성공한 경우
        // 설문 테이블에 해당 항목의 응답자 수를 1 증가 처리
        query = "INSERT INTO t_survey_attend VALUES (?, ?, ?, ?, SYSDATE)";
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setInt(1, savo.getAno());
            pstmt.setInt(2, savo.getSno());
            pstmt.setString(3, savo.getId());
            pstmt.setInt(4, savo.getNum());
//            pstmt.setString(5, savo.getAttendDate());


            result = pstmt.executeUpdate() == 1;

            DBConn.close(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result) {
            updateNumCnt(savo, savo.getNum());
        }
        return result;
    }

    public boolean updateNumCnt(SurveyAttendVO savo, int getNum) {
        String property = "";
        if (getNum == 1) {
            property = "num1cnt";
        } else if (getNum == 2) {
            property = "num2cnt";
        }
        query = "UPDATE t_survey SET " + property + " = " + property + " + 1 WHERE sno = ?";
//        query = "UPDATE t_survey SET ? = ? WHERE sno = ?";
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setInt(1, savo.getSno());

            result = pstmt.executeUpdate() == 1;

            DBConn.close(pstmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}