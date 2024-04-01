package javaz.util.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//DAO ; Data Access Object
public class MemberDAO {
    //Database 대신 사용
    private List<MemberVO> memberList = new ArrayList<MemberVO>();

    //회원 비밀번호 변경하기
    public boolean updatePw(String id, String pw) {
        for (MemberVO mvo : memberList) {
            if(mvo != null && id.equals(mvo.getId())) {
                mvo.setPw(pw);
                MemberLogin.memberMap.replace(id, pw);
                return true;
            }
        }
        return false;
    }

    //회원 정보 하나 보기
    public MemberVO selectMember(String id){
        for (MemberVO mvo : memberList) {
            if(mvo != null && id.equals(mvo.getId())) {
                return mvo;
            }
        }
        return null;
    }

    //전체 회원목록
    public List<MemberVO> selectMember(){
        return memberList;
    }

    //회원가입
    public boolean insertMember(MemberVO mvo) {
        mvo.setJoinDate(new Date());

        if( memberList.add(mvo) ) {  //회원가입 성공
            MemberLogin.memberMap.put(mvo.getId(), mvo.getPw());
            return true;
        } else {					//회원가입 실패
            return false;
        }
    }
}