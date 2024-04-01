package rental.vo;

public class FacVO {

	private int facNo;
	private String facName;
	private String facAddr;
	private String createAt;
	
	public int getFacNo() {
		return facNo;
	}
	public String getFacName() {
		return facName;
	}
	public String getFacAddr() {
		return facAddr;
	}
	public String getCreateAt() {
		return createAt;
	}
	public void setFacNo(int facNo) {
		this.facNo = facNo;
	}
	public void setFacName(String facName) {
		this.facName = facName;
	}
	public void setFacAddr(String facAddr) {
		this.facAddr = facAddr;
	}
	public void setCreateAt(String createDate) {
		this.createAt = createDate;
	}
}
