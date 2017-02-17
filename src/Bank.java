import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bank {

	private static final String PROBLEM = "bank";
	private static final String EXT = ".dat";

	public static void main(String[] args) {
		Scanner scan;
		try {
			scan = new Scanner(new File(PROBLEM + EXT));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		int times = Integer.parseInt(scan.nextLine());
		while (times-- > 0) {
			String s = scan.nextLine();
			int cmds = Integer.parseInt(scan.nextLine());
			while (cmds-- > 0) {
				String ccmd = scan.nextLine();
				String[] cmd = ccmd.split(" ");
				if (cmd[0].equals("SEARCH"))
					printLine(search(s, Integer.parseInt(cmd[1]) - 1, cmd[2].charAt(0)));
				else if (cmd[0].equals("REPLACE"))
					printLine(replace(s, Integer.parseInt(cmd[1]), cmd[2].charAt(0)));
				else if (cmd[0].equals("DELETE"))
					printLine(delete(s, Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2])));
				else if (cmd[0].equals("INSERT")) {
					printLine(insert(s, Integer.parseInt(cmd[1]), ccmd.substring(ccmd.indexOf(cmd[1]) + cmd[1].length() + 1)));
				}
			}
		}
		scan.close();
	}
	
	public static int search(String s, int start, char c) {
		return s.substring(start).indexOf(c) + start + 1;
	}
	
	public static String replace(String s, int index, char c) {
		return (new StringBuilder(s)).replace(index - 1, index, "" + c).toString();
	}
	
	public static String delete(String s, int start, int number) {
		return s.substring(0, start - 1) + s.substring(start - 1 + number);
	}
	
	public static String insert(String s, int start, String in) {
		return (new StringBuilder(s)).insert(start - 1, in + " ").toString();
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
