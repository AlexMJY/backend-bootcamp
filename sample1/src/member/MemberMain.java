package member;

import java.util.Scanner;
public class MemberMain {
    private Scanner sc;
    private MemberVO mvo;
    private MemberDAO mdao;
    private MemberLogin ml;

    // 0. 멤버필드들을 초기화하는 생성자
    public MemberMain() {
        sc = new Scanner(System.in);
        mdao = new MemberDAO();
        ml = new MemberLogin();
    }

    // 1. 메인메뉴
    private void menu() {
        while (true) {
            System.out.println(">> MEMBER only SYSTEM MAIN -------");
            System.out.println("   1.가입  2.로그인  3.시스템종료 ");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();

            // 메뉴 번호 검증
            int num = 0;
            try {
                num = Integer.parseInt(input);

                if (num < 1 || num > 3) {
                    System.out.println(">> 1, 2, 3 중 하나의 숫자를 입력해주세요.");
                    System.out.println();
                }
            } catch (NumberFormatException e) {
                System.out.println(">> 숫자를 입력해 주세요.");
                System.out.println();
            }

            if (num == 1) { join(); }
            if (num == 2) {login(); }
            if (num == 3) {
                System.out.println(">> 시스템을 종료합니다.");
                sc.close();
                System.exit(0);
            }
        }
    }

    // 2. 로그인
    public void login() {
        System.out.println();
        System.out.println(">> MEMBER only SYSTEM LOGIN -------");
        System.out.print("   아이디  : ");
        String id = sc.nextLine();
        System.out.print("   비밀번호 :  ");
        String pw = sc.nextLine();

        if (ml.loginChk(id, pw)) {
            mvo = mdao.getMemberById(id);
            System.out.println(">> " + mvo.getName() + "님 환영합니다.");

            if (mvo.getAdmin()) { adminMenu(id); }
            else { memberMenu(id); }
        } else {
            System.out.println(">> 아이디 또는 비밀번호가 일치하지 않습니다.");
            System.out.println();
        }
    }

    public void adminMenu(String id) {
        while (true) {
            System.out.println();
            System.out.println(">> MEMBER only SYSTEM Admin Mode -------");
            System.out.println("   1.회원목록\t 2.로그아웃   ");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();

            // 메뉴 번호 검증
            int num = 0;
            try {
                num = Integer.parseInt(input);
                if (num < 1 || num > 2) {
                    System.out.println(">> 1, 2 중 하나의 숫자를 입력해 주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println(">> 숫자를 입력해 주세요.");
            }

            if (num == 1) { memberList(); }
            if (num == 2) { logout(id); break; }
        }
    }

    // 3.1 전체 회원 목록 보기
    public void memberList() {
        // 테스트용 계정 제외하고 출력


        for (MemberVO member : mdao.selectMember()) {
            if (!member.getEmail().equals("")) {  // admin 출력 안함

                System.out.println();
//                    System.out.printf("%5s\\t|%4s\\t|%8s\\n\" + , \"아이디\", \"이름\", \"이메일\", \"가입일자");
                System.out.printf("  아이디: %s  |  이름: %s  |  이메일: %s |", member.getId(), member.getName(), member.getEmail());
                System.out.println("  가입일자: " + member.getJoinDate());
                System.out.println("--------------------------------------------------------------");

                System.out.println(member);
            }

        }
        if (mdao.selectMember().size() < 5) {
            System.out.println(">> 가입된 회원이 없습니다.");
        }
    }

    public void memberMenu(String id) {
        while (true) {
            System.out.println();
            System.out.println(">> MEMBER only SYSTEM 사용자 모드 -------");
            System.out.println("   1.내 정보  2.비밀번호 변경  3.로그아웃");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();

            // 메뉴 번호 검증
            int num = 0;
            try {
                num = Integer.parseInt(input);

                if (num < 1 || num > 3) {
                    System.out.println(">> 1, 2, 3 중 하나의 숫자를 입력해 주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println(">> 숫자를 입력해 주세요.");
            }

            if (num == 1) { memberInfo(id); }
            if (num == 2) { changePW(id); }
            if (num == 3) { logout(id); break; }
        }
    }

    // 4.1 내 정보 보기
    public void memberInfo(String id) {
        mvo = mdao.getMemberById(id);

        System.out.println(" 아이디 : " + mvo.getId());
        System.out.println(" 비밀번호 : " + mvo.getPw());
        System.out.println(" 이름 : " + mvo.getName());
        System.out.println(" 이메일 : " + mvo.getEmail());
        System.out.println(" 사진 : " + mvo.getPhoto());
        System.out.println(" 성별 : " + mvo.getGender());
    }

    // 4.2 비밀번호 변경하기
    public void changePW(String id) {
        System.out.println(">> 비밀번호를 변경합니다.");
        System.out.print(" 기존 비밀번호 : ");
        String currentPW = sc.nextLine();
        System.out.print(" 신규 비밀번호 : ");
        String newPW = sc.nextLine();

        boolean isChanged = mdao.updatePW(id, currentPW, newPW);

        if (isChanged) {
            System.out.println(">> 비밀번호가 변경되었습니다.");
            return;
        }
        System.out.println(">> 변경실패!  기존 비밀번호가 틀립니다.");
    }

    // 5. 회원가입
    public void join() {
        System.out.println();
        System.out.println(">> MEMBER only SYSTEM 회원가입 -------");
        System.out.print(" 아이디 : ");
        String id = sc.nextLine();

        System.out.print(" 비밀번호 : ");
        String pw = sc.nextLine();

        System.out.print(" 이름 : ");
        String name = sc.nextLine();

        System.out.print(" 이메일 : ");
        String email = sc.nextLine();

        System.out.print(" 사진 : ");
        String photo = sc.nextLine();

        System.out.print(" 성별(F/M) : ");
        String gender = sc.nextLine();

        boolean inInsert = mdao.insertMember(new MemberVO(id, pw, name, email, photo, gender));

        if (inInsert) {
            System.out.println(">> 회원가입이 완료되었습니다.");
            System.out.println(">> 로그인 후 이용해 주세요.");
            System.out.println(mdao.selectMember());
        }
        System.out.println();
    }
    // 6. 로그아웃
    public void logout(String id) {
        ml.logout(id);
        System.out.println();
        return;
    }

    public static void main(String[] args) {
        new MemberMain().menu();
    }

}
