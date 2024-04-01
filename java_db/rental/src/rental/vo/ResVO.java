package rental.vo;

public class ResVO {
	
	private int resNo;
	private String userId;
	private String facName;
	private Boolean avl;
	
	private String resDate;
	private String applicationDate;
	
	private String content;
	
	
	public int getResNo() {
		return resNo;
	}
	public void setResNo(int resNo) {
		this.resNo = resNo;
	}
	public String getUserId() {
		return userId;
	}
	public String getFacName() {
		return facName;
	}
	public Boolean getAvl() {
		return avl;
	}
	public String getResDate() {
		return resDate;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setFacName(String facName) {
		this.facName = facName;
	}
	public void setAvl(Boolean avl) {
		this.avl = avl;
	}
	public void setResDate(String resDate) {
		this.resDate = resDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
