package todaypig.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	public List<MemberVO> Getmemberlist() throws Exception{
		List<todaypig.member.MemberVO> list = new ArrayList<MemberVO>();
		// 0. JDBC 드라이버 로딩
		// new oracledriver import 후 주소 복사
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1. 접속 정보를 이용해서 DB 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		// 2. 작업 공간 만들기 
		Statement statement = connection.createStatement();
		// 3. 조회쿼리 입력후 resultSet에 데이터 넣기
		ResultSet resultSet = statement.executeQuery("select * from member");
		
		while (resultSet.next()) {                                                                                   
			String memberid = resultSet.getString("member_id");
			String memberpw = resultSet.getString("member_pw");
			String memberbdayaddtime = resultSet.getString("member_bday");
			String memberbday = memberbdayaddtime.substring(0, 10);
			String membergender = resultSet.getString("member_gender");
			String memberdelete = resultSet.getString("member_delete");
			list.add(new MemberVO(memberid,memberpw,memberbday,membergender,memberdelete));
		}
		resultSet.close();                                                                                           
		statement.close();                                                                                           
		connection.close();
		return list;
	}
	
	 public MemberVO getMember(String searchId) throws Exception {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      //DriverManager.registerDriver(new OracleDriver());  Class.forName도 있고 이 방법도 있음 오타 가능성이 더 적어서 좋음
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
	      Statement statement = connection.createStatement();
	      // StringBuilder vs StringBuffer
	      // 문자를 연결할 때 사용
	      StringBuilder builder = new StringBuilder();
	      builder.append("SELECT ");
	      builder.append("    MEMBER_ID, ");
	      builder.append("    MEMBER_PW, ");
	      builder.append("    TO_CHAR(MEMBER_BDAY,'YYYY\"년\" MM\"월\" DD\"일\"') MEMBER_BDAY, ");
	      builder.append("    MEMBER_GENDER, ");
	      builder.append("    MEMBER_DELETE ");
	      builder.append("FROM ");
	      builder.append("    MEMBER ");
	      builder.append("WHERE ");
	      builder.append("    MEMBER_ID = '" + searchId + "' ");
	      String sql = builder.toString();

	      ResultSet resultSet = statement.executeQuery(sql);
	      MemberVO vo = null;
	      if (resultSet.next()) {
	    	  String memberid = resultSet.getString("member_id");
	    	  String memberpw = resultSet.getString("member_pw");
	    	  String memberbday = resultSet.getString("member_bday");
	    	  String membergender = resultSet.getString("member_gender");
	    	  String memberdelete = resultSet.getString("member_delete");
	         vo = new MemberVO(memberid,memberpw,memberbday,membergender,memberdelete);
	      }
	      resultSet.close();
	      statement.close();
	      connection.close();
	      return vo;
	   }
	 
	   public int insertMember(MemberVO vo) {
	      return 0;
	   }
	   
	   public int updateMember(MemberVO vo) throws Exception {
		  Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
	      StringBuilder builder = new StringBuilder();
	      builder.append("UPDATE");
	      builder.append("     MEMBER");
	      builder.append(" SET ");
	      builder.append("     MEMBER_DELETE = ? ");
	      builder.append(" WHERE");
	      builder.append("     MEMBER_ID = ? ");
	      String sql = builder.toString();
	      PreparedStatement statement = connection.prepareStatement(sql);
	      statement.setString(1, vo.getMemberDelete());
	      statement.setString(2, vo.getMemberId());
	      //executeUpdate :  INSERT, UPDATE, DELETE
	      //executeQuery : SELECT
	      int executeUpdate = statement.executeUpdate();
	      statement.close();
	      connection.close();
	      
	      return executeUpdate;
	   }
	   
	   public int updateMypage(MemberVO vo,String newPw) throws Exception {
		  Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
	      StringBuilder builder = new StringBuilder();
	      builder.append("UPDATE ");
	      builder.append("     MEMBER ");
	      builder.append(" SET ");
	      builder.append("     MEMBER_PW = ? ");
	      builder.append(" WHERE ");
	      builder.append("     MEMBER_ID = ? ");
	      String sql = builder.toString();
	      PreparedStatement statement = connection.prepareStatement(sql);
	      statement.setString(1, newPw);
	      statement.setString(2, vo.getMemberId());
           //executeUpdate :  INSERT, UPDATE, DELETE
	      //executeQuery : SELECT
	      int executeUpdate = statement.executeUpdate();
	      statement.close();
	      connection.close();
	      
	      return executeUpdate;
	   }
	   
	   public int Secession(String UserId) throws Exception {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
		      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		      StringBuilder builder = new StringBuilder();
		      builder.append("UPDATE");
		      builder.append("     MEMBER");
		      builder.append(" SET ");
		      builder.append("     MEMBER_DELETE = 'y' ");
		      builder.append(" WHERE");
		      builder.append("     MEMBER_ID = '"+ UserId + "' ");
		      String sql = builder.toString();
		      PreparedStatement statement = connection.prepareStatement(sql);
		      int executeUpdate = statement.executeUpdate();
		      statement.close();
		      connection.close();
		      
		      return executeUpdate;
	   }      

	   
	   public int deleteMember(String id) {
	      return 0;
	   }

}