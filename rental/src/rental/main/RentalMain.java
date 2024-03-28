package rental.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rental.dao.FacDAO;
import rental.vo.FacVO;
import rental.vo.ResVO;
import rental.vo.ReviewVO;


public class RentalMain {
    private FacVO fvo;
    private FacDAO fdao; 
    private ReviewVO reviewVo;


    public RentalMain() {
        fdao = new FacDAO(); // NullPointerException은 new로 선언 안한 경우가 대부분
    }
    
    
    // 시설조회
    public void adminFacSearch() {
    	
    	System.out.println();
    	System.out.println("   < 시설 정보 조회 >");
    	System.out.print(">>  시설 아이디 입력 : ");	

    	String id = UserMain.sc.nextLine();
    	FacVO fvo = fdao.selectFac(id);
    	
    	
    	if (fvo == null) {
    		System.out.println("  !! 존재하지 않는 시설입니다. !!");
    		return;
    	} else {
    		System.out.println(">>  시설번호 : " + fvo.getFacNo());
            System.out.println(">>  시설이름 : " + fvo.getFacName());
            System.out.println(">>  시설주소 : " + fvo.getFacAddr());
            
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date crtDate;
			try {
				crtDate = sdf.parse(fvo.getCreateAt());
				System.out.println(">>  시설등록일 : " + sdf.format(crtDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
            return;
    	}
    }
    
    // 시설 리스트
    public List<FacVO> adminFacList() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<FacVO> userList = fdao.selectFac();
        if (userList == null || userList.size() < 1) {
        	System.out.println("  !! 등록된 시설이 없습니다. !!");
        } else {
        	System.out.println("시설번호 | 시설이름 | 시설주소 | 시설등록일");
        	System.out.println("--------------------------------------------");
        	for (FacVO fvo : userList) {
        		try {
        			Date crtDate = sdf.parse(fvo.getCreateAt());
            		System.out.println(
            				fvo.getFacNo() + " | " +
            						fvo.getFacName() + " | " +
            						fvo.getFacAddr() + " | " +
            				sdf.format(crtDate)
            				);
        		} catch (Exception e) {
        			e.printStackTrace();
        		}	
        	}
        }
        return userList;
    }

    // 시설 등록
    public void adminFacRegist() {
    	fvo = new FacVO();
        System.out.println();
        System.out.println("   <시설 등록>");
        
        System.out.print(">>  시설 이름 : ");
        String name = UserMain.sc.nextLine();
        fvo.setFacName(name);
        
        System.out.print(">>  시설 주소 : ");
        String addr = UserMain.sc.nextLine();
        fvo.setFacAddr(addr);
        
        System.out.print(">>  시설 등록일 : ");
        String crtd = UserMain.sc.nextLine();
        fvo.setCreateAt(crtd);
        
        boolean result = fdao.registerFac(fvo);
        if (result) {
            System.out.println("  ~ 시설 등록이 완료되었습니다. ~");
        } else {
            System.out.println("  !! 시설 등록에 실패하셨습니다. !!");
        }
    }
    
    // 시설 목록
    public void facInfo() {
    	while(true) {
            System.out.println();
            System.out.println(">> MEMBER only SYSTEM 사용자 모드 -----");
            System.out.println("   1.시설 리스트   2.시설 예약정보   3.시설 이용내역   4.돌아가기");
            System.out.print(">> 선택 : ");
            String input = UserMain.sc.nextLine();
            switch (input) {
            case "1":    facList();		break;
            case "2":    facResInfo();	break;
            case "3":	 facUseDetail();break;
            case "4":    return;    
            default: 
            }
        }
    }
    
    public void facList() {
    	List<FacVO> facList = adminFacList();
    	
    	while(true) {
            System.out.println();
            System.out.println(">> MEMBER only SYSTEM 사용자 모드 -----");
            System.out.println("   1.시설 예약   2.시설 후기 보기   3.돌아가기");
            System.out.print(">> 선택 : ");
            String input = UserMain.sc.nextLine();
            switch (input) {
            case "1":    facRes(facList);		break;
            case "2":    facReview(facList);	break;
            case "3":	return;
            default: 
            }
        }
    	

    }
    
    // 시설 리뷰 보기
    public void facReview(List<FacVO> facList) {
    	
    	System.out.print("   리뷰를 볼 시설의 번호를 입력하세요. (취소 0)\n>> 입력 : ");
    	int facNo = Integer.valueOf(UserMain.sc.nextLine());
    	
    	if(facNo == 0) {
    		System.out.println("  !! 취소하셧습니다. 시설메뉴로 돌아갑니다. !!");
    		return;
    	} else if (facList.size() < facNo) {
    		System.out.println("  !! 초과값입력. 시설메뉴로 돌아갑니다. !!");
    		return;
    	}
    	
    	int page = 1;
    	int lastPage;
    	FacVO fvo = facList.get(facNo -1);
    	
    	while (true) {
	    	List<ReviewVO> reviewList = fdao.selectFacReview(fvo.getFacName(), page);
	    	
	    	if(reviewList == null || reviewList.size() < 1) {
	    		System.out.println("  !! 등록된 리뷰가 없습니다. !!");
	    		return;
	    	}
	    	System.out.println("\n" + fvo.getFacName() + "리뷰-------------------------------------");
	    	lastPage = reviewList.get(0).getCount();
	    	for (int i = 0; i < reviewList.size(); i++) {
	    		try {
	    			ReviewVO reviewVo = reviewList.get(i);
	    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    			Date crtDate = sdf.parse(reviewVo.getCreateAt());
		
	    			System.out.println("작성자 : " + reviewVo.getUserId() + " 작성일 : " + sdf.format(crtDate));
	    			System.out.println("내용 : " + reviewVo.getContent());
	    			System.out.println("-----------------------------------------------------------------------");
		
	    		} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
	    	System.out.println("< " + page + " / " + lastPage + " >");
	    	
	    	System.out.print("   이동하고 싶은 페이지 입력 (종료 : 0)\n>>  입력: ");
	    	page = Integer.valueOf(UserMain.sc.nextLine());
	    	
	    	if (page == 0) {
	    		return;
	    	}
    	
    	}
    }
    
    
    // 시설 예약 메서드
    public void facRes(List<FacVO> facList) {
    	
    	System.out.print("    예약할 시설의 번호를 입력하세요. (취소 0)\n>> 입력 : ");
    	int facNo = Integer.valueOf(UserMain.sc.nextLine());
    	
    	if(facNo == 0) {
    		System.out.println("  !! 취소하셧습니다. 시설메뉴로 돌아갑니다. !!");
    		return;
    	} else if (facList.size() < facNo) {
    		System.out.println("  !! 초과값입력. 시설메뉴로 돌아갑니다. !!");
    		return;
    	}

    	FacVO fvo = facList.get(facNo -1);

    	
    	// 달력 만들기
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONDAY) + 1;
		
		cal.set(year, month, 1);
		
		int startDay = cal.get(Calendar.DAY_OF_WEEK);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		
		
		// 다음 달 예약된 일 전체 가져오기.
		System.out.println(fvo.getFacName());
		ArrayList<Integer> dayList = fdao.rentalDaySelect(fvo);
		
		if (cal.get(Calendar.MONTH) + 1 == 1) {
			year += 1;
		}
		
		System.out.println();
		System.out.println(
				year + "년 " + (cal.get(Calendar.MONTH) + 1) + "월 " + 
				fvo.getFacName() + " 예약"
			
		);
		
		// 요일 출력
		String[] week = {"일", "월", "화", "수", "목", "금", "토"};
		for (String s : week) {
			System.out.print(s + "\t");
		}
		System.out.println("\n--------------------------------------------------");
		
		// 시작 요일 전까지 '\t'
		for (int i = 1; i < startDay; i++) {
			System.out.print("\t");
		}
		
		// 달력 출력
		for (int i = 1; i <= lastDay; i++) {
			
			// 예약이 안되는 날은 x로 표시
			if (dayList.contains(i)) {
				System.out.printf("%2s\t", "x");
			} else {
				System.out.printf("%2d\t", i);
			}
			
			// 7일 마다 줄바꿈
			if (startDay++ % 7 == 0) {
				System.out.println();
			}
		}
		System.out.println();
		
		
		// 선택했을때 없는데이터 / 중복 날짜 처리해야함.
    	System.out.print("   예약일을 선택해주세요. (취소 0)\n>> 입력 : ");
    	String selecRentalDate = year + "-" + (cal.get(Calendar.MONTH) + 1) + "-";
    	int selectDay = Integer.valueOf(UserMain.sc.nextLine());
    	
    	// 이미 예약 된 날짜 및 지정된 값 외 입력시
    	if (selectDay == 0 ) {
    		System.out.println("  !! 취소하셧습니다. 시설메뉴로 돌아갑니다. !!");
    		return;
    	}
    	else if (dayList.contains(selectDay)) {
    		System.out.println("  !! 이미 예약된 날짜입니다. !!");
    		return;
    	} else if (0 >= selectDay || lastDay + 1 <= selectDay){
    		System.out.println(">> " + year + "년 " + (cal.get(Calendar.MONTH) + 1) + "월예약은 1일 ~ " + lastDay + "일 중에 선택해주세요.");
    		return;
    	} else {
    		selecRentalDate += selectDay;
    	}
    	
    	// 예약정보 INSERT
    	ResVO rvo = new ResVO();
    	rvo.setUserId(UserMain.loginId);
    	rvo.setFacName(fvo.getFacName());
    	rvo.setResDate(selecRentalDate);
    	
    	// 이용신청 insert 및 출력
    	try {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	Date crtDate = sdf.parse(fvo.getCreateAt());
	    	
	    	if (fdao.insertRental(rvo)) {
	    		System.out.println("    " + sdf.format(crtDate) + " | " + fvo.getFacName() + " 이용신청이 성공하였습니다.");
	    	} else {
	    		System.out.println("    " + sdf.format(crtDate) + " | " + fvo.getFacName() + " 이용신청에 실패하였습니다.");
	    	}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    // 시설 예약정보
    public void facResInfo() {
    	
    	List<ResVO> resList = fdao.resSelect(UserMain.loginId);
    	if (resList == null || resList.size() < 1) {
    		System.out.println("  !! 예약한 시설이 없습니다. !!");
    	} else {
    		System.out.println("번호 | 예약번호 | 시설이름 | 예약일 | 신청시간");
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		for (int i = 0; i < resList.size(); i++) {
    			try {
					ResVO rvo = resList.get(i);
					Date crtDate = sdf.parse(rvo.getResDate());
					System.out.println((i + 1) + " | " + rvo.getResNo() + " | " + rvo.getFacName() + " | "
								  + sdf.format(crtDate) + " | " + rvo.getApplicationDate());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	}
    	
    	
    	while(true) {
            System.out.println();
            System.out.println("     < 유저 모드 >");
            System.out.println("   1.예약취소   2.돌아가기");
            System.out.print(">> 선택 : ");
            String input = UserMain.sc.nextLine();
            switch (input) {
            case "1":
            	System.out.print("   예약 취소할 번호를 입력해주세요. (취소 0)\n>> 입력 : ");
            	int num = Integer.valueOf(UserMain.sc.nextLine());
            	if (num == 0) {
            		System.out.println("  !! 취소하셧습니다. 시설메뉴로 돌아갑니다. !!");
            		return;
            	}
            	
            	try {
            		resCancel(resList.get(num-1));
            	} catch (Exception e) {
					System.out.println("  !! 존재하지 않는 번호입니다.  !!");
				}
            	break;
            	
            case "2":    return;    
            default: 
            }
        }
    }
    
    // 예약 취소
    public void resCancel(ResVO rvo) {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");        
    	Date dataDate;
    	
		try {
			dataDate = formatter.parse(rvo.getResDate());
			
	        Date currentDate = new Date();
	
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.DAY_OF_YEAR, 1);
	        Date tomorrowDate = calendar.getTime();
	
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Date crtDate = sdf.parse(rvo.getResDate());

	        if (sdf.format(dataDate).equals(sdf.format(currentDate)) ||
	           sdf.format(dataDate).equals(sdf.format(tomorrowDate))) {
	            System.out.println("  !! 예약취소는 이틀 전까지만 가능합니다. !!");
	        } else {
	        	if (fdao.deleteRental(rvo.getResNo())) {
	        		System.out.println(sdf.format(crtDate) + " | " + rvo.getFacName() + "의 예약이 취소되었습니다.");
	        	}
	        }
	        
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    
    public void facUseDetail() {
        // resno, facname, res_date 가져오기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<ResVO> useList = fdao.facUseList(UserMain.loginId);

        if (useList == null || useList.size() < 1) {
           System.out.println(" !! 이용한 시설이 없습니다. !!");
        } else {
           System.out.println("시설번호   |   시설이름   |   예약일");
           System.out.println("--------------------------------------");

           for (int i = 0; i < useList.size(); i++) {
              try {
                 ResVO rvo = useList.get(i);
                 Date crtDate = sdf.parse(rvo.getResDate());
                 System.out.println(
                       (i + 1) + " | " + rvo.getResNo() + " | " + rvo.getFacName() + " | " + sdf.format(crtDate));
              } catch (Exception e) {
                 e.printStackTrace();
              }
           }
        }

        System.out.println("   리뷰를 작성하시겠습니까?");
        System.out.println(">>  예 - 1  / 아니요 - 2  : ");
        String reviewYesNo = UserMain.sc.nextLine();
        switch (reviewYesNo) {
        case "1":
           System.out.println("   리뷰를 남길 시설 번호를 입력해주세요 : ");
           int input = Integer.valueOf(UserMain.sc.nextLine());
           
           if (useList.get(input - 1).getContent() != null) {
               System.out.println();
               System.out.println("   이미 리뷰가 존재합니다.");
               System.out.println("   이전 메뉴로 돌아갑니다.");
               System.out.println();
               facUseDetail();
               break;
           } else {
               registerReview(useList.get((input) - 1));
               break;
           }
        case "2":    return;
        default:        break;
        }
     }

    // 리뷰 등록
    public void registerReview(ResVO rvo) {
       reviewVo = new ReviewVO();

       System.out.print(" 리뷰 입력 : ");
       String contentInput = UserMain.sc.nextLine();

       reviewVo.setReviewNo(rvo.getResNo());
       reviewVo.setUserId(rvo.getUserId());
       reviewVo.setFacName(rvo.getFacName());
       reviewVo.setContent(contentInput);

       boolean result = fdao.insertReview(reviewVo);

       if (result == true) {
          System.out.println("  ~ 리뷰 작성이 완료되었습니다. ~");
       } else {
          System.out.println("  !! 리뷰 작성작성에 실패했습니다. !!");
       }
    }

    // 리뷰 리스트
    public void facReviewList() {
       
       List<ReviewVO> reviewList = fdao.selectReview(UserMain.loginId);

       if (reviewList == null || reviewList.size() < 1) {
          System.out.println("  !! 등록된 리뷰가 없습니다. !!");
       } else {
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          System.out.println("인덱스  |  리뷰번호  | 시설이름  | 등록일   |   리뷰");

          for (int i = 0; i < reviewList.size(); i++) {
             try {
                ReviewVO reviewVo = reviewList.get(i);
                Date crtDate = sdf.parse(reviewVo.getCreateAt());

                System.out.println((i + 1) + " | " + reviewVo.getReviewNo() + " | " + reviewVo.getFacName() + " | "
                      + sdf.format(crtDate) + " | " + reviewVo.getContent());
             } catch (Exception e) {
                e.printStackTrace();
             }
          }
       }


       System.out.println("   리뷰를 수정하시겠습니까? (예: 1 / 아니요: 2) : ");

       String reviewEditYesNo = UserMain.sc.nextLine();
       
       switch (reviewEditYesNo) {
       case "1":
          System.out.println(">>  수정할 리뷰 번호를 입력해주세요 : ");
          int input = Integer.valueOf(UserMain.sc.nextLine());
          reviewEdit(reviewList.get((input) - 1));
          break;
       case "2":
          return;
       default:
          break;
       }
    }

    // 리뷰 수정
    public void reviewEdit(ReviewVO reviewVo) {

       // update review
       System.out.print("리뷰를 입력 : ");
       String reviewContent = UserMain.sc.nextLine();

       boolean result = fdao.updateReview(reviewVo, reviewContent);
       if (result) {
          System.out.println("  ~ 리뷰 수정이 완료되었습니다. !");
       } else {
          System.out.println("  !! 리뷰 수정에 실패했습니다. !!");
       }

    }
    

}