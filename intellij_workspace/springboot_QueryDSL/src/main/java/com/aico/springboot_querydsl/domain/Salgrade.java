    package com.aico.springboot_querydsl.domain;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import lombok.Data;

    @Entity
    @Data
    public class Salgrade {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long no;
        private Integer grade;
        private Integer losal;
        private Integer hisal;
    }
