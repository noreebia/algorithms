package a.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ItemAdventure {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String itemString = scanner.nextLine();
		scanner.close();

		if(itemString.length() == 0 || itemString == null){
			System.out.println(0);
			return;
		}
		
		String[] stringsOfItems = itemString.split("\\s+");

		int numOfInputs = stringsOfItems.length;
		int[] itemsPickedUp = new int[numOfInputs];
		for (int i = 0; i < numOfInputs; i++) {
			itemsPickedUp[i] = Integer.parseInt(stringsOfItems[i]);
//			System.out.print(itemsPickedUp[i] + " ");
		}
		
//		System.out.println();

		LinkedList<Integer> playerItems = new LinkedList<>();
		List<Integer> droppedItems = new ArrayList<>();
		
		
		for (Integer item : itemsPickedUp) {
			if (!playerItems.contains(item)) {
				if (playerItems.size() < 3) {
					playerItems.addFirst(item);
				} else {
//					droppedItems.add(playerItems.get(playerItems.size()-1));
//					playerItems.remove(playerItems.size() - 1);
					
					droppedItems.add(playerItems.removeLast());
					playerItems.addFirst(item);
				}
			} else {
				int indexOfExistingItem = playerItems.indexOf(item);
				playerItems.remove(indexOfExistingItem);
				playerItems.addFirst(item);
			}
		}
		
		
//		for (Integer item : itemsPickedUp) {
//			if (!playerItems.contains(item)) {
//				if (playerItems.size() < 3) {
//					playerItems.add(item);
//				} else {
//					droppedItems.add(playerItems.get(playerItems.size()-1));
//					playerItems.remove(playerItems.size() - 1);
//					playerItems.add(item);
//				}
//			} else {
//				int indexOfExistingItem = playerItems.indexOf(item);
//				playerItems.remove(indexOfExistingItem);
//				droppedItems.add(item);
//				playerItems.add(item);
//			}
//		}

		if (droppedItems.size() > 0) {
			for (Integer droppedItem : droppedItems) {
				System.out.println(droppedItem);
			}
		} else {
			System.out.println(0);
		}
	}
}
