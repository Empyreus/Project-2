import java.util.Random;

public class RandomAVL {
	AVLTree avl;

	public RandomAVL(boolean printFlag) {
		avl = new AVLTree();
		avl.setPrintFlag(printFlag);
	}

	/*
	 * Populate tree with 35 random integers range : 10 -99
	 */
	public void populate() {
		final int number = 35, min = 10, max = 99;
		final int mFactor = (max - min + 1);
		for (int i = 0; i < number; i++) {
			int rn = (int) (Math.random() * mFactor + min);
			avl.insert(rn);
			printTree("Tree after inserting random number: " + rn);
		}
	}

	public void remove() {
		while (avl.getHeight() >= 0) {
			avl.removeRoot();
			printTree("Tree after deleting root:");
		}
	}

	public void printTree(String label) {
		TreePrinterAVL tp = new TreePrinterAVL(avl);
		tp.print(label);
	}

	/* Generate random numbers use seed to get same set of numbers
	 * 
	 */
	public static int[] generateRandomInt(int num, long seed) {
		int[] numbers = new int[num];
		Random rg = new Random(seed);
		for (int i = 0; i < num; ++i) {
			numbers[i] = rg.nextInt();
		}
		return numbers;
	}

	public void performInsertion(int[] numbers) {
		int len = numbers.length;
		for (int i = 0; i < len; ++i) {
			avl.insert(numbers[i]);
		}
	}

	public void performSearch(int[] numbers) {
		int len = numbers.length;
		for (int i = 0; i < len; ++i) {
			avl.search(numbers[i]);
		}
	}

	public void performMixture(int[] insertNumbers, int[] searchNumbers) {
		performInsertion(insertNumbers);
		performSearch(searchNumbers);
	}

	public static void main(String[] args) {
		boolean printFlag = true;
		RandomAVL ra = new RandomAVL(printFlag);
		// Part 1

		ra.populate();
		ra.remove();

		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");

		// Part2
		printFlag = false;
		final int k = 5000;
		long seed = k; // Seed is same as number of random integers to generate
		final int[] kNumbers = generateRandomInt(k, seed); 
		int num = 1000;
		for (int i = 0; i < 4; ++i) {
			System.out.println("");
			System.out.println("");
			seed = num; // Seed as number
			int[] numbers = generateRandomInt(num, seed);
			RandomAVL test_ra = new RandomAVL(printFlag);
			
			long start = System.nanoTime();
			test_ra.performInsertion(numbers);
			long end = System.nanoTime();
			System.out.println("Tavl(" + num + ") = " + (end - start)
					+ " nanoseconds");

			// Search
			start = System.nanoTime();
			test_ra.performSearch(kNumbers);
			end = System.nanoTime();
			System.out.println("Tavl_search(" + num + "," + k + ") = "
					+ (end - start) + " nanoseconds");

			// Mixture of insertions and searches.
			int insertFactor = 1 + i;
			int searchFactor = 1;

			int numInsertions = insertFactor * k;
			int numSearches = searchFactor * k;
			seed = numInsertions;
			int[] insertionNumbers = generateRandomInt(numInsertions, seed);
			seed = numSearches;
			int[] searchNumbers = generateRandomInt(numSearches, seed);

			start = System.nanoTime();
			test_ra.performMixture(insertionNumbers, searchNumbers);
			end = System.nanoTime();
			System.out.println("Insertion:Search = " + insertFactor + ":"
					+ searchFactor);
			System.out.println("Tavl_mixture(" + num + "," + numInsertions
					+ "," + numSearches + ") = " + (end - start)
					+ " nanoseconds");
			num = num * 10;
		}
	}
}
