package memberz.main;

import java.util.List;
import java.util.Scanner;

import memberz.dao.MemberDAO;
import memberz.vo.MemberVO;

public class MemberMain {
    public static Scanner sc;
    private MemberVO mvo;
    private MemberDAO mdao;
    public static String loginId;	//로그인 아이디 저장
    private SurveyMain sm;


    //0.멤버필드들을 초기화하는 생성자
    public MemberMain() {
        sc = new Scanner(System.in);
        mvo = new MemberVO();
        mdao = new MemberDAO();
        sm = new SurveyMain();
    }

    public void menu() {		//1.메인메뉴
        while(true) {
            System.out.println();
            System.out.println(">> MEMBER only SYSTEM MAIN -----------------------");
            System.out.println("\t\t1.회원가입\t\t2.로그인\t\t3.ID찾기\t\t4.PW찾기\t\t5.시스템 종료");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();

            switch (input) {
                case "1":	join();		break;
                case "2":	login();	break;
                case "3": findId();     break;
                case "4": findPw();     break;
                case "5":	System.out.println("시스템을 종료합니다.");
                    sc.close();
                    System.exit(0);
                default:System.out.println(">> 1 ~ 5을 입력해주세요.");
            }
            System.out.println();
        }//END while
    }//END menu()

    public void login() { 		//2.로그인
        System.out.println();
        System.out.println(">> MEMBER only SYSTEM 로그인 --------");
        System.out.print("   아이디 : ");
        String id = sc.nextLine();

        System.out.print("   비밀번호 : ");
        String pw = sc.nextLine();

        boolean result = mdao.loginChk(id, pw);
        if(result == true) {	//로그인 성공
            loginId = id;
            if(id.equals("admin"))	adminMenu();
            else					memberMenu();
        } else {				//로그인 실패
            System.out.println(">> 아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    public void join(){			//5.회원 가입
        mvo = new MemberVO();

        System.out.println();
        System.out.println(">> MEMBER only SYSTEM 회원가입 --------");
        System.out.print("   아이디 : ");
        String id = sc.nextLine();
        mvo.setId(id);

        System.out.print("   비밀번호 : ");
        String pw = sc.nextLine();
        mvo.setPw(pw);

        System.out.print("   이름 : ");
        mvo.setName(sc.nextLine());

        System.out.print("   이메일 : ");
        mvo.setEmail(sc.nextLine());

        System.out.print("   사진 : ");
        mvo.setPhoto(sc.nextLine());

        System.out.print("   성별(F/M) : ");
        mvo.setGender(sc.nextLine());

        boolean result = mdao.insertMember(mvo);
        if (id.equals("admin"))  // admin 아이디로 회원가입 못함
            result = false;
        
        if(result) {	//회원 가입에 성공한 경우
            System.out.println(">> 회원 가입이 완료되었습니다.");
            System.out.println(">> 로그인 후 이용해주세요.");
        } else {				// 회원 가입에 실패한 경우
            System.out.println(">> 회원 가입에 실패하였습니다.");
            System.out.println(">> 잠시 후 다시 이용해주세요.");
        }
    }

    public void adminMenu(){ 	//3.관리자 메뉴
        while(true) {
            System.out.println();
            System.out.println(">> MEMBER only SYSTEM 관리자 모드 -----");
            System.out.println("\t\t1.회원목록\t\t2.회원정보조회\t\t3.설문관리\t\t4.로그아웃");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();
            switch (input) {
                case "1":	memberList();	break;
                case "2":   memberSearch(); break;
                case "3":   sm.adminSurveyMenu(); break;
                case "4":	logout();		break;
                default:System.out.println(">> 1 ~ 5를 입력해주세요.");
            }
        }
    }


    public void memberList(){	//3.1 전체 회원 목록 보기
        List<MemberVO> memberList = mdao.selectMember();
        if(memberList == null || memberList.size() < 1 ) {
            System.out.println(">> 등록된 회원이 없습니다.");
        } else {		//그렇지 않은 경우
            System.out.println("아이디|이름|이메일|가입일자");
            System.out.println("--------------------------------");
            for (MemberVO mvo : memberList) {
                System.out.println(mvo.getId() + "|" +
                        mvo.getName() + "|" +
                        mvo.getEmail() + "|" +
                        mvo.getJoinDate().toLocaleString());
            }
        }
    }

    public void logout(){		//6.로그아웃
        System.out.println(">> 로그아웃이 완료되었습니다.");
    }

    public void memberMenu(){	//4.회원 메뉴
        while(true) {
            System.out.println();
            System.out.println(">> MEMBER only SYSTEM 사용자 모드 -----");
            System.out.println("\t\t1.내 정보\t\t2.비밀번호 변경\t\t3.설문\t\t4.로그아웃");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();
            switch (input) {
                case "1":	memberInfo();	break;
                case "2":	changePw();		break;
                case "3":	sm.memberSurveyMenu();		break;
                case "4":	return;
                default:System.out.println(">> 1 ~ 4을 입력해주세요.");
            }
        }
    }

    public void memberInfo(){	//4.1 내 정보 보기
        MemberVO mvo = mdao.selectMember(loginId);
        System.out.println("\t아이디 : " + mvo.getId());
        System.out.println("\t비밀번호 : " + mvo.getPw());
        System.out.println("\t이름 : " + mvo.getName());
        System.out.println("\t이메일 : " + mvo.getEmail());
        System.out.println("\t사진 : " + mvo.getPhoto());
        System.out.println("\t성별 : " + mvo.getGender());
    }

    public void changePw(){		//4.2 비밀번호 변경하기
        System.out.println();
        System.out.println(">> 비밀번호를 변경합니다.");
        System.out.print("\t기존 비밀번호 : ");
        String pw = sc.nextLine();
        mvo = mdao.selectMember(loginId);

        if(!pw.equals(mvo.getPw())) {		//기존 비밀번호가 일치하지 않는 경우
            System.out.println(">> 기존 비밀번호가 일치하지 않습니다.");
        } else { 	//그렇지 않은 경우 신규 비밀번호 입력받기
            System.out.print("\t신규 비밀번호 : ");
            pw = sc.nextLine();
            if (mdao.updatePw(loginId, pw)) {
                System.out.println(">> 비밀번호 변경 완료");
            } else {
                System.out.println(">> 비밀번호 변경 실패");
            }
        }
    }

    public void findId() { //7. 아이디 찾기
        System.out.println();
        System.out.println(">> 아이디를 찾습니다.");
        System.out.print("\t 이름 : ");
        String user_name = sc.nextLine();
        System.out.print("\t 이메일 : ");
        String user_email = sc.nextLine();

        if (mdao.selectId(user_name, user_email)) {
            MemberVO mvo = mdao.selectMember2(user_name);
            System.out.println(">> 아이디 : " + mvo.getId());
        } else {
            System.out.println(">> 이름 또는 이메일이 잘못입력됐습니다.");
        }
    }

    public void findPw() { //8. 비밀번호 찾기
        System.out.println();
        System.out.println(">> 비밀번호를 찾습니다.");
        System.out.print("\t 아이디 : ");
        String user_id = sc.nextLine();
        System.out.print("\t 이메일 : ");
        String user_email = sc.nextLine();

        if (mdao.selectPw(user_id, user_email)) {
            MemberVO mvo = mdao.selectMember(user_id);
            System.out.println(">> 비밀번호 : " + mvo.getPw());
        } else {
            System.out.println(">> 아이디 또는 이메일이 잘못입력됐습니다.");
        }

    }

    public void memberSearch() {
        System.out.println();
        System.out.println(">> 회원 정보 조회 -----");
        System.out.println("\t\t1.수정\t\t2.삭제\t\t3.종료");
        System.out.print(">> 선택 : ");
        String input = sc.nextLine();
        switch (input) {
            case "1":	updMember();	break;
            case "2":	delMember();		break;
            case "3":
                sc.close();
                System.exit(0);
            default:System.out.println(">> 1 ~ 3을 입력해주세요.");
        }


    }

    public void updMember() {
        System.out.println("hi");

    }

    private void delMember() {
        System.out.println();
        System.out.println(">> 삭제할 아이디를 입력해주세요.");
        System.out.print(">> 선택 : ");
        String input = sc.nextLine();
        if (mdao.deleteMember(input)) {
            System.out.println(">> 삭제가 완료되었습니다.");
        } else {
            System.out.println(">> 삭제를 실패했습니다.");
        }
    }

    public static void main(String[] args) {
        new MemberMain().menu();
    }

}

