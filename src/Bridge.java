import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Bridge {

	private static final String PROBLEM = "bridge";
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
			StringBuilder sb = new StringBuilder(scan.nextLine());
			List<Character> pq = new ArrayList<>();
			List<Card> cards = new ArrayList<>();
			while (sb.length() > 0) {
				if (pq.size() == 2) {
					cards.add(new Card(pq.remove(0), Suit.byCharacter(pq.remove(0))));
					continue;
				}
				pq.add(sb.charAt(0));
				sb.deleteCharAt(0);
				pq.add(sb.charAt(0));
				sb.deleteCharAt(0);
			}
			List<Card> clubs = getSuitCardsFromHand(cards, Suit.CLUBS);
			List<Card> diamonds = getSuitCardsFromHand(cards, Suit.DIAMONDS);
			List<Card> hearts = getSuitCardsFromHand(cards, Suit.HEARTS);
			List<Card> spades = getSuitCardsFromHand(cards, Suit.SPADES);
			//printLine(pointsFromHand(cards));
			printLine(clubs);
			printLine(diamonds);
			printLine(hearts);
			printLine(spades);
			int points = pointsFromHand(cards) + pointsFromSuit(clubs) + pointsFromSuit(diamonds)
					+ pointsFromSuit(hearts) + pointsFromSuit(spades);
			printLine("BRIDGE POINTS = " + points);
			cards = null;
			clubs = null;
			diamonds = null;
			hearts = null;
			spades = null;
		}
		scan.close();
	}

	private static List<Card> getSuitCardsFromHand(List<Card> hand, Suit suit) {
		List<Card> suitCards = new ArrayList<>();
		for (Card c : hand) {
			if (c.getSuit() == suit)
				suitCards.add(c);
		}
		return suitCards;
	}

	private static int pointsFromSuit(List<Card> suitOfCards) {
		if (suitOfCards.size() == 0)
			return 3;
		int points = 0;
		if (suitOfCards.size() == 2)
			points += 1;
		if (suitOfCards.size() == 1)
			points += 2;
		for (Card c : suitOfCards)
			if (c.value >= 11)
				points += c.value - 10;
		return points;
	}

	private static int pointsFromHand(List<Card> hand) {
		int aces = 0;
		for (Card c : hand) {
			if (c.getValue() == 14)
				aces++;
		}
		return (aces >= 4) ? 1 : 0;
	}

	private static class Card {

		private int value;
		private Suit suit;

		public Card(char value, Suit suit) {
			if (value == '0')
				this.value = 10;
			else if (value == 'A')
				this.value = 14;
			else if (value == 'K')
				this.value = 13;
			else if (value == 'Q')
				this.value = 12;
			else if (value == 'J')
				this.value = 11;
			else
				this.value = value - 48;
			this.suit = suit;
		}

		public int getValue() {
			return value;
		}

		public Suit getSuit() {
			return suit;
		}
		
		@Override
		public String toString() {
			return value + " of " + suit.toString();
		}
	}

	protected static enum Suit {

		CLUBS("Clubs", 'C'), DIAMONDS("Diamonds", 'D'), HEARTS("Hearts", 'H'), SPADES("Spades", 'S');

		private String name;
		private char character;

		Suit(String name, char character) {
			this.name = name;
			this.character = character;
		}

		public static Suit byName(String s) {
			for (Suit su : Suit.values())
				if (s.toUpperCase().charAt(0) == su.character)
					return su;
			return null;
		}

		public static Suit byCharacter(Character c) {
			for (Suit su : Suit.values())
				if (Character.toUpperCase(c) == su.character)
					return su;
			return null;
		}

		public String getName() {
			return name;
		}

		public char getCharacter() {
			return character;
		}
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
