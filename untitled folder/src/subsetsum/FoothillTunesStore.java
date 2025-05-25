package subsetsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import cs1c.MillionSongDataSubset;
import cs1c.SongEntry;
import cs1c.SubsetSum;
import cs1c.TimeConverter;

/**
 * FoothillTunesStore uses the subset-sum algorithm to create a playlist of songs
 * that best matches a target duration input by the user.
 * It reads a JSON file containing song data using MillionSongDataSubset,
 * then calls the SubsetSum.findSubsetOfSongs() method to generate a playlist whose total
 * duration is as close as possible to the target without exceeding it.
 *
 * Part II: Extra Credit
 */
public class FoothillTunesStore {

    public static void main(String[] args) {
        // Use a relative file path for the JSON file containing song data.
        final String FILEPATH = "resources/music_genre_subset.json";

        // Parse the JSON input file using MillionSongDataSubset.
        MillionSongDataSubset msd = new MillionSongDataSubset(FILEPATH);
        SongEntry[] allSongs = msd.getArrayOfSongs();

        // Convert the array of songs to an ArrayList for processing.
        ArrayList<SongEntry> songList = new ArrayList<>(Arrays.asList(allSongs));
        System.out.printf("Welcome! We have over %d songs in FoothillTunesStore!\n", songList.size());

        // Prompt the user for the desired playlist duration (in seconds).
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nEnter the duration of your play list (in seconds):");
        while (!keyboard.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a numeric value for duration.");
            keyboard.next();
        }
        double duration = keyboard.nextDouble();

        // Capture runtime for the subset-sum algorithm.
        long startTime = System.nanoTime();
        ArrayList<SongEntry> playlist = SubsetSum.findSubsetOfSongs(songList, duration);
        long estimatedTime = System.nanoTime() - startTime;

        // Report the elapsed runtime.
        System.out.println("\nAlgorithm Elapsed Time: " + TimeConverter.convertTimeToString(estimatedTime));

        // Display the resulting playlist.
        System.out.println("Songs in play list:");
        System.out.println(playlist);

        System.err.flush();
        System.out.println("Done with FoothillTunesStore.");
    }
}
