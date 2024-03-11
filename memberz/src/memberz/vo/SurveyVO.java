package memberz.vo;

import java.util.Date;

public class SurveyVO {
    private int sno;
    private String title;
    private String num1;
    private String num2;
    private Date startDate;
    private int endDate;
    private int num1Cnt;
    private int num2Cnt;

    public void setSno(int sno) {
        this.sno = sno;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public void setNum1Cnt(int num1Cnt) {
        this.num1Cnt = num1Cnt;
    }

    public void setNum2Cnt(int num2Cnt) {
        this.num2Cnt = num2Cnt;
    }


    public int getSno() {
        return sno;
    }

    public String getTitle() {
        return title;
    }

    public String getNum1() {
        return num1;
    }

    public String getNum2() {
        return num2;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public int getNum1Cnt() {
        return num1Cnt;
    }

    public int getNum2Cnt() {
        return num2Cnt;
    }
}
