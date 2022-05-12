package chapter02;

import java.util.Scanner;

public class Ex4 {
	public static void main(String[] args) {
		double pi = Math.PI;
		Scanner scanner = new Scanner(System.in);
		System.out.print("원기둥 밑변의 반지름을 입력하시오.(단위: cm): ");
		double rad = scanner.nextDouble();
		
		System.out.print("원기둥의 높이를 입력하시오.(단위: cm): ");
		double height = scanner.nextDouble();
		
		double area = pi * rad * rad;
		double  vol = area * height;
		
		System.out.println("원기둥 밑변의 넓이는 " + area +
				"㎠이고, 원기둥의 부피는 " + vol + "㎤ 이다.");
		
		
		
		
	}

}
