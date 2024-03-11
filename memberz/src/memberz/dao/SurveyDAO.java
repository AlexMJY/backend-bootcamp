package memberz.dao;

import memberz.common.DBConn;
import memberz.vo.SurveyVO;

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
        query = "SELECT * FROM t_survey";
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
                svo.setStartDate(rs.getDate("startDate"));
                svo.setEndDate(rs.getInt("endDate"));
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
                svo.setStartDate(rs.getDate("startDate"));
                svo.setEndDate(rs.getInt("endDate"));
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
        query = "INSERT INTO t_survey VALUES (?, ?, ?, ?, SYSDATE, ?, ?, ?)";
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setInt(1, svo.getSno());
            pstmt.setString(2, svo.getTitle());
            pstmt.setString(3, svo.getNum1());
            pstmt.setString(4, svo.getNum2());
            pstmt.setInt(5, svo.getEndDate());
            pstmt.setInt(6, svo.getNum1Cnt());
            pstmt.setInt(7, svo.getNum2Cnt());

            result = pstmt.executeUpdate() == 1;
            DBConn.close(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
