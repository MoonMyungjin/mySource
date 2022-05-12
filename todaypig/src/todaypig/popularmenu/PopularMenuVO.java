package todaypig.popularmenu;

public class PopularMenuVO {
	private String foodId;
	private String foodName;
	private String restaurantName;
	private String restaurantAddress;
	private String restaurantTime;
	private String restaurantTell;
	private String restaurantRestday;
	private String offerPrice;
	
	

	public PopularMenuVO(String foodId, String foodName) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
	}

	public PopularMenuVO(String foodName, String restaurantName, String restaurantAddress, String restaurantTime,
			String restaurantTell, String restaurantRestday, String offerPrice) {
		this.foodName = foodName;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.restaurantTime = restaurantTime;
		this.restaurantTell = restaurantTell;
		this.restaurantRestday = restaurantRestday;
		this.offerPrice = offerPrice;
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public String getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(String offerPrice) {
		this.offerPrice = offerPrice;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
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

	public String getRestaurantTime() {
		return restaurantTime;
	}

	public void setRestaurantTime(String restaurantTime) {
		this.restaurantTime = restaurantTime;
	}

	public String getRestaurantTell() {
		return restaurantTell;
	}

	public void setRestaurantTell(String restaurantTell) {
		this.restaurantTell = restaurantTell;
	}

	public String getRestaurantRestday() {
		return restaurantRestday;
	}

	public void setRestaurantRestday(String restaurantRestday) {
		this.restaurantRestday = restaurantRestday;
	}

}