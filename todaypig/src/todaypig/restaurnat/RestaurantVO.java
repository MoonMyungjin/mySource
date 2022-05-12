package todaypig.restaurnat;

public class RestaurantVO {
	private String restaurantNo;
	private String restaurantName;
	private String restaurantAddress;
	private String regionNo;
	private String restaurantOpen;
	private String restaurantClose;
	private String restaurantTel;
	private String restaurantRestday;
	private String restaurantScore;
	private String restaurantDelete;

	public RestaurantVO(String restaurantNo, String restaurantName) {
		this.restaurantNo = restaurantNo;
		this.restaurantName = restaurantName;
	}
	
	

	public RestaurantVO(String restaurantName, String restaurantAddress, String regionNo, String restaurantOpen,
			String restaurantClose, String restaurantTel) {
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.regionNo = regionNo;
		this.restaurantOpen = restaurantOpen;
		this.restaurantClose = restaurantClose;
		this.restaurantTel = restaurantTel;
	}



	public RestaurantVO(String restaurantNo, String restaurantName, String restaurantAddress, String regionNo,
			String restaurantOpen, String restaurantClose, String restaurantTel, String restaurantRestday,
			String restaurantScore, String restaurantDelete) {
		this.restaurantNo = restaurantNo;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.regionNo = regionNo;
		this.restaurantOpen = restaurantOpen;
		this.restaurantClose = restaurantClose;
		this.restaurantTel = restaurantTel;
		this.restaurantRestday = restaurantRestday;
		this.restaurantScore = restaurantScore;
		this.restaurantDelete = restaurantDelete;
	}



	public String getRestaurantNo() {
		return restaurantNo;
	}

	public void setRestaurantNo(String restaurantNo) {
		this.restaurantNo = restaurantNo;
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

	public String getRegionNo() {
		return regionNo;
	}

	public void setRegionNo(String regionNo) {
		this.regionNo = regionNo;
	}

	public String getRestaurantOpen() {
		return restaurantOpen;
	}

	public void setRestaurantOpen(String restaurantOpen) {
		this.restaurantOpen = restaurantOpen;
	}

	public String getRestaurantClose() {
		return restaurantClose;
	}

	public void setRestaurantClose(String restaurantClose) {
		this.restaurantClose = restaurantClose;
	}

	public String getRestaurantTel() {
		return restaurantTel;
	}

	public void setRestaurantTel(String restaurantTel) {
		this.restaurantTel = restaurantTel;
	}

	public String getRestaurantRestday() {
		return restaurantRestday;
	}

	public void setRestaurantRestday(String restaurantRestday) {
		this.restaurantRestday = restaurantRestday;
	}

	public String getRestaurantScore() {
		return restaurantScore;
	}

	public void setRestaurantScore(String restaurantScore) {
		this.restaurantScore = restaurantScore;
	}

	public String getRestaurantDelete() {
		return restaurantDelete;
	}

	public void setRestaurantDelete(String restaurantDelete) {
		this.restaurantDelete = restaurantDelete;
	}
}
