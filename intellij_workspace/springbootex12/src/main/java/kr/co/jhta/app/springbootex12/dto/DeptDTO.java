package kr.co.jhta.app.springbootex12.dto;

import kr.co.jhta.app.springbootex12.domain.Dept;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeptDTO {
    private int deptno;
    private String dname;
    private String loc;

    public Dept toEntity() {
        Dept dept = new Dept();
        dept.setDeptno(deptno);
        dept.setDname(dname);
        dept.setLoc(loc);
        return dept;
    }
}
