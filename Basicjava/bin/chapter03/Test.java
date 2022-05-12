package chapter03;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double A = scanner.nextDouble();
		double B = scanner.nextDouble();
		
		double result = A / B;
		
		if (B == 0.0) {
			System.out.println("결과:무한대");
		} else {


			System.out.println("결과:" +result);
		}
		
		
		
	}
}
