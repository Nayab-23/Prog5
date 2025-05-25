
PROJECT DESCRIPTION:
This project implements a solution to the subset-sum problem using a recursive
backtracking algorithm. The project is divided into two parts:

Part I – Grocery Shopping:
   • Reads a CSV file (e.g., groceries.txt, inputFile01.txt, inputFile02.txt) containing
     grocery item names and prices (only the prices are used).
   • Uses the SubsetSum.findSubset() method to select a subset of prices that best fits a
     user-provided budget without exceeding it.
   • The ShoppingBag class handles file input, prompts for a budget, and outputs the
     selected subset along with the algorithm’s runtime.

Part II – FoothillTunesStore (Extra Credit):
   • Reads a JSON file (resources/music_genre_subset.json) containing song data using
     MillionSongDataSubset.
   • Uses the SubsetSum.findSubsetOfSongs() method to generate a playlist whose total
     duration is as close as possible to a target duration (provided by the user)
     without exceeding it.
   • The FoothillTunesStore class handles user interaction, timing, and displays the
     resulting playlist.

------------------------------------------------------------
ALGORITHM IMPROVEMENTS FOR A LARGER SET:
In Part II, the data set (the song list) is significantly larger than the grocery prices in Part I.
To improve the algorithm’s performance on larger sets, the following enhancements were implemented:
   • Early Pruning: The recursive backtracking immediately terminates further exploration once
     the current cumulative duration exceeds the target, preventing unnecessary computations.
   • Efficient Subset Tracking: The algorithm maintains the best (closest) subset found so far,
     updating it only when a closer match is discovered.
   • Optimized Summation: Java streams are used for summing durations, which improves clarity
     and performance.
These improvements ensure that the algorithm scales efficiently when processing thousands of
song entries, minimizing both memory usage and execution time.



------------------------------------------------------------
TESTING:
Part I – Grocery Shopping:
   • Input files (inputFile01.txt and inputFile02.txt) contain grocery prices.
   • Example test cases:
         – Budget = 0.5 → Expect an empty list (no affordable items).
         – Budget = 16 (or an exact sum from the file) → Expect the complete list.
         – Budget greater than the total (e.g., 2000 or 400) → Expect the complete list of prices.
   • Refer to RUN.txt for sample outputs and runtime details.

Part II – FoothillTunesStore (Extra Credit):
   • The JSON file (music_genre_subset.json) is parsed into an array of SongEntry objects using
     MillionSongDataSubset.
   • The program prompts for a target playlist duration (in seconds) and selects the optimal subset
     of songs using the subset-sum algorithm.
   • Verify that the total duration of the selected songs is as close as possible to the target without
     exceeding it.
   • The elapsed runtime is displayed along with the final playlist output.

------------------------------------------------------------
DOCUMENTATION:
   • Javadocs have been generated from the source code and are available in the docs folder.
