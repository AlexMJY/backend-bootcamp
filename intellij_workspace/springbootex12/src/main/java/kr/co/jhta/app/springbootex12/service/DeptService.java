package kr.co.jhta.app.springbootex12.service;

import kr.co.jhta.app.springbootex12.dto.DeptDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeptService {
    ResponseEntity<List<DeptDTO>> readAll();

    void write(DeptDTO deptDTO);

    DeptDTO readOne(int no);

    void modifyOne(int no, DeptDTO deptDTO);

    void remove(int no);
}
