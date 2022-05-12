package chapter03;

public class ConditionalOp {
	public static void main(String[] args) {
		int score = 80;
		char grade = (score > 90) ? 'A' : ( (score > 80) ? 'B' : 'C' );
		System.out.println(score + "점은 " + grade + "등급입니다.");
				
		int var1 = 5;
		int var2 = 2;
		double var3 = var1 / var2;
		int var4 = (int) (var3 * var2);
	}
}
