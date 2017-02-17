import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Strings {

	private static final String PROBLEM = "strings";
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
			String s = scan.nextLine();
			printLine(s);
			printLine(s.length());
			printLine(s.toUpperCase());
			printLine(s.substring(s.length() / 2));
			printLine(s.toLowerCase().charAt(0) + "" + s.toLowerCase().charAt(s.length() - 1));
			printLine();
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
