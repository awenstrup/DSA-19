import java.util.Arrays;

public class EditDistance {

    public static int minEditDist(String a, String b) {
        int dp[][] = new int[a.length()+1][b.length()+1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                //base cases
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;

                //dp dependent cases
                else if (a.charAt(i-1) == b.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else {
                    dp[i][j] = 1 + Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1]));
                }

                printDP(dp);
            }
        }
        return dp[a.length()][b.length()];
    }

    public static void printDP(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println();
    }
}


