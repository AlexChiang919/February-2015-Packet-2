import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Scrambled {

	private static final String PROBLEM = "";
	private static final String EXT = "scrambled.dat";

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
			PrefixExpression pf = new PrefixExpression();
			for (int i = split.length - 1; i >= 0; i--) {
				pf.push(split[i]);
			}
			printLine(pf);
		}
		scan.close();
	}
	
	public static class PrefixExpression {
		
		private Stack<String> stack = new Stack<>();
		
		public void push(String ch) {
			if (Scrambled.isOperator(ch)) {
				int a = Integer.parseInt(stack.pop());
				int b = Integer.parseInt(stack.pop());
				if (ch.equals("+"))
					stack.push(String.valueOf(a + b));
				if (ch.equals("-"))
					stack.push(String.valueOf(a - b));
				if (ch.equals("*"))
					stack.push(String.valueOf(a * b));
				if (ch.equals("/"))
					stack.push(String.valueOf(a / b));
			} else
				stack.push(ch);
			//printLine(stack);
		}
		
		@Override
		public String toString() {
			if (stack.size() == 1)
				return stack.pop();
			return stack.toString();
		}
	}

	private static boolean isOperator(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
			return true;
		else
			return false;
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
