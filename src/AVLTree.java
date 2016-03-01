public class AVLTree {
	AVLNode root;
	boolean printFlag;

	public AVLTree() {
		root = null;
		printFlag = false;
	}

	public void setPrintFlag(boolean b) {
		printFlag = b;
	}
	
	public AVLNode getRoot() {
		return root;
	}

	public void insert(int x) {
		root = insert(x, root);
	}

	public boolean search(int x) {
		return search(x, root);
	}

	public void removeRoot() {
		if (root != null)
			root = remove(root.element, root);
	}

	public int getHeight() {
		return height(root);
	}

	/**
	 * Return the height of node t, or -1, if null.
	 */
	private int height(AVLNode t) {
		return t == null ? -1 : t.height;
	}

	/**
	 * Internal method to insert into a subtree.
	 * 
	 * @param x
	 *            the item to insert.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private AVLNode insert(int x, AVLNode t) {
		if (t == null)
			return new AVLNode(x, null, null);
		if (x < t.element)
			t.left = insert(x, t.left);
		else if (x > t.element)
			t.right = insert(x, t.right);
		else
			; // Duplicate; do nothing
		return balance(t);
	}

	private static final int ALLOWED_IMBALANCE = 1;

	// Assume t is either balanced or within one of being balanced
	private AVLNode balance(AVLNode t) {
		if (t == null)
			return t;

		if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
			if (height(t.left.left) >= height(t.left.right)) {
				if (printFlag)
					System.out.println("Single left rotation: " + t.element);
				t = rotateWithLeftChild(t);
			} else {
				if (printFlag)
					System.out.println("Double right-left rotation: "
							+ t.element);
				t = doubleWithLeftChild(t);
			}
		} else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
			if (height(t.right.right) >= height(t.right.left)) {
				if (printFlag)
					System.out.println("Single right rotation: " + t.element);
				t = rotateWithRightChild(t);
			} else {
				if (printFlag)
					System.out.println("Double left-right rotation: "
							+ t.element);
				t = doubleWithRightChild(t);
			}
		}
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}

	/**
	 * Rotate binary tree node with left child. For AVL trees, this is a single
	 * rotation for case 1. Update heights, then return new root.
	 */
	private AVLNode rotateWithLeftChild(AVLNode k2) {
		AVLNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	/**
	 * Rotate binary tree node with right child. For AVL trees, this is a single
	 * rotation for case 1. Update heights, then return new root.
	 */
	private AVLNode rotateWithRightChild(AVLNode k1) {

		AVLNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.left), k1.height) + 1;

		return k2;
	}

	/**
	 * Double rotate binary tree node: first left child with its right child;
	 * then node k3 with new left child. For AVL trees, this is a double
	 * rotation for case 2. Update heights, then return new root.
	 */
	private AVLNode doubleWithLeftChild(AVLNode k3) {
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	/**
	 * Double rotate binary tree node: first right child with its left child;
	 * then node k3 with new right child. For AVL trees, this is a double
	 * rotation for case 2. Update heights, then return new root.
	 */
	private AVLNode doubleWithRightChild(AVLNode k3) {
		k3.right = rotateWithLeftChild(k3.right);
		return rotateWithRightChild(k3);
	}

	/**
	 * Internal method to remove from a subtree.
	 * 
	 * @param x
	 *            the item to remove.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private AVLNode remove(int x, AVLNode t) {
		if (t == null)
			return t; // Item not found; do nothing

		if (x < t.element)
			t.left = remove(x, t.left);
		else if (x > t.element)
			t.right = remove(x, t.right);
		else if (t.left != null && t.right != null) // Two children
		{
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else
			t = (t.left != null) ? t.left : t.right;
		return balance(t);
	}

	private boolean search(int x, AVLNode t) {
		if (t == null)
			return false;
		if (x < t.element)
			return search(x, t.left);
		if (x > t.element)
			return search(x, t.right);
		return true;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	private AVLNode findMin(AVLNode t) {
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		return findMin(t.left);
	}

}
