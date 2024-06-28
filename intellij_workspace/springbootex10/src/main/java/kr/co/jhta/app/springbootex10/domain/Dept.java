package kr.co.jhta.app.springbootex10.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString(exclude = "empList")
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long deptno;
    private String dname;
    private String loc;

//    @OneToMany(mappedBy = "dept", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // mappedBy: Emp.java의 dept 칼럼과 연결. fetch: 모드 설정(Eager: 즉시, Lazy: 지연)
    @OneToMany(mappedBy = "dept", fetch = FetchType.EAGER)
    private List<Emp> empList;
}
