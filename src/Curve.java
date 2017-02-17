import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Curve {

	private static final String PROBLEM = "curve";
	private static final String EXT = ".dat";

	public static void main(String[] args) {
		Scanner scan;
		try {
			scan = new Scanner(new File(PROBLEM + EXT));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		while (scan.hasNextLine()) {
			String[] split = scan.nextLine().split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			int c = b;
			int c_max = b;
			int t = a;
			while (t > 0) {
				c = c_max;
				while (c > 0) {
					printLine(getStars(c));
					c /= 2;
				}
				c_max -= 5;
				t--;
			}
			printLine();
		}
		scan.close();
	}
	
	private static String getStars(int number) {
		String stars = "";
		for (int i = 0; i < number; i++) {
			stars += "*";
		}
		return stars;
	}

	public static void print(Object... o) {
		for (Object obj : o) {
			System.out.print(obj);
		}
	}

	public static void printLine(Object... o) {
		if (o.length <= 0) {
			System.out.println();
			return;
		}
		for (Object obj : o) {
			System.out.println(obj);
		}
	}

	public static void printF(boolean newLine, String format, Object... o) {
		System.out.printf(format + ((newLine) ? "\n" : ""), o);
	}

}
