package chapter03;

import java.util.Scanner;

public class Ex9 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("첫 번째 수: ");
		double A = sc.nextDouble();

		System.out.print("두 번째 수: ");
		double B = sc.nextDouble();
		double result = A / B;
		
		System.out.println("---------------------");
		if ((B != 0.0) || (B != 0)) {
			System.out.println("결과 : " + result);
		} else {
			System.out.println("결과 : 무한대");
		}
		
	}
}
