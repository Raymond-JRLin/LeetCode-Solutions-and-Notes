// 877. Stone Game
// User Accepted: 1191
// User Tried: 1716
// Total Accepted: 1238
// Total Submissions: 3234
// Difficulty: Medium
// Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
//
// The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
//
// Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.
//
// Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
//
//
//
// Example 1:
//
// Input: [5,3,4,5]
// Output: true
// Explanation:
// Alex starts first, and can only take the first 5 or the last 5.
// Say he takes the first 5, so that the row becomes [3, 4, 5].
// If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
// If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
// This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
//
//
// Note:
//
// 2 <= piles.length <= 500
// piles.length is even.
// 1 <= piles[i] <= 500
// sum(piles) is odd.


class Solution {
    public boolean stoneGame(int[] piles) {

        // return mytry(piles);

        // return method2(piles);

        return method3(piles);

        // return method4(piles);
    }

    private boolean method4(int[] p) {
        // 1D iteration DP
        int[] dp = Arrays.copyOf(p, p.length);;
        for (int d = 1; d < p.length; d++)
            for (int i = 0; i < p.length - d; i++)
                dp[i] = Math.max(p[i] - dp[i + 1], p[i + d] - dp[i]);
        return dp[0] > 0;
    }

    private boolean method3(int[] p) {
        // 2D iteration DP
        // ref: https://leetcode.com/problems/stone-game/discuss/154610/C++JavaPython-DP-or-Just-return-true
        int n = p.length;
        // definition: dp[i][j] = the biggest number of stones you can get more than opponent picking piles in piles[i] ~ piles[j]
        // You can first pick piles[i] or piles[j].
        // If you pick piles[i], your result will be piles[i] - dp[i + 1][j]
        // If you pick piles[j], your result will be piles[j] - dp[i][j - 1]
        // so: dp[i][j] = max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1])
        int[][] dp  = new int[n][n];
        // initialization
        for (int i = 0; i < n; i++) {
            dp[i][i] = p[i];
        }
        // DP
        // for (int d = 1; d < n; d++) {
        //     for (int i = 0; i < n - d; i++) {
        //         dp[i][i + d] = Math.max(p[i] - dp[i + 1][i + d], p[i + d] - dp[i][i + d - 1]);
        //     }
        // }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(p[i] - dp[i + 1][j], p[j] - dp[i][j - 1]);
            }
        }
        // result
        return dp[0][n - 1] > 0;
    }

    private boolean method2(int[] nums) {
        // 这题确实和 coins 很像， 但有一个特殊的地方， 就是一共有偶数个 pile， 所有的 pile 里的石头加起来是奇数， 所以最终的结果一定不会有 ties。所以可以把所有的石头分成两份： 奇数和偶数 index， 先手可以全取奇数位或偶数位， 总有一个大于另一个， 所以先手必胜
        // ref: https://leetcode.com/problems/stone-game/discuss/154610/C++JavaPython-DP-or-Just-return-true
        return true;
    }

    private boolean mytry(int[] nums) {
        // I think it's just coins problem
        int n = nums.length;
        int[][] f = new int[n][n];
        for (int[] row : f) {
            Arrays.fill(row, -1);
        }

        recursion(nums, f, 0, n - 1);

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return f[0][n - 1] > sum / 2;
    }
    private int recursion(int[] nums, int[][] f, int i, int j) {
        if (i >= nums.length || j < 0 || i > j) {
            return 0;
        }
        if (f[i][j] != -1) {
            return f[i][j];
        }
        if (i == j) {
            return nums[i];
        }
        f[i][j] = Math.max(nums[i] + recursion(nums, f, i + 1, j), nums[j] + recursion(nums, f, i, j - 1));
        return f[i][j];
    }
}
