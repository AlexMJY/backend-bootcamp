package rental.vo;


import java.sql.Date;

public class UserVO {
	
	private int userNo;
	private String id;
	private String pw;
	private String name;


	private String email;
	private Boolean  admin;
	private String phone;
	private Date joinDate;
	
	
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public Boolean getAdmin() {
		return admin;
	}
	
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

}
