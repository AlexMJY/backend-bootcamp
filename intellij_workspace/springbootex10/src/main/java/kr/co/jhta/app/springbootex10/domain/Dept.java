package kr.co.jhta.app.springbootex10.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deptno;
    private String dname;
    private String loc;
}
