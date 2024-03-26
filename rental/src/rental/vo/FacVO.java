package rental.vo;

import java.sql.Date;

public class FacVO {
	
	private int facNo;
	private String facName;
	private String facAddr;
	private Date createDate;
	public int getFacNo() {
		return facNo;
	}
	public void setFacNo(int facNo) {
		this.facNo = facNo;
	}
	public String getFacName() {
		return facName;
	}
	public void setFacName(String facName) {
		this.facName = facName;
	}
	public String getFacAddr() {
		return facAddr;
	}
	public void setFacAddr(String facAddr) {
		this.facAddr = facAddr;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
