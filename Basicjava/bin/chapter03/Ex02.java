package chapter03;

import java.util.Scanner;

public class Ex02 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("수도입니까?(수도: 1, 수도아님: 0) ");
		int cap = scanner.nextInt();
		
		System.out.print("총 인구는?(단위: 만) ");
		int pop = scanner.nextInt();
		
		System.out.print("연소득이 1억 이상인 인구는?(단위: 만) ");
		int inc = scanner.nextInt();
		
		System.out.println((((cap == 1) && (pop >= 100)) || (inc > 50)) ? "이 도시는 메트로폴리스입니다." : "이 도시는 메트로폴리스가 아닙니다");
	}
}
