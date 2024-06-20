package kr.co.jhta.web.service;

import kr.co.jhta.web.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    public List<BoardDTO> ReadAll();

    public void write(BoardDTO dto);

    public BoardDTO readOne(int bno);

    public void dropOne(int bno);

}
