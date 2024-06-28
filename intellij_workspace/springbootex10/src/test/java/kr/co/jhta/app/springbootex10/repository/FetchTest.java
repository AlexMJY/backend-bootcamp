package kr.co.jhta.app.springbootex10.repository;

import kr.co.jhta.app.springbootex10.domain.Dept;
import kr.co.jhta.app.springbootex10.domain.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
//@Transactional
public class FetchTest {

    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Test
    public void test() {
        // 초기 데이터 삽입
        // 부서 1, 경영, 서울
        // 부서 2, IT 부산
        Dept dept1 = Dept.builder().dname("경영").loc("서울").build();
        Dept dept2 = Dept.builder().dname("IT").loc("부산").build();
        deptRepository.save(dept1);
        deptRepository.save(dept2);

        // 해당 부서에 근무하는 사원 10명씩 추가
        for (int i = 1; i <= 10; i++) {
            Emp emp1 = Emp.builder().ename("뽀로로" + i).job("백수" + i).sal(1000L).mgr(1000L).comm(1000L).dept(dept1).build();
            Emp emp2 = Emp.builder().ename("보노보노" + i).job("수달" + i).sal(1000L).mgr(1000L).comm(1000L).dept(dept2).build();
            empRepository.save(emp1);
            empRepository.save(emp2);
        }

        List<Emp> emps = empRepository.findAll();
        for (Emp e : emps) {
            System.out.println("부서명 : " + e.getDept().getDname());
        }


        System.out.println("-------------------------------");
        Dept dept = empRepository.getDeptEmpno(1L);
        System.out.println(dept);


    }

}
