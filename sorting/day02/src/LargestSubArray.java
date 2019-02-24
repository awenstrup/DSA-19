import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Arrays;

public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        HashMap<Integer, int[]> map = new HashMap<>();
        int[] temp = {0, 0};
        map.put(0, temp);
        int count = 0;
        int index = 0;


        for (int i : nums) {
            if (i == 1)
                count++;
            else
                count--;

            int k = count;
            int[] v = new int[2];

            int[] def = {index, index};
            int[] curr = map.getOrDefault(count, def);
            v[0] = curr[0];
            v[1] = index;

            System.out.println("entry: " + k + " " + Arrays.toString(v));

            map.put(k, v);

            index++;
        }

        System.out.println();

        Iterator it = map.entrySet().iterator();

        int[] maxRange = new int[2];
        int maxRangeSize = 0;
        int maxCount = 0;

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            int low = ((int[])e.getValue())[0];
                if ((int)e.getKey() != 0)
                    low++;
            int high = ((int[])e.getValue())[1];
            System.out.println("range: " + low + "-" + high);
            if (high - low > maxRangeSize) {
                maxRange[0] = low;
                maxRange[1] = high;
                maxRangeSize = high - low;
                maxCount = (int)e.getKey();
            }
        }


        System.out.println(maxCount + " " + Arrays.toString(maxRange));
        return maxRange;
    }
}
/*
    int[] count = new int[nums.length];
    int prev = 0;

        for (int i : nums) {
                if (i == 1)
                count[i] = prev + 1;
                else
                count[i] = prev - 1;
                }
*/