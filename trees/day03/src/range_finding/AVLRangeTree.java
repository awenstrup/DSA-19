package range_finding;


import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;

public class AVLRangeTree extends BinarySearchTree<Integer> {


    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> delete(RangeNode<Integer> n, Integer key) {
        n = super.delete(n, key);
        if (n != null) {

            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));

            n.decendants = getDecendants(n.leftChild) + getDecendants(n.rightChild) + 1;
            n.minD = getMinOfSubtree(n);
            n.maxD = getMaxOfSubtree(n);

            return balance(n);
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> insert(RangeNode<Integer> n, Integer key) {
        n = super.insert(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            n.decendants = getDecendants(n.leftChild) + getDecendants(n.rightChild) + 1;
            n.minD = getMinOfSubtree(n);
            n.maxD = getMaxOfSubtree(n);
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    RangeNode<Integer> deleteMin(RangeNode<Integer> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            n.decendants = getDecendants(n.leftChild) + getDecendants(n.rightChild) + 1;
            n.minD = getMinOfSubtree(n);
            n.maxD = getMaxOfSubtree(n);
            return balance(n);
        }
        return null;
    }

    /**
     * Find a key's node, returning the node just above it if is not in the tree
     */
    public RangeNode<Integer> findRoundUp(RangeNode<Integer> n, Integer key) {
        return null;
    }

    public int plinkoLeft(RangeNode<Integer> n, int min, int max, HashSet<RangeNode<Integer>> set) {
        if (n == null)
            return 0;

        //System.out.println("Node: " + n.key);
        set.add(n);

        if (n.key < min) {
            //System.out.println("Too small, cutting right");
            if (n.leftChild == null)
                return -1 + plinkoLeft(n.rightChild, min, max, set);
            else
                return -1 - n.leftChild.decendants + plinkoLeft(n.rightChild, min, max, set);
        }

        else if (n.key > max) {
            //System.out.println("Too big, cutting left");
            if (n.rightChild == null)
                return -1 + plinkoLeft(n.leftChild, min, max, set);
            else
                return -1 - n.rightChild.decendants + plinkoLeft(n.leftChild, min, max, set);
        }

        else {
            //System.out.println("Just right, lean left");
            return plinkoLeft(n.leftChild, min, max, set);
        }
    }

    public int plinkoRight(RangeNode<Integer> n, int min, int max, HashSet<RangeNode<Integer>> set) {
        if (n == null)
            return 0;

        //System.out.println("Node: " + n.key);

        if (n.key < min) {
            //System.out.println("Too small, cutting right");
            if (n.leftChild == null) {
                if (set.contains(n))
                    return plinkoRight(n.rightChild, min, max, set);
                return -1 + plinkoRight(n.rightChild, min, max, set);
            }
            else {
                if (set.contains(n))
                    return plinkoRight(n.rightChild, min, max, set);
                return -1 - n.leftChild.decendants + plinkoRight(n.rightChild, min, max, set);
            }
        }

        else if (n.key > max) {
            //System.out.println("Too big, cutting left");
            if (n.rightChild == null) {
                if (set.contains(n))
                    return plinkoRight(n.leftChild, min, max, set);
                return -1 + plinkoRight(n.leftChild, min, max, set);
            }
            else {
                if (set.contains(n))
                    return plinkoRight(n.leftChild, min, max, set);
                return -1 - n.rightChild.decendants + plinkoRight(n.leftChild, min, max, set);
            }
        }

        else {
            //System.out.println("Just right, lean right");
            return plinkoRight(n.rightChild, min, max, set);
        }
    }

    public RangeNode<Integer> getMaxOfSubtree(RangeNode<Integer> n) {
        while (n.hasRightChild())
            n = n.rightChild;
        return n;
    }

    public RangeNode<Integer> getMinOfSubtree(RangeNode<Integer> n) {
        while (n.hasLeftChild())
            n = n.leftChild;
        return n;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(RangeNode x) {
        if (x == null) return -1;
        return x.height;
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree.
    RangeNode<Integer> balance(RangeNode<Integer> x) {
        if (balanceFactor(x) > 1) {
            if (balanceFactor(x.rightChild) < 0) {
                x.rightChild = rotateRight(x.rightChild);
            }
            x = rotateLeft(x);
        } else if (balanceFactor(x) < -1) {
            if (balanceFactor(x.leftChild) > 0) {
                x.leftChild = rotateLeft(x.leftChild);
            }
            x = rotateRight(x);
        }
        return x;
    }

    public int getDecendants(RangeNode<Integer> n) {
        if (n == null)
            return 0;
        return n.decendants;
    }

    public boolean isInRange(RangeNode<Integer> n, int min, int max) {
        if (n.key >= min && n.key <= max) {
            //System.out.println(n.key + " is in the range " + min + "-" + max);
            return true;
        }
        return false;
    }

    public void limitedIOT(RangeNode<Integer> n, List<Integer> list, int min, int max) {
        if (n != null) {
            if (n.maxD.key > min)
                limitedIOT(n.leftChild, list, min, max);
            if (isInRange(n, min, max))
                list.add(n.key);
            if (n.minD.key < max)
                limitedIOT(n.rightChild, list, min, max);
        }
    }

    // Return all keys that are between [lo, hi] (inclusive).
    // TODO: runtime = O(?)
    public List<Integer> rangeIndex(int lo, int hi) {
        // TODO
        List<Integer> l = new LinkedList<>();
        limitedIOT(root, l, lo, hi);
        return l;
    }

    // return the number of keys between [lo, hi], inclusive
    // TODO: runtime = O(lgn)
    public int rangeCount(int lo, int hi) {
        if (root == null)
            return 0;
        int sum = root.decendants;
        //System.out.println("Tree size: " + sum);

        HashSet<RangeNode<Integer>> visited  = new HashSet<>();

        if (root.key < lo || root.key > hi) {
            //System.out.println("Root not in range");
        }

        sum += plinkoLeft(root, lo, hi, visited);
        //System.out.println("Sum after left plinko: " + sum);

        sum += plinkoRight(root, lo, hi, visited);
        //System.out.println("Sum after both plinkos: " + sum);
        return sum;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(RangeNode x) {
        return height(x.rightChild) - height(x.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateRight(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.leftChild;
        x.leftChild = y.rightChild;
        y.rightChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));

        x.decendants = getDecendants(x.leftChild) + getDecendants(x.rightChild) + 1;
        y.decendants = getDecendants(y.leftChild) + getDecendants(y.rightChild) + 1;

        x.maxD = getMaxOfSubtree(x);
        y.maxD = getMaxOfSubtree(y);

        x.minD = getMinOfSubtree(x);
        y.minD = getMinOfSubtree(y);

        return y;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateLeft(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.rightChild;
        x.rightChild = y.leftChild;
        y.leftChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));

        x.decendants = getDecendants(x.leftChild) + getDecendants(x.rightChild) + 1;
        y.decendants = getDecendants(y.leftChild) + getDecendants(y.rightChild) + 1;

        x.maxD = getMaxOfSubtree(x);
        y.maxD = getMaxOfSubtree(y);

        x.minD = getMinOfSubtree(x);
        y.minD = getMinOfSubtree(y);

        return y;
    }
}
