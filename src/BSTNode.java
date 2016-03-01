public class BSTNode<AnyType> {
    int element;
    BSTNode<AnyType> right;
    BSTNode<AnyType> left;
    int height;

    BSTNode(int x) {
        this(x, null, null);
    }

    BSTNode(int x, BSTNode<AnyType> left, BSTNode<AnyType> right) {
        element = x;
        this.left = left;
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public BSTNode<AnyType> getLeft() {
        return left;

    }

    public BSTNode<AnyType> getRight() {
        return right;
    }

    public int data() {
        return element;
    }

}
