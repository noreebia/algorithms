package nhn_entertainment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WorldOfMatrices {

	private static Map<Integer, Integer> areaMetadata = new HashMap<>();
	private static int areaCount = 0;

	private static Map<Integer, List<int[]>> areas = new HashMap<>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int size = scanner.nextInt();
		scanner.nextLine();
		
		int[][] matrix = new int[size][size];
		for (int i = 0; i < size; i++) {
			String row = scanner.nextLine();
			String[] integerStrings = row.split(" ");
			for (int k = 0; k < size; k++) {
				matrix[i][k] = Integer.parseInt(integerStrings[k]);
			}
		}
		scanner.close();

		for (int i = 0; i < size; i++) {
			for (int k = 0; k < size; k++) {
				if (matrix[i][k] == 1) {
					int existingAreaID = getExistingAreaContainingPoint (i, k);
					if (existingAreaID > -1) {
						updateAreaSize(existingAreaID, i ,k);
					} else {
						foundNewArea(i, k);
					}
				}
			}
		}
		
		List<Integer> areaSizes = new LinkedList<Integer>(areaMetadata.values());
		
		if(areaSizes.size() == 0 || areaSizes ==null) {
			System.out.println(0);
			return;
		}
		
		Collections.sort(areaSizes); 
		
		System.out.println(areaMetadata.size());
		for(int i=0; i < areaSizes.size(); i++) {
			System.out.print(areaSizes.get(i));
			if(i < areaSizes.size()-1) {
				System.out.print(" ");
			}
		}
		return;
	}

	public static int getExistingAreaContainingPoint(int row, int column) {
		for (Map.Entry<Integer, List<int[]>> area : areas.entrySet()) {
			
			for(int[] location: area.getValue()) {
				if(Math.abs(row - location[0]) + Math.abs(column - location[1]) == 1 ) {
					return area.getKey();
				}
			}
		}
		
		return -1;
	}

	public static void updateAreaSize(int areaID, int row, int column) {
		areaMetadata.put(areaID, areaMetadata.get(areaID) + 1);
		int[] newLocation = {row, column};
		areas.get(areaID).add(newLocation);
	}

	public static void foundNewArea(int row, int column) {
		List<int[]> areaLocations = new ArrayList<>();
		areaLocations.add(new int[]{row, column});
		areas.put(areaCount, areaLocations);

		areaMetadata.put(areaCount++, 1);
	}
}
