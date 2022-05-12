package todaypig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Scanner;

import todaypig.join.JoinVO;
import todaypig.member.MemberDAO;
import todaypig.member.MemberVO;
import todaypig.recommend.Daedeok;
import todaypig.recommend.Donggu;
import todaypig.recommend.Junggu;
import todaypig.recommend.NonMember;
import todaypig.recommend.Seogu;
import todaypig.recommend.Yousung;
import todaypig.review.ReviewDAO;

public class Main {
	static MemberDAO dao = new MemberDAO();
	public static boolean islogin = false;
	public static String UserId;
	static ReviewDAO dao2 = new ReviewDAO();
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		Start();
	}

	public static void Start() throws Exception {
		Start:while(true) {
			try {
				System.out.println();
				System.out.println("                          [오 늘 의  Pick]              ");
				System.out.println("====================================================================");
				System.out.println("        1.로그인 || 2.회원가입 || 3.비회원사용 || 4.관리자 || 0.종료");
				System.out.println("====================================================================");
				System.out.print("원하시는 메뉴를 골라주세요>");
				int menu = Integer.parseInt(scanner.nextLine());
				switch (menu) {
				case 1:
					System.out.println(" ");
					System.out.println("선택하신메뉴 : 1.로그인");
					Connect.login();
					Members();
					break;
				case 2:
					System.out.println(" ");
					System.out.println("선택하신메뉴 : 2.회원가입");
					signin();
					break;
				case 3:
					System.out.println(" ");
					System.out.println("선택하신메뉴 : 3.비회원사용");
					NonMembers();
					break;	
				case 4:
					System.out.println(" ");
					System.out.println("선택하신메뉴 : 4.관리자");
					Adminlogin();
					break;
				case 0:
					System.out.println("이용해주셔서 감사합니다.");
					System.exit(0);
					break;
				default:
					System.out.println("");
					System.out.println("제시된 숫자를 입력해주세요");
					System.out.println("");
					break;
				}		
				 
			} catch (NumberFormatException e) {
				System.out.println("숫자를 입력해주세요");
			}
	}
}
	
	public static void signin() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\"back을\" 입력하여 이전페이지 이동");
		System.out.print("아이디 : ");
		String inputId = scanner.nextLine();
		if (inputId.equals("back")) {
			Start();
		}
		System.out.print("비밀번호 : ");
		String inputPW = scanner.nextLine();
		if (inputPW.equals("back")) {
			Start();
		}
		System.out.print("생년월일(YYYYMMDD) : ");
		String inputBDAY = scanner.nextLine();
		if (inputBDAY.equals("back")) {
			Start();
		}
		String inputGender;
		while(true) {	
			System.out.print("성별(MALE/FEMALE) : ");
			inputGender = scanner.nextLine();
			}
			if (inputGender.equals("back")) {
				Start();
			}
			if (inputGender.equals("MALE")||inputGender.equals("FEMALE")) {
				break;
			} else {
				System.out.println("성별은 \"MALE\"(m,M) 혹은 \"FEMALE\" 만 입력 해주십시오.");
				continue;
			}
			
		}
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.44.27:1521:xe", "todaypig", "java");
		StringBuilder builder = new StringBuilder();
		builder.append(" INSERT INTO MEMBER(MEMBER_ID,MEMBER_PW,MEMBER_BDAY,MEMBER_GENDER) ");
		builder.append(" VALUES (?,?,TO_DATE(?),?) ");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
//		이 인덱스는 1부터 시작
		statement.setString(1, inputId);
		statement.setString(2, inputPW);
		statement.setString(3, inputBDAY);
		statement.setString(4, inputGender);
		int executeUpdate = statement.executeUpdate();
		if(executeUpdate>0) {
			System.out.println("회원가입 되었습니다.");
		} else {
			System.out.println("회원가입에 실패 했습니다.");
		}
		statement.close();
		connection.close();
		 
	}
	
	public static void Members() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println(" ");
		System.out.println("                           회 원 전 용  메 뉴                   ");
		System.out.println("====================================================================");
		System.out.println("            1.마이페이지 || 2.로그아웃 || 3.메뉴추천 || 4.인기메뉴 ");
		System.out.println("====================================================================");
		System.out.print("원하시는 메뉴를 선택하세요>");
		int menu = Integer.parseInt(scanner.nextLine());
		switch (menu) {
		case 1:
			Mypage();
			break;
		case 2:
			islogin = false;
			Start();
			break;
		case 3:
			MemberSelect();
			break;
		case 4:
			View.PopularMenu();
			break;
		default:
			System.out.println();
			System.out.println("제시된 숫자를 입력해주세요");
			System.out.println();
			break;	
		}
		 
	}
	
	public static void Mypage() throws Exception {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			System.out.println(" ");
			System.out.println("                           마 이 페 이 지                    ");
			System.out.println("====================================================================");
			System.out.println(" 1.추천기록조회 || 2.리뷰작성 || 3.비밀번호변경 || 4.탈퇴 || 5.내정보 || 0.뒤로가기");
			System.out.println("====================================================================");
			System.out.print("원하는 메뉴를 골라주세요>");
			int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
			case 1:
				System.out.println();
				System.out.println("    상호명          메뉴명             추천일자       리뷰작성여부 ");
				List<JoinVO> list = dao2.getMyHistory();
				for (int i = 0; i < list.size(); i++) {
					JoinVO vo = list.get(i);
					System.out.printf("%02d. %-12s %-12s %s\t %s\n",i+1,vo.getRestaurantName(), vo.getFoodName(), vo.getHistoryDate(),vo.getHistoryWriteReview());
				}
				break;
			case 2:
				writeReview();
				break;
			case 3:
				MemberVO vo1 = dao.getMember(UserId);
				while (true) {
					System.out.print("현재 비밀번호를 입력하세요> ");
					String memberPw = scanner.nextLine();
					// 현재 비밀번호랑 같은지 확인하는 메소드
					if (!(vo1.getMemberPw().equals(memberPw))) {
						System.out.println("비밀번호가 잘못입력되었습니다.");
					} else {
						break;
					}
				}
				System.out.print("변경할 비밀번호을 입력하세요> ");
				String newPw = scanner.nextLine();
				int updateMypage = dao.updateMypage(vo1, newPw);
				if (updateMypage > 0) {
					System.out.println("정보가 변경되었습니다.");
				} else {
					System.out.println("변경되지 않았습니다.");
				}

				break;
			case 4:
	             System.out.print("회원 탈퇴 하시겠습니까? y / n ");
	             String memberDelete = scanner.nextLine();
	             if (memberDelete.equals("y")) {
	            	 int secession = dao.Secession(UserId);
	            	 if(secession > 0) {
	            		 System.out.println("회원 탈퇴 되었습니다.");
	            		 System.out.println(" ");
	            		 islogin=false;
	            		 Start();
	            	 } else {
	            		 System.out.println("회원 탈퇴에 실패 했습니다.");
	            		 System.out.println(" ");
	            	 }
					break;
				} else {
					System.out.println("회원 탈퇴에 실패 했습니다.");
					System.out.println(" ");
					break;
				}

			case 5:
				MemberVO vo = dao.getMember(UserId);
				if (vo != null) {
					System.out.println();
					System.out.println("아이디: " + vo.getMemberId());
					System.out.println("비밀번호: " + vo.getMemberPw());
					System.out.println("생년월일: " + vo.getMemberBday());
					System.out.println("성별: " + vo.getMemberGender());
				} else {
					System.out.println("조회한 아이디의 정보가 없습니다.");
				}
				break;

			case 0:
				Members(); 
				break;
			default:
				System.out.println("");
				System.out.println("제시된 숫자를 입력해주세요");
				System.out.println("");
				break;	
			}
			 
		}
	}
	
	public static void writeReview() throws Exception {
//      내가 선택한 history중 review를 작성하지 않은 history 목록을 보여주는 메소드
      List<JoinVO> list = dao2.getMyHistoryNotWriteReview();
      Scanner scanner = new Scanner(System.in);
      int inputI = 0;
      if (list.size() != 0) {
         for (int i = 0; i < list.size(); i++) {
            JoinVO vo = list.get(i);
            System.out.printf("%d. %s      \t   %s  \t    %s\n", i + 1, vo.getRestaurantName(), vo.getFoodName(),
                  vo.getHistoryDate());
         }
         System.out.printf("%s 님이 받으신 추천 중 작성하신 리뷰가 없는 목록입니다.  \n", UserId);
         System.out.print("** 리뷰를 작성할 기록의 번호를 입력해주세요>> ");
         try {
            inputI = Integer.parseInt(scanner.nextLine()) - 1;
            list.get(inputI);

         } catch (IndexOutOfBoundsException e) {
            System.out.println("----------------------------------------------");
            System.out.println("--------------정해진 번호만 입력해주세요.-------------");
            System.out.println("----------------------------------------------");
            writeReview();
         }
         JoinVO vo = list.get(inputI);
         System.out.println("       ");
         System.out.printf("%s일 %s 에서 먹었던 %s, 어떠셨나요? \n \n", vo.getHistoryDate(), vo.getRestaurantName(),
               vo.getFoodName());
         int RestaurantNO = vo.getRestaurantNO();
//         선택한 번호의 가게번호,음식아이디,회원id,기록일자 저장
         String FoodID = vo.getFoodID();
         String historyDate = vo.getHistoryDate();
         int RestaurantNo = vo.getRestaurantNO();
         int score;
         while (true) {
            System.out.println("1~10 정수를 입력해주세요.");
            System.out.printf("평점>>");
            score = Integer.parseInt(scanner.nextLine());
            if (score < 0 || score > 10) {
               System.out.println();
               continue;
            } else {
               break;
            }
         }

         System.out.println("해당음식에 대해 자유롭게 적어주세요.");
         System.out.printf("한줄평>>");

//         리뷰작성 메소드
         String comment = scanner.nextLine();
         int writereview = dao2.writereview(RestaurantNO, FoodID, score, comment);
         if (writereview > 0) {
            System.out.println("리뷰작성에 성공했습니다.");
            System.out.println(" ");
         } else {
            System.out.println("리뷰작성에 실패했습니다.");
            System.out.println(" ");
         }
//         리뷰 작성 후 writereview 를 'y'로 변경하는 메소드
         dao2.Checkwritereview(FoodID, historyDate, RestaurantNo);
//         가게 평점 평균입력
//         가게 평점 평균이 5이하라면 RESTAURANT_DELETE = 'y'로 변경
//         가게 평균 평점이 5초과라면 RESTAURANT_DELETE = null로 변경 하는 메소드
         dao2.AverageUpdate(RestaurantNo);
      } else {
         System.out.println(" ");
         System.out.println("모든 리뷰를 작성하셨습니다.");
      }

   }
	
	public static void MemberSelect() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println(" ");
		System.out.println("                            지 역 선 택                     ");
		System.out.println("====================================================================");
		System.out.println("    11.중구 || 12.서구 || 13.대덕구 || 14.유성구 || 15.동구 || 0.뒤로가기");
		System.out.println("====================================================================");
		System.out.print("음식점이 위치한 지역을 골라주세요>");
		int menu31 = Integer.parseInt(scanner.nextLine());
		switch (menu31) {
		case 11:
			Junggu.RecommendationJunggu();
			break;
		case 12:
			Seogu.RecommendationSeogu();
			break;
		case 13:
			Daedeok.RecommendationDaedeok();
			break;
		case 14:
			Yousung.RecommendationYousung();
			break;
		case 15:
			Donggu.RecommendationDong();
			break;
		case 0:
			Members();
		    break;
		default:
			System.out.println("");
			System.out.println("제시된 숫자를 입력해주세요");
			System.out.println("");
			break;	
		}
		Members();
		 
	}
	
	public static void NonMembers() throws Exception {
		Scanner scanner = new Scanner(System.in);
		NonMember:while (true) {
			System.out.println("");
			System.out.println("                           3.비회원사용                   ");
			System.out.println("====================================================================");
			System.out.println("       31.메뉴추천 || 32.개인맞춤추천 || 33.메뉴관리 || 30.뒤로가기 ");
			System.out.println("====================================================================");
			System.out.print("원하시는 메뉴를 골라주세요>");
			int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
//			32,33 : 회원전용메뉴 -> "로그인 후 사용가능합니다" 출력
			case 31:
				NonMembersRecommendation();
				break NonMember;
			case 32: case 33:
				if (!islogin) {
					System.out.println(" ");
					System.out.println("로그인 후 사용가능합니다");
				}
				break;
			case 30:
				Start();
				break;
			default:
				System.out.println("");
				System.out.println("제시된 숫자를 입력해주세요");
				System.out.println("");
				break;	
			}
			 
		}
	}
	
	public static void NonMembersRecommendation() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("");
		System.out.println("                         31.비회원 메뉴추천                   ");
		System.out.println("====================================================================");
		System.out.println(" 311.중구 || 312.서구 || 313.대덕구 || 314.유성구 || 315.동구 || 310.뒤로가기");
		System.out.println("====================================================================");
		System.out.print("음식점이 위치한 지역을 골라주세요>");
		int menu31 = Integer.parseInt(scanner.nextLine());
		switch (menu31) {
		case 311:
			NonMember.Food(1);
			break;
		case 312:
			NonMember.Food(2);
			break;
		case 313:
			NonMember.Food(3);
			break;
		case 314:
			NonMember.Food(4);
			break;
		case 315:
			NonMember.Food(5);
			break;
		case 310:
			NonMembers();
		    break;
		default:
			System.out.println("");
			System.out.println("제시된 숫자를 입력해주세요");
			System.out.println("");
			break;	
		}
		 
	}
	
	public static void Adminlogin() throws Exception {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print("관리비밀번호 입력 :");
			String inputPW = scanner.nextLine();
			if(inputPW.equals("back")) {
				Start();
			}
			if(inputPW.equals("열려라참깨")) {
				Admin();
			} else {
				System.out.println(" ");
				System.out.println("입력이 올바르지 않습니다. 다시 입력해주세요");
				System.out.println("이전화면 이동 >>> \"back\" 입력");
			}
		 	
		}
	}	
			
	public static void Admin() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println(" ");
		System.out.println("                             4.관리자                   ");
		System.out.println("====================================================================");
		System.out.println("                 1.회원관리 || 2.가게관리 || 0.뒤로가기 ");
		System.out.println("====================================================================");
		System.out.print("원하는 메뉴를 선택하세요>");
		int menu0 = Integer.parseInt(scanner.nextLine());
		switch (menu0) {
		case 1:
			MemberList();
            break;
		case 2:
			todaypig.View.restaurantMenu();
			break;	
		case 0:
			Start();
			break;				
		default:
			System.out.println("");
			System.out.println("제시된 숫자를 입력해주세요");
			System.out.println("");
			break;		
		}
		 
	}
		
	public static void MemberList() throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println(" ");
			System.out.println("                         회원목록 확인 및 삭제            ");
			System.out.println("====================================================================");
			System.out.println("             1. 회원 목록 확인 || 2. 회원 삭제 || 0.뒤로가기");
			System.out.println("====================================================================");
			System.out.print("원하는 메뉴를 선택하세요>");
			int menu = Integer.parseInt(scanner.nextLine());
				
			switch (menu) {
			case 1:
				 System.out.println("                                           회원 목록                            ");
				 System.out.println("==============================================================================================");
				 System.out.printf("     회원 아이디    |      회원 비밀번호       |       회원 생년월일        |    회원 성별     |  삭제 여부  \n");
				 System.out.println("==============================================================================================");
				 List<MemberVO> list = dao.Getmemberlist();
				 for (MemberVO vo : list) {
			 		System.out.printf("   %s  \t |   %s  \t\t|   %s\t\t |   %s\t |   %s\n", vo.getMemberId(), vo.getMemberPw(), vo.getMemberBday(),vo.getMemberGender(),vo.getMemberDelete());
				 }
				 System.out.println("==============================================================================================");
				 System.out.println(" ");
				 break;
             case 2:
            	 System.out.print("삭제할 아이디를 입력하세요> ");
                 String memberId = scanner.nextLine();
                 System.out.print("삭제 하시겠습니까? y / n ");
                 String memberDelete = scanner.nextLine();
                 int updateMember = dao.updateMember(new MemberVO(memberId, memberDelete));
                 if(updateMember > 0) {
                    System.out.println("정보가 변경되었습니다.");
                    System.out.println(" ");
                 } else {
                    System.out.println("정보변경에 실패 했습니다.");
                    System.out.println(" ");
                 }
                 break;
             case 0:
            	 Admin();
            	 break;
			}
			 	
		}
	}	
}

