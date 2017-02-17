import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Change {

	public static final String PROBLEM = "change";
	public static final String EXT = ".dat";

	public static void main(String[] args) {
		Scanner scan;
		try {
			scan = new Scanner(new File(PROBLEM + EXT));
		} catch (FileNotFoundException ex) {
			printLine("File not found: " + ex.getMessage());
			return;
		}
		while (scan.hasNextLine()) {
			String[] split = scan.nextLine().split(" ");
			double a = Double.parseDouble(split[0]);
			double b = Double.parseDouble(split[1]);
			if (((Math.round(a) + 1.0) - a == 1.0) || ((Math.round(b) + 1.0) - b == 1.0)) {
				printLine("0-0-0-0-0");
			} else {
				int aC = 100 - Integer.parseInt(split[0].substring(split[0].indexOf('.') + 1));
				int bC = 100 - Integer.parseInt(split[1].substring(split[1].indexOf('.') + 1));
				if (coins(bC) > coins(aC)) {
					printLine(change(bC));
				} else {
					printLine(change(aC));
				}
			}
		}
		scan.close();
	}

	private static int coins(int change) {
		String[] coins = change(change).split("[\\-]");
		int c = 0;
		for (String s : coins) {
			c += Integer.parseInt(s);
		}
		return c;
	}

	private static String change(int change) {
		int h = 0, q = 0, d = 0, n = 0, p = 0;
		while (change > 0) {
			if (change >= 50) {
				h += change / 50;
				change %= 50;
			}
			if (change >= 25) {
				q += change / 25;
				change %= 25;
			}
			if (change >= 10) {
				d += change / 10;
				change %= 10;
			}
			if (change >= 5) {
				n += change / 5;
				change %= 5;
			} else {
				p = change;
				change = 0;
			}
		}
		return h + "-" + q + "-" + d + "-" + n + "-" + p;
	}

	public static void print(Object... obj) {
		for (Object o : obj) {
			System.out.print(o);
		}
	}

	public static void printLine(Object... obj) {
		if (obj.length <= 0) {
			System.out.println();
			return;
		}
		for (Object o : obj) {
			System.out.println(o);
		}
	}

	public static void print(boolean newLine, String format, Object... obj) {
		System.out.printf(format + (newLine ? "\n" : ""), obj);
	}
}
