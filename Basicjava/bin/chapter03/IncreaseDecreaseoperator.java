package chapter03;

public class IncreaseDecreaseoperator {
	public static void main(String[] args) {
	int x = 10;
	int y = 10;
	int z;
	
	System.out.println("----------------------------------");
	x++;
	++x;
	System.out.println("x=" +x);

	System.out.println("----------------------------------");
	y--;
	--y;
	System.out.println("y=" +y);

	System.out.println("----------------------------------");
	z = x++;
	System.out.println("z=" + z);
	System.out.println("x=" + x);
		
	System.out.println("----------------------------------");
	z = ++x;
	System.out.println("z=" + z);
	System.out.println("x=" + x);
	
	System.out.println("----------------------------------");
	z = ++x + y++;
	System.out.println("z=" + z);
	System.out.println("x=" + x);
	System.out.println("y=" + y);
	System.out.printf("x=%s, y=%s, z=%s\n",x,y,z);
	
	}
}
