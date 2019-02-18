import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(n lgn)
     * Worst-case runtime: O(n lgn)
     * Average-case runtime: O(n lgn)
     *
     * Space-complexity:
     */

    @Override
    public int[] sort(int[] array) {

        if(array.length <= INSERTION_THRESHOLD) {
            System.out.println("pre-insertion: " + Arrays.toString(array));
            InsertionSort sort = new InsertionSort();
            int[] s = sort.sort(array);
            System.out.println("post-insertion: " + Arrays.toString(s));
            return s;
        }


        int[] out;
        int al = array.length;

        if (al == 1)
            return array;

        int[] a = Arrays.copyOfRange(array, 0, (al)/2);
        int[] b = Arrays.copyOfRange(array,  (al)/2, al);

        int[] orig = new int[al];
        for(int i = 0; i < a.length; i++) {
            orig[i] = a[i];
        }
        for(int i = 0; i < b.length; i++) {
            orig[i + a.length] = b[i];
        }

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        a = sort(a);
        b = sort(b);

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        out = merge(a, b);

        return out;

    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int[] out = new int[a.length + b.length];
        System.out.println("a:" + Arrays.toString(a));
        System.out.println("b:" + Arrays.toString(b));
        int aCount = 0;
        int al = a.length;
        int bCount = 0;
        int bl = b.length;

        for (int i = 0; i < a.length + b.length; i++) {
            if (aCount == al) {
                out[i] = b[bCount];
                bCount++;
            }
            else if (bCount == bl) {
                out[i] = a[aCount];
                aCount++;
            }
            else if (a[aCount] < b[bCount]) {
                out[i] = a[aCount];
                aCount++;
            }
            else if (b[bCount] <= a[aCount]){
                out[i] = b[bCount];
                bCount++;
            }
        }

        System.out.println("sorted: " + Arrays.toString(out));
        return out;
    }

}
