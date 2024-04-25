package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
	
	private int no;
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String motive;
	private int zipcode;
	private String address;
	
	public MemberVO(String id, String pw, String name, String gender, int zipcode,String address) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.zipcode = zipcode;
		this.address = address;
	}
	
}
