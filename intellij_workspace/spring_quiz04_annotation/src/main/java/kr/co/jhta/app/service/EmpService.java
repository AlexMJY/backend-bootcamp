package kr.co.jhta.app.service;

import kr.co.jhta.app.dao.CommonDAO;
import kr.co.jhta.app.dto.EmpDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpService implements DBService {

    @Qualifier("oracledao")
    @Autowired
    CommonDAO dao;

    @Override
    public List<EmpDTO> readAll() {
        dao.connect();
        List<EmpDTO> list = dao.selectAll();
        dao.close();
        return list;
    }

    @Override
    public EmpDTO readOne(int no) {
        dao.connect();
        EmpDTO dto = dao.selectOne(no);
        dao.close();
        return dto;
    }

    @Override
    public void write(EmpDTO dto) {
        dao.connect();
        dao.insertOne(dto);
        dao.close();
    }

    @Override
    public void modify(EmpDTO dto) {
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
