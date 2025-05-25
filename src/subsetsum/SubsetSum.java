package subsetsum;

import java.util.ArrayList;

/**
 * Provides a static method for solving the subset-sum problem for grocery prices.
 * Given a list of prices and a budget, it returns a subset whose total is as close
 * as possible to the budget without exceeding it.
 *
 * Pseudocode for the algorithm:
 *   function findSubset(prices, budget):
 *       best = empty list
 *       call findSubsetHelper(prices, budget, index=0, current empty list, best)
 *       return best
 *
 *   function findSubsetHelper(prices, budget, index, current, best):
 *       currentSum = sum(current)
 *       bestSum = sum(best)
 *       if currentSum <= budget and (budget - currentSum) < (budget - bestSum):
 *           update best to be a copy of current
 *       if index equals the length of prices OR currentSum exceeds budget:
 *           return
 *       Add prices[index] to current and recurse (include current element)
 *       Remove last element from current (backtrack)
 *       Recurse without including prices[index] (exclude current element)
 */
public class SubsetSum {

    /**
     * Finds the subset of prices that comes closest to the budget without going over.
     * @param prices List of grocery prices.
     * @param budget Target budget.
     * @return A list of prices representing the selected subset.
     */
    public static ArrayList<Double> findSubset(ArrayList<Double> prices, double budget) {
        ArrayList<Double> best = new ArrayList<>();
        findSubsetHelper(prices, budget, 0, new ArrayList<Double>(), best);
        return best;
    }

    /**
     * Recursive helper method to try including and excluding each price.
     *
     * @param prices  List of prices.
     * @param budget  Target budget.
     * @param index   Current index in the list.
     * @param current Current subset of prices.
     * @param best    Best subset found so far.
     */
    private static void findSubsetHelper(ArrayList<Double> prices, double budget, int index,
                                         ArrayList<Double> current, ArrayList<Double> best) {
        double currentSum = sum(current);
        double bestSum = sum(best);

        // If current subset is within budget and closer to the target than best so far, update best.
        if (currentSum <= budget && (budget - currentSum) < (budget - bestSum)) {
            best.clear();
            best.addAll(current);
        }

        // If we've reached the end of the list or the current sum exceeds budget, stop recursion.
        if (index == prices.size() || currentSum > budget)
            return;

        // Include the current element and recurse.
        current.add(prices.get(index));
        findSubsetHelper(prices, budget, index + 1, current, best);
        // Backtrack: remove the recently added element.
        current.remove(current.size() - 1);

        // Exclude the current element and recurse.
        findSubsetHelper(prices, budget, index + 1, current, best);
    }

    /**
     * Calculates the sum of the elements in the list.
     *
     * @param list List of Double values.
     * @return Sum of the list.
     */
    private static double sum(ArrayList<Double> list) {
        double total = 0;
        for (Double d : list)
            total += d;
        return total;
    }
}
