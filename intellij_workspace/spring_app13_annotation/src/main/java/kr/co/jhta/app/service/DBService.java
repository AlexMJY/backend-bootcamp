package kr.co.jhta.app.service;

import kr.co.jhta.app.dto.DeptDTO;

import java.util.List;

public interface DBService {
    public List<DeptDTO> readAll();
    public DeptDTO readOne(int no);
    public void write(DeptDTO dto);
    public void modify(DeptDTO dto);
    public void drop(int no);
}
