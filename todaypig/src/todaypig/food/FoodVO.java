package todaypig.food;

public class FoodVO {
	private String foodId;
	private String foodName;
	private String foodIsslow;
	private String categoryId;

	public FoodVO(String foodId, String foodName) {
		this.foodId = foodId;
		this.foodName = foodName;
	}

	public FoodVO(String foodName, String foodIsslow, String categoryId) {
		this.foodName = foodName;
		this.foodIsslow = foodIsslow;
		this.categoryId = categoryId;
	}

	public FoodVO(String foodId, String foodName, String foodIsslow, String categoryId) {
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodIsslow = foodIsslow;
		this.categoryId = categoryId;
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodIsslow() {
		return foodIsslow;
	}

	public void setFoodIsslow(String foodIsslow) {
		this.foodIsslow = foodIsslow;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
