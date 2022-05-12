package chapter02;

public class Test {

	public int sum(int a, int b) {
		return a+b;

	}

	public static void main(String[] args) {
		int a =3;
		int b =4;
		
		Test myTest = new Test();
		int c = myTest.sum(a, b);
		
		System.out.println(c);
			
		
	}
}