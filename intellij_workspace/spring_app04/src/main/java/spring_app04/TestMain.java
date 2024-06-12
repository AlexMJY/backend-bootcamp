package spring_app04;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/application.xml"));
        CommonDAO dao = factory.getBean("dao", CommonDAO.class);
        dao.connect();
        List<DeptDTO> list = dao.selectAll();
        for (DeptDTO dto : list) {
            System.out.println(dto);
        }
    }
}
