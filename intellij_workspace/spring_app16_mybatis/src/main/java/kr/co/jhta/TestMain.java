package kr.co.jhta;

import kr.co.jhta.dao.ConnectionManager;
import kr.co.jhta.dao.Dao;
import kr.co.jhta.dao.DeptDAO;
import kr.co.jhta.dto.DeptDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
//        ConnectionManager cm = new ConnectionManager();
        ApplicationContext ac = new ClassPathXmlApplicationContext("app.xml");
        Dao d = ac.getBean("dao", Dao.class);
//        List<DeptDTO> list = d.selectAll();
//        for (DeptDTO dto : list) {
//            System.out.println(dto);
//        }
        d.selectAll().forEach(System.out::println);
        System.out.println("-----------------------------------------------------------------");

        DeptDTO dto10 = d.selectOne(10);
        System.out.println(dto10);
        System.out.println("-----------------------------------------------------------------");

        DeptDTO dtoNew = new DeptDTO();
        dtoNew.setDeptno(44);
        dtoNew.setDname("gamer");
        dtoNew.setLoc("tokyo");
//        d.insertOne(dtoNew);
//        d.selectAll().forEach(System.out::println);
        System.out.println("-----------------------------------------------------------------");

        dto10.setDname("cafemanager");
        dto10.setLoc("incheon");
//        d.updateOne(dto10);

        System.out.println("-----------------------------------------------------------------");
        d.selectAll().forEach(System.out::println);

        System.out.println("-----------------------------------------------------------------");
        d.deleteOne(10);
        d.selectAll().forEach(System.out::println);



    }
}
