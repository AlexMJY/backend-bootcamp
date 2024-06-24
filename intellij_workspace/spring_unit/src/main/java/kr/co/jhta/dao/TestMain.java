package kr.co.jhta.dao;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {

        OracleDAO dao = new OracleDAO();

        List<DeptDTO> list = dao.deptList();
        System.out.println("Dept List : " + list);
    }
}
