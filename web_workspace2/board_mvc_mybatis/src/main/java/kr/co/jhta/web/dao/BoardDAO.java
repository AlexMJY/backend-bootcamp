package kr.co.jhta.web.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.jhta.web.vo.BoardVO;

public class BoardDAO {
	public SqlSessionFactory factory;
	
	public BoardDAO() {
		// 설계도
		try {
			Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); // 건설 노동자
			factory = builder.build(r);  // 공장
			r.close();  // 설계도 닫기
			
		} catch (IOException e) {
			System.out.println("config.xml 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}
	} // constructor() end
	
	public int getTotalCount() {
		SqlSession ss =  factory.openSession(true); // openSession(true) : auto commit
//		ss.selectOne("별칭", 매개변수);
		int count = ss.selectOne("kr.co.jhta.board.getTotal");
		ss.close();
		return count;
	} // getTotalCount() end
	
	
	public List<BoardVO> selectAll(int startNo, int endNo) { // 전체 게시물 추출
		SqlSession ss = factory.openSession(true);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		
		List<BoardVO> list =  ss.selectList("kr.co.jhta.board.allSelectBoard", map);
		ss.close();
		return list;
	} // selectAll() end
	
	public void addOne(BoardVO vo) {
		SqlSession ss = factory.openSession(true);
		ss.insert("kr.co.jhta.board.addOne", vo);
		ss.close();
	} // addOne() end
	
	public BoardVO getOne(int bno) {
		SqlSession ss = factory.openSession(true);
		BoardVO vo =  ss.selectOne("kr.co.jhta.board.getOne", bno);
		ss.close();
		return vo;
	}
	
	public void updateOne(BoardVO vo) {
		SqlSession ss = factory.openSession(true);
		ss.update("kr.co.jhta.board.updateOne", vo);
		ss.close();
	}

	public void deleteOne(int bno) {
		SqlSession ss = factory.openSession(true);
		ss.delete("kr.co.jhta.board.deleteOne", bno);
		ss.close();
	}
	
	
	
	
	
	
	
	
	
	
}
