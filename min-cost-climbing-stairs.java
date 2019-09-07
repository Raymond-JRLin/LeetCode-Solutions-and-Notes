// 746. Min Cost Climbing Stairs
// DescriptionHintsSubmissionsDiscussSolution
// On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
//
// Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
//
// Example 1:
// Input: cost = [10, 15, 20]
// Output: 15
// Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
// Example 2:
// Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
// Output: 6
// Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
// Note:
// cost will have a length in the range [2, 1000].
// Every cost[i] will be an integer in the range [0, 999].
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }

        return my_try(cost);
    }

    private int my_try(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        // initialization
        dp[0] = 0;
        dp[1] = cost[0];
        dp[2] = Math.min(dp[1] + cost[1], cost[1]);
        // DP
        for (int i = 3; i < n + 1; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i - 1];
        }
        return Math.min(dp[n - 1], dp[n]); // what given is cost, so we
    }
}
