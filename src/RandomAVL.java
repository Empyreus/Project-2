public class RandomAVL {
	AVLTree avl;

	public RandomAVL() {
		avl = new AVLTree();
	}

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

	public static void main(String[] args) {
		RandomAVL ra = new RandomAVL();
		ra.populate();
		ra.remove();
	}
}
