package range_finding;

public class RangeNode<T extends Comparable<T>> {
    public T key;
    public RangeNode<T> leftChild;
    public RangeNode<T> rightChild;
    public int height;

    public int decendants;

    public RangeNode<T> maxD;
    public RangeNode<T> minD;

    public RangeNode(T key) {
        this(key, 0);
    }

    public RangeNode(T key, int height) {
        this.key = key;
        this.leftChild = null;
        this.rightChild = null;
        this.height = height;

        decendants = 0;

        this.maxD = null;
        this.minD = null;
    }

    public boolean hasLeftChild() {
        return this.leftChild != null;
    }

    public boolean hasRightChild() {
        return this.rightChild != null;
    }

    public boolean isLeaf() {
        return !this.hasLeftChild() && !this.hasRightChild();
    }

    @Override
    public boolean equals(Object other) {
        RangeNode otherNode = (RangeNode) other;
        return otherNode.key.equals(this.key);
    }
}