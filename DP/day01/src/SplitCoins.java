import java.util.HashMap;

public class SplitCoins {

    public static int splitCoins(int[] coins) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        int i = coins.length;
        int sum = 0;

        for (int j = 0; j < i; j++) {
            sum += coins[j];
        }

        return recurse(coins, i, 0, sum, dp);

    }

    private static int recurse(int[] coins, int i, int tempSum, int sum, HashMap<Integer, Integer> dp) {
        if (dp.containsKey(tempSum)) {
            return dp.get(tempSum);
        }

        else if (i == 0) {
            int temp = Math.abs((sum - tempSum) - tempSum);
            dp.put(tempSum, temp);
            return temp;
        }

        return Math.min(recurse(coins, i - 1, tempSum + coins[i - 1], sum, dp), recurse(coins, i - 1, tempSum, sum, dp));
    }
}
