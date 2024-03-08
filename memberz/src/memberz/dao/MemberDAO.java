package memberz.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import memberz.common.DBConn;
import memberz.vo.MemberVO;

public class MemberDAO {
    List<MemberVO> memberList = new ArrayList<MemberVO>();
    private String query;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private boolean result;

    public boolean updatePw(String id, String pw) {	//회원 비밀번호 변경하기
        query = "UPDATE t_member SET pw = ? WHERE id = ?";
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, pw);
            pstmt.setString(2, id);

            result =  pstmt.executeUpdate() == 1;
            DBConn.close(pstmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public MemberVO selectMember(String id){		//회원 정보 하나 보기
        query = "SELECT * FROM t_member WHERE id = " + id;
        MemberVO mvo = null;
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                mvo = new MemberVO();
                mvo.setId(rs.getString("id"));
                mvo.setPw(rs.getString("pw"));
                mvo.setName(rs.getString("name"));
                mvo.setEmail(rs.getString("email"));
                mvo.setPhoto(rs.getString("photo"));
                mvo.setGender(rs.getString("gender"));
                mvo.setJoinDate(rs.getDate("joindate"));
            }
            DBConn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mvo;
    }

    public List<MemberVO> selectMember(){			//전체 회원목록
        query = "SELECT * FROM t_member";
        List<MemberVO> evoList = new ArrayList<MemberVO>();

        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {  // 결과값이 있으면 ExerciseVO에서 가져옴
                MemberVO mvo = new MemberVO();
                mvo = new MemberVO();
                mvo.setId(rs.getString("id"));
                mvo.setPw(rs.getString("pw"));
                mvo.setName(rs.getString("name"));
                mvo.setEmail(rs.getString("email"));
                mvo.setPhoto(rs.getString("photo"));
                mvo.setGender(rs.getString("gender"));
                mvo.setJoinDate(rs.getDate("joindate"));
                evoList.add(mvo);
            }
            DBConn.close(pstmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 쿼리 실행 및 실행 결과 받기
        return evoList;

    }

    public boolean insertMember(MemberVO mvo) {		//회원가입
        query = "INSERT INTO t_member VALUES (?, ?, ?, ?, ?, ?, SYSDATE)";

        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, mvo.getId());
            pstmt.setString(2, mvo.getPw());
            pstmt.setString(3, mvo.getName());
            pstmt.setString(4, mvo.getEmail());
            pstmt.setString(5, mvo.getPhoto());
            pstmt.setString(6, mvo.getGender());


            result = pstmt.executeUpdate() == 1;
            DBConn.close(pstmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public boolean loginChk(String id, String pw) {	//로그인 체크
        query = "SELECT pw FROM t_member WHERE id = " + id;
        MemberVO mvo = null;
        try {
            pstmt = memberz.common.DBConn.getConnection().prepareStatement(query);
            rs = pstmt.executeQuery(query);
            if (rs.next()) {
                mvo = new MemberVO();
                mvo.setPw(rs.getString("pw"));
            }
            memberz.common.DBConn.close(pstmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (mvo.getPw() == pw) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateMember(MemberVO mvo) { 	//회원정보 수정
        return false;
    }

    public boolean deleteMember(String id) { 		//회원정보 삭제
        return false;
    }

    public boolean selectId(String name, String email) { //ID 찾기
        return false;
    }

    public boolean selectPw(String name, String email) { //PW 찾기
        return false;
    }

    public void logout(String id) {						//로그아웃

    }
}
