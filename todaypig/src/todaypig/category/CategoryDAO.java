package todaypig.category;

public class CategoryDAO {
	
	public String getCategoryId(int columnIndex) {
		switch (columnIndex) {
		case 1:  return "KR";
		case 2:  return "CN";
		case 3:  return "JP";
		case 4:  return "WE";
		case 5:  return "CF";
		case 6:  return "SN";
		case 7:  return "AS";
		}
		return null;
	}

}
