package kr.co.jhta.web.spring_web_quiz01.control;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptDTO {
    private int deptno;
    private String dname;
    private String loc;
}
