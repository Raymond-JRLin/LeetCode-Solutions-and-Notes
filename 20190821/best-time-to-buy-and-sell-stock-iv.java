// 188. Best Time to Buy and Sell Stock IV
// DescriptionHintsSubmissionsDiscussSolution
//
// Say you have an array for which the i-th element is the price of a given stock on day i.
//
// Design an algorithm to find the maximum profit. You may complete at most k transactions.
//
// Note:
// You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
//
// Example 1:
//
// Input: [2,4,1], k = 2
// Output: 2
// Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
//
// Example 2:
//
// Input: [3,2,6,5,0,3], k = 2
// Output: 7
// Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
//              Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        return method1(prices, k);
    }

    // 具体更多做法参见 III， 我在 III 中即是按照 k 来做的
    // 这里注意一点就是 当 k >= n / 2 的时候， 我们可以 make maximum number of transactions， 即可以每天都买都卖去获取最大利润
    // 不直接算这种情况会 TLE

    private int method1(int[] prices, int times) {
        int n = prices.length;
        if (times >= n / 2) {
            return greedy(prices);
        }
        // definition
        int[][] f = new int[times + 1][n];
        // init
        // for (int i = 0; i < n; i++) {
        //     f[0][i] = 0;
        // }
        // DP
        for (int k = 1; k <= times; k++) {
            int max = 0 - prices[0];
            for (int i = 1; i < n; i++) {
                max = Math.max(max, f[k - 1][i - 1] - prices[i]);
                f[k][i] = Math.max(f[k][i - 1], prices[i] + max);
            }
        }
        // result
        return f[times][n - 1];
    }
    private int greedy(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                result += prices[i] - prices[i - 1];
            }
        }
        return result;
    }
}
