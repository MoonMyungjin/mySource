package chapter03;

import java.util.Scanner;

public class Ex03 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("윤년인지를 확인할 연도를 입력하세요: ");
		
		int year = scanner.nextInt();
		
		if( ((year%4 ==0) && (year%100 !=0)) || (year % 400 == 0) ) {
			System.out.printf("%d년은 윤년입니다.\n",year);
		} else {
			System.out.printf("%d년은 윤년이 아닙니다.\n",year);
		}
	
	}
}
