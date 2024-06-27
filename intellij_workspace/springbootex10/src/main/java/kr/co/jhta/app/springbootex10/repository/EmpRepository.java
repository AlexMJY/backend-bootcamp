package kr.co.jhta.app.springbootex10.repository;

import kr.co.jhta.app.springbootex10.domain.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmpRepository extends JpaRepository<Emp, Long> {

    @Query(value = "SELECT * from EMP WHERE empno = 1?", nativeQuery = true)
    Emp testEmp(Long empno);

    Emp findByEmpno(Long empno);
}

