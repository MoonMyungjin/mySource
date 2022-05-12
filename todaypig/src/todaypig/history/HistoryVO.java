package todaypig.history;

public class HistoryVO {
	private int restaurantNo;
	private String foodId;
	private String memberId;
	private String historyDate;
	private String historyWriteReview;

	public HistoryVO(int restaurantNo, String foodId, String memberId) {
		this.restaurantNo = restaurantNo;
		this.foodId = foodId;
		this.memberId = memberId;
	}

	public HistoryVO(int restaurantNo, String foodId, String memberId, String historyDate,
			String historyWriteReview) {
		this.restaurantNo = restaurantNo;
		this.foodId = foodId;
		this.memberId = memberId;
		this.historyDate = historyDate;
		this.historyWriteReview = historyWriteReview;
	}

	public int getRestaurantNo() {
		return restaurantNo;
	}

	public void setRestaurantNo(int restaurantNo) {
		this.restaurantNo = restaurantNo;
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

}
