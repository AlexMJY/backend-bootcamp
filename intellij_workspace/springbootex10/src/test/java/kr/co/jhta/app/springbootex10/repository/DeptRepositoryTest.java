package kr.co.jhta.app.springbootex10.repository;

import kr.co.jhta.app.springbootex10.domain.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeptRepositoryTest {
    @Autowired
    DeptRepository deptRepository;

    @Test
    public void findDeptNo() {
        Dept result = deptRepository.findById(20L).orElseThrow(() -> new IllegalArgumentException("Dept not found with id "));
        System.out.println(result.getDeptno() + " : " + result.getDname() + " : " + result.getLoc());
    }
}