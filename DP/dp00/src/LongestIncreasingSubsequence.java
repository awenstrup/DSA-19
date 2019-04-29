public class LongestIncreasingSubsequence {

    // Runtime: O(n^2)
    // Space: O(n)
    public static int LIS(int[] A) {
        // TODO
        if (A.length == 0) {
            return 0;
        }

        int[] memo = new int[A.length];
        memo[0] = 1;
        int out = 1;

        for (int i = 1; i < memo.length; i++) {
            int max = 0;

            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) {
                    max = Math.max(max, memo[j]);
                }
            }

            memo[i] = max + 1;
            out = Math.max(out, memo[i]);
        }
        return out;
    }
}