package chapter02;

public class Ex2 {
	public static void main(String[] args) {
	double dist = 40e12;
	double velo = 300_000;
//	System.out.println(dist);
//	System.out.println(velo);
	double ly = 60*60*24*365.25*velo;
//	System.out.println(ly);
	System.out.println("빛의 속도로 프록시마 센타우리 별까지 가는데 걸리는 시간은 "
			+ dist/ly + "광년이다.");
	
	
	}
}
