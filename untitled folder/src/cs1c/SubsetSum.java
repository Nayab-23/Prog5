package cs1c;

import java.util.ArrayList;

public class SubsetSum {
    public static ArrayList<Double> findSubset(ArrayList<Double> prices, double budget) {
        ArrayList<Double> best = new ArrayList<>();
        findSubsetHelper(prices, budget, 0, new ArrayList<>(), best);
        return best;
    }

    private static void findSubsetHelper(ArrayList<Double> prices, double budget, int index,
                                         ArrayList<Double> current, ArrayList<Double> best) {
        double currentSum = sum(current);
        double bestSum = sum(best);

        if (currentSum <= budget && (budget - currentSum) < (budget - bestSum)) {
            best.clear();
            best.addAll(current);
        }

        if (index == prices.size() || currentSum > budget) return;

        current.add(prices.get(index));
        findSubsetHelper(prices, budget, index + 1, current, best);
        current.remove(current.size() - 1);

        findSubsetHelper(prices, budget, index + 1, current, best);
    }

    private static double sum(ArrayList<Double> list) {
        return list.stream().mapToDouble(Double::doubleValue).sum();
    }

    public static ArrayList<SongEntry> findSubsetOfSongs(ArrayList<SongEntry> songs, double duration) {
        ArrayList<SongEntry> best = new ArrayList<>();
        findSubsetSongsHelper(songs, duration, 0, new ArrayList<>(), best);
        return best;
    }

    private static void findSubsetSongsHelper(ArrayList<SongEntry> songs, double duration, int index,
                                              ArrayList<SongEntry> current, ArrayList<SongEntry> best) {
        double currentSum = current.stream().mapToDouble(SongEntry::getDuration).sum();
        double bestSum = best.stream().mapToDouble(SongEntry::getDuration).sum();

        if (currentSum <= duration && (duration - currentSum) < (duration - bestSum)) {
            best.clear();
            best.addAll(current);
        }

        if (index == songs.size() || currentSum > duration) return;

        current.add(songs.get(index));
        findSubsetSongsHelper(songs, duration, index + 1, current, best);
        current.remove(current.size() - 1);

        findSubsetSongsHelper(songs, duration, index + 1, current, best);
    }
}