package kr.co.jhta.app.springbootex12.service;

import kr.co.jhta.app.springbootex12.domain.Dept;
import kr.co.jhta.app.springbootex12.dto.DeptDTO;
import kr.co.jhta.app.springbootex12.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {
    private final DeptRepository deptRepository;

    @Override
    public ResponseEntity<List<DeptDTO>> readAll() {
        List<Dept> list = deptRepository.findAll();
        List<DeptDTO> deptDTOList = new ArrayList<>();
        for (Dept dept : list) {
            DeptDTO deptDTO = new DeptDTO();
            deptDTO.setDeptno(dept.getDeptno());
            deptDTO.setDname(dept.getDname());
            deptDTO.setLoc(dept.getLoc());
            deptDTOList.add(deptDTO);
        }
//        return deptDTOList;
        return ResponseEntity.ok().body(deptDTOList);
    }

    @Override
    public void write(DeptDTO deptDTO) {
        Dept dept =  deptDTO.toEntity();
        deptRepository.save(dept);
    }

    @Override
    public DeptDTO readOne(int no) {
        Optional<Dept> result = deptRepository.findById(no);
        Dept dept = result.get();

        DeptDTO deptDTO = new DeptDTO();
        deptDTO.setDeptno(dept.getDeptno());
        deptDTO.setDname(dept.getDname());
        deptDTO.setLoc(dept.getLoc());

        return deptDTO;
    }

    @Override
    public void modifyOne(int no, DeptDTO deptDTO) {
//        Dept dept = deptRepository.findById(no).orElse(null);
//        dept.setDname(deptDTO.getDname());
//        dept.setLoc(deptDTO.getLoc());
//        deptRepository.save(dept);
//
        deptDTO.setDeptno(no);
        Dept entity = deptDTO.toEntity();
        deptRepository.save(entity);
    }

    @Override
    public void remove(int no) {
        deptRepository.deleteById(no);
    }
}
