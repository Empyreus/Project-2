public class AVLNode {
	// Constructors
	AVLNode(int theElement) {
		this(theElement, null, null);
	}

	AVLNode(int theElement, AVLNode lt, AVLNode rt) {
		element = theElement;
		left = lt;
		right = rt;
	}

	public int getHeight() {

		return height;
	}

	public AVLNode getRight() {

		return right;
	}

	public AVLNode getLeft() {

		return left;
	}

	public int getData() {
		return element;
	}

	int element; // The data in the node
	AVLNode left; // Left child
	AVLNode right; // Right child
	int height; // Height
}