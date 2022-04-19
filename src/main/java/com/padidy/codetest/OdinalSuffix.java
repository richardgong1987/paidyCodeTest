package com.padidy.codetest;

public class OdinalSuffix {


	private static final int EDGE_CASES = 10;

	public static String getOdinalSuffix(int number) {
		if (number == 0) {
			return Integer.toString(number);
		}
		String ordinalSuffix = isEdgeCase(number) ? "th" : determineOrdinalSuffix(number);
		return Integer.toString(number).concat(ordinalSuffix);
	}

	private static boolean isEdgeCase(int number) {
		int modeToTen = number % 10;
		int modeToHundred = number % 100;
		return ((modeToHundred - modeToTen) == EDGE_CASES);
	}

	private static String determineOrdinalSuffix(Integer number) {
		int modeToTen = number % 10;
		return switch (modeToTen) {
			case 1 -> "st";
			case 2 -> "nd";
			case 3 -> "rd";
			default -> "th";
		};
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			System.out.println(getOdinalSuffix(i));
		}
	}
}
