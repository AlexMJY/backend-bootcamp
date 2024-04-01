package memberz.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

            result = pstmt.executeUpdate() == 1;
            DBConn.close(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public MemberVO selectMember(String id){		//회원 정보 하나 보기
        query = "SELECT * FROM t_member WHERE id = ?";
        MemberVO mvo = null;
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, id);
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(pstmt, rs);
        }
        return mvo;
    }

    public MemberVO selectMember2(String name){		//회원 정보 하나 보기
        query = "SELECT pw, id FROM t_member WHERE name = ?";
        MemberVO mvo = null;
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                mvo = new MemberVO();
                mvo.setId(rs.getString("id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(pstmt, rs);
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
//                MemberVO mvo = new MemberVO();
                MemberVO mvo = new MemberVO();
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
        query = "INSERT INTO t_member(ID, PW, NAME, EMAIL, PHOTO, GENDER, JOINDATE) VALUES (?, ?, ?, ?, ?, ?, SYSDATE)";

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
        query = "SELECT * FROM t_member WHERE id = ? AND pw = ?";

        MemberVO mvo = null;
        try {
            pstmt = memberz.common.DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();
            if (rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            memberz.common.DBConn.close(pstmt, rs);
        }
        return false;
    }

    public boolean updateMember(String currentId, MemberVO updateInfo) {

        return false;
    }

    public boolean deleteMember(String id) { 		//회원정보 삭제
        query = "DELETE * FROM t_emp WHERE id = ?";
        try {
            pstmt = memberz.common.DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, id);
//            rs = pstmt.executeQuery();
            result = pstmt.executeUpdate() == 1;
            DBConn.close(pstmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean selectId(String name, String email) { //ID 찾기
        query = "SELECT * FROM t_member WHERE name = ? AND email = ?";

        MemberVO mvo = null;
        try {
            pstmt = memberz.common.DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);

            rs = pstmt.executeQuery();
            if (rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            memberz.common.DBConn.close(pstmt, rs);
        }
        return false;
    }

    public boolean selectPw(String id, String email) { //PW 찾기
        query = "SELECT * FROM t_member WHERE id = ? AND email = ?";

        MemberVO mvo = null;
        try {
            pstmt = memberz.common.DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, email);

            rs = pstmt.executeQuery();
            if (rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            memberz.common.DBConn.close(pstmt, rs);
        }
        return false;
    }

    public void logout(String id) {						//로그아웃

    }
}
