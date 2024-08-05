package com.aico.springboot_querydsl.repository;

import com.aico.springboot_querydsl.domain.Emp;
import com.aico.springboot_querydsl.domain.QDept;
import com.aico.springboot_querydsl.domain.QEmp;
import com.aico.springboot_querydsl.domain.QSalgrade;
import com.aico.springboot_querydsl.dto.EmpDeptSalgradeDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EmpCustomImpl implements EmpCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<EmpDeptSalgradeDTO> getJoinDataOne(Long empno) {
        QEmp emp = QEmp.emp;
        QDept dept = QDept.dept;
        QSalgrade salgrade = QSalgrade.salgrade;

        JPAQuery<Tuple> query = queryFactory
                .select(emp.empno, emp.ename, emp.job, emp.mgr, emp.hiredate, emp.sal, emp.comm, emp.dept, dept.dname, dept.loc, emp.salgrade, salgrade.grade, salgrade.losal, salgrade.hisal)
                .from(emp)
                .join(dept)
                .on(emp.dept.deptno.eq(dept.deptno))
                .join(salgrade)
                .on(emp.salgrade.no.eq(salgrade.no))
                .where(emp.empno.eq(empno))
                .orderBy(emp.dept.deptno.desc());

        List<Tuple> list1 = query.fetchJoin().fetch();
        System.out.println("list1: " + list1);

        List<EmpDeptSalgradeDTO> list = query.fetchJoin().fetch().stream().map(t -> {
            System.out.println("t : " + t);
            return EmpDeptSalgradeDTO.builder()
                    .empno(empno)
                    .ename(t.get(emp.ename))
                    .job(t.get(emp.job))
                    .mgr(t.get(emp.mgr))
                    .hiredate(t.get(emp.hiredate))
                    .sal(t.get(emp.sal))
                    .comm(t.get(emp.comm))
                    .deptno(t.get(emp.dept.deptno))
                    .dname(t.get(dept.dname))
                    .loc(t.get(dept.loc))
                    .no(t.get(emp.salgrade.no))
                    .grade(t.get(salgrade.grade))
                    .losal(t.get(salgrade.losal))
                    .hisal(t.get(salgrade.hisal))
                    .build();
        }).collect(Collectors.toList());

        return ResponseEntity.ok(list).getBody();
    }

    @Override
    public List<Emp> getLimitData(int no) {
        QEmp emp = QEmp.emp;

        List<Emp> emps = queryFactory
                .select(emp)
                .from(emp)
                .orderBy(emp.sal.desc())
                .offset(1) // 결과에서 1번째 레코드부터 시작
                .limit(no) // 최대 no개 레코드 가져와
                .fetch();

        return emps;
    }

    @Override
    public List<Emp> getDynamicData(String keyword, int sal) {
        QEmp emp = QEmp.emp;

        BooleanBuilder builder = new BooleanBuilder();
        if (keyword != null) {
            builder.and(emp.ename.contains(keyword));
            if (sal != 0) {
                builder.and(emp.sal.gt(sal));
            }
        }

        List<Emp> emps = queryFactory
                .select(emp)
                .from(emp)
                .where(builder)
                .fetch();

        return emps;
    }
}
