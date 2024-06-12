package javaz.util.member;

import java.util.List;
import java.util.Scanner;

public class MemberMain {
	private Scanner sc;
	private MemberVO mvo;
	private MemberDAO mdao;
	private MemberLogin ml;
	private String loginId;	//로그인 아이디 저장
	
	//0.멤버필드들을 초기화하는 생성자
	public MemberMain() {
		sc = new Scanner(System.in);
		mvo = new MemberVO();
		mdao = new MemberDAO();
		ml = new MemberLogin();
	}
	
	public void menu() {		//1.메인메뉴
		while(true) {
			System.out.println();
			System.out.println(">> MEMBER only SYSTEM MAIN ---------");
			System.out.println("   1.가입     2.로그인     3.시스템 종료");
			System.out.print(">> 선택 : ");
			String input = sc.nextLine();
			
			switch (input) {	 
			case "1":	join();		break;
			case "2":	login();	break; 
			case "3":	System.out.println("시스템을 종료합니다.");
						sc.close();
						System.exit(0);
			default:System.out.println(">> 1 ~ 3을 입력해주세요."); 
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
		
		boolean result = ml.loginChk(id, pw);
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
		if(result == true) {	//회원 가입에 성공한 경우
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
			System.out.println("   1.회원목록     2.로그아웃     3.메인 메뉴");
			System.out.print(">> 선택 : ");
			String input = sc.nextLine();
			switch (input) {	 
			case "1":	memberList();	break;
			case "2":	logout();		break; 
			case "3":	return;
			default:System.out.println(">> 1 ~ 3을 입력해주세요."); 
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
			System.out.println("   1.내 정보    2.비밀번호 변경    3.로그아웃");
			System.out.print(">> 선택 : ");
			String input = sc.nextLine();
			switch (input) {	 
			case "1":	memberInfo();	break;
			case "2":	changePw();		break; 
			case "3":	return;
			default:System.out.println(">> 1 ~ 3을 입력해주세요."); 
			}
		}
	}
	
	public void memberInfo(){	//4.1 내 정보 보기
		MemberVO mvo = mdao.selectMember(loginId);
		System.out.println("   아이디 : " + mvo.getId());
		System.out.println("   이름 : " + mvo.getName());
		System.out.println("   이메일 : " + mvo.getEmail());
		System.out.println("   사진 : " + mvo.getPhoto());
		System.out.println("   성별 : " + mvo.getGender());
	}
	
	public void changePw(){	//4.2 비밀번호 변경하기
		System.out.println();
		System.out.println(">> 비밀번호를 변경합니다.");
		System.out.print("   기존 비밀번호 : ");
		String pw = sc.nextLine();
		mvo = mdao.selectMember(loginId);
		
		if(!pw.equals(mvo.getPw())) {		//기존 비밀번호가 일치하지 않는 경우
			System.out.println(">> 기존 비밀번호가 일치하지 않습니다.");
		} else { 	//그렇지 않은 경우 신규 비밀번호 입력받기
			System.out.print("   신규 비밀번호 : ");
			pw = sc.nextLine();
			mdao.updatePw(loginId, pw);
			System.out.println(">> 비밀번호가 변경되었습니다.");
		}
		
	}
	
	public static void main(String[] args) {
		new MemberMain().menu();
	}

}














