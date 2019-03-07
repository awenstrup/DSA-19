import java.util.HashMap;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    int height;
    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
        n = super.delete(n, key);
        if (n != null) {
            n.height = height(n);
        }
        return balance(n);
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    TreeNode<T> insert(TreeNode<T> n, T key) {
        n = super.insert(n, key);
        if (n != null) {
            n.height = height(n);
        }
        return balance(n);
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        if (n == null)
            return -1;

        return Math.max(height(n.leftChild), height(n.rightChild)) + 1;
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree. Return the head of the new subtree
    TreeNode<T> balance(TreeNode<T> n) {
        TreeNode<T> out = n;
        if (n == null)
            return null;

        if(size > 0) {
            //System.out.println("current tree is :");
            //printTree(root);
        }

        if (balanceFactor(n) > 1) { //very left heavy
            //System.out.println("bf(" + n.key + ") is " + balanceFactor(n));
            //System.out.println("balancing");
            if (balanceFactor(n.leftChild) < 0) { //left child is right heavy
                n.leftChild = rotateLeft(n.leftChild);
                //System.out.println("after rotation, tree looks like:");
                //printTree(root);
            }
            out = rotateRight(n);
            //System.out.println("after rotation, tree looks like:");
            //printTree(root);
        }

        else if (balanceFactor(n) < -1) { //very right heavy
            //System.out.println("bf(" + n.key + ") is " + balanceFactor(n));
            //System.out.println("balancing");
            if (balanceFactor(n.rightChild) > 0) { //right child is right heavy
                n.rightChild = rotateRight(n.rightChild);
                //System.out.println("after rotation, tree looks like:");
                //printTree(root);
            }
            out = rotateLeft(n);
            //System.out.println("after rotation, tree looks like:");
            //printTree(root);
        }

        if (n.hasLeftChild())
            n.leftChild.height = height(n.leftChild);
        if (n.hasRightChild())
            n.rightChild.height = height(n.rightChild);
        n.height = height(n);

        return out;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    public int balanceFactor(TreeNode<T> n) {
        if (n == null)
            return 0;

        int hl = -1;
        if (n.hasLeftChild())
            hl = n.leftChild.height;
        //System.out.println("__________________");

        //System.out.println("while balancing, lh(" + (Integer)n.key + ") = " + hl);

        int hr = -1;
        if (n.hasRightChild())
            hr = n.rightChild.height;

        //System.out.println("while balancing, rh(" + (Integer)n.key + ") = " + hr);
        //System.out.println("___________");
        return hl - hr;
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        System.out.println("rotating " + Integer.toString((Integer)n.key) + " right");
        TreeNode<T> newTop = n.leftChild;
        TreeNode<T> temp = newTop.rightChild;
        newTop.rightChild = n;
        n.leftChild = temp;
        if (n == root)
            root = newTop;

        return newTop;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        System.out.println("rotating " + Integer.toString((Integer)n.key) + " left");
        TreeNode<T> newTop = n.rightChild;
        TreeNode<T> temp = newTop.leftChild;
        newTop.leftChild = n;
        n.rightChild = temp;
        if (n == root)
            root = newTop;

        if (n != null)
            n.height = height(n);

        if (newTop != null)
            newTop.height = height(newTop);

        System.out.println("new top is : " + (Integer)(newTop.key));
        return newTop;
    }
}