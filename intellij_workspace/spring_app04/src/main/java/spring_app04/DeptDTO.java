package spring_app04;

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
