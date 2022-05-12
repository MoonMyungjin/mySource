package todaypig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Connect extends Main{
	public static int Idconfirm(String inputId) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT COUNT(*)     ");
		builder.append("   FROM MEMBER     ");
        builder.append(" WHERE ");
        builder.append("    MEMBER_ID = '" + inputId + "' ");
		String sql = builder.toString();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		int count = 0;
		if (resultSet.next()) {
			count = resultSet.getInt("count(*)");
		}
		return count; 
	}
	
	public static void login() throws Exception{
		while (!islogin) {
			System.out.println("");
			System.out.println("  ** 로그인 (아이디에 back을 입력: 초기화면이동)");
			Scanner scanner = new Scanner(System.in);
			System.out.print("아이디  : ");
			String inputId = scanner.nextLine();
			if (inputId.equals("back")) {
				Start();
			}
			System.out.print("비밀번호 : ");
			String inputPW = scanner.nextLine();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
//		입력아이디 유무 확인
			StringBuilder builder = new StringBuilder();
			builder.append(" SELECT MEMBER_ID,     ");
			builder.append("        MEMBER_PW,     ");
			builder.append("        MEMBER_DELETE    "); 
			builder.append("   FROM MEMBER     ");
	        builder.append("WHERE");
	        builder.append("    MEMBER_ID = '" + inputId + "' ");
			String sql = builder.toString();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				String outPutId = resultSet.getString("member_id");
				String outputPW = resultSet.getString("member_pw");
				String outputDelete= resultSet.getString("member_delete");
				if (!(outPutId.equals(inputId))) {
					System.out.println();
					System.out.println("해당하는 아이디가 없습니다.");
					continue;
				} else if("y".equals(outputDelete)) {
					System.out.println();
					System.out.println("탈퇴한 회원 입니다. 재 가입 후 이용 바랍니다.");
					continue;
				} else if (!(outputPW.equals(inputPW))) {
					System.out.println();
					System.out.println("비밀번호를 잘못 입력하셨습니다.");
					continue;
				} else {
					System.out.println();
					System.out.println("로그인 성공!");
					UserId = inputId;
					islogin = true;
					System.out.printf("%s 님 반가워요\n",UserId);
				}
			}
			statement.close();
			connection.close();
		}
	}
}
