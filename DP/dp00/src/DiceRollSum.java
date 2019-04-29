import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DiceRollSum {

    // Runtime: O(n)
    // Space: O(n)
    public static int diceRollSum(int N) {
        if (N == 0) {
            return 1;
        }

        int[] memo = new int[N + 1];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }
        dieRollHelp(N, memo);
        return memo[N];
    }

    private static int dieRollHelp(int N, int[] memo) {
        if (N == 0 || N == 1) {
            return 1;
        }
        else if (N < 0) {
            return 0;
        }

        if (memo[N] != -1) {
            return memo[N];
        }

        int total = 0;
        for (int i = 1; i <= 6; i++) {
            total += dieRollHelp(N - i, memo);
        }

        memo[N] = total;
        return total;
    }


    public static int diceRollSumAlt(int N) {
        HashMap<Integer, ArrayList<ArrayList<Integer>>> memo = new HashMap<>();

        memo.put(0, new ArrayList<>());
        memo.get(0).add(new ArrayList<>());

        for (int i = 1; i <= N; i++) {
            ArrayList<ArrayList<Integer>> prev = memo.get(i-1);
            ArrayList<ArrayList<Integer>> curr = getNewDie(prev);
            curr.addAll(incrementDie(prev));
            curr = removeDuplicates(curr);
            memo.put(i, curr);
        }
        return memo.get(N).size();
    }

    public static ArrayList<ArrayList<Integer>> getNewDie(ArrayList<ArrayList<Integer>> prev) {
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        for (ArrayList<Integer> al : prev) {
            for (int i = 0; i <= al.size(); i++) {
                ArrayList<Integer> newList = copyOf(al);
                newList.add(i, 1);
                out.add(newList);
            }
        }
        return out;
    }

    public static ArrayList<ArrayList<Integer>> incrementDie(ArrayList<ArrayList<Integer>> prev) {
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        for (ArrayList<Integer> al : prev) {
            for (int i = 0; i < al.size(); i++) {
                ArrayList<Integer> newList = copyOf(al);
                newList.set(i, newList.get(i) + 1);
                if(newList.get(i) <= 6)
                    out.add(newList);
            }
        }
        return out;
    }

    public static ArrayList<ArrayList<Integer>> removeDuplicates(ArrayList<ArrayList<Integer>> in) {
        HashSet<Integer> visited = new HashSet<>();
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        for (ArrayList<Integer> al : in) {
            int num = 0;
            for (Integer i : al) {
                num *= 10;
                num += i;
            }
            if (visited.contains(num))
                ;
            else {
                visited.add(num);
                out.add(al);
            }
        }
        return out;
    }

    public static ArrayList<Integer> copyOf(ArrayList<Integer> in) {
        ArrayList<Integer> out = new ArrayList<>();
        for (Integer i : in) {
            out.add(i);
        }
        return out;
    }

}
