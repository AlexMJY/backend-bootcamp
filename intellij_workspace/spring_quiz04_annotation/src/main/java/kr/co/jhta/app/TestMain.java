package kr.co.jhta.app;

import kr.co.jhta.app.dao.CommonDAO;
import kr.co.jhta.app.dto.EmpDTO;
import kr.co.jhta.app.service.EmpService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("app.xml");
        
        // emp 테이블 전체조회
        // 1건 조회
        // 추가, 변경, 삭제

        EmpService es = ac.getBean("service", EmpService.class);
        List<EmpDTO> list =  es.readAll();
        for (EmpDTO dto : list) {
            System.out.println(dto);
        }

    }
}
