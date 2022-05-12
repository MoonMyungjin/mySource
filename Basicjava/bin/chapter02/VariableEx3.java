package chapter02;

public class VariableEx3 {
	public static void main(String[] args) {
	int x = 3;
	int y = 5;
	System.out.println("x:" + x + ", y:" + y + ",\t x + y =" + x+y);
	
	// 2개 변수의 값 교환
	int temp = x;
	x = y;
	y = temp;
	System.out.println("x:" + x + ", y:" + y);
	}
}
