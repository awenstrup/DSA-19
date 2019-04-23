import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;


public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        Arrays.sort(occupied);
        System.out.println(Arrays.toString(occupied));

        PriorityQueue<Integer> pq = new PriorityQueue<>(10, Collections.reverseOrder());
        for (int i = 0; i < occupied.length - 1; i++) {
            pq.add(occupied[i+1] - occupied[i] - 1);
        }

        int gapsRemoved = 0;

        System.out.println("--------------");
        for (int i = 0; i < M - 1; i++) {
            if (pq.isEmpty())
                ;
            else {
                int temp = pq.poll();
                gapsRemoved += temp;
                System.out.println(temp);
            }
        }
        System.out.println("-----------------");

        System.out.println(1 + occupied[occupied.length-1] - occupied[0] - gapsRemoved);
        return 1 + occupied[occupied.length-1] - occupied[0] - gapsRemoved;
    }
}