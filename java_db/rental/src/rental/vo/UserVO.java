package rental.vo;

import java.sql.Date;

public class UserVO {
	
	private int userNo;
	private String id;
	private String pw;
	private String name;
	private Boolean admin;
	private String email;
	private String phone;
	private Date joinDate;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserNo() {
		return userNo;
	}
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	public String getName() {
		return name;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public String getPhone() {
		return phone;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

}
