package kr.co.jhta.web.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.jhta.web.vo.DeptVO;

public class DeptDAO {
	private SqlSessionFactory factory;

	public DeptDAO() {
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
	} // constructor end
	
	public List<DeptVO> queryAllDept() {
		SqlSession ss =  factory.openSession(true);
		List<DeptVO> list =  ss.selectList("kr.co.jhta.queryAllDept");
		ss.close();
		return list;
	} // queryAllEmp() end
	
	public DeptVO queryDeptOne(int deptno) {
		SqlSession ss = factory.openSession(true);
		DeptVO vo =  ss.selectOne("kr.co.jhta.queryDeptOne", deptno);
		ss.close();
		return vo;
	} // queryEmpOne() end
	
	public void insertDeptOne(DeptVO vo) {
		SqlSession ss = factory.openSession(true);
		ss.insert("kr.co.jhta.insertDeptOne", vo);
		ss.close();
	} // insertEmpOne() end
	
	public void updateDeptOne(DeptVO vo) {
		SqlSession ss = factory.openSession(true);
		ss.update("kr.co.jhta.updateDeptOne", vo);
		ss.close();
	} // updateEmpOne() end
	
	public void deleteDeptOne(int deptno) {
		SqlSession ss = factory.openSession(true);
		ss.delete("kr.co.jhta.deleteDeptOne", deptno);
		ss.close();
	} // deleteEmpOne() end
	
	
}
