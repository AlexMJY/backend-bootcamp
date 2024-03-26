package rental.main;

import java.util.Scanner;

import rental.dao.UserDAO;
import rental.vo.UserVO;


public class RentalMain {
	public static Scanner sc;
    public static String loginId;	//로그인 아이디 저장
    private UserVO uvo;
    private UserDAO udao;
    

    public RentalMain() {
    	sc = new Scanner(System.in);
        uvo = new UserVO();
        udao = new UserDAO();
    }
    
    public void menu() {
        while(true) {
            System.out.println();
            System.out.println(">> MEMBER only SYSTEM MAIN -----------------------");
            System.out.println("   1.회원가입   2.로그인   3.ID찾기   4.PW찾기   5.시스템 종료");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();

            switch (input) {
            case "1":    join();        break;
            case "2":    login();    break; 
            case "3":    findId();    break; 
            case "4":    findPw();    break; 
            case "5":    System.out.println("시스템을 종료합니다.");
                        sc.close();
                        System.exit(0);
            default:System.out.println(">> 1 ~ 5을 입력해주세요."); 
            }
            System.out.println();
        }
    }
    
    public void adminMenu(){
        while(true) {
            System.out.println();
            System.out.println(">> MEMBER only SYSTEM 관리자 모드 -----");
            System.out.println("   1.회원목록     2.회원정보조회    3. 설문     4.로그아웃     ");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();
            switch (input) {     
            case "1":    userList();                    break;

            case "5":    return;
            default:System.out.println(">> 1 ~ 5을 입력해주세요."); 
            }
        }
    }
    
    public void memberMenu() {
        while(true) {
            System.out.println();
            System.out.println(">> MEMBER only SYSTEM 사용자 모드 -----");
            System.out.println("   1.내 정보    2.비밀번호 변경    3. 설문    4.로그아웃");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();
            switch (input) {
            case "1":    userInfo();                break;
            case "2":    changePw();                    break; 
            case "4":    return;
            default:System.out.println(">> 1 ~ 4을 입력해주세요."); 
            }
        }
    }
    
    
    
    public void login() {
    	System.out.println();
    	System.out.println(">> 로그인 ");
    	System.out.print(">> 아이디 : ");
    	String userId = sc.nextLine();
    	System.out.print(">> 비밀번호 : ");
    	String uiserPw = sc.nextLine();
    	
    	boolean result = udao.loginChk(userId, uiserPw);
    	if ( result == true ) {
    		loginId = userId;
    		if ( userId.equals("admin")) {
    			adminMenu();
    		}
    		else {
    			memberMenu();
    		}
    	} else {
    		System.out.println(">> 아이디 또는 비밀번호가 일치하지 않습니다.");
    	}
    }
    
    public void findPw() {
    	System.out.println();
    	System.out.println(">> 비밀번호를 찾습니다.");
    	System.out.print("  아이디 : ");
    	String userId = sc.nextLine();
    	System.out.print("  이메일 : ");
    	String userEmail = sc.nextLine();
    	
    	if (udao.selectPw(userId, userEmail)) {
    		UserVO uvo = udao.selelctMember(userId);
    		System.out.println(">> 비밀번호 : " + uvo.getPw());
    	} else {
    		System.out.println(">> 아이디 또는 이메일이 잘못입력됐습니다.");
    	}
    }
    
    
	public static void main(String[] args) {
		new RentalMain().menu();

	}

}
