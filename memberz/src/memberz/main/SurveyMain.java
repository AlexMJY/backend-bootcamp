package memberz.main;

import memberz.dao.SurveyDAO;
import memberz.vo.MemberVO;
import memberz.vo.SurveyAttendVO;
import memberz.vo.SurveyVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SurveyMain {
    private SurveyDAO sdao;
    private SurveyVO svo;
    private SurveyAttendVO savo;
    private int detail;


    public SurveyMain() {
        sdao = new SurveyDAO();
        svo = new SurveyVO();
        savo = new SurveyAttendVO();
    }

    public void memberSurveyMenu() {
        while (true) {
            System.out.println();
            System.out.println(">>\t\t1.설문 목록\t\t2.내 설문 보기\t\t3.시스템 종료\t\t4.메뉴로 돌아가기");
            System.out.print(">> 선택 : ");
            String input = MemberMain.sc.nextLine();

            switch (input) {
                case "1":  surveyList();
                    surveyDetail();
                    break;
                case "2":   break;
                case "3":
                    System.out.println("시스템을 종료합니다.");
                    MemberMain.sc.close();
                    System.exit(0);
                case "4":
                    new MemberMain().memberMenu();
                default:
                    System.out.println(">> 1 ~ 3을 입력해주세요.");
            }

        }
    }

    public void adminSurveyMenu() {
        while (true) {
            System.out.println();
            System.out.println(">>\t\t1.설문 목록\t\t2.설문 등록\t\t3.시스템 종료\t\t4.메뉴로 돌아가기");
            System.out.print(">> 선택 : ");
            String input = MemberMain.sc.nextLine();

            switch (input) {
                case "1":  surveyList(); break;
                case "2":  join(); break;
                case "3":
                    System.out.println("시스템을 종료합니다.");
                    MemberMain.sc.close();
                    System.exit(0);
                case "4":
                    new MemberMain().adminMenu();
                default:
                    System.out.println(">> 1 ~ 3을 입력해주세요.");
            }
        }


    }

    public void surveyList() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        List<SurveyVO> surveyList = sdao.selectSurvey();
        if (surveyList == null || surveyList.size() < 1) {
            System.out.println(">> 등록된 설문이 없습니다.");
        } else {
            System.out.println("아이디 | 제목 | 선택1 | 선택2 | 시작일자 | 마감일자");
            System.out.println("-----------------------------------------------");
            for (SurveyVO svo : surveyList) {
                try {
                    Date startDateFormat = sdf.parse(svo.getStartDate()); // String 타입을 Date 타입으로 변환
                    Date endDateFormat = sdf.parse(svo.getEndDate()); // String 타입을 Date 타입으로 변환
                    System.out.println(
                            svo.getSno() + " | "
                                    + svo.getTitle() + " | "
                                    + svo.getNum1() + " | "
                                    + svo.getNum2() + " | "
                                    + sdf.format(startDateFormat) + " | "
                                    + sdf.format(endDateFormat));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void surveyDetail() {
        while (true) {
            System.out.println();
            System.out.print(">> 상세히 볼 아이디(SNO) 선택 : ");
            int detail = MemberMain.sc.nextInt();

//        MemberMain.sc.nextLine();

            SurveyVO vo = sdao.selectSurvey(detail);
            System.out.println("sno : " + vo.getSno());
            System.out.println("title : " + vo.getTitle());
            System.out.print("항목1 - " + vo.getNum1() + " vs ");
            System.out.println("항목2 - " + vo.getNum2());
            System.out.println("startDate : " + vo.getStartDate());
            System.out.println("endDate : " + vo.getEndDate());
            System.out.println("num1cnt : " + vo.getNum1Cnt());
            System.out.println("num2cnt : " + vo.getNum2Cnt());

            voteSurvey(detail);
        }
    }

    public void voteSurvey(int detail) {
        System.out.print(">> 설문에 참여하려면 YES, 안하려면 NO을 입력해주세요 : ");
        while (true) {
            String sv = MemberMain.sc.nextLine();
            if (sv.equals("YES")) {
                if (checkSurvey()) {
                    System.out.println("이미 설문에 참여하셨습니다.");
                    new MemberMain().memberMenu();
                } else {
                    System.out.println("설문에 참여합니다~~");
                    doSurvey(detail);
                }


            } else if (sv.equals("NO")) {
                System.out.println("메인 메뉴로 돌아갑니다.");
                new MemberMain().memberMenu();

            }
        }
    }

    public boolean checkSurvey() {

        return false;
    }

    public void doSurvey(int detail) {
        System.out.println();
        System.out.println();
        System.out.println(">> ANO를 입력하세요");
        System.out.print(">> 입력 : ");
        String ano = MemberMain.sc.nextLine();
        savo.setAno(Integer.parseInt(ano));

//        System.out.print(">> SNO를 입력하세요 : " + savo.getSno());
//        String sno = MemberMain.sc.nextLine();
        savo.setSno(detail);

//            System.out.println(">> ID를 입력하세요");
        savo.setId(MemberMain.loginId);

        System.out.println(">> 선택할 항목을 선택 하세요 (1 또는 2)");
        System.out.print(">> 입력 : ");
        String num = MemberMain.sc.nextLine();
        savo.setNum(Integer.parseInt(num));


//        System.out.println(">> 설문 작성한 날짜를 입력하세요");
//        System.out.print(">> 입력 : ");
//        String attendDate = MemberMain.sc.nextLine();
//        savo.setAttendDate(attendDate);

        boolean result = sdao.doSurveyDAO(savo);
        if (result) {
            sdao.updateNumCnt(savo, savo.getNum());
            System.out.println(">> 설문작성을 완료했습니다.");
            new MemberMain().memberMenu();
        } else {
            System.out.println(">> 설문작성에 실패했습니다.");
            System.out.println(">> 메인 메뉴로 돌아갑니다.");
            new MemberMain().memberMenu();
        }
    }


    public void join() {  // 설문 생성
        svo = new SurveyVO();
        System.out.println();
        System.out.println(">> 설문생성");

        System.out.print("설문 번호 : ");
        int sno = MemberMain.sc.nextInt();
        MemberMain.sc.nextLine();
        svo.setSno(sno);

        System.out.print("설문 제목 : ");
        String title = MemberMain.sc.nextLine();
        svo.setTitle(title);

        System.out.print("num1 : ");
        String num1 = MemberMain.sc.nextLine();
        svo.setNum1(num1);

        System.out.print("num2 : ");
        String num2 = MemberMain.sc.nextLine();
        svo.setNum2(num2);

        System.out.print("startDate : ");
        String startDate = MemberMain.sc.nextLine();
        svo.setStartDate(String.valueOf(startDate));


        System.out.print("endDate : ");
        String endDate = MemberMain.sc.nextLine();
        svo.setEndDate(String.valueOf(endDate));

        System.out.print("num1Cnt : ");
        int num1Cnt = MemberMain.sc.nextInt();
        MemberMain.sc.nextLine();
        svo.setNum1Cnt(num1Cnt);

        System.out.print("num2Cnt : ");
        int num2Cnt = MemberMain.sc.nextInt();
        MemberMain.sc.nextLine();
        svo.setNum2Cnt(num2Cnt);

        boolean result = sdao.insertSurvey(svo);
        if (result) {
            System.out.println(">> 설문 생성이 완료되었습니다.");
        } else {
            System.out.println(">> 설문 생성에 실패하셨습니다.");
        }
    }
}
