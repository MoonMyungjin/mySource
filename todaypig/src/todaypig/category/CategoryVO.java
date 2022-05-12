package todaypig.category;

public class CategoryVO {
	private String categoryId;
	private String categoryName;

	public CategoryVO(String categoryId) {
		this.categoryId = categoryId;
	}

	public CategoryVO(String categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
