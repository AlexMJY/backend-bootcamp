package com.aico.springboot_querydsl.service;

import com.aico.springboot_querydsl.domain.*;
import com.aico.springboot_querydsl.dto.EmpDeptSalgradeDTO;
import com.aico.springboot_querydsl.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {
    private final EmpRepository empRepository;

    @Override
    public List<Emp> findAll() {
        return empRepository.findAll();
    }

    @Override
    public Emp findOne(Long empno) {
        return empRepository.findById(empno).orElse(null);
    }

    @Override
    public List<Emp> getNativeQuery(Long empno) {
        return empRepository.getNativeQuery(empno);
    }

    public List<EmpDeptSalgradeDTO> getJoinData(Long empno) {
        return empRepository.getJoinDataOne(empno);
    }

    public List<Emp> getLimitData(int no) {
        return empRepository.getLimitData(no);
    }

    @Transactional
    @Override
    public List<Emp> getDynamicSql(String keyword, int sal) {
        List<Emp> dynamicData = empRepository.getDynamicData(keyword, sal);
        return dynamicData;
    }

}
