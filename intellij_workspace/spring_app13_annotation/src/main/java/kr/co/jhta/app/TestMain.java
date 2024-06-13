package kr.co.jhta.app;

import java.sql.SQLException;
import java.util.List;

import kr.co.jhta.app.dao.CommonDAO;
import kr.co.jhta.app.dto.DeptDTO;
import kr.co.jhta.app.service.DeptService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) throws SQLException {
		
//		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/app.xml"));
		ApplicationContext context = new ClassPathXmlApplicationContext("app.xml");

//		CommonDAO dao = context.getBean("dao", CommonDAO.class);
//		dao.connect();
//		List<DeptDTO> list = dao.selectAll();
//		for (DeptDTO dto : list) {
//			System.out.println(dto);
//		}

		DeptService service = context.getBean("service", DeptService.class);
		List<DeptDTO> list =  service.readAll();
		for (DeptDTO dto : list) {
			System.out.println(dto);
		}

//		DeptDTO dto10 = service.readOne(10);
//		System.out.println(dto10);
//
//		dto10.setDname("Writer");
//		dto10.setLoc("하와이");
//		service.modify(dto10);

//		service.drop(10);

//		DeptDTO dtoNew = new DeptDTO(10, "eater", "hyehwadong");
//		service.write(dtoNew);

	}
}
