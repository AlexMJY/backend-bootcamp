package com.aico.springboot_querydsl.repository;

import com.aico.springboot_querydsl.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Dept, Long> {
}
