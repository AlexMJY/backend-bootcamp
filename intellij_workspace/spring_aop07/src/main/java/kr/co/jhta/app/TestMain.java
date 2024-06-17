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

        List<EmpDTO> list = service.readAll();

        list.forEach(System.out::println);

        System.out.println("-----------------------");

        EmpDTO empDTO = service.readOne(10);
        System.out.println(empDTO);
    }
}
