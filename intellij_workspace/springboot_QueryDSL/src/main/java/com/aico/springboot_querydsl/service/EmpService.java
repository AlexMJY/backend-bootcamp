package com.aico.springboot_querydsl.service;

import com.aico.springboot_querydsl.domain.Emp;
import com.aico.springboot_querydsl.dto.EmpDeptSalgradeDTO;

import java.util.List;

public interface EmpService {
    public List<Emp> findAll();

    Emp findOne(Long empno);

    List<Emp> getNativeQuery(Long empno);

    public List<EmpDeptSalgradeDTO> getJoinData(Long empno);

    public List<Emp> getLimitData(int no);


    List<Emp> getDynamicSql(String keyword, int sal);
}
