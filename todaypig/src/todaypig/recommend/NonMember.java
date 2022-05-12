package todaypig.recommend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import todaypig.join.JoinVO;

public class NonMember {
	static Scanner scanner = new Scanner(System.in);
	public static List<JoinVO> Food(int Region) throws Exception {
		System.out.println();
		System.out.println("===========================오늘의 Pick 추천============================");
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
		builder.append(" where c.food_id = b.food_id ");
		builder.append("   and a.restaurant_no = c.restaurant_no ");
		builder.append("   and a.region_no ="+Region);
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

		int max = list.size();
		int randomNum = (int) (Math.random() * max);
		String menu = list.get(randomNum).getFoodName();
		// 여기까지 랜덤호출
		for (int i = 1; i < list.size(); i++) {
			JoinVO vo = list.get(i);
			if (vo.getFoodName().equals(menu)) {
				System.out.printf("%d. %s(%s)|%s|%s원\n", i, vo.getRestaurantName(), vo.getRestaurantAddress(),
						vo.getFoodName(), vo.getOfferPrice());
			}
		}
		System.out.println(" ");
		System.out.println("  ** 이 용 해 주 셔 서 감 사 합 니 다. **");
		System.out.println(" ");
		return list;

	}
}
