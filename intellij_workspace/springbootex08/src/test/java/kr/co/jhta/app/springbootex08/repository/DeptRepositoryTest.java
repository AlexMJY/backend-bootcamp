package kr.co.jhta.app.springbootex08.repository;

import kr.co.jhta.app.springbootex08.entity.Dept;
import org.junit.jupiter.api.DisplayName;
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
    public void testClass() {
        System.out.println("deptRepository : " + deptRepository);
    }

    @Test
    @DisplayName("dept insert test")
    public void testAddOne() {
        Dept d1 = Dept.builder()
                .dname("IT")
                .loc("독도")
                .build();

        deptRepository.save(d1);
    }

    @Test
    public void testSelect() {
        int no = 1;
        Optional<Dept> result = deptRepository.findById(no);
        if (result.isPresent()) {
            Dept d = result.get();
            System.out.println("======== " + d.getDname() + " ========");
        }
    }

    @Test
    public void test_10부서검색() {
        Optional<Dept> dept = deptRepository.findById(10);
        assertTrue(dept.isPresent());
        Dept dept1 = dept.get();
        System.out.println("dept1 : " + dept1);
    }


}