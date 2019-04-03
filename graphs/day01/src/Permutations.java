import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        List<Integer> unused = new LinkedList<>();
        for (Integer i : A) {
            unused.add(i);
        }
        List<List<Integer>> permutations = new LinkedList<>();
        permutationHelper(new LinkedList<>(), unused, permutations);
        return permutations;
    }

    public static void permutationHelper(List<Integer> current, List<Integer> unused, List<List<Integer>> permutations) {
        //base case
        if (unused.size() == 0) {
            permutations.add(new LinkedList<>(current));
            System.out.println(permutations);
        }

        //recursive case
        for (int i = 0; i < unused.size(); i++) {
            //LinkedList<Integer> workingCopy = new LinkedList<>(unused);
            Integer item = unused.get(i);
            current.add(item);
            unused.remove(i);
            permutationHelper(current, unused, permutations);
            current.remove(item);
            unused.add(i, item);
        }
    }
}
