// 309. Best Time to Buy and Sell Stock with Cooldown
// DescriptionHintsSubmissionsDiscussSolution
//
// Say you have an array for which the ith element is the price of a given stock on day i.
//
// Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
//
//     You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
//     After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
//
// Example:
//
// Input: [1,2,3,0,2]
// Output: 3
// Explanation: transactions = [buy, sell, cooldown, buy, sell]
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        return mytry(prices);
    }

    private int mytry(int[] prices) {
        int n = prices.length;
        // definition: f[i][j], j = 0, 1, 2 for buy, sell, rest
        int[][] f = new int[n][3]; // <day, status of stock>
        // initialization
        f[0][0] = -prices[0]; // buy
        // DP
        for (int i = 1; i < n; i++) {
            // buy: 前一天不能是 sell， 所以维持之前买的， 或者 cooldown 之后重新买
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            // sell: must buy first, 所以前一天不能是 cooldown， 因为 cooldown 意味着前 2 天已经卖掉了
            f[i][1] = Math.max(f[i - 1][0] + prices[i], f[i - 1][1]);
            // cooldown: 前一天是怎样都行， 维持现状
            // 其实这里得保证不能是 [buy -> rest -> buy]， 实际上一定是 buy[i] <= rest[i]， 所以 rest 前一个一定选不到 buy， 也就实际上避免了这种情况。 更实际上， 下面这个一定会选择 f[i - 1][1] 因为 rest[i] <= sell[i]
            f[i][2] = Math.max(f[i - 1][0], Math.max(f[i - 1][1], f[i - 1][2]));
        }
        // result
        return Math.max(f[n - 1][0], Math.max(f[n - 1][1], f[n - 1][2]));
    }
}
