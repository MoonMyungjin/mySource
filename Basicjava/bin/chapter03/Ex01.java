package chapter03;

import java.util.Scanner;

public class Ex01 {
	public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	System.out.print("첫 번째 숫자를 입력하세요: ");
	int A = scanner.nextInt();
	System.out.print("두 번째 숫자를 입력하세요: ");	
	int B = scanner.nextInt();
	
	int numB = ((A+B) + Math.abs(A-B)) / 2;
	int numS = ((A+B) - Math.abs(A-B)) / 2;
	
	int resultA = numB / numS;	//quotient
	int resultB = numB % numS;	//remainder
	
	System.out.printf("큰수를 작은 수로 나눈몫은 %d이고, 나머지는 %d이다.",resultA,resultB);
	}
}
