package com.aico.springboot_querydsl.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empno;
    private String ename;
    private String job;
    private Integer mgr;
    private LocalDateTime hiredate;
    private Integer sal;
    private Integer comm;

    @ManyToOne
    @JoinColumn(name = "deptno")
    private Dept dept;

    @ManyToOne
    @JoinColumn(name = "no")
    private Salgrade salgrade;
}
