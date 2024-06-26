package kr.co.jhta.app.springbootex05.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeptDTO {
    private int deptno;
    private String dname;
    private String loc;
}
