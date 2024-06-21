package kr.co.jhta.web.dao;

import kr.co.jhta.web.dto.BoardDTO;

import java.util.List;
import java.util.Map;

public interface CommonDAO {
    public List<BoardDTO> selectAll(int startNo, int endNo);

    public void insertOne(BoardDTO dto);

    public BoardDTO selectOne(int bno);

    public void deleteOne(int bno);

    public void updateOne(BoardDTO dto);

    public int getTotal();

    public List<BoardDTO> selectAll(Map<String, Object> map);
}
