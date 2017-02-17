import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UILingo {

	private static final String PROBLEM = "uilingo";
	private static final String EXT = ".dat";

	public static void main(String[] args) {
		Scanner scan;
		try {
			scan = new Scanner(new File(PROBLEM + EXT));
		} catch (FileNotFoundException ex) {
			printF(true, "File not found: %s", ex.getMessage());
			return;
		}
		int boards = Integer.parseInt(scan.nextLine());
		while (boards-- > 0) {
			int[][] board = parseBoard(scan.nextLine().split(" "));
			int games = Integer.parseInt(scan.nextLine());
			while (games-- > 0) {
				String[] game = scan.nextLine().split(" ");
				boolean[][] imitate = new boolean[4][4];
				fill(imitate, false);
				for (String s : game) {
					String i = search(board, Integer.parseInt(s));
					if (!i.equals("-1 -1"))
						imitate[Integer.parseInt(i.split(" ")[0])][Integer.parseInt(i.split(" ")[1])] = true;
				}
				int money = 0;
				if (fourCorners(imitate))
					money += 5;
				if (flat(imitate))
					money += 10;
				if (plumb(imitate))
					money += 15;
				if (slant(imitate))
					money += 20;
				printLine(money);
			}
		}
		scan.close();
	}

	private static int[][] parseBoard(String[] s) {
		int[][] out = new int[4][4];
		int i = 0;
		for (int r = 0; r < out.length; r++) {
			for (int c = 0; c < out.length; c++) {
				out[r][c] = Integer.parseInt(s[i++]);
			}
		}
		return out;
	}
	
	private static void fill(boolean[][] b, boolean value) {
		for (int r = 0; r < b.length; r++) {
			for (int c = 0; c < b.length; c++) {
				b[r][c] = value;
			}
		}
	}

	private static String search(int[][] board, int number) {
		for (int a = 0; a < board.length; a++) {
			for (int b = 0; b < board[a].length; b++) {
				if (board[a][b] == number) {
					return a + " " + b;
				}
			}
		}
		return "-1 -1";
	}

	private static boolean fourCorners(boolean[][] board) {
		return board[0][0] && board[0][board[0].length - 1] && board[board.length - 1][0]
				&& board[board.length - 1][board[board.length - 1].length - 1];
	}

	private static boolean flat(boolean[][] board) {
		for (int r = 0; r < 4; r++) {
			int o = 0;
			for (int c = 0; c < 4; c++) {
				if (board[r][c])
					o++;
			}
			if (o >= 4)
				return true;
			o = 0;
		}
		return false;
	}

	private static boolean plumb(boolean[][] board) {
		for (int c = 0; c < 4; c++) {
			int o = 0;
			for (int r = 0; r < 4; r++) {
				if (board[r][c])
					o++;
			}
			if (o >= 4)
				return true;
			o = 0;
		}
		return false;
	}

	private static boolean slant(boolean[][] board) {
		int d = 0;
		for (int i = 0; i < 4; i++) {
			if (board[i][i])
				d++;
		}
		if (d >= 4)
			return true;
		d = 0;
		for (int i = 0; i < 4; i++) {
			if (board[i][3 - i])
				d++;
		}
		if (d >= 4)
			return true;
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
