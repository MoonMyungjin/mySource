package todaypig.review;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import todaypig.Main;
import todaypig.join.JoinVO;

public class ReviewDAO extends Main{
	

	public List<JoinVO> getMyHistoryNotWriteReview() throws Exception{
		List<JoinVO> list = new ArrayList<JoinVO>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		StringBuilder builder = new StringBuilder();
		builder.append("    SELECT A.RESTAURANT_NO,       ");
		builder.append("           B.RESTAURANT_NAME,       ");
		builder.append("           A.FOOD_ID,       ");
		builder.append("           C.FOOD_NAME,       ");
		builder.append("           A.MEMBER_ID,       ");
		builder.append("           A.HISTORY_DATE,       ");
		builder.append("           A.HISTORY_WRITEREVIEW       ");
		builder.append("      FROM HISTORY A, RESTAURANT B, FOOD C       ");
		builder.append("     WHERE A.FOOD_ID = C.FOOD_ID ");
		builder.append("       AND B.RESTAURANT_NO = A.RESTAURANT_NO ");
		builder.append("       AND MEMBER_ID = '"+ UserId +"'       ");
		builder.append("       AND HISTORY_WRITEREVIEW IS NULL       ");
		String sql = builder.toString();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			int restaurantNO = resultSet.getInt("RESTAURANT_NO");
			String restaurantName = resultSet.getString("RESTAURANT_NAME");
			String foodID = resultSet.getString("FOOD_ID");
			String foodName = resultSet.getString("FOOD_NAME");
			String memberID = resultSet.getString("MEMBER_ID");
			String historyDateaddtime = resultSet.getString("HISTORY_DATE");
			String historyDate = historyDateaddtime.substring(0, 11);
			String historyWriteReview = resultSet.getString("HISTORY_WRITEREVIEW");
			list.add(new JoinVO(foodID,foodName,memberID,historyDate,historyWriteReview,restaurantNO,restaurantName));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}
	
	public List<JoinVO> getMyHistory() throws Exception{
		List<JoinVO> list = new ArrayList<JoinVO>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		StringBuilder builder = new StringBuilder();
		builder.append("    SELECT B.RESTAURANT_NAME,       ");
		builder.append("           C.FOOD_NAME,       ");
		builder.append("           A.HISTORY_DATE,       ");
		builder.append("           A.HISTORY_WRITEREVIEW       ");
		builder.append("      FROM HISTORY A, RESTAURANT B, FOOD C       ");
		builder.append("     WHERE A.FOOD_ID = C.FOOD_ID ");
		builder.append("       AND B.RESTAURANT_NO = A.RESTAURANT_NO ");
		builder.append("       AND MEMBER_ID = '"+ UserId +"'       ");
		builder.append("     ORDER BY 3       ");
		String sql = builder.toString();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			String restaurantName = resultSet.getString("RESTAURANT_NAME");
			String foodName = resultSet.getString("FOOD_NAME");
			String historyDateaddtime = resultSet.getString("HISTORY_DATE");
			String historyDate = historyDateaddtime.substring(0, 11);
			String historyWriteReview = resultSet.getString("HISTORY_WRITEREVIEW");
			list.add(new JoinVO(foodName,historyDate,historyWriteReview,restaurantName));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}
	
	public int writereview(int restaurantNO,String foodID,int score,String comment) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	    Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
	    StringBuilder builder = new StringBuilder();
//	    현재 review table에서 가장큰값에서 1을 더한 값을 가져옴 >> 새글 리뷰번호로 사용
	    builder.append("    SELECT MAX(REVIEW_NO)+1       ");
	    builder.append("    FROM REVIEW       ");
	    String sql = builder.toString();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		int reviewNo = 0;
		while (resultSet.next()) {
			reviewNo = resultSet.getInt("MAX(REVIEW_NO)+1");
		}
	    
		StringBuilder builder2 = new StringBuilder();
	    builder2.append(" INSERT INTO REVIEW VALUES(?,?,?,?,?,?)             ");
	    String sql2 = builder2.toString();
	    PreparedStatement statement2 = connection.prepareStatement(sql2);
		statement2.setInt(1, reviewNo);
		statement2.setString(2, UserId);
		statement2.setInt(3, restaurantNO);
		statement2.setString(4, foodID);
		statement2.setInt(5, score);
		statement2.setString(6, comment);
		int executeUpdate = statement2.executeUpdate();
		
		statement.close();
	    connection.close();
		return executeUpdate;
	}
	
	public int Checkwritereview(String foodID,String historyDate,int restaurantNO) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	    Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
	    StringBuilder builder = new StringBuilder();
	    builder.append("   UPDATE HISTORY  ");
	    builder.append("      SET HISTORY_WRITEREVIEW = 'y'  ");
	    builder.append("    WHERE MEMBER_ID = ?  ");
	    builder.append("      AND FOOD_ID = ?  ");
	    builder.append("      AND RESTAURANT_NO = ?  ");
	    String sql = builder.toString();
	    PreparedStatement statement = connection.prepareStatement(sql);
	    statement.setString(1, UserId);
	    statement.setString(2, foodID);
	    statement.setInt(3, restaurantNO);
		int executeUpdate = statement.executeUpdate();
		
		statement.close();
	    connection.close();
		return executeUpdate;
	}
	
	public int AverageUpdate(int restaurantNO) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	    Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
//	    리뷰테이블에서 매개변수 식당의 평균평점을 가지고와서 newscore에 저장
	    StringBuilder builder = new StringBuilder();
	    builder.append("   SELECT RESTAURANT_NO,  ");
	    builder.append("          ROUND(AVG(REVIEW_SCORE),1) AS ASCORE  ");
	    builder.append("     FROM REVIEW  ");
	    builder.append("    WHERE RESTAURANT_NO = " + restaurantNO);
	    builder.append("    GROUP BY RESTAURANT_NO  ");
	    String sql = builder.toString();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		int newscore = 0;
		while (resultSet.next()) {
			newscore = resultSet.getInt("ASCORE");
		}
//		restaurant table 내 매개변수restaurantNO를 받은 식당의 평점을 newscore로 업데이트
		StringBuilder builder2 = new StringBuilder();
	    builder2.append("  UPDATE RESTAURANT   ");
	    builder2.append("     SET RESTAURANT_SCORE = ?   ");
	    builder2.append("   WHERE RESTAURANT_NO = ?   ");
	    String sql2 = builder2.toString();
	    PreparedStatement statement2 = connection.prepareStatement(sql2);
	    statement2.setInt(1, newscore);
	    statement2.setInt(2, restaurantNO);
		int executeUpdate = statement2.executeUpdate();
		
//		가게 평점 평균이 5이하라면 RESTAURANT_DELETE = 'y'로 변경
//		가게 평균 평점이 5초과라면 RESTAURANT_DELETE = null로 변경 하는 메소드
		if (newscore>5 && newscore<=10) {
			StringBuilder builder3 = new StringBuilder();
			builder3.append("   UPDATE RESTAURANT   ");
			builder3.append("      SET RESTAURANT_DELETE = NULL   ");
			builder3.append("    WHERE RESTAURANT_NO = "+ restaurantNO);
			String sql3 = builder3.toString();
		    PreparedStatement statement3 = connection.prepareStatement(sql3);
		    statement3.executeUpdate();
		} else {
			StringBuilder builder3 = new StringBuilder();
			builder3.append("   UPDATE RESTAURANT   ");
			builder3.append("      SET RESTAURANT_DELETE = 'y'   ");
			builder3.append("    WHERE RESTAURANT_NO = "+ restaurantNO);
			String sql3 = builder3.toString();
		    PreparedStatement statement3 = connection.prepareStatement(sql3);
		    statement3.executeUpdate();
		}
		
		statement.close();
		connection.close();
		return executeUpdate;
	}
	
}
