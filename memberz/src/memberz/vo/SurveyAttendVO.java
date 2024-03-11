package memberz.vo;

import java.util.Date;

public class SurveyAttendVO {
    // ANO, SNO, ID, NUM, ATTENDDATE
    private int ano;
    private int sno;
    private String id;
    private int num;
    private Date attendDate;

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setAttendDate(Date attendDate) {
        this.attendDate = attendDate;
    }

    public int getAno() {
        return ano;
    }

    public int getSno() {
        return sno;
    }

    public String getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public Date getAttendDate() {
        return attendDate;
    }
}
