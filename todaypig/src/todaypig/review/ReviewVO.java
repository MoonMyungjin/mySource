package todaypig.review;

public class ReviewVO {
	private int ReviewNO;
	private String MemberID;
	private int RestaurantNO;
	private String FoodID;
	private int ReviewScore;
	private String ReviewComment;
	
	public ReviewVO(int reviewNO, String memberID, int restaurantNO, String foodID, int reviewScore,
			String reviewComment) {
		ReviewNO = reviewNO;
		MemberID = memberID;
		RestaurantNO = restaurantNO;
		FoodID = foodID;
		ReviewScore = reviewScore;
		ReviewComment = reviewComment;
	}

	public int getReviewNO() {
		return ReviewNO;
	}

	public void setReviewNO(int reviewNO) {
		ReviewNO = reviewNO;
	}

	public String getMemberID() {
		return MemberID;
	}

	public void setMemberID(String memberID) {
		MemberID = memberID;
	}

	public int getRestaurantNO() {
		return RestaurantNO;
	}

	public void setRestaurantNO(int restaurantNO) {
		RestaurantNO = restaurantNO;
	}

	public String getFoodID() {
		return FoodID;
	}

	public void setFoodID(String foodID) {
		FoodID = foodID;
	}

	public int getReviewScore() {
		return ReviewScore;
	}

	public void setReviewScore(int reviewScore) {
		ReviewScore = reviewScore;
	}

	public String getReviewComment() {
		return ReviewComment;
	}

	public void setReviewComment(String reviewComment) {
		ReviewComment = reviewComment;
	}

	@Override
	public String toString() {
		return String.format(
				"ReivewVO [ReviewNO=%s, MemberID=%s, RestaurantNO=%s, FoodID=%s, ReviewScore=%s, ReviewComment=%s]",
				ReviewNO, MemberID, RestaurantNO, FoodID, ReviewScore, ReviewComment);
	}
}
