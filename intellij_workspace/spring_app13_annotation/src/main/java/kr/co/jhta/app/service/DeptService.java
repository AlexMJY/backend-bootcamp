package kr.co.jhta.app.service;

import kr.co.jhta.app.dao.CommonDAO;
import kr.co.jhta.app.dto.DeptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class DeptService implements DBService {

    @Qualifier("oracledao")
    @Autowired
    CommonDAO dao;

    public void setDao(CommonDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<DeptDTO> readAll() {
        dao.connect();
        List<DeptDTO> list = dao.selectAll();
        dao.close();
        return list;
    }

    @Override
    public DeptDTO readOne(int no) {
        dao.connect();
        DeptDTO dto = dao.selectOne(no);
        dao.close();
        return dto;
    }

    @Override
    public void write(DeptDTO dto) {
        dao.connect();
        dao.insertOne(dto);
        dao.close();
    }

    @Override
    public void modify(DeptDTO dto) {
        dao.connect();
        dao.updateOne(dto);
        dao.close();
    }

    @Override
    public void drop(int no) {
        dao.connect();
        dao.deleteOne(no);
        dao.close();
    }
}
