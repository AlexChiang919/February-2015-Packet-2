import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Archimedes {

	private static final String PROBLEM = "archimedes";
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
			double r = Double.parseDouble(scan.nextLine());
			printF(true, "Radius = %.1f, Sphere Vol = %.2f, Cylinder Vol = %.2f", r,
					(4D / 3D) * Math.PI * Math.pow(r, 3), Math.PI * Math.pow(r, 2) * (2 * r));
		}
		scan.close();
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
