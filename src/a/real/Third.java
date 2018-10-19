package a.real;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Third {
	private static int balance = 0;
	private static int fee = 1;
	private static int possessedCoins = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int days = scanner.nextInt();
		scanner.nextLine();

		int[] prices = new int[days];
		for (int i = 0; i < days; i++) {
			prices[i] = scanner.nextInt();
		}
		scanner.close();

		List<Integer> daysToSell = determineDaysToSell(prices);
		for (int k = 0; k < days; k++) {
			// if not last day
			if (k < days - 1) {
				// if price rises tomorrow
				if (prices[k + 1] >= prices[k]) {
					buy(prices[k]);
					System.out.println("bought on day: " + k);
				}
				// if price decreases tomorrow
				else if (prices[k + 1] < prices[k]) {
					// if not first day
					if (k > 0) {
						if (prices[k - 1] < prices[k]) {
							if(prices[k] * possessedCoins - prices[k-1] * possessedCoins >  prices[k-1] * possessedCoins + fee) {
								for (int i = 0; i < possessedCoins; i++) {
									sell(prices[k]);
								}
								balance--;
								possessedCoins = 0;
								System.out.println("sold on day: " + k);
							} else {
								System.out.println("continuing");
								continue;
							}
						}
					}
					// first day
					else {
						continue;
					}
				}
			} else {
				if (possessedCoins > 0) {
					if(prices[k] * possessedCoins > 1) {
						for (int i = 0; i < possessedCoins; i++) {
							sell(prices[k]);
						}
						balance--;
						possessedCoins = 0;
					}
				}
			}
			System.out.println(balance);
		}

		System.out.println(balance);

	}
	
	private static List<Integer> determineDaysToSell(int[] prices) {
		List<Integer> daysToSell = new ArrayList<>();
		for(int i=0; i < prices.length; i++) {
			if(i > 0) {
				if(prices[i] > prices[i-1]) {
					daysToSell.add(i);
				}
			}
		}
		return daysToSell;
	}
	private static void buy(int value) {
		balance -= value;
		possessedCoins++;
	}

	private static void sell(int value) {
		balance += value;
	}
}
