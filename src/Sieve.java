import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sieve {

	private static final String PROBLEM = "sieve";
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
			int[] array = new int[b - a + 1];
			fillArray(array, a, b);
			int sieve = 2;
			printArray(array);
			while (!allPrimes(array)) {
				if (testSieve(array, sieve)) {
					printLine("sieve = " + sieve);
				} else {
					sieve++;
					continue;
				}
				sieve(array, sieve++);
				printArray(array);
			}
			printLine();
		}
		scan.close();
	}
	
	private static void fillArray(int[] array, int a, int b) {
		int index = 0;
		for (int i = a; i <= b; i++) {
			array[index++] = i;
		}
	}
	
	private static boolean testSieve(int[] array, int n) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] <= n)
				continue;
			if (array[i] % n == 0)
				return true;
		}
		return false;
	}
	
	private static void sieve(int[] array, int n) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] <= n)
				continue;
			if (array[i] % n == 0)
				array[i] = 0;
		}
	}
	
	private static boolean allPrimes(int[] array) {
		for (int i : array) {
			if (i == 0)
				continue;
			if (!prime(i))
				return false;
		}
		return true;
	}
	
	private static boolean prime(int number) {
		if (number <= 2)
			return true;
		for (int i = 2; i < number; i++) {
			if (number % i == 0)
				return false;
		}
		return true;
	}
	
	private static void printArray(int[] array) {
		for (int i : array) {
			if (i == 0) {
				print("*");
			} else {
				print(i);
			}
			print(constructSpaces(4 - String.valueOf(i).length()));
		}
		printLine();
	}
	
	private static String constructSpaces(int spaces) {
		String s = "";
		for (int a = 0; a < spaces; a++) {
			s += " ";
		}
		return s;
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
