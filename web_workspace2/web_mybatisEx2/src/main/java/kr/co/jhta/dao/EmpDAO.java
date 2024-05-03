package kr.co.jhta.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.jhta.vo.EmpVO;

public class EmpDAO {
	private SqlSessionFactory factory;
	
	public EmpDAO() {
		// 1. 설계도
		try {
			Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
		// 2. 건설노동자
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		// 3. 공장
			factory = builder.build(r);
		// 4. 설계도 닫기
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  // constructor end
	
	public List<EmpVO> queryAllEmp() {
		SqlSession ss =  factory.openSession(true);
		List<EmpVO> list =  ss.selectList("kr.co.jhta.web.queryAllEmp");
		ss.close();
		return list;
	} // queryAllEmp() end
	
	public EmpVO queryEmpOne(int empno) {
		SqlSession ss = factory.openSession(true);
		EmpVO vo =  ss.selectOne("kr.co.jhta.web.queryEmpOne", empno);
		ss.close();
		return vo;
	} // queryEmpOne() end
	
	public void insertEmpOne(EmpVO vo) {
		SqlSession ss = factory.openSession(true);
		ss.insert("kr.co.jhta.web.insertEmpOne", vo);
		ss.close();
	} // insertEmpOne() end
	
	public void updateEmpOne(EmpVO vo) {
		SqlSession ss = factory.openSession(true);
		ss.update("kr.co.jhta.web.updateEmpOne", vo);
		ss.close();
	} // updateEmpOne() end
	
	public void deleteEmpOne(int empno) {
		SqlSession ss = factory.openSession(true);
		ss.delete("kr.co.jhta.web.deleteEmpOne", empno);
		ss.close();
	} // deleteEmpOne() end
	
}
