package kr.co.jhta.app.springbootex07.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeptDTO {
    private int deptno;
    private String dname;
    private String loc;
}
