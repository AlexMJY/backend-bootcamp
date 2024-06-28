package kr.co.jhta.app.springbootex10.repository;

import kr.co.jhta.app.springbootex10.domain.Dept;
import kr.co.jhta.app.springbootex10.domain.Emp;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeptRepositoryTest {
    @Autowired
    DeptRepository deptRepository;

    @Autowired
    EmpRepository empRepository;

//    @Test
//    public void findDeptNo() {
//        Dept result = deptRepository.findById(20L).orElseThrow(() -> new IllegalArgumentException("Dept not found with id "));
//        System.out.println(result.getDeptno() + " : " + result.getDname() + " : " + result.getLoc());
//    }

    @Test
    @Order(1)
    @DisplayName("새로운 데이터 입력")
    public void testNewData() {
        Dept dept = Dept.builder().dname("RE100").loc("Seoul").build();
        deptRepository.save(dept);

        Emp emp1 = new Emp();
        emp1.setEname("뽀로로");
        emp1.setSal(1000L);
        emp1.setComm(0L);
        emp1.setDept(dept);
        emp1.setJob("MANAGER");
        empRepository.save(emp1);
        // --------------------------
        Emp emp2 = new Emp();
        emp2.setEname("둘리");
        emp2.setSal(1000L);
        emp2.setComm(0L);
        emp2.setDept(dept);
        emp2.setJob("MANAGER");
        empRepository.save(emp2);
        // --------------------------
        Emp emp3 = new Emp();
        emp3.setEname("타요");
        emp3.setSal(1000L);
        emp3.setComm(0L);
        emp3.setDept(dept);
        emp3.setJob("MANAGER");
        empRepository.save(emp3);



    }

    // 1번 부서의 정보 가져오기
    @Test
    @DisplayName("1번 부서 정보 가져오기")
    @Order(2)
    void testFind1() {
        Dept dept = deptRepository.findById(1L).get();
        assertNotNull(dept);
        log.info("dept : {}", dept.toString());

        List<Emp> empList = dept.getEmpList();
        assertNotNull(empList);

        empList.forEach(emp -> {
            System.out.println(emp.getEmpno() + " : " + emp.getEname() + " : " + emp.getSal());
        });
    }

    @Test
    @Order(3)
    void testUpdate2() {
        // 1번 부서에 근무하는 둘리의 월급을 1500으로 변경
        Optional<Dept> result = deptRepository.findById(1L);

        if (result.isPresent()) {
            Dept dept = result.get();
            List<Emp> empList = dept.getEmpList();
            for (Emp e : empList) {
                if (e.getEname().equals("둘리")) {
                    e.setSal(1500L);
                    empRepository.save(e);
                }
                System.out.println(e.getEmpno() + " : " + e.getEname() + " : " + e.getSal());
            }
        }
    }
//    @Test
//    @Order(3)
//    void testUpdate2() {
//        // 1번 부서에 근무하는 둘리의 월급을 1500으로 변경
//        deptRepository.findById(1L).ifPresent(dept -> {
//            dept.getEmpList().stream()
//                    .filter(e -> e.getEname().equals("둘리"))
//                    .findFirst()
//                    .ifPresent(e -> {
//                        e.setSal(1500L);
//                        empRepository.save(e);
//                    });
//
//            dept.getEmpList().forEach(e ->
//                    System.out.println(e.getEmpno() + " : " + e.getEname() + " : " + e.getSal())
//            );
//        });
//    }
    
    @DisplayName("1번 부서번호를 10으로 변경")
    @Test
    @Order(4)
    void testUpdateDeptNo() {
        Dept dept = deptRepository.findById(1L).orElse(null);
        dept.setDeptno(10L);
        deptRepository.save(dept);
    }


    @DisplayName("모든 사원들의 부서번호 2로 변경")
    @Test
    @Order(5)
    void testEmpUpdate() {
        // 1번 부서의 사원들을 2번 부서로 변경
//        Dept dept1 = deptRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException());
//        Dept dept2 = deptRepository.findById(2L).orElseThrow(() -> new IllegalArgumentException());
//
//        List<Emp> empList = dept1.getEmpList();
//        for (Emp e : empList) {
//            e.setDept(dept2);
//            empRepository.save(e);
//        }
        Dept d = new Dept();
        d.setDeptno(1L);
        List<Emp> result = empRepository.findByDept(d);
        log.info("result : {}", result);
        result.forEach(emp -> {
            Dept dept = new Dept();
            dept.setDeptno(2L);
            emp.setDept(dept);
            empRepository.save(emp);
        });
    }

    @DisplayName("2번 부서 삭제")
    @Test
    @Order(6)
    void testDeptDelte() {
        deptRepository.deleteById(2L);
        Optional<Dept> result = deptRepository.findById(2L);
        assertTrue(result.isEmpty());
    }
    



//    @Test
//    public void testNewData2() {
//        Emp emp = new Emp();
//        emp.setEname("뽀로로");
//        emp.setSal(1000L);
//        emp.setComm(0L);
//        emp.setJob("MANAGER");
//        empRepository.save(emp);
//    }

}