public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        if(leftChild(i) < size) {
            int p = heap[i];
            int c1 = heap[leftChild(i)];
            int c2;

            int max = c1;
            int maxI = leftChild(i);

            int temp;

            if (rightChild(i) < size) {
                c2 = heap[rightChild(i)];
                if (c2 > max) {
                    max = c2;
                    maxI = rightChild(i);
                }
            }

            if (p > max)
                ;
            else {
                temp = p;
                heap[i] = max;
                heap[maxI] = temp;
                sink(maxI);
            }


        }
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime: O(n)
     * Worst-case runtime: O(nlgn)
     * Average-case runtime: (nlgn)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        for (int i=size-1; i>0; i--) {
            int temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;

            size--;

            sink(0);
        }

        return heap;
    }
}
