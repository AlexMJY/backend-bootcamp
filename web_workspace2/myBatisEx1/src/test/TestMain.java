package test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vo.DeptVO;

public class TestMain {
	public static void main(String[] args) {
		System.out.println("Hello MyBatis World");
		
		// MyBatis 실행방법
		// 1. MyBatis의 설정파일 읽어오기 (설계도)
		try {
			Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
			
		// Connection conn == SqlSession <== SqlSessionFactory <= SqlSessionFactoryBuilder
		
		// 2. 건설 노동자 : SqlSessionFactoryBuilder
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		// 3. 공장 건설 
			SqlSessionFactory factory =  builder.build(r);
			
		// 4. 설계도 닫기
			r.close();
			
		// 5. 자동차 생산
			SqlSession ss =  factory.openSession(true);  // true : auto commit
			
			System.out.println("ss : " + ss);
			
			// JDBC : Connection
			// MyBatis : SqlSession
			
			
			System.out.println("======================================================================");
			// 1건 추가
			DeptVO vo2 = new DeptVO(99, "회계", "LA");
			ss.insert("addOne", vo2);
			
			
			System.out.println("======================================================================");
			// 데이터 모두 검색
			List<DeptVO> list = ss.selectList("AllDept");
			System.out.println("list : " + list);
			
			// for문 사용해서 부서번호 : 부서명 : 위치 형식 출력
			for (DeptVO vo : list) {
				System.out.println(vo.getDeptno() + " : " + vo.getDname() + " : " + vo.getLoc());
			}
			
			System.out.println("======================================================================");
			
			// Stream 사용해서 부서번호 : 부서명 : 위치 : 형식 출력
			list.stream().forEach(vo -> System.out.println(vo.getDeptno() + " : " + vo.getDname() + " : " + vo.getLoc()));
			
			System.out.println("======================================================================");
			// 1건 데이터 읽기
			DeptVO vo = ss.selectOne("SelectOne", 20);
			System.out.println("1건 조회 : " + vo.getDeptno() + " : " + vo.getDname() + " : " + vo.getLoc());
			
			System.out.println("======================================================================");
			// 6번 부서 값 변경
			DeptVO vo3 = new DeptVO();
			vo3.setDeptno(2);
			vo3.setDname("IT");
			vo3.setLoc("울산");
			ss.update("modifyOne", vo3);
			DeptVO vo4 = ss.selectOne("SelectOne", 6);
			System.out.println("1건 조회 : " + vo4.getDeptno() + " : " + vo4.getDname() + " : " + vo4.getLoc());
			
			System.out.println("======================================================================");
			// 6번 부서 삭제
			ss.delete("deleteDeptOne", 6);
			
			// 자원반납
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
