public class BSTNode {
    int element;
    BSTNode right;
    BSTNode left;
    int height;

    BSTNode(int x) {
        this(x, null, null);
    }

    BSTNode(int x, BSTNode left, BSTNode right) {
        element = x;
        this.left = left;
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public BSTNode getLeft() {
        return left;

    }

    public BSTNode getRight() {
        return right;
    }

    public int data() {
        return element;
    }

}
