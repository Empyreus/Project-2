
public class BinarySearchTree {
    BSTNode root;
    public BinarySearchTree(){
        root = null;
    }
    public BSTNode getRoot(){
        return root;
    }
    public void insert(int x){
        root = insert(x,root);
    }
    public void setRoot(int x){
        BSTNode temp = new BSTNode(x);
        root = temp;
    }
    public int getHeight(){
        return root.getHeight();
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
    private BSTNode insert(int x, BSTNode t) {
        if (t == null)
            //return new BSTNode(x, null, null);
            setRoot(x);
        if (x < t.element)
            t.left = insert(x, t.left);
        else if (x > t.element)
            t.right = insert(x, t.right);
        else
            ; // Duplicate; do nothing
        return t;
    }
}
