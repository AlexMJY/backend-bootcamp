package kr.co.jhta.app.service;

import kr.co.jhta.app.dto.EmpDTO;

import java.util.List;

public interface DBService {
    public List<EmpDTO> readAll();
    public EmpDTO readOne(int no);
    public void write(EmpDTO dto);
    public void modify(EmpDTO dto);
    public void drop(int no);
}
