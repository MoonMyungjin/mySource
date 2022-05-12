package chapter03;

public class Stex01 {
	public static void main(String[] args) {
	int numOfApples = 123;
	int sizeOfBucket = 10;
	int numOfBuckket = (numOfApples / sizeOfBucket);
	
	System.out.println("필요한 바구니의 수 : "+numOfBuckket);
	
	int num = 37;
	System.out.println((num/10*10 +10)-num);
	
	char ch1 = 'Z';
		System.out.println((int) ch1);
	int A=91;
		System.out.println((char) A);
			
	char ch = 'O';
	boolean b = (((int)ch>=48 && (int)ch<=57) || ((int)ch>=45 && (int)ch<=90)
			    ||  ((int)ch>=97 && (int)ch<=122));
	
	System.out.println(b);
//	0-48 9-57 A-65 z-90 a-97 z-122
	
	}
}
