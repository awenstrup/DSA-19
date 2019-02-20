import java.lang.reflect.Array;
import java.util.*;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        System.out.println(Arrays.toString(out));
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        PriorityQueue<Integer> above = minPQ();
        PriorityQueue<Integer> below = maxPQ();

        for (int i = 0; i < inputStream.length; i++) {
            int c = inputStream[i];
            if (i % 2 == 0)
                below.offer(c);
            else
                above.offer(c);

            if (!(above.isEmpty() || below.isEmpty()) && below.peek() > above.peek()) {
                int temp = above.poll();
                above.offer(below.poll());
                below.offer(temp);
            }

            double median = 0;

            if (i % 2 == 0)
                median = below.peek();
            else
                median = (double)(below.peek() + above.peek())/2;

            runningMedian[i] = median;

        }
        System.out.println(Arrays.toString(runningMedian));
        return runningMedian;
    }
}

/*
    int count = 0;
    int[] med = {0, 0, 0};
//for odd counts, med[1] = median
//for even counts, (med[1] + med[0])/2 = median

        if (count < inputStream.length) {
        med[0] = inputStream[0];
        runningMedian[count] = med[1];
        count++;
        }

        if (count < inputStream.length) {
        med[1] = inputStream[1];
        runningMedian[count] = med[1] + med[0];
        count++;
        }

        if (count < inputStream.length) {
        med[2] = inputStream[2];
        Arrays.sort(med);
        runningMedian[count] = med[1];
        count++;
        }

        int above = 0; //will always be 1 larger than, or equal to, below
        int below = 0;
        int temp;

        for (int i = count; i < inputStream.length; i++) {
        int c = inputStream[i];
        if (c <= med[0]) {
        if (above > below)
        below++;
        else {
        above++;
        med[2] = med[1];
        med[1] = med[0];
        med[0] = c;
        }
        }
        if (c >= med[2])
        if (above <= below)
        above++;
        else {
        below++;
        med[0] = med[1];
        med[1] = med[2];
        med[2] = c;
        }
        }
        return runningMedian;
        }
*/
