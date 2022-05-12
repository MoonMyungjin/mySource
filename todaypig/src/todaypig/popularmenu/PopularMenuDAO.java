package todaypig.popularmenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleDriver;




public class PopularMenuDAO {
	
	 //싱글톤 패턴 사용 밖에서 부를 땐 getInstance메소드 사용해야함
    private static PopularMenuDAO instance = new PopularMenuDAO();

    private PopularMenuDAO() {}

    public static PopularMenuDAO getInstance() {
        return instance;
    }
	
	//인기 메뉴 리스트 top5
	 public List<PopularMenuVO> getPopularMenuVO() throws Exception {
		  ArrayList<PopularMenuVO> list = new ArrayList<>();
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
	      Statement statement = connection.createStatement();
	      StringBuilder builder = new StringBuilder();
	      builder.append("  SELECT A.FOOD_NAME, ");
	      builder.append("           B.FOOD_ID, ");
	      builder.append("           B.COUNTF ");
	      builder.append("      FROM FOOD A, ");
	      builder.append("                      (SELECT * ");
	      builder.append("                            FROM  (SELECT FOOD_ID, ");
	      builder.append("                                         COUNT(FOOD_ID) AS COUNTF ");
	      builder.append("                                    FROM HISTORY ");
	      builder.append("                                    WHERE HISTORY_DATE BETWEEN SYSDATE-30 AND SYSDATE ");
	      builder.append("                                   GROUP BY FOOD_ID ");
	      builder.append("                                   ORDER BY 2 DESC)  ");
	      builder.append("                           WHERE ROWNUM BETWEEN 1 AND 5) B     ");
	      builder.append("     WHERE A.FOOD_ID=B.FOOD_ID ");
	      builder.append("     ORDER BY 3 DESC ");      
	      String sql = builder.toString();

	      ResultSet resultSet = statement.executeQuery(sql);
	      while (resultSet.next()) {
	    	  String foodId = resultSet.getString("FOOD_ID");
	    	  String foodname = resultSet.getString("FOOD_NAME");
	         list.add(new PopularMenuVO(foodId, foodname));
	      }
	      resultSet.close();
	      statement.close();
	      connection.close();
	      return list;
	   }
	 
	//음식번호를 매개변수로 그 음식의 인기 음식점 리스트 top3
	   public List<PopularMenuVO> getPopularRestaurant(String foodId) throws Exception {
	      ArrayList<PopularMenuVO> list = new ArrayList<>();
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
	      Statement statement = connection.createStatement();
	      StringBuilder builder = new StringBuilder();
	      builder.append("SELECT B.RESTAURANT_NAME,");
	      builder.append("        B.RESTAURANT_ADDRESS,");
	      builder.append("        B.RESTAURANT_OPEN||'-'||B.RESTAURANT_CLOSE AS RESTAURANT_TIME,");
	      builder.append("        B.RESTAURANT_TEL,");
	      builder.append("        B.RESTAURANT_RESTDAY,");
	      builder.append("        C.FOOD_NAME,");
	      builder.append("        A.OFFER_PRICE");
	      builder.append("    FROM OFFER A, RESTAURANT B, FOOD C");
	      builder.append("   WHERE A.RESTAURANT_NO=B.RESTAURANT_NO");
	      builder.append("     AND A.FOOD_ID=C.FOOD_ID");
	      builder.append("     AND A.FOOD_ID='"+foodId+"'");
	      builder.append("    AND  A.RESTAURANT_NO IN (SELECT RESTAURANT_NO");
	      builder.append("                              FROM  (SELECT RESTAURANT_NO,");
	      builder.append("                                           COUNT(FOOD_ID)");
	      builder.append("                                      FROM HISTORY");
	      builder.append("                                     WHERE FOOD_ID = '"+foodId+"'");
	      builder.append("                                      AND HISTORY_DATE BETWEEN SYSDATE-30 AND SYSDATE");
	      builder.append("                                     GROUP BY RESTAURANT_NO");
	      builder.append("                                     ORDER BY 2 DESC) ");
	      builder.append("                             WHERE ROWNUM BETWEEN 1 AND 3)");
	      builder.append("    ORDER BY 1");
	      String sql = builder.toString();
	      ResultSet resultSet = statement.executeQuery(sql);
	      
	      while(resultSet.next()) {
	         String restaurantName    = resultSet.getString("RESTAURANT_NAME");          
	         String restaurantAddress = resultSet.getString("RESTAURANT_ADDRESS");       
	         String restaurantTime    = resultSet.getString("RESTAURANT_TIME");   
	         String restaurantTell    = resultSet.getString("RESTAURANT_TEL");          
	         String restaurantRestday = resultSet.getString("RESTAURANT_RESTDAY");       
	         String foodName          = resultSet.getString("FOOD_NAME");                
	         String offerPrice        = resultSet.getString("OFFER_PRICE");      
	         list.add(new PopularMenuVO(foodName, restaurantName, restaurantAddress, restaurantTime, restaurantTell, restaurantRestday, offerPrice));
	         
	      }
	      return list;
	   }

	   

	   
	 //연령별 인기메뉴 10대 TOP3
		 public List<PopularMenuVO> getPopularMenuVO(int input) throws Exception {
			 String ageGroup = " ";
			 switch (input) {
			case 10:
				ageGroup = "('20030101') and to_date('20121231')";	
				break;
			case 20:
				ageGroup = "('19930101') and to_date('20021231')";	
				break;
			case 30:
				ageGroup = "('19830101') and to_date('19921231')";	
				break;
			default:
				break;
			}
			  ArrayList<PopularMenuVO> list = new ArrayList<>();
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		      Statement statement = connection.createStatement();
		      StringBuilder builder = new StringBuilder();
		      
	 			builder.append(" SELECT ");
	 			builder.append("     A.FOOD_NAME FOOD_NAME, ");
	 			builder.append("     B.FOOD_ID FOOD_ID, ");
	 			builder.append("     B.COUNTF COUNTF ");
	 			builder.append(" FROM FOOD A,(SELECT * ");
	 			builder.append("         FROM (SELECT FOOD_ID, ");
	 			builder.append("                     COUNT(FOOD_ID) AS COUNTF ");
	 			builder.append("                 FROM HISTORY A INNER JOIN MEMBER B ");
	 			builder.append("                      ON A.MEMBER_ID = B.MEMBER_ID ");
	 			builder.append("                 WHERE B.MEMBER_BDAY between "+ ageGroup);
	 			builder.append("                 GROUP BY FOOD_ID ");
	 			builder.append("                 ORDER BY 2 DESC) ");
	 			builder.append("         WHERE ROWNUM <= 3) B ");
	 			builder.append(" WHERE A.FOOD_ID = B.FOOD_ID ");
	 			builder.append(" ORDER BY 3 DESC ");
  
		      String sql = builder.toString();

		      ResultSet resultSet = statement.executeQuery(sql);
		      while (resultSet.next()) {
		    	  String foodId = resultSet.getString("FOOD_ID");
		    	  String foodname = resultSet.getString("FOOD_NAME");
		         list.add(new PopularMenuVO(foodId, foodname));
		      }
		      resultSet.close();
		      statement.close();
		      connection.close();
		      return list;
		   }
		 
//		 //연령별 인기메뉴 20대 TOP3
//		 public List<PopularMenuVO> getPopularMenuVO20() throws Exception {
//			  ArrayList<PopularMenuVO> list = new ArrayList<>();
//		      Class.forName("oracle.jdbc.driver.OracleDriver");
//		      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
//		      Statement statement = connection.createStatement();
//		      StringBuilder builder = new StringBuilder();
//		      
//				builder.append(" SELECT ");
//				builder.append("     A.FOOD_NAME FOOD_NAME, ");
//				builder.append("     B.FOOD_ID FOOD_ID, ");
//				builder.append("     B.COUNTF COUNTF ");
//				builder.append(" FROM FOOD A,(SELECT * ");
//				builder.append("         FROM (SELECT FOOD_ID, ");
//				builder.append("                     COUNT(FOOD_ID) AS COUNTF ");
//				builder.append("                 FROM HISTORY A INNER JOIN MEMBER B ");
//				builder.append("                      ON A.MEMBER_ID = B.MEMBER_ID ");
//				builder.append("                 WHERE B.MEMBER_BDAY between ('19930101') and to_date('20021231') ");
//				builder.append("                 GROUP BY FOOD_ID ");
//				builder.append("                 ORDER BY 2 DESC) ");
//				builder.append("         WHERE ROWNUM <= 3) B ");
//				builder.append(" WHERE A.FOOD_ID = B.FOOD_ID ");
//				builder.append(" ORDER BY 3 DESC ");
//  
//		      String sql = builder.toString();
//
//		      ResultSet resultSet = statement.executeQuery(sql);
//		      while (resultSet.next()) {
//		    	  String foodId = resultSet.getString("FOOD_ID");
//		    	  String foodname = resultSet.getString("FOOD_NAME");
//		         list.add(new PopularMenuVO(foodId, foodname));
//		      }
//		      resultSet.close();
//		      statement.close();
//		      connection.close();
//		      return list;
//		   } 
//		 
//		 //연령별 인기메뉴 30대 TOP3
//		 public List<PopularMenuVO> getPopularMenuVO30() throws Exception {
//			  ArrayList<PopularMenuVO> list = new ArrayList<>();
//		      Class.forName("oracle.jdbc.driver.OracleDriver");
//		      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
//		      Statement statement = connection.createStatement();
//		      StringBuilder builder = new StringBuilder();
//		      
//				builder.append(" SELECT ");
//				builder.append("     A.FOOD_NAME FOOD_NAME, ");
//				builder.append("     B.FOOD_ID FOOD_ID, ");
//				builder.append("     B.COUNTF COUNTF ");
//				builder.append(" FROM FOOD A,(SELECT * ");
//				builder.append("         FROM (SELECT FOOD_ID, ");
//				builder.append("                     COUNT(FOOD_ID) AS COUNTF ");
//				builder.append("                 FROM HISTORY A INNER JOIN MEMBER B ");
//				builder.append("                      ON A.MEMBER_ID = B.MEMBER_ID ");
//				builder.append("                 WHERE B.MEMBER_BDAY between ('19830101') and to_date('19921231') ");
//				builder.append("                 GROUP BY FOOD_ID ");
//				builder.append("                 ORDER BY 2 DESC) ");
//				builder.append("         WHERE ROWNUM <= 3) B ");
//				builder.append(" WHERE A.FOOD_ID = B.FOOD_ID ");
//				builder.append(" ORDER BY 3 DESC ");
//  
//		      String sql = builder.toString();
//
//		      ResultSet resultSet = statement.executeQuery(sql);
//		      while (resultSet.next()) {
//		    	  String foodId = resultSet.getString("FOOD_ID");
//		    	  String foodname = resultSet.getString("FOOD_NAME");
//		         list.add(new PopularMenuVO(foodId, foodname));
//		      }
//		      resultSet.close();
//		      statement.close();
//		      connection.close();
//		      return list;
//		   } 
		 
				 //성별 인기 메뉴 리스트 top5
				 public List<PopularMenuVO> getPopularMenuByGenderVO(String memberGender) throws Exception {
					  ArrayList<PopularMenuVO> list = new ArrayList<>();
				      Class.forName("oracle.jdbc.driver.OracleDriver");
				      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
				      Statement statement = connection.createStatement();
				      StringBuilder builder = new StringBuilder();
				      builder.append("SELECT A.FOOD_NAME,");
				      builder.append("         B.FOOD_ID,");
				      builder.append("         B.COUNTF");
				      builder.append("    FROM FOOD A,");
				      builder.append("                    (SELECT *");
				      builder.append("                          FROM  (SELECT A.FOOD_ID,");
				      builder.append("                                       COUNT(A.FOOD_ID) AS COUNTF");
				      builder.append("                                  FROM HISTORY A, MEMBER B");
				      builder.append("                                  WHERE A.MEMBER_ID=B.MEMBER_ID");
				      builder.append("                                    AND B.MEMBER_GENDER = '"+ memberGender +"'");
				      builder.append("                                    AND A.HISTORY_DATE BETWEEN SYSDATE-30 AND SYSDATE");
				      builder.append("                                 GROUP BY A.FOOD_ID");
				      builder.append("                                 ORDER BY 2 DESC) ");
				      builder.append("                         WHERE ROWNUM BETWEEN 1 AND 5) B    ");
				      builder.append("   WHERE A.FOOD_ID=B.FOOD_ID");
				      builder.append("   ORDER BY 3 DESC");
				      String sql = builder.toString();

				      ResultSet resultSet = statement.executeQuery(sql);
				      while (resultSet.next()) {
				    	  String foodId = resultSet.getString("FOOD_ID");
				    	  String foodname = resultSet.getString("FOOD_NAME");
				         list.add(new PopularMenuVO(foodId, foodname));
				      }
				      resultSet.close();
				      statement.close();
				      connection.close();
				      return list;
				   }
}