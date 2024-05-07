package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Value Object

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptVO {
	// 기본생성자
	// 매개변수 있는 생성자
	// Setter
	// Getter
	
	private int deptno;
	private String dname;
	private String loc;
	
	
	
//	@Override
//	public String toString() {
//		return this.getDeptno() + " : " + this.getDname() + " : " + this.getLoc();
//	}
	
	
}
