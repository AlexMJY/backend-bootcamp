package kr.co.jhta.app.springbootex08.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "dept")
@Entity
public class Dept {
    @Id
    @GeneratedValue
    @Column(name = "deptno")
    private int dpetno;
    private String dname;
    private String loc;
}
