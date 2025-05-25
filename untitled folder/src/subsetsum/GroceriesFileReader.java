package subsetsum;

import java.io.*;
import java.util.*;

/**
 * GroceriesFileReader reads a CSV file containing grocery items and their prices.
 * Each line in the file is of the form: itemName,price.
 * It returns an ArrayList of Double values representing the prices.
 * All exceptions are handled within the method.
 *
 * Usage:
 *   ArrayList<Double> prices = new GroceriesFileReader().readFile("resources/groceries.txt");
 */
public class GroceriesFileReader {
    /**
     * Reads the grocery file and extracts the prices.
     * @param filename The relative path to the CSV file.
     * @return An ArrayList of Double values containing the prices.
     */
    public ArrayList<Double> readFile(String filename) {
        ArrayList<Double> prices = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // Process each line by splitting on the comma.
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    // Parse the price and add it to the list.
                    prices.add(Double.parseDouble(parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return prices;
    }
}
