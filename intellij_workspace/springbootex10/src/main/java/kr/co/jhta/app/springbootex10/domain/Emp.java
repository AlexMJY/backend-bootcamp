package kr.co.jhta.app.springbootex10.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = "dept")
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empno;

    @Column(name = "ename", length = 50, nullable = false)
    private String ename;

    private String job;

    private Long mgr;

    private LocalDateTime hiredate;

//    @Column(name = "sal", precision = 7, scale = 2)
    private Long sal;

    private Long comm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deptno")
    private Dept dept;
}
