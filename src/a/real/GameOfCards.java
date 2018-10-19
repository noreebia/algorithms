package a.real;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameOfCards {
	private static List<Integer> cards;
	private static int numOfCards;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		numOfCards = scanner.nextInt();
		int shuffleCount = scanner.nextInt();

		int[] n = new int[shuffleCount];
		for (int i = 0; i < shuffleCount; i++) {
			n[i] = scanner.nextInt();
		}
		scanner.close();

		cards = new ArrayList<>();
		for (int i = 0; i < numOfCards; i++) {
			cards.add(i + 1);
		}

		for (int k = 0; k < shuffleCount; k++) {
			shuffleCards(cards, n[k]);
		}

		for (int p = 0; p < 5; p++) {
			System.out.println(cards.get(p));
		}
		return;
	}


	public static void shuffleCards(List<Integer> cards, int n) {
		List<Integer> cardsThatMoveUp = new ArrayList<>();
		
		for (int i = n; i <= cards.size() - n-1; i++) {
			cardsThatMoveUp.add(cards.get(i));
		}		
		
		for(int i= cards.size() - n-1; i >= n ; i--) {
			cards.remove(i);
		}

		if (cardsThatMoveUp.size() > 2 * n) {
			shuffleCards(cardsThatMoveUp, n);
		}

		cards.addAll(0, cardsThatMoveUp);

		return;
	}
}
