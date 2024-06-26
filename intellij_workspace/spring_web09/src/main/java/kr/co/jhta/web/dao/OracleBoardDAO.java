package kr.co.jhta.web.dao;

import kr.co.jhta.web.dao.CommonDAO;
import kr.co.jhta.web.dto.BoardDTO;
import kr.co.jhta.web.dto.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @component : Spring이 관리해라(범용적)
// @Repository : component와 기능은 같음, db와 직접 연결된경우
@Repository("oracleDB")
public class OracleBoardDAO implements CommonDAO {

    @Autowired
    private SqlSession ss;


    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    StringBuffer sb = new StringBuffer();



    @Override
    public List<BoardDTO> selectAll(int startNo, int endNo) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startNo", startNo);
        map.put("endNo", endNo);

        System.out.println("ss : " + ss);
        //         ss.selectList("별칭 ==> 네임스페이스명");
        return ss.selectList("kr.co.jhta.board.selectAll", map);

    }

    @Override
    public void insertOne(BoardDTO dto) {
        ss.insert("kr.co.jhta.board.insertOne", dto);
    }

    @Override
    public BoardDTO selectOne(int bno) {
        BoardDTO dto = ss.selectOne("kr.co.jhta.board.selectOne", bno);
        return dto;
    }

    @Override
    public void deleteOne(int bno) {
        ss.delete("kr.co.jhta.board.deleteOne", bno);
    }

    @Override
    public void updateOne(BoardDTO dto) {
        ss.update("kr.co.jhta.board.updateOne", dto);
    }

    @Override
    public int getTotal() {
        return ss.selectOne("kr.co.jhta.board.getTotal");
    }

    @Override
    public List<BoardDTO> selectAll(Map<String, Object> map) {
        return List.of();
    }

}
