import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Friends {

	private static final String PROBLEM = "friends";
	private static final String EXT = ".dat";

	public static void main(String[] args) {
		Scanner scan;
		try {
			scan = new Scanner(new File(PROBLEM + EXT));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		boolean suggest = false;
		HashMap<String, ArrayList<String>> friends = new HashMap<>();
		while (scan.hasNextLine()) {
			String nextLine = scan.nextLine();
			if (nextLine.equals("*")) {
				suggest = false;
				friends.clear();
				printLine();
				continue;
			} else if (nextLine.equals("-")) {
				suggest = true;
				continue;
			}
			if (!suggest) {
				ArrayList<String> abc = new ArrayList<>(Arrays.asList(nextLine.split("[:]?\\s")));
				friends.put(abc.remove(0), abc);
				abc = null;
			} else {
				String who = nextLine;
				HashMap<String, Integer> mutual = new HashMap<>();
				for (String f : friends.get(who)) {
					for (String ff : friends.get(f)) {
						if (!(ff.equals(who) || friends.get(who).contains(ff))) {
							int o = 0;
							if (mutual.containsKey(ff))
								o = mutual.get(ff);
							mutual.put(ff, ++o);
						}
					}
				}
				String mostMutual = "";
				int occ = Integer.MIN_VALUE;
				for (String s : mutual.keySet())
					if (mutual.get(s) > occ) {
						mostMutual = s;
						occ = mutual.get(s);
					}
				printLine(mostMutual);
			}
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
