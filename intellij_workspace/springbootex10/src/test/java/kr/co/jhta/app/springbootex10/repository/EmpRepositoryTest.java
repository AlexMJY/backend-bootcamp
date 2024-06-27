package kr.co.jhta.app.springbootex10.repository;

import kr.co.jhta.app.springbootex10.domain.Dept;
import kr.co.jhta.app.springbootex10.domain.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmpRepositoryTest {
    @Autowired
    EmpRepository empRepository;

    // 7788번 사원의 정보를 가져오는 test case
    // method : test_emp
    // 이름이 scott인지 테스트
    @Test
    public void find7788() {
        Optional<Emp> result = empRepository.findById(7788L);
        if (result.isPresent()) {
            Emp emp = result.get();
            System.out.println(emp.getEname());
            Dept dept = emp.getDeptno();
            System.out.println(dept.getDname() + " : " + dept.getLoc());
        }
    }
}