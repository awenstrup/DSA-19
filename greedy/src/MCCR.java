import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MCCR {
    private static class EdgeComparator implements Comparator<Edge> {
        public int compare(Edge a, Edge b)
        {
            if (a.weight() < b.weight())
            {
                return -1;
            }
            else if (a.weight() > b.weight())
            {
                return 1;
            }

            return 0;
        }
    }

    public static int MCCR(EdgeWeightedGraph G) {
        HashSet<Integer> inMST = new HashSet<>();
        HashSet<Integer> notInMST = new HashSet<>();

        boolean first = true;
        for (Integer i : G.vertices) {
            if (first) {
                inMST.add(i);
                first = false;
            }
            else
                notInMST.add(i);
        }

        int counter = 0;
        while (!notInMST.isEmpty()) {
            Edge edge = getUsefulEdge(G, inMST, notInMST);
            if (inMST.contains(edge.w())) {
                inMST.add(edge.v());
                notInMST.remove(edge.v());
            }
            else if (inMST.contains(edge.v())) {
                inMST.add(edge.w());
                notInMST.remove(edge.w());
            }
            counter += edge.weight();
        }
        return counter;
    }

    public static Edge getUsefulEdge(EdgeWeightedGraph G, HashSet<Integer> inMST, HashSet<Integer> notInMST) {
        PriorityQueue<Edge> out = new PriorityQueue<>(10, new EdgeComparator());
        for (Integer i : inMST) {
            HashSet<Edge> edges = (HashSet<Edge>)G.edges(i);
            for (Edge e : edges) {
                if (notInMST.contains(e.other(i)))
                    out.offer(e);
            }
        }
        return out.poll();
    }

    }

