package todaypig.restaurnat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import todaypig.join.JoinVO;

public class RestaurantDAO {
	
	//가게 전체목록조회 가게번호와 상호명만 데이터 추출
	public List<RestaurantVO> getRestaurantList() throws Exception{
		List<RestaurantVO> list = new ArrayList<>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT RESTAURANT_NO, RESTAURANT_NAME FROM RESTAURANT ORDER BY 1");
		
		while(resultSet.next()) {
			String restaurantNo = resultSet.getString("RESTAURANT_NO");
			String restaurantName = resultSet.getString("RESTAURANT_NAME");
			list.add(new RestaurantVO(restaurantNo, restaurantName));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
		

}
	//가게번호를 매개변수로 Restaurant의 모든 컬럼 정보 추출
	public RestaurantVO getRestaurant(int searchNo) throws Exception  {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("    RESTAURANT_NO,");
		builder.append("    RESTAURANT_NAME, ");
		builder.append("    RESTAURANT_ADDRESS, ");
		builder.append("    REGION_NO, ");
		builder.append("    RESTAURANT_OPEN, ");
		builder.append("    RESTAURANT_CLOSE, ");
		builder.append("    RESTAURANT_TEL, ");
		builder.append("    RESTAURANT_RESTDAY, ");
		builder.append("    RESTAURANT_SCORE, ");
		builder.append("    RESTAURANT_DELETE");
		builder.append("  FROM RESTAURANT");
		builder.append("  WHERE RESTAURANT_NO = "+searchNo);
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		
		RestaurantVO vo = null;
		if(resultSet.next()) {
			String restaurantNo       = resultSet.getString("RESTAURANT_NO");
			String restaurantName     = resultSet.getString("RESTAURANT_NAME");    
			String restaurantAddress  = resultSet.getString("RESTAURANT_ADDRESS");    
			String regionNo           = resultSet.getString("REGION_NO");    
			String restaurantOpen     = resultSet.getString("RESTAURANT_OPEN");    
			String restaurantClose    = resultSet.getString("RESTAURANT_CLOSE");    
			String restaurantTel      = resultSet.getString("RESTAURANT_TEL");    
			String restaurantRestday  = resultSet.getString("RESTAURANT_RESTDAY");    
			String restaurantScore    = resultSet.getString("RESTAURANT_SCORE");    
			String restaurantDelete   = resultSet.getString("RESTAURANT_DELETE");
			vo = new RestaurantVO(restaurantNo, restaurantName, restaurantAddress, regionNo, restaurantOpen, restaurantClose, restaurantTel, restaurantRestday, restaurantScore, restaurantDelete);
		}
		resultSet.close();
		statement.close();
		connection.close();
		return vo;
	}
	
	
	
	//NULL값이 많은 RESTDAY,SCORE,DELETE칼럼은 뺴고 INSERT
	public int insertRestaurant(RestaurantVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO RESTAURANT (RESTAURANT_NO, ");
		builder.append("                        RESTAURANT_NAME, ");
		builder.append("                        RESTAURANT_ADDRESS, ");
		builder.append("                        REGION_NO, ");
		builder.append("                        RESTAURANT_OPEN, ");
		builder.append("                        RESTAURANT_CLOSE, ");
		builder.append("                        RESTAURANT_TEL) ");
		builder.append("  VALUES((SELECT MAX(RESTAURANT_NO)+1 FROM RESTAURANT),?,?,?,?,?,?)");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getRestaurantName());
		statement.setString(2, vo.getRestaurantAddress());
		statement.setString(3, vo.getRegionNo());
		statement.setString(4, vo.getRestaurantOpen());
		statement.setString(5, vo.getRestaurantClose());
		statement.setString(6, vo.getRestaurantTel());
		int executeUpdate = statement.executeUpdate();
		
		statement.close();
	    connection.close();
		return executeUpdate;
		
	}
	// 밑의 columnIndex를 받아 getColumnName()메소드로 컬럼의 이름으로 변환 후 쿼리 삽입해서 수정하는 메소드
	public int updateRestaurant(int searchNo, int columnIndex, String newData) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE RESTAURANT");
		builder.append(" SET ");
		builder.append(getColumnName(columnIndex));
		builder.append(" = ");
		builder.append(newData);
		builder.append(" WHERE RESTAURANT_NO = ");
		builder.append(searchNo);
		String sql = builder.toString();
		int executeUpdate = statement.executeUpdate(sql);
		statement.close();
	    connection.close();
	    return executeUpdate;
		
	}
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 1:  return "RESTAURANT_NAME";     
		case 2:  return "RESTAURANT_ADDRESS";  
		case 3:  return "REGION_NO";           
		case 4:  return "RESTAURANT_OPEN";     
		case 5:  return "RESTAURANT_CLOSE";    
		case 6:  return "RESTAURANT_TEL";      
		}
		return null;
	}
	
	//삭제요청 메소드 RESTAURANT_DELETE컬럼을 Y로 변경
	public int requestDeleteRestaurant(int searchNo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		Statement statement = connection.createStatement();
		int executeUpdate = statement.executeUpdate("UPDATE RESTAURANT SET RESTAURANT_DELETE = 'Y' WHERE RESTAURANT_NO = "+searchNo);
		statement.close();
	    connection.close();
		return executeUpdate;
	}
	
	//삭제요청한 (RESTAURANT_DELETE = 'Y')인  가게 조회
	public List<RestaurantVO> getRequestDeleteList() throws Exception {
		List<RestaurantVO> list = new ArrayList<>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT RESTAURANT_NO, RESTAURANT_NAME FROM RESTAURANT WHERE RESTAURANT_DELETE = 'Y' ORDER BY 1");
		
		while(resultSet.next()) {
			String restaurantNo = resultSet.getString("RESTAURANT_NO");
			String restaurantName = resultSet.getString("RESTAURANT_NAME");
			list.add(new RestaurantVO(restaurantNo, restaurantName));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
		
	}
	
	//삭제요청한 (RESTAURANT_DELETE = 'Y')인 가게 데이터 삭제
	public int deleteRestaurant() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		Statement statement = connection.createStatement();
		int executeUpdate = statement.executeUpdate("DELETE FROM RESTAURANT WHERE RESTAURANT_DELETE = 'Y'");
		return executeUpdate;	
		
	}
	
	//가게별 리뷰 조회, 가게번호로 아이디,음식명,평점,코멘트,추천일자 추출
	public List<JoinVO> getRestaurantReview(int searchNo) throws Exception {
		List<JoinVO> list = new ArrayList<>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT A.MEMBER_ID, ");
		builder.append("       B.FOOD_NAME, ");
		builder.append("       A.REVIEW_SCORE, ");
		builder.append("       A.REVIEW_COMMENT,  ");
		builder.append("       TO_CHAR(C.HISTORY_DATE, 'YYYY-MM-DD') AS HISTORY_DATE");
		builder.append("  FROM REVIEW A, FOOD B, HISTORY C ");
		builder.append("  WHERE A.FOOD_ID=B.FOOD_ID");
		builder.append("    AND A.FOOD_ID=C.FOOD_ID");
		builder.append("    AND A.MEMBER_ID=C.MEMBER_ID");
		builder.append("    AND A.RESTAURANT_NO=C.RESTAURANT_NO");
		builder.append("    AND A.RESTAURANT_NO="+searchNo);
		builder.append(" ORDER BY 5");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
			String memberID = resultSet.getString("MEMBER_ID");
			String foodName = resultSet.getString("FOOD_NAME");
			String reviewScore = resultSet.getString("REVIEW_SCORE");
			String reviewComment = resultSet.getString("REVIEW_COMMENT");
			String historyDate = resultSet.getString("HISTORY_DATE");
			list.add(new JoinVO(foodName, historyDate, memberID, reviewScore, reviewComment));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}
	
	public List<JoinVO> getMenuOfRestaurant(int searchNo) throws Exception{
		List<JoinVO> list = new ArrayList<>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT B.FOOD_NAME, ");
		builder.append("       A.OFFER_PRICE ");
		builder.append("  FROM OFFER A, FOOD B");
		builder.append("  WHERE A.FOOD_ID=B.FOOD_ID");
		builder.append("    AND A.RESTAURANT_NO=" + searchNo);
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
			String foodName = resultSet.getString("FOOD_NAME");
			int offerPrice = Integer.parseInt(resultSet.getString("OFFER_PRICE"));
			list.add(new JoinVO(foodName, offerPrice));
		}	
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}
	
	//--가게별 메뉴등록 JoinVO로 RESTAURANT_NO, FOOD_ID, OFFER_PRICE를 받아 OFFER에 등록
	public int insertMenuOfRestaurant(JoinVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		Statement statement = connection.createStatement();
		int insertMenuOfRestaurant = statement.executeUpdate("INSERT INTO OFFER VALUES ("+vo.getRestaurantNO()+",(SELECT FOOD_ID FROM FOOD WHERE FOOD_NAME = '"+vo.getFoodName()+"'),"+vo.getOfferPrice()+")");
		return insertMenuOfRestaurant;
		
	}
	
	public int deleteMenuOfRestaurant(String foodName,int RestaurantNo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		Statement statement = connection.createStatement();
		int insertMenuOfRestaurant = statement.executeUpdate("DELETE FROM OFFER WHERE RESTAURANT_NO = '"+ RestaurantNo +"'+  AND  FOOD_ID = (SELECT FOOD_ID FROM FOOD WHERE FOOD_NAME = '"+foodName+"')");
		return insertMenuOfRestaurant;
		
	}
}
