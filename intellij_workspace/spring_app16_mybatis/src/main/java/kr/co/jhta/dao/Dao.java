package kr.co.jhta.dao;

import kr.co.jhta.dto.DeptDTO;

import java.util.List;

public interface Dao {
    public List<DeptDTO> selectAll();
    public DeptDTO selectOne(int no);
    public void insertOne(DeptDTO dto);
    public void updateOne(DeptDTO dto);
    public void deleteOne(int no);
}
