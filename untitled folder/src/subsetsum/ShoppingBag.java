package subsetsum;

import java.util.ArrayList;
import java.util.Scanner;
import cs1c.TimeConverter;

/**
 * ShoppingBag creates a grocery shopping list by selecting a subset of items
 * whose total price is as close as possible to the provided budget.
 * It reads prices from a CSV file and uses the SubsetSum algorithm.
 */
public class ShoppingBag {
	private ArrayList<Double> priceOfGroceries;

	/**
	 * Constructor for ShoppingBag that reads in the grocery prices.
	 * @param filePath Path to the CSV file containing grocery data.
	 */
	public ShoppingBag(String filePath) {
		GroceriesFileReader reader = new GroceriesFileReader();
		priceOfGroceries = reader.readFile(filePath);
		if (priceOfGroceries.size() < 1) {
			System.out.println("WARNING: The list of groceries is empty.");
			return;
		}
		System.out.printf("The list of groceries has %d items.\n", priceOfGroceries.size());
	}

	/**
	 * Returns the list of grocery prices.
	 * @return List of prices.
	 */
	public ArrayList<Double> getPriceOfGroceries() {
		return priceOfGroceries;
	}

	/**
	 * Main method that drives the program. It reads the prices, prompts for a budget,
	 * runs the subset-sum algorithm, and displays the results.
	 *
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		// Use a relative path for the input file.
		final String FILEPATH = "resources/inputFile02.txt";

		// Create a ShoppingBag object and get the list of grocery prices.
		ShoppingBag bag = new ShoppingBag(FILEPATH);
		ArrayList<Double> shoppingList = bag.getPriceOfGroceries();

		// Display the list of groceries (prices).
		System.out.println("Groceries wanted:");
		System.out.println(shoppingList);

		// Prompt the user for their budget.
		Scanner keyboard = new Scanner(System.in);
		double budget = -1;
		do {
			System.out.println("\nEnter your budget:");
			String input = keyboard.nextLine();
			// Valid input: numeric string with optional decimal
			String validInputPattern = "[0-9]+[0-9]*\\.*[0-9]*";
			if (!input.matches(validInputPattern)) {
				System.out.println("Invalid input " + input);
				System.out.println("Enter a numeric value for the budget.");
				continue;
			}
			budget = Double.parseDouble(input);
		} while (budget < 0);

		// Capture runtime for the subset-sum algorithm.
		long startTime = System.nanoTime();
		ArrayList<Double> purchases = SubsetSum.findSubset(shoppingList, budget);
		long estimatedTime = System.nanoTime() - startTime;

		// Display the runtime and results.
		System.out.println("\nAlgorithm Elapsed Time: " + TimeConverter.convertTimeToString(estimatedTime));
		System.out.println("Purchased grocery prices are:");
		System.out.println(purchases);

		System.out.println("Done with ShoppingBag.");
	}
}
