package memberz.main;

import memberz.dao.SurveyDAO;
import memberz.vo.SurveyAttendVO;
import memberz.vo.SurveyVO;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SurveyMain {
    private SurveyDAO sdao;
    private SurveyVO svo;
    private SurveyAttendVO savo;

    public SurveyMain() {
        sdao = new SurveyDAO();
        svo = new SurveyVO();
        savo = new SurveyAttendVO();
    }

    public void memberSurveyMenu() {
        System.out.println();
        System.out.println(">>\t\t1.설문 목록\t\t2.내 설문 보기\t\t3.시스템 종료");
        System.out.print(">> 선택 : ");
        String input = MemberMain.sc.nextLine();

        switch (input) {
            case "1":  surveyList();
                surveyDetail(); break;
            case "2":   break;
            case "3":
                System.out.println("시스템을 종료합니다.");
                MemberMain.sc.close();
                System.exit(0);
            default:
                System.out.println(">> 1 ~ 3을 입력해주세요.");
        }

    }

    public void adminSurveyMenu() {
        System.out.println();
        System.out.println(">>\t\t1.설문 목록\t\t2.설문 등록\t\t3.시스템 종료");
        System.out.print(">> 선택 : ");
        String input = MemberMain.sc.nextLine();

        switch (input) {
            case "1":  surveyList(); break;
            case "2":  join(); break;
            case "3":
                System.out.println("시스템을 종료합니다.");
                MemberMain.sc.close();
                System.exit(0);
            default:
                System.out.println(">> 1 ~ 3을 입력해주세요.");
        }


    }

    public void surveyList() {
        List<SurveyVO> surveyList = sdao.selectSurvey();
        if (surveyList == null || surveyList.size() < 1) {
            System.out.println(">> 등록된 설문이 없습니다.");
        } else {
            System.out.println("아이디 | 제목 | 선택1 | 선택2 | 시작일자 | 마감일자");
            System.out.println("-----------------------------------------------");
            for (SurveyVO svo : surveyList) {
                System.out.println(svo.getSno() + " | " +
                        svo.getTitle() + " | " + svo.getNum1() + " | " +
                        svo.getNum2Cnt() + " | " + svo.getStartDate() + " | " +
                        svo.getEndDate());
            }
        }
    }

    public void surveyDetail() {
        System.out.println();
        System.out.print(">> 상세히 볼 아이디(SNO) 선택 : ");
        int sno = MemberMain.sc.nextInt();
//        MemberMain.sc.nextLine();

        SurveyVO vo = sdao.selectSurvey(sno);
        System.out.println("sno : " + vo.getSno());
        System.out.println("title : " + vo.getTitle());
        System.out.println("num1 : " + vo.getNum1());
        System.out.println("num2 : " + vo.getNum2());
        System.out.println("startDate : " + vo.getStartDate());
        System.out.println("endDate : " + vo.getEndDate());
        System.out.println("num1cnt : " + vo.getNum1Cnt());
        System.out.println("num2cnt : " + vo.getNum2Cnt());

        System.out.println(">> 설문 참여하시겠습니까?");
        System.out.print(">> 참여하려면 y, 안하려면 n 입력해주세요 : ");
        String sv = MemberMain.sc.nextLine();

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


        System.out.print("endDate : ");
        int endDate = MemberMain.sc.nextInt();
        MemberMain.sc.nextLine();
        svo.setEndDate(endDate);

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
