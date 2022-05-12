package chapter02;

import java.util.Scanner;

public class Ex3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("첫번째 수를 입력하세요: ");
		int A = scanner.nextInt();
		System.out.print("두번째 수를 입력하세요: ");
		int B = scanner.nextInt();

		System.out.print("첫번째 수가 두번째 수보다 큰가? ");
		if (A>B) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		
	}
}
