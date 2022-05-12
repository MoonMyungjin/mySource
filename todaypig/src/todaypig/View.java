package todaypig;

import java.util.List;
import java.util.Scanner;

import todaypig.category.CategoryDAO;
import todaypig.food.FoodDAO;
import todaypig.food.FoodVO;
import todaypig.join.JoinVO;
import todaypig.popularmenu.PopularMenuDAO;
import todaypig.popularmenu.PopularMenuVO;
import todaypig.restaurnat.RestaurantDAO;
import todaypig.restaurnat.RestaurantVO;

public class View {
	static Scanner scanner = new Scanner(System.in);
	static RestaurantDAO dao = new RestaurantDAO();
	static FoodDAO foodDAO = new FoodDAO();
	static CategoryDAO categoryDAO  = new CategoryDAO();

	public static void restaurantMenu() throws Exception {
		restaurantMenu: while (true) {
			System.out.println();
			System.out.println("                               42.가게관리                   ");
			System.out.println("====================================================================");
			System.out.println("  421.가게목록 || 422.가게상세조회 || 423.가게추가 || 424.가게삭제 || 0.뒤로가기 ");
			System.out.println("====================================================================");
			System.out.print("원하시는 메뉴를 골라주세요>");
			int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
			case 421:
				List<RestaurantVO> list = dao.getRestaurantList();
				System.out.println();
				System.out.println("---[가게번호]----[상호명]-------------");
				for (RestaurantVO vo : list) {
					System.out.printf("     %4s     %-10s\n", vo.getRestaurantNo(), vo.getRestaurantName());
				}
				System.out.println("---------------------------------");
				break;
				
			case 422:
				System.out.print("상세조회를 원하시는 가게번호를 입력해주세요>");
				int searchNo = Integer.parseInt(scanner.nextLine());
				RestaurantVO vo = dao.getRestaurant(searchNo);
				if (vo != null) {
					System.out.println();
					System.out.println("---------------------------------");
					System.out.println("가게번호: "+vo.getRestaurantNo());
					System.out.println("상호명: "+vo.getRestaurantName());
					System.out.println("가게주소: "+vo.getRestaurantAddress());
					System.out.println("지역번호: "+vo.getRegionNo());
					System.out.println("오픈시간: "+vo.getRestaurantOpen());
					System.out.println("마감시간: "+vo.getRestaurantClose());
					System.out.println("전화번호: "+vo.getRestaurantTel());
					System.out.println("휴무요일: "+vo.getRestaurantRestday());
					System.out.println("평점평균: "+vo.getRestaurantScore());
					System.out.println("삭제요청여부: "+vo.getRestaurantDelete());
					System.out.println("---------------------------------");
					DetailsOfTheRestaurant: while (true) {
						String str1 = "";
						String str2 = "";
						for(int i=vo.getRestaurantName().length(); i<16; i++){
							  if(i%2 == 0) {
								  str1 += "-";
							  }else {
								  str2 += "-";
							  }
							}
						System.out.println();
						System.out.println("--------------------------" +str1+"["+ vo.getRestaurantName()+"]"+str2
								+ "------------------------");
						System.out.println("   1.가게정보수정 || 2.가게메뉴관리 || 3.가게삭제요청 || 4.가게리뷰보기 || 0.뒤로가기");
						System.out.println(
								"---------------------------------------------------------------------");
						System.out.print("원하시는 메뉴를 골라주세요>");
						menu = Integer.parseInt(scanner.nextLine());
						switch (menu) {
						case 1:
							System.out.println();
							System.out.println("--------------------------" +str1+"["+ vo.getRestaurantName()+"]"+str2
									+ "------------------------");
							System.out.println("1.상호명 | 2.가게주소 | 3.지역번호 | 4.오픈시간 | 5.마감시간 | 6.전화번호 | 0.뒤로가기");
							System.out.println(
									"---------------------------------------------------------------------");
							System.out.print("수정을 원하시는 정보를 골라주세요>");
							int columnIndex = Integer.parseInt(scanner.nextLine());
							if (columnIndex == 0) {
								break;
							}
							System.out.print("새로운 정보를 입력해주세요>");
							String newData = scanner.nextLine();
							if (columnIndex != 3) {
								newData = "'" + newData + "'";
							}
							int updateRestaurant = dao.updateRestaurant(searchNo, columnIndex, newData);
							if (updateRestaurant > 0) {
								System.out.println("가게 정보수정이 완료되었습니다.");
							} else {
								System.out.println("수정 실패했습니다.");
							}
							break;
						case 2:
							MenuOfRestaurant(searchNo);
							break;
						case 3:
							int requestDeleteRestaurant = dao.requestDeleteRestaurant(searchNo);
							if (requestDeleteRestaurant > 0) {
								System.out.println("가게 삭제요청이 완료되었습니다.");
							} else {
								System.out.println("삭제요청 실패했습니다.");
							}
							break;
						case 4:
							List<JoinVO> review = dao.getRestaurantReview(searchNo);
							if (review.size() != 0) {
								System.out.println("                                       " + vo.getRestaurantName()
										+ " 리뷰 목록                            ");
								System.out.println(
										"=========================================================================================================");
								System.out.printf(
										"     아이디    |     음식명     | 평점 |    추천일자    |                        코멘트                           \n");
								System.out.println(
										"=========================================================================================================");
								for (JoinVO joinvo : review) {
									System.out.printf("   %-10s    %-10s  %-3s  %s    %-50s\n",
											joinvo.getMemberID(), joinvo.getFoodName(), joinvo.getReviewScore(),
											joinvo.getHistoryDate(), joinvo.getReviewComment());
									if (review.get(review.size() - 1) != joinvo)
										System.out.println(
												"---------------------------------------------------------------------------------------------------------");
								}
								System.out.println(
										"=========================================================================================================");
							}else {
								System.out.println("등록된 리뷰가 없습니다.");
							}
							System.out.println();
							break;

						case 0:
							break DetailsOfTheRestaurant;
						default:
							System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
							break;
						}
					}
				} else {
					System.out.println("조회한 아이디의 정보가 없습니다.");
				}
				break;
				
			case 423:
				System.out.println("새로운 가게를 추가합니다. 정보를 입력해주세요");
				System.out.print("상호명>");
				String restaurantName = scanner.nextLine();  
				System.out.println("주소는 지번주소로 입력");
				System.out.print("가게주소>");
				String restaurantAddress = scanner.nextLine();  
				System.out.println("1:중구    2:서구   3:대덕구   4:유성구  5:동구");
				System.out.print("지역번호>");
				String regionNo = scanner.nextLine();
				System.out.println("24시간 기준 'HHMI'");
				System.out.print("오픈시간>");
				String restaurantOpen = scanner.nextLine();           
				System.out.println("24시간 기준 'HHMI'");
				System.out.print("마감시간>");
				String restaurantClose = scanner.nextLine(); 
				System.out.println("'000-0000-0000'형식으로 입력");
				System.out.print("전화번호>");
				String restaurantTel = scanner.nextLine();
				int insertRestaurant = dao.insertRestaurant(new RestaurantVO(restaurantName, restaurantAddress, regionNo, restaurantOpen, restaurantClose, restaurantTel));
				if(insertRestaurant > 0) {
					System.out.println("새로운 가게정보가 등록되었습니다.");
				} else {
					System.out.println("추가되지 않았습니다.");
				}
				break;
				
			case 424:
				List<RestaurantVO> list2 = dao.getRequestDeleteList();
				if (list2.size() != 0) {
					System.out.println();
					System.out.println("--------[삭제요청된 가게 리스트]--------");
					for (RestaurantVO vo2 : list2) {
						System.out.printf("     %4s     %-10s\n", vo2.getRestaurantNo(), vo2.getRestaurantName());
					}
					System.out.println("----------------------------------");
					System.out.print("정말 삭제하시겠습니까? (Y or N) >");
					String check = scanner.nextLine();
					if (check.equals("Y") || check.equals("y")) {
						int deleteRestaurant = dao.deleteRestaurant();
						if (deleteRestaurant > 0) {
							System.out.println("가게 삭제요청이 완료되었습니다.");
						} else {
							System.out.println("삭제요청 실패했습니다.");
						}
					} else {
						System.out.println("가게관리 메뉴로 돌아갑니다.");
					} 
				} else {
					System.out.println("현재 삭제요청된 가게가 없습니다.");
					System.out.println("가게관리 메뉴로 돌아갑니다.");
				}
				break;
				
			case 0: 
				Main.Admin();
				break;
				
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
	
	public static void MenuOfRestaurant(int searchNo) throws Exception {
		menuOfRestaurant: while (true) {
			String str1 = "";
			String str2 = "";
			for(int i=dao.getRestaurant(searchNo).getRestaurantName().length(); i<16; i++){
				  if(i%2 == 0) {
					  str1 += "-";
				  }else {
					  str2 += "-";
				  }
				}
			
			System.out.println();
			System.out.println("--------------------------" +str1+"["+ dao.getRestaurant(searchNo).getRestaurantName()+"]"+str2
					+ "------------------------");
			System.out.println("                    1.메뉴목록 || 2.메뉴추가 || 3.메뉴삭제 || 0.뒤로가기");
			System.out.println("---------------------------------------------------------------------");
			System.out.print("원하시는 메뉴를 골라주세요>");
			int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
			case 1:
				System.out.println();
				System.out.println("--------------------------" +str1+"["+ dao.getRestaurant(searchNo).getRestaurantName()+"]"+str2
						+ "------------------------");
				List<JoinVO> list = dao.getMenuOfRestaurant(searchNo);
				for (JoinVO vo : list) {
					System.out.printf("\t\t\t\t%s \t%d원\n", vo.getFoodName(), vo.getOfferPrice());
				}
				System.out.println("---------------------------------------------------------------------");
				break;
			case 2:
				System.out.println("새로운 메뉴를 추가합니다. 정보를 입력해주세요");
				System.out.println("1.한식, 2.중식, 3.일식, 4.양식");
				System.out.print("음식종류 번호>");
				int columnIndex = Integer.parseInt(scanner.nextLine());
				List<String> foodListOfCategory = foodDAO.getFoodListOfCategory(categoryDAO.getCategoryId(columnIndex));
				System.out.print("음식명>");
				String foodName = scanner.nextLine();  
				if(!(foodListOfCategory.contains(foodName))) {
					System.out.println("등록되지 않은 음식입니다. 새로운 음식을 추가합니다. 정보를 입력해주세요");
					System.out.print("슬로우푸드입니까? (Y or N) >");
					String foodIsslow = scanner.nextLine();  
					int insertFood = foodDAO.insertFood(new FoodVO(foodName, foodIsslow, categoryDAO.getCategoryId(columnIndex)));
					if(insertFood > 0) {
						System.out.println("새로운 음식 등록이 완료되었습니다.");
						System.out.println("이어서 메뉴추가를 진행합니다.");
					} else {
						System.out.println("음식 등록을 실패했습니다.");
						break;
					}
				}
				System.out.print("가격>");
				int offerPrice  = Integer.parseInt(scanner.nextLine());  
				int insertMenuOfRestaurant = dao.insertMenuOfRestaurant(new JoinVO(searchNo, foodName, offerPrice));
				if(insertMenuOfRestaurant > 0) {
					System.out.println("새로운 메뉴 등록이 완료되었습니다.");
				} else {
					System.out.println("추가되지 않았습니다.");
				}
				break;
			case 3:
				System.out.println("삭제할 메뉴를 입력해주세요.");
				System.out.print("음식명>");
				String foodName1 = scanner.nextLine();
				int deleteMenuOfRestaurant = dao.deleteMenuOfRestaurant(foodName1,searchNo);
				if(deleteMenuOfRestaurant > 0) {
					System.out.println("메뉴가 삭제되었습니다.");
				} else {
					System.out.println("삭제되지 않았습니다.");
				}
				break;
			case 0:
				break menuOfRestaurant;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
		
	}

		
	public static void PopularMenu() throws Exception {
		PopularMenu: while (true) {
			System.out.println(" ");
			System.out.println("                             인기 메뉴                                 ");
			System.out.println("====================================================================");
			System.out.println("   1.전체 인기 메뉴 TOP5 || 2.연령별 인기 메뉴 || 3.성별 인기 메뉴 || 0.뒤로가기   ");
			System.out.println("====================================================================");
			System.out.print("원하시는 메뉴를 골라주세요>");
			int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
			case 1:
				PopularMenutop5();
				break;
				
			case 2:
				System.out.println(" ");
				System.out.println("                             인기 메뉴                                 ");
				System.out.println("====================================================================");
				System.out.println("    1.10대      ||   2.20대      ||  3.30대 이상   ||  0.뒤로가기     ");
				System.out.println("====================================================================");
				System.out.print("연령대를 선택하세요>");
				int age = Integer.parseInt(scanner.nextLine());
				switch (age) {
				case 1:
					PopularMenuByAge10();
					break;
				case 2:
					PopularMenuByAge20();
					break;
				case 3:
					PopularMenuByAge30();
					break;
				case 0:
					break;
				}			
				break;
			
			case 3:
				PopularMenuByGender();
				break;
			case 0:
				Main.Members();
				break;
				
			}
		}
	}

	public static void PopularMenutop5() throws Exception {
		PopularMenuDAO dao = PopularMenuDAO.getInstance();
		List<PopularMenuVO> menu = dao.getPopularMenuVO();
		System.out.println(" ");
		System.out.println("                            TOP5  메뉴 추천                                 ");
		System.out.println("====================================================================");
		System.out.println("     1위."+ menu.get(0).getFoodName() + "  2위."+menu.get(1).getFoodName()+"  3위."+ menu.get(2).getFoodName()+ "  4위."+ menu.get(3).getFoodName()+ "  5위."+ menu.get(4).getFoodName());
		System.out.println("====================================================================");
		System.out.print("원하시는 순위를 골라주세요, 관련 식당 정보를 안내 드립니다>");
		int index = Integer.parseInt(scanner.nextLine());
		List<PopularMenuVO> restaurant = dao.getPopularRestaurant(menu.get(index-1).getFoodId());
		System.out.println(" ");
		System.out.println("                                          식 당  정 보  안 내                                ");
		System.out.println("==================================================================================================================");
		System.out.println("  식당명       |  식당 주소                           |  식당 운영시간  |  식당 번호       | 식당 휴무일  | 해당 메뉴 가격");
		System.out.println("==================================================================================================================");
		for (PopularMenuVO popularMenuVO : restaurant) { 
		System.out.printf(" %-10s %-32s %-13s %-15s %-10s %-9s\n", 
			popularMenuVO.getRestaurantName(), popularMenuVO.getRestaurantAddress(),popularMenuVO.getRestaurantTime(),popularMenuVO.getRestaurantTell(),popularMenuVO.getRestaurantRestday(),popularMenuVO.getOfferPrice());
			}		
		System.out.println("==================================================================================================================");
		System.out.println();
	}
	
	
	public static void PopularMenuByAge10() throws Exception {
		PopularMenuDAO dao = PopularMenuDAO.getInstance();
		List<PopularMenuVO> menu = dao.getPopularMenuVO(10);
		System.out.println(" ");
		System.out.println("                            TOP3  메뉴 추천                                 ");
		System.out.println("====================================================================");
		System.out.println("  1위." + menu.get(0).getFoodName() + "\t\t2위." + menu.get(1).getFoodName()
				+ "\t\t3위." + menu.get(2).getFoodName());
		System.out.println("====================================================================");
		System.out.print("원하시는 순위를 골라주세요, 관련 식당 정보를 안내 드립니다>");
		int index = Integer.parseInt(scanner.nextLine());
		List<PopularMenuVO> restaurant = dao.getPopularRestaurant(menu.get(index-1).getFoodId());
		System.out.println(" ");
		System.out.println("                                          식 당  정 보  안 내                                ");
		System.out.println("==================================================================================================================");
		System.out.println("  식당명       |  식당 주소                           |  식당 운영시간  |  식당 번호       | 식당 휴무일  | 해당 메뉴 가격");
		System.out.println("==================================================================================================================");
		for (PopularMenuVO popularMenuVO : restaurant) { 
		System.out.printf(" %-10s %-32s %-13s %-15s %-10s %-9s\n", 
			popularMenuVO.getRestaurantName(), popularMenuVO.getRestaurantAddress(),popularMenuVO.getRestaurantTime(),popularMenuVO.getRestaurantTell(),popularMenuVO.getRestaurantRestday(),popularMenuVO.getOfferPrice());
			}		
		System.out.println("==================================================================================================================");
		System.out.println();
	}

	public static void PopularMenuByAge20() throws Exception {
		PopularMenuDAO dao = PopularMenuDAO.getInstance();
		List<PopularMenuVO> menu = dao.getPopularMenuVO(20);
		System.out.println(" ");
		System.out.println("                            TOP3  메뉴 추천                                 ");
		System.out.println("====================================================================");
		System.out.println("  1위." + menu.get(0).getFoodName() + "\t\t2위." + menu.get(1).getFoodName()
				+ "\t\t3위." + menu.get(2).getFoodName());
		System.out.println("====================================================================");
		System.out.print("원하시는 순위를 골라주세요, 관련 식당 정보를 안내 드립니다>");
		int index = Integer.parseInt(scanner.nextLine());
		List<PopularMenuVO> restaurant = dao.getPopularRestaurant(menu.get(index-1).getFoodId());
		System.out.println(" ");
		System.out.println("                                          식 당  정 보  안 내                                ");
		System.out.println("==================================================================================================================");
		System.out.println("  식당명       |  식당 주소                           |  식당 운영시간  |  식당 번호       | 식당 휴무일  | 해당 메뉴 가격");
		System.out.println("==================================================================================================================");
		for (PopularMenuVO popularMenuVO : restaurant) { 
		System.out.printf(" %-10s %-32s %-13s %-15s %-10s %-9s\n", 
			popularMenuVO.getRestaurantName(), popularMenuVO.getRestaurantAddress(),popularMenuVO.getRestaurantTime(),popularMenuVO.getRestaurantTell(),popularMenuVO.getRestaurantRestday(),popularMenuVO.getOfferPrice());
			}		
		System.out.println("==================================================================================================================");
		System.out.println();
	}

	public static void PopularMenuByAge30() throws Exception {
		PopularMenuDAO dao = PopularMenuDAO.getInstance();
		List<PopularMenuVO> menu = dao.getPopularMenuVO(30);
		System.out.println(" ");
		System.out.println("                            TOP3  메뉴 추천                                 ");
		System.out.println("====================================================================");
		System.out.println("  1위." + menu.get(0).getFoodName() + "\t\t2위." + menu.get(1).getFoodName()
				+ "\t\t3위." + menu.get(2).getFoodName());
		System.out.println("====================================================================");
		System.out.print("원하시는 순위를 골라주세요, 관련 식당 정보를 안내 드립니다>");
		int index = Integer.parseInt(scanner.nextLine());
		List<PopularMenuVO> restaurant = dao.getPopularRestaurant(menu.get(index-1).getFoodId());
		System.out.println(" ");
		System.out.println("                                          식 당  정 보  안 내                                ");
		System.out.println("==================================================================================================================");
		System.out.println("  식당명       |  식당 주소                           |  식당 운영시간  |  식당 번호       | 식당 휴무일  | 해당 메뉴 가격");
		System.out.println("==================================================================================================================");
		for (PopularMenuVO popularMenuVO : restaurant) { 
		System.out.printf(" %-10s %-32s %-13s %-15s %-10s %-9s\n", 
			popularMenuVO.getRestaurantName(), popularMenuVO.getRestaurantAddress(),popularMenuVO.getRestaurantTime(),popularMenuVO.getRestaurantTell(),popularMenuVO.getRestaurantRestday(),popularMenuVO.getOfferPrice());
			}		
		System.out.println("==================================================================================================================");
		System.out.println();
	}

	
	public static void PopularMenuByGender() throws Exception {
		ByGender: while (true) {
			PopularMenuDAO dao = PopularMenuDAO.getInstance();
			System.out.println(" ");
			System.out.println("                              성별 선택                                ");
			System.out.println("====================================================================");
			System.out.println("         1.여성 인기     ||     2.남성 인기      ||    0.뒤로가기          ");
			System.out.println("====================================================================");
			System.out.print("성별을 선택하세요>");
			int genderNum = Integer.parseInt(scanner.nextLine());
			String memberGender = "";
			if (genderNum == 1) {
				memberGender = "FEMALE";
			} else if (genderNum == 2) {
				memberGender = "MALE";
			} else if (genderNum == 0) {
				break;
			} else {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
			List<PopularMenuVO> menu = dao.getPopularMenuByGenderVO(memberGender);
			System.out.println("                           TOP3  메뉴 추천                                   ");
			System.out.println("====================================================================");
			System.out.println("  1위." + menu.get(0).getFoodName() + "\t\t2위." + menu.get(1).getFoodName()
					+ "\t\t3위." + menu.get(2).getFoodName());
			System.out.println("====================================================================");
			System.out.print("식당 정보를 원하는 메뉴의 순위를 입력해주세요>");
			int index = Integer.parseInt(scanner.nextLine());
			List<PopularMenuVO> restaurant = dao.getPopularRestaurant(menu.get(index - 1).getFoodId());
			System.out.println();
			System.out.println(
					"                                           식 당 정 보 안 내                                ");
			System.out.println(
					"==================================================================================================================");
			System.out.println(
					"  식당명       |  식당 주소                           |  식당 운영시간  |  식당 번호       | 식당 휴무일  | 해당 메뉴 가격");
			System.out.println(
					"==================================================================================================================");
			for (PopularMenuVO vo : restaurant) {
				System.out.printf("   %-10s %-30s %-13s %-15s %-10s %-9s\n", vo.getRestaurantName(),
						vo.getRestaurantAddress(), vo.getRestaurantTime(), vo.getRestaurantTell(),
						vo.getRestaurantRestday(), vo.getOfferPrice());
			}
			System.out.println(
					"==================================================================================================================");
		}
		
	} 
	
	
	

}

