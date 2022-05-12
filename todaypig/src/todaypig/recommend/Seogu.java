package todaypig.recommend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import todaypig.Main;
import todaypig.join.JoinVO;

public class Seogu extends Main {
	static Scanner scanner = new Scanner(System.in);
	
	public static void RecommendationSeogu() throws Exception {
		System.out.println("");
		System.out.println("                           서구 내 메뉴추천                   ");
		System.out.println("====================================================================");
		System.out.println("          21.중식 || 22.일식 || 23.한식 || 24.양식 || 0.뒤로가기");
		System.out.println("====================================================================");
		System.out.print("추천받을 음식의 카테고리를 입력해주세요>");
		int menu21 = Integer.parseInt(scanner.nextLine());
		switch (menu21) {
		case 21:
			SeoguFoodCN();
			break;
		case 22:
			SeoguFoodJP();
			break;
		case 23:
			SeoguFoodKR();
			break;
		case 24:
			SeoguFoodWE();
			break;
		case 0:
			MemberSelect();
			break;
		default:
			System.out.println("");
			System.out.println("제시된 숫자를 입력해주세요");
			System.out.println("");
			break;	
		}
		
}		

	public static List<JoinVO> SeoguFoodCN() throws Exception {
		System.out.println();
		System.out.println("===========================오늘의 Pick 추천============================");
		List<JoinVO> list = getRestaurantCN();
		setRandomCN(list);
		
		return list;
	}
	
	public static List<JoinVO> SeoguFoodJP() throws Exception {
		System.out.println();
		System.out.println("===========================오늘의 Pick 추천============================");
		List<JoinVO> list = getRestaurantJP();
		setRandomJP(list);
		
		return list;
	}
	
	public static List<JoinVO> SeoguFoodKR() throws Exception {
		System.out.println();
		System.out.println("===========================오늘의 Pick 추천============================");
		List<JoinVO> list = getRestaurantKR();
		setRandomKR(list);
		
		return list;
	}
	
	public static List<JoinVO> SeoguFoodWE() throws Exception {
		System.out.println();
		System.out.println("===========================오늘의 Pick 추천============================");
		List<JoinVO> list = getRestaurantWE();
		setRandomWE(list);
		
		return list;
	}


	private static void setRandomCN(List<JoinVO> list) throws Exception {
		int max = list.size();
		int randomNum = (int) (Math.random() * max);
		String menu = list.get(randomNum).getFoodName();
		// 여기까지 랜덤호출
		for (int i = 0; i < list.size(); i++) {
			JoinVO vo = list.get(i);
			if (vo.getFoodName().equals(menu)) {
				System.out.printf("%d. %s(%s)|%s|%s원\n", i, vo.getRestaurantName(), vo.getRestaurantAddress(),
						vo.getFoodName(), vo.getOfferPrice());
			}
		}
		System.out.println("결정하실 번호를 입력해주세요.");
		int choice = Integer.parseInt(scanner.nextLine());
		JoinVO vo = list.get(choice);
		System.out.println("");
		String foodID = vo.getFoodID();
		int restaurantNO = vo.getRestaurantNO();
		System.out.println("결정하신 메뉴>>");
		System.out.printf("     메뉴명 : %s \n",vo.getFoodName());
		System.out.printf("     상호명 : %s \n",vo.getRestaurantName());
		System.out.printf("       가격 : %d \n",vo.getOfferPrice());
		System.out.printf("   영업시간 : %d ~ %d \n ",vo.getRestaurantOpen(),vo.getRestaurantClose());
		if (vo.getFoodIsSlow().equals("Y")) {
			System.out.println(" ");
			System.out.println("주문에서 식사까지 오래걸리는 메뉴입니다. 예약전화 권장합니다.");
			System.out.println(" ");
		}
		System.out.printf("가게전화번호 : %s \n",vo.getRestaurantTel());
		System.out.printf("    가게주소 : %s \n",vo.getRestaurantAddress());
		System.out.printf("    휴무요일 : %s \n",vo.getRestaurantRestDay());
		System.out.printf("    가게평점 : %.1f점 (10점만점) \n",vo.getRestaurantScore());
		System.out.println(" ");
		System.out.print("1.최종확정 || 2. 다시추천받기 || 0. 초기화면으로 >>");
		int choice2 = Integer.parseInt(scanner.nextLine());
		switch (choice2) {
		case 1:
			Memory(foodID, restaurantNO);
			break;
		case 2:
			SeoguFoodCN();
			break;
		case 0:
			MemberSelect();
			break;
		default:
			System.out.println("");
			System.out.println("제시된 숫자를 입력해주세요");
			System.out.println("");
			break;
		}
			
	}

	private static List<JoinVO> getRestaurantCN() throws Exception {
		List<JoinVO> list = new ArrayList<JoinVO>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("select a.restaurant_no, " );
		builder.append("       b.food_id, " );
		builder.append("       a.restaurant_name , " );
		builder.append("       a.restaurant_address , " );
		builder.append("       a.restaurant_open , " );
		builder.append("       a.restaurant_close , " );
		builder.append("       a.restaurant_tel, " );
		builder.append("       a.restaurant_restday, " );
		builder.append("       a.restaurant_score, " );
		builder.append("       b.food_name,  " );
		builder.append("       b.food_isslow,   ");
		builder.append("       c.offer_price   ");
		builder.append("  from restaurant a, food b, offer c ");
		builder.append(" where b.food_id like 'CN%' ");
		builder.append("   and c.food_id = b.food_id ");
		builder.append("   and a.restaurant_no = c.restaurant_no ");
		builder.append("   and a.region_no = 2 ");
		String sql = builder.toString();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			int restaurantNo = resultSet.getInt("RESTAURANT_NO");
			String foodId = resultSet.getString("FOOD_ID");
			String restaurantName = resultSet.getString("RESTAURANT_NAME");
			String restaurantAddress = resultSet.getString("RESTAURANT_ADDRESS");
			int restaurantOpen = resultSet.getInt("restaurant_open");
			int restaurantClose = resultSet.getInt("restaurant_close");
			String restaurantTel = resultSet.getString("RESTAURANT_tel");
			String restaurantRestday = resultSet.getString("RESTAURANT_restday");
			double restaurantScore = resultSet.getDouble("RESTAURANT_score");
			String foodName = resultSet.getString("FOOD_NAME");
			int offerPrice = resultSet.getInt("OFFER_PRICE");
			String foodisSlow = resultSet.getString("FOOD_ISSLOW");
			list.add(new JoinVO(restaurantNo, foodId, restaurantName, restaurantAddress, restaurantOpen, restaurantClose, restaurantTel, restaurantRestday, restaurantScore ,foodName, offerPrice, foodisSlow));
		}

		resultSet.close();
		statement.close();
		connection.close();
		return list;

	}
	
	private static void setRandomJP(List<JoinVO> list) throws Exception {
		int max = list.size();
		int randomNum = (int) (Math.random() * max);
		String menu = list.get(randomNum).getFoodName();
		// 여기까지 랜덤호출
		for (int i = 0; i < list.size(); i++) {
			JoinVO vo = list.get(i);
			if (vo.getFoodName().equals(menu)) {
				System.out.printf("%d. %s(%s)|%s|%s원\n", i, vo.getRestaurantName(), vo.getRestaurantAddress(),
						vo.getFoodName(), vo.getOfferPrice());
			}
		}
		System.out.println("결정하실 번호를 입력해주세요.");
		int choice = Integer.parseInt(scanner.nextLine());
		JoinVO vo = list.get(choice);
		System.out.println("");
		String foodID = vo.getFoodID();
		int restaurantNO = vo.getRestaurantNO();
		System.out.println("결정하신 메뉴>>");
		System.out.printf("     메뉴명 : %s \n", vo.getFoodName());
		System.out.printf("     상호명 : %s \n", vo.getRestaurantName());
		System.out.printf("       가격 : %d \n", vo.getOfferPrice());
		System.out.printf("   영업시간 : %d ~ %d \n ", vo.getRestaurantOpen(), vo.getRestaurantClose());
		if (vo.getFoodIsSlow().equals("Y")) {
			System.out.println(" ");
			System.out.println("주문에서 식사까지 오래걸리는 메뉴입니다. 예약전화 권장합니다.");
			System.out.println(" ");
		}
		System.out.printf("가게전화번호 : %s \n", vo.getRestaurantTel());
		System.out.printf("    가게주소 : %s \n", vo.getRestaurantAddress());
		System.out.printf("    휴무요일 : %s \n", vo.getRestaurantRestDay());
		System.out.printf("    가게평점 : %.1f점 (10점만점) \n", vo.getRestaurantScore());
		System.out.println(" ");
		System.out.print("1.최종확정 || 2. 다시추천받기 || 0. 초기화면으로 >>");
		int choice2 = Integer.parseInt(scanner.nextLine());
		switch (choice2) {
		case 1:
			Memory(foodID, restaurantNO);
			break;
		case 2:
			SeoguFoodJP();
			break;
		case 0:
			MemberSelect();
			break;
		default:
			System.out.println("");
			System.out.println("제시된 숫자를 입력해주세요");
			System.out.println("");
			break;
		}
		
	}

	private static List<JoinVO> getRestaurantJP() throws Exception {
		List<JoinVO> list = new ArrayList<JoinVO>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("select a.restaurant_no, ");
		builder.append("       b.food_id, ");
		builder.append("       a.restaurant_name , ");
		builder.append("       a.restaurant_address , ");
		builder.append("       a.restaurant_open , ");
		builder.append("       a.restaurant_close , ");
		builder.append("       a.restaurant_tel, ");
		builder.append("       a.restaurant_restday, ");
		builder.append("       a.restaurant_score, ");
		builder.append("       b.food_name,  ");
		builder.append("       b.food_isslow,   ");
		builder.append("       c.offer_price   ");
		builder.append("  from restaurant a, food b, offer c ");
		builder.append(" where b.food_id like 'JP%' ");
		builder.append("   and c.food_id = b.food_id ");
		builder.append("   and a.restaurant_no = c.restaurant_no ");
		builder.append("   and a.region_no = 2 ");
		String sql = builder.toString();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			int restaurantNo = resultSet.getInt("RESTAURANT_NO");
			String foodId = resultSet.getString("FOOD_ID");
			String restaurantName = resultSet.getString("RESTAURANT_NAME");
			String restaurantAddress = resultSet.getString("RESTAURANT_ADDRESS");
			int restaurantOpen = resultSet.getInt("restaurant_open");
			int restaurantClose = resultSet.getInt("restaurant_close");
			String restaurantTel = resultSet.getString("RESTAURANT_tel");
			String restaurantRestday = resultSet.getString("RESTAURANT_restday");
			double restaurantScore = resultSet.getDouble("RESTAURANT_score");
			String foodName = resultSet.getString("FOOD_NAME");
			int offerPrice = resultSet.getInt("OFFER_PRICE");
			String foodisSlow = resultSet.getString("FOOD_ISSLOW");
			list.add(
					new JoinVO(restaurantNo, foodId, restaurantName, restaurantAddress, restaurantOpen, restaurantClose,
							restaurantTel, restaurantRestday, restaurantScore, foodName, offerPrice, foodisSlow));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}

	private static void setRandomKR(List<JoinVO> list) throws Exception {
		int max = list.size();
		int randomNum = (int) (Math.random() * max);
		String menu = list.get(randomNum).getFoodName();
		// 여기까지 랜덤호출
		for (int i = 0; i < list.size(); i++) {
			JoinVO vo = list.get(i);
			if (vo.getFoodName().equals(menu)) {
				System.out.printf("%d. %s(%s)|%s|%s원\n", i, vo.getRestaurantName(), vo.getRestaurantAddress(),
						vo.getFoodName(), vo.getOfferPrice());
			}
		}
		System.out.println("결정하실 번호를 입력해주세요.");
		int choice = Integer.parseInt(scanner.nextLine());
		JoinVO vo = list.get(choice);
		System.out.println("");
		String foodID = vo.getFoodID();
		int restaurantNO = vo.getRestaurantNO();
		System.out.println("결정하신 메뉴>>");
		System.out.printf("     메뉴명 : %s \n", vo.getFoodName());
		System.out.printf("     상호명 : %s \n", vo.getRestaurantName());
		System.out.printf("       가격 : %d \n", vo.getOfferPrice());
		System.out.printf("   영업시간 : %d ~ %d \n ", vo.getRestaurantOpen(), vo.getRestaurantClose());
		if (vo.getFoodIsSlow().equals("Y")) {
			System.out.println(" ");
			System.out.println("주문에서 식사까지 오래걸리는 메뉴입니다. 예약전화 권장합니다.");
			System.out.println(" ");
		}
		System.out.printf("가게전화번호 : %s \n", vo.getRestaurantTel());
		System.out.printf("    가게주소 : %s \n", vo.getRestaurantAddress());
		System.out.printf("    휴무요일 : %s \n", vo.getRestaurantRestDay());
		System.out.printf("    가게평점 : %.1f점 (10점만점) \n", vo.getRestaurantScore());
		System.out.println(" ");
		System.out.print("1.최종확정 || 2. 다시추천받기 || 0. 초기화면으로 >>");
		int choice2 = Integer.parseInt(scanner.nextLine());
		switch (choice2) {
		case 1:
			Memory(foodID, restaurantNO);
			break;
		case 2:
			SeoguFoodKR();
			break;
		case 0:
			MemberSelect();
			break;
		default:
			System.out.println("");
			System.out.println("제시된 숫자를 입력해주세요");
			System.out.println("");
			break;
		}
		
	}

	private static List<JoinVO> getRestaurantKR() throws Exception {
		List<JoinVO> list = new ArrayList<JoinVO>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("select a.restaurant_no, ");
		builder.append("       b.food_id, ");
		builder.append("       a.restaurant_name , ");
		builder.append("       a.restaurant_address , ");
		builder.append("       a.restaurant_open , ");
		builder.append("       a.restaurant_close , ");
		builder.append("       a.restaurant_tel, ");
		builder.append("       a.restaurant_restday, ");
		builder.append("       a.restaurant_score, ");
		builder.append("       b.food_name,  ");
		builder.append("       b.food_isslow,   ");
		builder.append("       c.offer_price   ");
		builder.append("  from restaurant a, food b, offer c ");
		builder.append(" where b.food_id like 'KR%' ");
		builder.append("   and c.food_id = b.food_id ");
		builder.append("   and a.restaurant_no = c.restaurant_no ");
		builder.append("   and a.region_no = 2 ");
		String sql = builder.toString();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			int restaurantNo = resultSet.getInt("RESTAURANT_NO");
			String foodId = resultSet.getString("FOOD_ID");
			String restaurantName = resultSet.getString("RESTAURANT_NAME");
			String restaurantAddress = resultSet.getString("RESTAURANT_ADDRESS");
			int restaurantOpen = resultSet.getInt("restaurant_open");
			int restaurantClose = resultSet.getInt("restaurant_close");
			String restaurantTel = resultSet.getString("RESTAURANT_tel");
			String restaurantRestday = resultSet.getString("RESTAURANT_restday");
			double restaurantScore = resultSet.getDouble("RESTAURANT_score");
			String foodName = resultSet.getString("FOOD_NAME");
			int offerPrice = resultSet.getInt("OFFER_PRICE");
			String foodisSlow = resultSet.getString("FOOD_ISSLOW");
			list.add(
					new JoinVO(restaurantNo, foodId, restaurantName, restaurantAddress, restaurantOpen, restaurantClose,
							restaurantTel, restaurantRestday, restaurantScore, foodName, offerPrice, foodisSlow));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}
	
	private static void setRandomWE(List<JoinVO> list) throws Exception {
		int max = list.size();
		int randomNum = (int) (Math.random() * max);
		String menu = list.get(randomNum).getFoodName();
		// 여기까지 랜덤호출
		for (int i = 0; i < list.size(); i++) {
			JoinVO vo = list.get(i);
			if (vo.getFoodName().equals(menu)) {
				System.out.printf("%d. %s(%s)|%s|%s원\n", i, vo.getRestaurantName(), vo.getRestaurantAddress(),
						vo.getFoodName(), vo.getOfferPrice());
			}
		}
		System.out.println("결정하실 번호를 입력해주세요.");
		int choice = Integer.parseInt(scanner.nextLine());
		JoinVO vo = list.get(choice);
		System.out.println("");
		String foodID = vo.getFoodID();
		int restaurantNO = vo.getRestaurantNO();
		System.out.println("결정하신 메뉴>>");
		System.out.printf("     메뉴명 : %s \n", vo.getFoodName());
		System.out.printf("     상호명 : %s \n", vo.getRestaurantName());
		System.out.printf("       가격 : %d \n", vo.getOfferPrice());
		System.out.printf("   영업시간 : %d ~ %d \n ", vo.getRestaurantOpen(), vo.getRestaurantClose());
		if (vo.getFoodIsSlow().equals("Y")) {
			System.out.println(" ");
			System.out.println("주문에서 식사까지 오래걸리는 메뉴입니다. 예약전화 권장합니다.");
			System.out.println(" ");
		}
		System.out.printf("가게전화번호 : %s \n", vo.getRestaurantTel());
		System.out.printf("    가게주소 : %s \n", vo.getRestaurantAddress());
		System.out.printf("    휴무요일 : %s \n", vo.getRestaurantRestDay());
		System.out.printf("    가게평점 : %.1f점 (10점만점) \n", vo.getRestaurantScore());
		System.out.println(" ");
		System.out.print("1.최종확정 || 2. 다시추천받기 || 0. 초기화면으로 >>");
		int choice2 = Integer.parseInt(scanner.nextLine());
		switch (choice2) {
		case 1:
			Memory(foodID, restaurantNO);
			break;
		case 2:
			SeoguFoodWE();
			break;
		case 0:
			MemberSelect();
			break;
		default:
			System.out.println("");
			System.out.println("제시된 숫자를 입력해주세요");
			System.out.println("");
			break;
		}
		
	}

	private static List<JoinVO> getRestaurantWE() throws Exception {
		List<JoinVO> list = new ArrayList<JoinVO>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("select a.restaurant_no, ");
		builder.append("       b.food_id, ");
		builder.append("       a.restaurant_name , ");
		builder.append("       a.restaurant_address , ");
		builder.append("       a.restaurant_open , ");
		builder.append("       a.restaurant_close , ");
		builder.append("       a.restaurant_tel, ");
		builder.append("       a.restaurant_restday, ");
		builder.append("       a.restaurant_score, ");
		builder.append("       b.food_name,  ");
		builder.append("       b.food_isslow,   ");
		builder.append("       c.offer_price   ");
		builder.append("  from restaurant a, food b, offer c ");
		builder.append(" where b.food_id like 'WE%' ");
		builder.append("   and c.food_id = b.food_id ");
		builder.append("   and a.restaurant_no = c.restaurant_no ");
		builder.append("   and a.region_no = 2 ");
		String sql = builder.toString();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			int restaurantNo = resultSet.getInt("RESTAURANT_NO");
			String foodId = resultSet.getString("FOOD_ID");
			String restaurantName = resultSet.getString("RESTAURANT_NAME");
			String restaurantAddress = resultSet.getString("RESTAURANT_ADDRESS");
			int restaurantOpen = resultSet.getInt("restaurant_open");
			int restaurantClose = resultSet.getInt("restaurant_close");
			String restaurantTel = resultSet.getString("RESTAURANT_tel");
			String restaurantRestday = resultSet.getString("RESTAURANT_restday");
			double restaurantScore = resultSet.getDouble("RESTAURANT_score");
			String foodName = resultSet.getString("FOOD_NAME");
			int offerPrice = resultSet.getInt("OFFER_PRICE");
			String foodisSlow = resultSet.getString("FOOD_ISSLOW");
			list.add(
					new JoinVO(restaurantNo, foodId, restaurantName, restaurantAddress, restaurantOpen, restaurantClose,
							restaurantTel, restaurantRestday, restaurantScore, foodName, offerPrice, foodisSlow));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}

	public static int Memory(String foodID, int restaurantNO) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("insert into history " );
		builder.append("    values (?,?,?,sysdate,null) " );
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, restaurantNO);
		statement.setString(2, foodID);
		statement.setString(3, UserId);
		int executeUpdate = statement.executeUpdate();
		
		statement.close();
		connection.close();
		return executeUpdate;		
	}

}
