package chapter02;

import java.util.Iterator;
import java.util.Scanner;

public class ex5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
//		변수명은 간결하면서 담는 내용을 모두 표현하도록 설정
//		A,a 등의 변수 사용 X
//		A -> coin500 등으로 변경
		System.out.print("500원짜리 동전의 갯수: ");
		int A = scanner.nextInt(); // 500원 = A
		
		System.out.print("100원짜리 동전의 갯수: ");
		int B = scanner.nextInt(); // 100원 = B
		
		System.out.print("50원짜리 동전의 갯수: ");
		int C = scanner.nextInt(); //  50원	= c
		
		System.out.print("10원짜리 동전의 갯수: ");
		int D = scanner.nextInt(); //  10원 = D		

		int sum = 500 * A + 100 * B + 50 * C + 10 * D;
		
//		System.out.println("저금통 안의 동전의 총 액수: "+sum);
		System.out.printf("저금통 안의 동전의 총 액수: %,d", sum);
	}
}
