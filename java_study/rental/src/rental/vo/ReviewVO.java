package rental.vo;

public class ReviewVO {
	
	private int reviewNo;
	private String userId;
	private String facName;
	private String content;
	private String createAt;
	private String editAt;
	private	int count;
	
	public int getReviewNo() {
		return reviewNo;
	}
	public String getUserId() {
		return userId;
	}
	public String getFacName() {
		return facName;
	}
	public String getContent() {
		return content;
	}
	public String getCreateAt() {
		return createAt;
	}
	public String getEditAt() {
		return editAt;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setFacName(String facName) {
		this.facName = facName;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	public void setEditAt(String editAt) {
		this.editAt = editAt;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
