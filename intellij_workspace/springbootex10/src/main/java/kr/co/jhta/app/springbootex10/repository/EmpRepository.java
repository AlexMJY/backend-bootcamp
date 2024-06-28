package kr.co.jhta.app.springbootex10.repository;

import kr.co.jhta.app.springbootex10.domain.Dept;
import kr.co.jhta.app.springbootex10.domain.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpRepository extends JpaRepository<Emp, Long> {

    @Query(value = "SELECT * from EMP WHERE empno = 1?", nativeQuery = true)
    Emp testEmp(Long empno);

    Emp findByEmpno(Long empno);

    Emp findByEname(String ename);

    List<Emp> findByDept(Dept dept);

    @Query("SELECT D FROM Dept D JOIN FETCH Emp E On D.deptno = E.dept.deptno WHERE D.deptno = :deptno")
    Dept getDeptEmpno(Long deptno);
}

