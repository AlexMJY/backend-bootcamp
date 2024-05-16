package member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// DAO : Data Access object
public class MemberDAO {
    // DB 대신 사용 - static 뺄 경우 비번 변경 후 로그인 안 됨
    private static List<MemberVO> memberList;

    // MembersLogin과 동일하게 값 입력 필요하지 않은지
    public MemberDAO() {
        memberList = new ArrayList<MemberVO>();
        memberList.add(new MemberVO("admin", "1234", "nadmin", "", "", ""));
        memberList.add(new MemberVO("aaa", "1111", "naaa", "", "", ""));
        memberList.add(new MemberVO("bbb", "2222", "nbbb", "", "", ""));
        memberList.add(new MemberVO("ccc", "3333", "nccc", "", "", ""));

        getMemberById("admin").setAdmin(true);
    }

    // 회원가입
    public boolean insertMember(MemberVO mvo) {
        // 필수항목 입력 검증
        if (mvo.getId().equals("")) {
            System.out.println(">> 아이디는 필수항목입니다.");
            return false;
        } else if (mvo.getPw().equals("")) {
            System.out.println(">> 비밀번호는 필수항목입니다.");
            return false;
        } else if (mvo.getEmail().equals("")) {
            System.out.println(">> 이메일은 필수항목입니다.");
            return false;
        }

        // 아이디 중복 여부 확인
        for (MemberVO member : memberList) {
            if (member.getId().equals(mvo.getId())) {
                System.out.println(">> 중복된 아이디입니다.");
                return false;
            }
        }

        // 이메일 검증
        if (!mvo.getEmail().contains("@") || !mvo.getEmail().contains(".com")
            && !mvo.getEmail().contains(".net") && !mvo.getEmail().contains(".co.kr")) {
            System.out.println(">> 이메일 형식이 올바르지 않습니다.");
            return false;
        }

        // 성별 확인
        if (mvo.getGender().equalsIgnoreCase("F")) {
            mvo.setGender("F");
        } else if (mvo.getGender().equalsIgnoreCase("M")) {
            mvo.setGender("M");
        } else if (mvo.getGender().equals("")) {

        } else {
            System.out.println(">> 성별은 F 혹은 M으로 입력해주세요. (공란 가능");
            return false;
        }

        // 아이디에 admin이 포함될 경우 admin 계정으로 생성 - 대소문자 관계없이 하려면 어떻게?
        if (mvo.getId().contains("admin")) { mvo.setAdmin(true); }

        // 1. MemberVO 객체에 오늘 날짜를 저장한 후
        // memberList에 mvo 담기
        mvo.setJoinDate(new Date());

        if (memberList.add(mvo)) {
            // 2. memberMap 객체에 아이디와 비밀번호 저장
            MemberLogin.memberMap.put(mvo.getId(), mvo.getPw());
            return true;
        } else {
            return false;
        }




    }

    // 전체 회원목록
    public List<MemberVO> selectMember() {
        // memberList 반환
        return memberList;
    }

    // 아이디로 사용자 정보 접근
    public MemberVO getMemberById(String id) {
        for (MemberVO member : memberList) {
            if (member != null && member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }

    // 입력받은 비밀번호가 동일한지 확인
    public boolean updatePW(String id, String currentPW, String newPW) {
        MemberVO member = getMemberById(id);

        if (member.getPw().equals(currentPW)) {
            member.setPw(newPW);
            System.out.println(memberList);
            return true;
        }
        return false;
    }

    // 로그인 체크
    public boolean existChk(String id, String pw) {
        MemberVO member = getMemberById(id);
        System.out.println(memberList);

        if (member != null && member.getPw().equals(pw)) {
            MemberLogin.memberMap.put(member.getId(), member.getPw());
            return true;
        }
        return false;
    }
 }
