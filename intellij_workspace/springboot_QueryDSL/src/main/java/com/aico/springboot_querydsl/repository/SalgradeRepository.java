package com.aico.springboot_querydsl.repository;

import com.aico.springboot_querydsl.domain.Salgrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalgradeRepository extends JpaRepository<Salgrade, Long> {
}
