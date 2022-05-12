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

public class Donggu extends Main {
	static Scanner scanner = new Scanner(System.in);
	
	public static void RecommendationDong() throws Exception {
		System.out.println("");
		System.out.println("                        대덕구 내 메뉴추천                   ");
		System.out.println("====================================================================");
		System.out.println("         31.중식 || 32.일식 || 33.한식 || 34.양식 || 0.뒤로가기");
		System.out.println("====================================================================");
		System.out.print("추천받을 음식의 카테고리를 입력해주세요>");
		int menu51 = Integer.parseInt(scanner.nextLine());
		switch (menu51) {
		case 51:
			DongguFood(1);
			break;
		case 52:
			DongguFood(2);
			break;
		case 53:
			DongguFood(3);
			break;
		case 54:
			DongguFood(4);
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

	public static List<JoinVO> DongguFood(int Category) throws Exception {
		System.out.println();
		System.out.println("===========================오늘의 Pick 추천============================");
		switch (Category) {
		case 1:
			List<JoinVO> list1 = getRestaurant("'CN%'");
			setRandom(list1,1);
			return list1;
		case 2:
			List<JoinVO> list2 = getRestaurant("'JP%'");
			setRandom(list2,2);
			return list2;
		case 3:
			List<JoinVO> list3 = getRestaurant("'KR%'");
			setRandom(list3,3);
			return list3;
		case 4:
			List<JoinVO> list4 = getRestaurant("'WE%'");
			setRandom(list4,4);
			return list4;
		default:
			break;
		}
		return null;
	}

	private static void setRandom(List<JoinVO> list,int Category) throws Exception {
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
			DongguFood(Category);
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

	public static List<JoinVO> getRestaurant(String Category) throws Exception {
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
		builder.append(" where b.food_id like"+ Category);
		builder.append("   and c.food_id = b.food_id ");
		builder.append("   and a.restaurant_no = c.restaurant_no ");
		builder.append("   and a.region_no = 5 ");
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
	
	private static int Memory(String foodID, int restaurantNO) throws Exception {
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
