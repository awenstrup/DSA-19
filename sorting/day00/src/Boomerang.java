import java.util.*;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        ArrayList<int[]> l = new ArrayList<>();
        int boomerangs = 0;

        for (int i = 0; i < points.length; i++) {
            l.add(points[i]);
        }

        HashMap<Integer, Integer> dists = new HashMap<>();
        for (int[] p1 : l) {
            dists.clear();
            for (int[] p2 : l) {
                Integer k = (int)(Math.pow(p2[0] - p1[0], 2) + Math.pow(p2[1] - p1[1], 2));



                if(k != 0) {
                    Integer count = dists.getOrDefault(k, 0) + 1;
                    dists.put(k, count);
                }
            }

            //technique to iterate through HashMap from https://www.baeldung.com/java-iterate-map
            Iterator<Map.Entry<Integer, Integer>> iterator = dists.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                boomerangs += (entry.getValue() * (entry.getValue() - 1));
                System.out.println((entry.getValue() * (entry.getValue() - 1)));
            }
        }
        return boomerangs;
    }

}

