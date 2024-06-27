package kr.co.jhta.app.springbootex10.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empno;
    private String ename;
    private String job;
    private Long mgr;
    private LocalDateTime hiredate;
    private Long sal;
    private Long comm;

    @ManyToOne
    @JoinColumn(name = "deptno")
    private Dept deptno;

}
