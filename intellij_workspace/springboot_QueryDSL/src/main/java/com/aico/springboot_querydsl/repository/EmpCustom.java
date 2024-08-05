package com.aico.springboot_querydsl.repository;

import com.aico.springboot_querydsl.domain.Emp;
import com.aico.springboot_querydsl.dto.EmpDeptSalgradeDTO;

import java.util.List;

public interface EmpCustom {
    List<EmpDeptSalgradeDTO> getJoinDataOne(Long empno);
    public List<Emp> getLimitData(int no);
    public List<Emp> getDynamicData(String keyword, int sal);
}
