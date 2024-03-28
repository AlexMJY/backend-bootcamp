package rental.main;

import java.util.List;
import java.util.Scanner;

import rental.dao.UserDAO;
import rental.vo.UserVO;


public class UserMain {
	public static Scanner sc;
    public static String loginId;
    private UserVO uvo;
    private UserDAO udao;
    
    private RentalMain rm;

    public UserMain() {
    	sc = new Scanner(System.in);
        uvo = new UserVO();
        udao = new UserDAO();
        rm = new RentalMain();
    }
    
    // 시스템 메인 메뉴
    public void menu() {
        while(true) {
            System.out.println();
            System.out.println();
            System.out.println("                         < 메인메뉴 >                            ");
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
            default:
            }
            System.out.println();
        }
    }
    
    // 관리자 메인메뉴
    public void adminMenu(){
        while(true) {
            System.out.println();
            System.out.println("                <관리자 메뉴>");
            System.out.println("   1.회원관리     2.시설관리    3.로그아웃     ");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();
            switch (input) {     
            case "1":    adminMenuMember();  break;
            case "2":    adminMenuFac(); break;
            case "3": 	 menu(); break;
            default:
            	
            }
        }
    }
    
    // 관리자 회원목록 메뉴
    public void adminMenuMember() {
       while(true) {
          System.out.println();
          System.out.println("                   <회원 관리 모드>");
          System.out.println("   1.회원목록   2.회원정보조회   3.관리자 메뉴로 이동");
          System.out.print(">> 선택 : ");
          String input = sc.nextLine();
          switch (input) {
          case "1": userList(); break;
          case "2": userSearch(); break;
          case "3": adminMenu(); break;
          default:
        	  
         }
          
       }
    }
    
    // 1.회원목록
    public void userList() {
       List<UserVO> userList = udao.selectUser();
       
       if(userList == null || userList.size() < 1) {
    	   System.out.println("  !! 등록된 회원이 없습니다. !!");
       } else {
    	   System.out.println("아이디 | 이름 | 이메일 | 전화번호 | 가입일자");
    	   
    	   userList.forEach(uvo -> {
    		   System.out.println(uvo.getId() + " | " + uvo.getName() + " | "  + 
    	                          uvo.getEmail() + " | " + uvo.getPhone() + " | " + uvo.getJoinDate());
    	   }); 
       }
    }
    
    // 2.회원정보조회
    public void userSearch() {
    	
    	System.out.printf(">> 조회할 아이디 : ");
    	String id = sc.nextLine();
    	UserVO uvo = udao.selectUser(id);
    	
    	if (uvo == null) {
    		System.out.println("   !! 존재하지 않는 회원입니다. !!");
    		return;
    	}
    	
    	while (true) {
    		System.out.println("   아이디 : " + uvo.getId());
    		System.out.println("   비빌번호 : " + uvo.getPw());
    		System.out.println("   이름 : " + uvo.getName());
    		System.out.println("   이메일 : " + uvo.getEmail());
    		System.out.println("   전화번호 : " + uvo.getPhone());
    		System.out.println("   1.비밀번호 변경    2.이메일 변경   3.전화번호 변경   4.삭제   5.뒤로가기");
			System.out.print(">> 선택 : ");
			String input = sc.nextLine();
			String value = null;
			
			switch (input) {
			case "1":
				System.out.println(">>   비밀번호 : ");
				value = sc.nextLine();
				udao.updateUser("userPw", value, id);
				break;
				
			case "2":
				System.out.println(">>  새 이메일 : ");
				value = sc.nextLine();
				udao.updateUser("userEmail", value, id);
				
				break;
				
			case "3":
				System.out.println(">>   새 전화번호 : ");
				value = sc.nextLine();
				udao.updateUser("userPhone",  value, id);
				
				break;
				
			case "4":
				System.out.print("   " + uvo.getId() + "을(를) 삭제하시겟습니까? (Y / N)\n입력 : ");
				String answer = sc.nextLine();
				if (answer.equalsIgnoreCase("Y")) {
					if (udao.deleteUser(uvo.getId())) {
						System.out.println(uvo.getId() + "을(를) 삭제하였습니다.");
						adminMenuMember();
					} else {
						System.out.println(uvo.getId() + "의 삭제를 실패하였습니다.");
					}
					
				} else if (answer.equalsIgnoreCase("N")){
					System.out.println("취소했습니다.");
					
				} else {
					System.out.println("잘못 입력했습니다..");
				}
				
				break;
				
			case "5":  return;
				
			default:
				
				break;
			}
    		
    	
    	}
    	
    }
      
    // 관리자 시설 메뉴
    public void adminMenuFac() {
        while(true) {
            System.out.println();
            System.out.println("                       <시설 관리 메뉴>");
            System.out.println("   1.시설조회     2.시설리스트    3.시설등록     4.로그아웃     ");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();
            switch (input) {     
            case "1":    rm.adminFacSearch(); break;
            case "2":    rm.adminFacList();    break;
            case "3":    rm.adminFacRegist();    break;
            case "4":     return;
            default: 
            }
        }
    }
    

    // user 메뉴
    public void userMenu() {
        while(true) {
            System.out.println();
            System.out.println("                       <유저 메뉴>");
            System.out.println("   1.내 정보    2.시설 정보   3.리뷰 목록   4.로그아웃");
            System.out.print(">> 선택 : ");
            String input = sc.nextLine();
            switch (input) {
            case "1":    userInfo(); break;
            case "2":    rm.facInfo();    break;
            case "3":    rm.facReviewList(); break;
            case "4":    return;    
            default: 
            }
        }
    }
    
    // user 정보 조회
    public void userInfo() {
    	
    	System.out.println("                       <유저 정보 조회>");
    	UserVO uvo = udao.selectUser(loginId);
    	
    	System.out.println("아 이 디 : " + uvo.getId());
    	System.out.println("이    름 : " + uvo.getName());
    	System.out.println("이 메 일 : " + uvo.getEmail());
    	System.out.println("전화번호 : " + uvo.getPhone());
    	System.out.println("가 입 일 : " + uvo.getJoinDate());
  
    	System.out.println();
        System.out.println("   1.비밀번호 변경    2.이메일 변경   3.전화번호 변경    4.돌아가기");
        System.out.print(">> 선택 : ");
        String input = sc.nextLine();
        switch (input) {
	        case "1": changePw(); break;
	        case "2": changeEmail(); break;
	        case "3": changePhone(); break;
	        case "4": userMenu(); break;
	        default:
        }
    }
    
    
    // user 비밀번호 변경
    public void changePw() {
    	System.out.println();
		System.out.println("    비밀번호를 변경합니다.   ");
		System.out.print(">>   기존 비밀번호 : ");
		String pw = sc.nextLine();
		uvo = udao.selectUser(loginId);
		
		if(!pw.equals(uvo.getPw())) {
			System.out.println("  !! 기존 비밀번호가 일치하지 않습니다. !!");
		} else {
			System.out.print(">>  신규 비밀번호 : ");
			pw = sc.nextLine();
			
			if ( udao.updateUser("userPw", pw, loginId) ) {
				System.out.println("  ~ 비밀번호가 변경되었습니다. ~ ");
			} else {
				System.out.println("  !! 비밀번호 변경에 실패하였습니다. !!");
			}
		}
    }
    
    // user 이메일 변경
    public void changeEmail() {
    	System.out.println();
		System.out.println("    이메일을 변경합니다.");
		System.out.print(">>  변경할 이메일 주소 : ");
		String email = sc.nextLine();
		
		if ( udao.updateUser("userEmail", email, loginId) ) {
			System.out.println("  ~ 이메일이 변경되었습니다. ~");
		} else {
			System.out.println("  !! 이메일에 변경에 실패하였습니다. !!");
		}
    }
    
    // user 전화번호 변경
    public void changePhone() {
    	System.out.println();
		System.out.println("    전화번호를 변경합니다.");
		System.out.print(">>  변경할 전화번호 : ");
		String phone = sc.nextLine();
		
		if ( udao.updateUser("userPhone", phone, loginId) ) {
			System.out.println("  ~ 전화번호가 변경되었습니다. ~");
		} else {
			System.out.println("  !! 전화번호 변경에 실패하였습니다.  !!");
		}
    }
    
    // 회원가입
    public void join() {
        
    	System.out.println("     <회원가입>");
        uvo = new UserVO();
        // String userId, userPass, userName, userPhone;
        
        System.out.println();
        System.out.print(">> 아이디 입력 : ");
        uvo.setId(sc.nextLine());
        
        // 2번 입력받아서 확인받기
        System.out.print(">> 비밀번호 입력 : ");
        uvo.setPw(sc.nextLine());
        
        System.out.print(">> 이름 입력 : ");
        uvo.setName(sc.nextLine());
        
        // 연락처
        System.out.print(">> 전화번호 입력 : ");
        uvo.setPhone(sc.nextLine());
        
        System.out.print(">> 이메일 입력 : ");
        uvo.setEmail(sc.nextLine());
        
        boolean result = udao.insertUser(uvo);
        if(result == true) {
            System.out.println("  ~ 회원 가입이 완료되었습니다. ~");
            System.out.println("    ~ 로그인 후 이용해주세요. ~");
        } else {
            System.out.println("  !! 회원 가입에 실패하였습니다. !!");
            System.out.println("  !! 잠시 후 다시 이용해주세요. !!");
        }
        
    }
    
    // 로그인
    public void login() {
    	uvo = new UserVO();
    	
    	
       System.out.println();
       System.out.println("    <로그인> ");
        
       System.out.print(">> 아이디 : ");
       String id = sc.nextLine();
       uvo.setId(id);
        
       System.out.print(">> 비밀번호 : ");
       String pw = sc.nextLine();
       uvo.setPw(pw);
       
       
       uvo = udao.loginChk(uvo);
       
       if ( uvo.getName() != null ) {
          loginId = uvo.getId();
          
          
          if (uvo.getAdmin()) {
             adminMenu();
          }
          else {
             userMenu();
          }
       } else {
          System.out.println("  !! 아이디 또는 비밀번호가 일치하지 않습니다. !!");
       }
    }
    

    
    // 아이디 찾기
    public void findId() {
    	System.out.println("    <아이디 찾기>");
        System.out.print(">>  이름 : ");
        String name = sc.nextLine();
        
        System.out.print(">>  이메일 : ");
        String email = sc.nextLine();
        
        String id = udao.selectId(name, email);
        
        if (id != null) {
            System.out.println(" 찾은 아이디 : " + id);
        } else {
            System.out.println("  !! 아이디를 찾을수없습니다. !!");
        }
  }
    
    // 비밀번호 찾기
    public void findPw() {
    	System.out.println("    <비밀번호 찾기>");
       System.out.println();

       System.out.print(">>  아이디 : ");
       String userId = sc.nextLine();
       System.out.print(">>  이메일 : ");
       String userEmail = sc.nextLine();
       
       String pw = udao.selectPw(userId, userEmail);
       String printPw = "";
       
       if (pw != null) {
    	   
    	   printPw = pw.substring(0, pw.length()/2);
			for (int i=0; i< pw.substring(pw.length()/2).length(); i++) {
				printPw += "*";
			}
			System.out.println("찾은 패스워드 : " + printPw);
       } else {
    	   System.out.println("  !! 아이디 또는 이메일이 잘못입력됐습니다. !!");
       }
    }
    

    public static void main(String[] args) {
      new UserMain().menu();

   }

}