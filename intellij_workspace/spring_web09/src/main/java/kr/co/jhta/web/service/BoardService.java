package kr.co.jhta.web.service;

import kr.co.jhta.web.dto.BoardDTO;

import java.util.List;
import java.util.Map;

public interface BoardService {

    public List<BoardDTO> readAll(int startNo, int endNo);
    public List<BoardDTO> readAll(Map<String, Object> map);

    public void write(BoardDTO dto);

    public BoardDTO readOne(int bno);

    public void dropOne(int bno);

    public void update(BoardDTO dto);

    public int getTotal();
}
