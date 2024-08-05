package com.aico.springboot_querydsl.repository;

import com.aico.springboot_querydsl.domain.Emp;
import com.aico.springboot_querydsl.dto.EmpDeptSalgradeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpRepository extends JpaRepository<Emp, Long>, EmpCustom {

    // 사원 이름으로 검색
    List<Emp> findAllByEnameContainingOrderByEmpnoDesc(String ename);


    @Query(value = "SELECT e.empno, e.ename, e.job, e.mgr, e.hiredate, e.sal, e.comm, d.deptno, d.dname, d.loc, s.no, s.grade, s.losal, s.hisal " +
            "FROM emp e LEFT JOIN " +
            "dept d ON e.deptno = d.deptno LEFT JOIN " +
            "salgrade s ON e.no = s.no " +
            "where e.empno= :empno", nativeQuery = true)
    public List<Emp> getNativeQuery(@Param("empno") Long empno);


}
