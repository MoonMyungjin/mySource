package chapter02;

public class VariableExample {
	public static void main(String[] args) {
		/*
		 * 변수는 데이터를 저장하는 공간
		 * 이름이 필요
		 * 자바는 c언어와 문법이 매우 비슷
		 * 데이터의 종류에 따라 변수를 구분
		 */
		// 변수의 선언 및 초기화가 중요
		
		int age; // ****변수 선언
		age = 10; // 변수의 초기화
		age = 20; // 데이터 초기화(==reset)
		int age2 = 10+30; // 변수의 선언과 동시에 초기화
		System.out.println(age);
		System.out.println(age2);
	}
}