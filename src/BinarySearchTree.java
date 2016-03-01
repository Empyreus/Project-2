public class BinarySearchTree {
    BSTNode<Integer> root;
    boolean printFlag;

    public BinarySearchTree() {
        root = null;
        printFlag = false;
    }

    public BSTNode<Integer> getRoot() {
        return root;
    }

    public void insert(int x) {
        root = insert(x, root);
    }

    public void setRoot(int x) {
        BSTNode<Integer> temp = new BSTNode<Integer>(x);
        root = temp;
    }

    public boolean search(int x) {
        return search(x, root);
    }

    public int getHeight() {
        return root.getHeight();
    }
    public void setHeight(){
        root.setHeight();
    }

    public void setPrintFlag(boolean x) {
        printFlag = x;
    }

    public void removeRoot() {
        if (root != null)
            root = remove(root.element, root);
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
    private BSTNode<Integer> insert(int x, BSTNode<Integer> t) {
        if (t == null)
            return new BSTNode<Integer>(x, null, null);
        if (x < t.element)
            t.left = insert(x, t.left);
        else if (x > t.element)
            t.right = insert(x, t.right);
        else
            ; // Duplicate; do nothing
        return t;
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
    private BSTNode<Integer> remove(int x, BSTNode<Integer> t) {
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
        return t;
    }

    private boolean search(int x, BSTNode<Integer> t) {
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
    private BSTNode<Integer> findMin(BSTNode<Integer> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }
}
