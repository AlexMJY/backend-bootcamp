package kr.co.jhta.app.dao;

import kr.co.jhta.app.dto.EmpDTO;

import java.util.List;

public interface CommonDAO {

    public void connect();
    public List<EmpDTO> selectAll();
    public EmpDTO selectOne(int empno);
    public void insertOne(EmpDTO dto);
    public void updateOne(EmpDTO dto);
    public void deleteOne(int deptno);
    public void close();
}
