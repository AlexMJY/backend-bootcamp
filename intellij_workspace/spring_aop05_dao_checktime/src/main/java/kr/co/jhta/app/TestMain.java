package kr.co.jhta.app;

import kr.co.jhta.app.dto.EmpDTO;
import kr.co.jhta.app.service.DBService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("app.xml");

        DBService service = ac.getBean("service", DBService.class);

        // 요 작업이 시간이 너무 지연...
        List<EmpDTO> list = service.readAll();
        list.forEach(System.out::println);
    }
}
