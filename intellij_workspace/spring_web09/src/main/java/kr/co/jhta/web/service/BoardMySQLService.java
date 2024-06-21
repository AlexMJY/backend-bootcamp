package kr.co.jhta.web.service;

import kr.co.jhta.web.dao.CommonDAO;
import kr.co.jhta.web.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("mysqlService")
public class BoardMySQLService implements BoardService {

    @Autowired
    @Qualifier("mysqlDB")
    CommonDAO dao;

    @Override
    public List<BoardDTO> readAll(int startNo, int endNo) {
        return dao.selectAll(startNo, endNo);
    }

    @Override
    public List<BoardDTO> readAll(Map<String, Object> map) {
        return dao.selectAll(map);
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

    @Override
    public void update(BoardDTO dto) {
        dao.updateOne(dto);
    }

    @Override
    public int getTotal() {
        return dao.getTotal();
    }
}
