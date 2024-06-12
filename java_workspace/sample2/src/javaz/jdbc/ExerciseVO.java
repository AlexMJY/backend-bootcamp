package javaz.jdbc;

import java.sql.Date;

public class ExerciseVO {
    // t_emp 테이블의 컬럼과 일치하는 타입의 필드들 선언
    // empno, ename, deptno, sal, hiredate
    private int empno;
    private String ename;
    private int deptno;
    private int sal;
    private Date hireDate;

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    ////////////////////////////////////

    public int getEmpno() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public int getDeptno() {
        return deptno;
    }

    public int getSal() {
        return sal;
    }

    public Date getHireDate() {
        return hireDate;
    }
}
