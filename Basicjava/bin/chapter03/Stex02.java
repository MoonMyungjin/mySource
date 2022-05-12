package chapter03;

public class Stex02 {
	public static void main(String[] args) {
		char ch ='E';
		
//		0-48 9-57 A-65 Z-90 a-97 z-122		
		char lowerCase = (char) ((((int)ch>=65) && (int)ch<=90) ? (int)ch+32 : ch) ;
		
		System.out.println("ch:"+ch);
		System.out.println("ch to lowercase:" +lowerCase);
		
	}
}
