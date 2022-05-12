package todaypig.join;

public class JoinVO {
	private String categoryID;
	private String categoryName;
	private String foodID;
	private String foodName;
	private String foodIsSlow;
	private String historyDate;
	private String historyWriteReview;
	private String memberID;
	private String memberPW;
	private String memberBDay;
	private String memberGender;
	private String memberDelete;
	private int offerPrice;
	private int regionNo;
	private String regionName;
	private int restaurantNO;
	private String restaurantName;
	private String restaurantAddress;
	private int restaurantOpen;
	private int restaurantClose;
	private String restaurantTel;
	private String restaurantRestDay;
	private Double restaurantScore;
	private String restaurantDelete;
	private String reviewNo;
	private String reviewScore;
	private String reviewComment;
	
	public JoinVO(String foodName, int offerPrice) {
		this.foodName = foodName;
		this.offerPrice = offerPrice;
	}
	
	public JoinVO(int restaurantNO, String foodName, int offerPrice) {
		this.restaurantNO = restaurantNO;
		this.foodName = foodName;
		this.offerPrice = offerPrice;
	}

	public JoinVO(String foodName, String historyDate, String historyWriteReview, String restaurantName) {
		this.foodName = foodName;
		this.historyDate = historyDate;
		this.historyWriteReview = historyWriteReview;
		this.restaurantName = restaurantName;
	}
	
	public JoinVO(int restaurantNO, String foodID, String restaurantName, String restaurantAddress, String foodName, int offerPrice  
			) {
		this.foodID = foodID;
		this.foodName = foodName;
		this.offerPrice = offerPrice;
		this.restaurantNO = restaurantNO;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
	}

	public JoinVO(int restaurantNO, String foodID, String restaurantName, String restaurantAddress,
			int restaurantOpen, int restaurantClose, String restaurantTel, String restaurantRestDay,
			Double restaurantScore, String foodName, int offerPrice, String foodIsSlow) {
		this.foodID = foodID;
		this.foodName = foodName;
		this.offerPrice = offerPrice;
		this.restaurantNO = restaurantNO;
		this.restaurantAddress = restaurantAddress;
		this.restaurantName = restaurantName;
		this.restaurantOpen = restaurantOpen;
		this.restaurantClose = restaurantClose;
		this.restaurantTel = restaurantTel;
		this.restaurantRestDay = restaurantRestDay;
		this.restaurantScore = restaurantScore;
		this.foodIsSlow = foodIsSlow;
	}
	
	public JoinVO(String foodID, String foodName,  String memberID, String historyDate, String historyWriteReview,
			int restaurantNO, String restaurantName) {
		this.foodID = foodID;
		this.foodName = foodName;
		this.historyDate = historyDate;
		this.historyWriteReview = historyWriteReview;
		this.memberID = memberID;
		this.restaurantNO = restaurantNO;
		this.restaurantName = restaurantName;
	}

	public JoinVO(String foodName, String historyDate, String memberID, String reviewScore, String reviewComment) {
		this.foodName = foodName;
		this.historyDate = historyDate;
		this.memberID = memberID;
		this.reviewScore = reviewScore;
		this.reviewComment = reviewComment;
	}
	
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getFoodID() {
		return foodID;
	}
	public void setFoodID(String foodID) {
		this.foodID = foodID;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodIsSlow() {
		return foodIsSlow;
	}
	public void setFoodIsSlow(String foodIsSlow) {
		this.foodIsSlow = foodIsSlow;
	}
	public String getHistoryDate() {
		return historyDate;
	}
	public void setHistoryDate(String historyDate) {
		this.historyDate = historyDate;
	}
	public String getHistoryWriteReview() {
		return historyWriteReview;
	}
	public void setHistoryWriteReview(String historyWriteReview) {
		this.historyWriteReview = historyWriteReview;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberPW() {
		return memberPW;
	}
	public void setMemberPW(String memberPW) {
		this.memberPW = memberPW;
	}
	public String getMemberBDay() {
		return memberBDay;
	}
	public void setMemberBDay(String memberBDay) {
		this.memberBDay = memberBDay;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberDelete() {
		return memberDelete;
	}
	public void setMemberDelete(String memberDelete) {
		this.memberDelete = memberDelete;
	}
	public int getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(int offerPrice) {
		this.offerPrice = offerPrice;
	}
	public int getRegionNo() {
		return regionNo;
	}
	public void setRegionNo(int regionNo) {
		this.regionNo = regionNo;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public int getRestaurantNO() {
		return restaurantNO;
	}
	public void setRestaurantNO(int restaurantNO) {
		this.restaurantNO = restaurantNO;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public int getRestaurantOpen() {
		return restaurantOpen;
	}
	public void setRestaurantOpen(int restaurantOpen) {
		this.restaurantOpen = restaurantOpen;
	}
	public int getRestaurantClose() {
		return restaurantClose;
	}
	public void setRestaurantClose(int restaurantClose) {
		this.restaurantClose = restaurantClose;
	}
	public String getRestaurantTel() {
		return restaurantTel;
	}
	public void setRestaurantTel(String restaurantTel) {
		this.restaurantTel = restaurantTel;
	}
	public String getRestaurantRestDay() {
		return restaurantRestDay;
	}
	public void setRestaurantRestDay(String restaurantRestDay) {
		this.restaurantRestDay = restaurantRestDay;
	}
	public Double getRestaurantScore() {
		return restaurantScore;
	}
	public void setRestaurantScore(Double restaurantScore) {
		this.restaurantScore = restaurantScore;
	}
	public String getRestaurantDelete() {
		return restaurantDelete;
	}
	public void setRestaurantDelete(String restaurantDelete) {
		this.restaurantDelete = restaurantDelete;
	}
	public String getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(String reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getReviewScore() {
		return reviewScore;
	}
	public void setReviewScore(String reviewScore) {
		this.reviewScore = reviewScore;
	}
	public String getReviewComment() {
		return reviewComment;
	}
	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}
	
}
