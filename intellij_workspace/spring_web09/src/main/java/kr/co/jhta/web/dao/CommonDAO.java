package kr.co.jhta.web.dao;

import kr.co.jhta.web.dto.BoardDTO;

import java.util.List;

public interface CommonDAO {
    public List<BoardDTO> selectAll();

    public void insertOne(BoardDTO dto);

    public BoardDTO selectOne(int bno);

    public void deleteOne(int bno);

}
