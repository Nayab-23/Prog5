

CS1C Spring 2025 Assignment 5: LazySearchTree with Garbage Collection


========================
How to Build and Run
========================

1. Make sure you have Java (JDK 8 or later) installed.

2. Compile all Java files:
   javac src/lazyTrees/*.java

3. Run the SuperMarket simulation:
   java -cp src lazyTrees.SuperMarket

   - By default, it uses resources/inventory_log.txt.
   - To test other files, edit the TESTFILE variable in SuperMarket.java:
     //final String TESTFILE = "resources/inventory_short.txt";
     //final String TESTFILE = "resources/inventory_invalid_removal.txt";

4. To change the garbage collection threshold, edit the value of
   GARBAGE_COLLECTION_THRESHOLD in SuperMarket.java.

========================
Files
========================

- src/lazyTrees/LazySearchTree.java
  -- Implements a BST with lazy deletion and garbage collection.

- src/lazyTrees/SuperMarket.java
  -- Simulates inventory operations and triggers garbage collection.

- src/lazyTrees/Item.java
  -- Represents inventory items.

- resources/inventory_log.txt
  -- Main test input.

- resources/inventory_short.txt
  -- Short test input.

- resources/inventory_invalid_removal.txt
  -- Test input with invalid removals.

- resources/RUN.txt
  -- Sample output for all test cases and threshold settings.

========================
What to Look For
========================

- Before and after each garbage collection, the inventory state is printed.
- At the end of each run, the final inventory state is shown.
- Changing the threshold demonstrates different garbage collection behaviors.

========================
Contact
========================

For questions, contact [YOUR EMAIL HERE].
