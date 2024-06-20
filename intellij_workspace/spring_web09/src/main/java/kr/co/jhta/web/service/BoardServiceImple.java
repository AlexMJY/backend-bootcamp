package kr.co.jhta.web.service;

import kr.co.jhta.web.dao.CommonDAO;
import kr.co.jhta.web.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImple implements BoardService {

    @Autowired
    CommonDAO dao;

    @Override
    public List<BoardDTO> ReadAll() {
        return dao.selectAll();
    }

    @Override
    public void write(BoardDTO dto) {
        dao.insertOne(dto);
    }

    @Override
    public BoardDTO readOne(int bno) {
        BoardDTO dto = dao.selectOne(bno);
        return dto;
    }

    @Override
    public void dropOne(int bno) {
        dao.deleteOne(bno);
    }


}
