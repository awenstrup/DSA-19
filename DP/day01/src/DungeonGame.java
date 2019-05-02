import java.util.Arrays;

public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        int[][] dp = new int[map.length][map[0].length];
        int[][] leftOver = new int[map.length][map[0].length];

        for (int i = map.length - 1; i >= 0; i--) {
            for (int j = map[0].length - 1; j >= 0; j--) {
                //base case
                if (i == map.length - 1 && j == map[0].length - 1) {
                    dp[i][j] = 1;
                    if (map[i][j] <= 0)
                        dp[i][j] -= map[i][j];
                }

                else if (i == map.length - 1) {
                    dp[i][j] = dp[i][j+1] - map[i][j];
                    if (dp[i][j] < 1)
                        dp[i][j] = 1;
                }

                else if (j == map[0].length - 1) {
                    dp[i][j] = dp[i+1][j] - map[i][j];
                    if (dp[i][j] < 1)
                        dp[i][j] = 1;
                }

                else {
                    dp[i][j] = Math.min(dp[i+1][j], dp[i][j+1]) - map[i][j];
                    if (dp[i][j] < 1)
                        dp[i][j] = 1;
                }
            }
        }
        printDP(dp);
        return dp[0][0];
    }

    public static void printDP(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println();
    }

    public static int burnLives(int leftover, int burn, int[][] map) {
        if (leftover >= burn)
            return burn;
        else
            return leftover;
    }
}
