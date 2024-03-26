package rental.main;

import java.util.List;
import java.util.Scanner;

import rental.dao.UserDAO;
import rental.vo.FacVO;
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
            System.out.println("   1.회원관리     2.시설관리    3.로그아웃     ");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();
            switch (input) {     
            case "1":    adminMenuMember();                    break;
            case "2":    adminMenuFac(); break;
            case "3": return;
            default:System.out.println(">> 1 ~ 5을 입력해주세요."); 
            }
        }
    }
    
    public void adminMenuMember() {
    	while(true) {
    		System.out.println();
    		System.out.println(">> 회원 관리 모드");
    		System.out.println("   1.회원목록   2.회원정보조회   3.AdminMenu로 이동");
    		System.out.print(">> 선택 : ");
    		String input = sc.nextLine();
    		switch (input) {
			case "1": userList(); break;
			case "2": userSearch(); break;
			case "3": adminMenu(); break;
			default:
				System.out.println(">> 1 ~ 3을 입력해주세요.");;
			}
    		
    	}
    }
    
    public void adminMenuFac() {
        while(true) {
            System.out.println();
            System.out.println(">> MEMBER only SYSTEM 관리자 모드 -----");
            System.out.println("   1.시설조회     2.시설리스트    3.시설등록     4.로그아웃     ");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();
            switch (input) {     
            case "1":    adminFacSearch(); break;
            case "2":    adminFacList();    break;
            case "3":    adminFacRegist();    break;
            case "4":      return;
            default:System.out.println(">> 1 ~ 5을 입력해주세요."); 
            }
        }
    }
    
    // 시설조회
    public void adminFacSearch() {
    	System.out.println();
    	System.out.println(">> 시설 정보 조회");
    	System.out.print(">> 시설 아이디 입력 : ");
    	String id = sc.nextLine();
    	FacVO uvo = udao.selectFac(id);
    	
    	if (uvo == null) {
    		System.out.println(">> 존재하지 않는 시설입니다.");
    		return;
    	}
    	
    	while (true) {
    		 System.out.println("   시설번호 : " + uvo.getFacNo());
             System.out.println("   시설이름 : " + uvo.getFacName());
             System.out.println("   시설주소 : " + uvo.getFacAddr());
             System.out.println("   시설등록일 : " + uvo.getCreateDate());
             
             
             System.out.println("   1.수정   2.삭제   3.종료");
             System.out.println(">> 선택 : ");
             String input = sc.nextLine();
             switch (input) {
			case "1": 				
	             System.out.print("   시설이름 : ");
	             uvo.setFacName(sc.nextLine());
	             
	             System.out.print("   시설주소 : ");
	             uvo.setFacAddr(sc.nextLine());
	             	           

	             System.out.println(
	                		udao.updateFac(uvo) ? 
	                		"수정 완료" : "수정 실패");
	             System.out.println("수정 완료되었습니다.");
				break;
				
			case "2":
				System.out.println(udao.deleteFac(uvo) ?
									"삭제 완료" : "삭제 실패");
			
			case "3":
				return;
			default: 
				System.out.println(">> 1~3을 입력해주세요.");
				
			}
             
    	}
    	
    }
    
    
    // 시설 리스트
    public void adminFacList() {
        List<FacVO> userList = udao.selectFac();
        if (userList == null || userList.size() < 1) {
        	System.out.println(" 등록된 시설이 없습니다.");
        } else {
        	System.out.println("시설번호 | 시설이름 | 시설주소 | 시설등록일");
        	System.out.println("--------------------------------------------");
        	for (FacVO uvo : userList) {
        		System.out.println(
        				uvo.getFacNo() + " | " +
        				uvo.getFacName() + " | " +
        				uvo.getFacAddr() + " | " +
        				uvo.getCreateDate().toLocaleString()
        				);
        	}
        	
        }
        
        
    }

    // 시설 등록
    public void adminFacRegist() {
        
    }
    
    // 시설 수정
    public void adminFacEdit() {
        
    }
    
    // 시설 삭제
    public void adminFacDelete() {
        
    }
    
    
    
    public void memberMenu() {
        while(true) {
            System.out.println();
            System.out.println(">> MEMBER only SYSTEM 사용자 모드 -----");
            System.out.println("   1.내 정보    2.시설 정보   3.로그아웃");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();
            switch (input) {
            case "1":    userInfo(); break;
            case "2":    facInfo();	 break;
            case "3":    return;    
            default: System.out.println(">> 1 ~ 4을 입력해주세요."); 
            }
        }
    }
    
    public void userInfo() {
    	System.out.println();
        System.out.println(">> MEMBER only SYSTEM 사용자 모드 -----");
        System.out.println("   1.비밀번호 변경    2.이메일 변경   3.폰번호 변경    4.돌아가기");
        System.out.print(">> 선택 : ");
        String input = sc.nextLine();
        switch (input) {
		case "1": changePw(); break;
		case "2": changeEmail(); break;
		case "3": changePhone(); break;
		case "4": memberMenu(); break;
		

		default: System.out.println(">> 1 ~ 3을 입력해주세요.");
		}
    }
    
    
    
  

    
    // 시설 목록
    public void facInfo() {
    	while(true) {
            System.out.println();
            System.out.println(">> MEMBER only SYSTEM 사용자 모드 -----");
            System.out.println("   1.시설리스트    2.시설 예약정보   3.시설 이용내역   4.돌아가기");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();
            switch (input) {
            case "1":    facList(); break;
            case "2":    facResInfo();	 break;
            case "3":	 facUseDetail();	 break;
            case "4":    return;    
            default: System.out.println(">> 1 ~ 4을 입력해주세요."); 
            }
        }
    	
    	
    
    
    	
    }
    
    // 시설 리스트
    public void facList() {
    	
    }
    
    // 시설 예약 메서드
    public void facRes(String fac_input) {
    	
    }
    
    
    // 예약 완료된 시설 리스트
    public void facResInfo() {
    	
		
    	String input = sc.nextLine();
    	resCancel(input);
    }
    
    // 예약 취소
    public void resCancel(String cancel_input) {
    	
    }
    
    public void facUseDetail() {
    	
    }


    
    // 비밀번호 변경
    public void changePw() {
    	
    }
    
    // 이메일 변경
    public void changeEmail() {
    	
    }
    
    // 폰번호 변경
    public void changePhone() {
	
}
    
    
    public void join() {
        
        uvo = new UserVO();
        // String userId, userPass, userName, userPhone;
        
        System.out.println();
        System.out.print("아이디 입력 : ");
        uvo.setId(sc.nextLine());
        
        // 2번 입력받아서 확인받기
        System.out.print("비밀번호 입력 : ");
        uvo.setPw(sc.nextLine());
        
        System.out.print("이름 입력 : ");
        uvo.setName(sc.nextLine());
        
        // 연락처
        System.out.print("연락처 입력 : ");
        uvo.setPhone(sc.nextLine());
        
        boolean result = udao.insertUser(uvo);
        if(result == true) {
            System.out.println(">> 회원 가입이 완료되었습니다.");
            System.out.println(">> 로그인 후 이용해주세요.");
        } else {
            System.out.println(">> 회원 가입에 실패하였습니다.");
            System.out.println(">> 잠시 후 다시 이용해주세요.");
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
    
 // 아이디 찾기
    public void findId() {
        System.out.print("   이름 : ");
        String name = sc.nextLine();
        
        System.out.print("   이메일 : ");
        String email = sc.nextLine();
        
        String id = udao.selectId(name, email);
        
        if (id != null) {
            System.out.println("찾은 아이디 : " + id);
        } else {
            System.out.println("아이디를 찾을수없습니다.");
        }
  }
    
    // 비밀번호 찾기
    public void findPw() {
    	System.out.println();
    	System.out.println(">> 비밀번호를 찾습니다.");
    	System.out.print("  아이디 : ");
    	String userId = sc.nextLine();
    	System.out.print("  이메일 : ");
    	String userEmail = sc.nextLine();
    	
    	String pw = udao.selectPw(userId, userEmail);
    	
    	if (pw != null) {
    		UserVO uvo = udao.selelctMember(userId);
    		System.out.println(">> 비밀번호 : " + uvo.getPw());
    	} else {
    		System.out.println(">> 아이디 또는 이메일이 잘못입력됐습니다.");
    	}
    }
    
    
    public void userList() {
    	
    }
    
    public void userSearch() {
    	
    }
    
    
	public static void main(String[] args) {
		new RentalMain().menu();

	}

}
