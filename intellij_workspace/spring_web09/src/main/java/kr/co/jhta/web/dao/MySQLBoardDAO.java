package kr.co.jhta.web.dao;

import kr.co.jhta.web.dto.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("mysqlDB")
public class MySQLBoardDAO implements CommonDAO {
    @Autowired
    private SqlSession ss;

    StringBuffer sb = new StringBuffer();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;




    @Override
    public List<BoardDTO> selectAll(int startNo, int endNo) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startNo", startNo);
        map.put("endNo", endNo);
        System.out.println("ss : " + ss);
        return ss.selectList("kr.co.jhta.board.selectAllMySQL", map);
    }

    @Override
    public void insertOne(BoardDTO dto) {
        ss.insert("kr.co.jhta.board.insertOneMySQL", dto);    }

    @Override
    public BoardDTO selectOne(int bno) {
        BoardDTO dto = ss.selectOne("kr.co.jhta.board.selectOneMySQL", bno);
        return dto;
    }

    @Override
    public void deleteOne(int bno) {
        ss.delete("kr.co.jhta.board.deleteOneMySQL", bno);
    }

    @Override
    public void updateOne(BoardDTO dto) {
        ss.update("kr.co.jhta.board.updateOneMySQL", dto);
    }

    @Override
    public int getTotal() {
        return ss.selectOne("kr.co.jhta.board.getTotalMySQL");
    }

    @Override
    public List<BoardDTO> selectAll(Map<String, Object> map) {
        map.put("offset", (int) map.get("startNo") - 1);
        return ss.selectList("kr.co.jhta.board.selectAllMySQL", map);
    }
}
