package test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vo.EmpVO;

public class TestMain {
	
	public static void main(String[] args) {
		try {
			Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(r);
			r.close();
			SqlSession ss = factory.openSession(true);
			
			System.out.println("ss:"+ss);
			
			List<EmpVO> list = ss.selectList("selectAll");
			
			list.stream().forEach(vo -> System.out.println(
					vo.getEmpno() + " : " + 
					vo.getEname() + " : " +
					vo.getJob()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
