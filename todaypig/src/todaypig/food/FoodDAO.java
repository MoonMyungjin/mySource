package todaypig.food;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {

	// 음식 전체목록조회 음식번호와 음식이름으로 데이터 추출
	public List<FoodVO> getFoodList() throws Exception {
		List<FoodVO> list = new ArrayList<>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig",
				"java");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT FOOD_NO, FOOD_NAME FROM FOOD ORDER BY 1");

		while (resultSet.next()) {
			String foodId = resultSet.getString("FOOD_ID");
			String foodName = resultSet.getString("FOOD_NAME");
			list.add(new FoodVO(foodId, foodName));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}

	// 음식 넘버로 모든 컬럼 정보 추출
	public FoodVO getFood(String searchId) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig",
				"java");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("	FOOD_ID");
		builder.append("	FOOD_NAME");
		builder.append("	FOOD_ISSLOW");
		builder.append("	CATEGORY_ID");
		builder.append("  FROM FOOD");
		builder.append(" WHERE FOOD_ID = " + searchId);
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);

		FoodVO vo = null;
		if (resultSet.next()) {
			String foodId = resultSet.getString("FOOD_ID");
			String foodName = resultSet.getString("FOOD_NAME");
			String foodIsslow = resultSet.getString("FOOD_ISSLOW");
			String categoryId = resultSet.getString("CATEGORY_ID");
			vo = new FoodVO(foodId, foodName, foodIsslow, categoryId);
		}
		resultSet.close();
		statement.close();
		connection.close();
		return vo;
	}
	
	// 카테고리번호로 카테고리에 속하는 음식명 추출
		public List<String> getFoodListOfCategory(String categoryId) throws Exception {
			ArrayList<String> list = new ArrayList<>();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig",
					"java");
			Statement statement = connection.createStatement();
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT ");
			builder.append("	FOOD_NAME");
			builder.append("  FROM FOOD");
			builder.append(" WHERE CATEGORY_ID = '"+categoryId+"'");
			String sql = builder.toString();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				list.add(resultSet.getString("FOOD_NAME"));
			}
			resultSet.close();
			statement.close();
			connection.close();
			return list;
		}

	// FOOD_ID는 자동부여, FOOD_NAME,FOOD_ISSLOW,FOOD_ISSLOW 입력하는 INSERT
	public int insertFood(FoodVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO FOOD (FOOD_ID, ");
		builder.append("                  FOOD_NAME, ");
		builder.append("                  FOOD_ISSLOW, ");
		builder.append("                  CATEGORY_ID) ");
		builder.append("     VALUES((SELECT ?||TO_CHAR(COUNT(FOOD_ID)+1, 'FM000') AS FOOD_ID FROM FOOD WHERE CATEGORY_ID = ?),?,?,?)");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getCategoryId());
		statement.setString(2, vo.getCategoryId());
		statement.setString(3, vo.getFoodName());
		statement.setString(4, vo.getFoodIsslow());
		statement.setString(5, vo.getCategoryId());
		int executeUpdate = statement.executeUpdate();

		statement.close();
		connection.close();
		return executeUpdate;

	}

	public int updateFood(String newData) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig",
				"java");
		PreparedStatement statement = connection.prepareStatement("UPDATE FOOD SET ? = ? WHERE FOOD_ID = ?");
		statement.setString(1, "'" + newData + "'");		
	    int executeUpdate = statement.executeUpdate();
		statement.close();
		connection.close();
		return executeUpdate;
	}
	
	public int deleteMember(int searchId) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig",
				"java");
		Statement statement = connection.createStatement();
		int executeUpdate = statement.executeUpdate("UPDATE FOOD SET FOOD_DELETE = 'Y' WHERE FOOD_NO = " + searchId);
		statement.close();
		connection.close();
		return executeUpdate;
	}

}
