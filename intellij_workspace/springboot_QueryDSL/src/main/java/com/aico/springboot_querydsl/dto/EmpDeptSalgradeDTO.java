package com.aico.springboot_querydsl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpDeptSalgradeDTO {
    private Long empno;
    private String ename;
    private String job;
    private Integer mgr;
    private LocalDateTime hiredate;
    private Integer sal;
    private Integer comm;
    private Long deptno;
    private String dname;
    private String loc;
    private Long no;
    private Integer grade;
    private Integer losal;
    private Integer hisal;
}
