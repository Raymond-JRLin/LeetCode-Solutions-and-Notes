// 123. Best Time to Buy and Sell Stock III
// DescriptionHintsSubmissionsDiscussSolution
//
// Say you have an array for which the ith element is the price of a given stock on day i.
//
// Design an algorithm to find the maximum profit. You may complete at most two transactions.
//
// Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
//
// Example 1:
//
// Input: [3,3,5,0,0,3,1,4]
// Output: 6
// Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//              Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
//
// Example 2:
//
// Input: [1,2,3,4,5]
// Output: 4
// Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
//              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
//              engaging multiple transactions at the same time. You must sell before buying again.
//
// Example 3:
//
// Input: [7,6,4,3,1]
// Output: 0
// Explanation: In this case, no transaction is done, i.e. max profit = 0.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // return method1(prices);

        // return method2(prices, 2);

        // return method2_2(prices, 2);

        // return method3(prices, 2);

        // return method3_2(prices, 2);

        // return method4(prices);

        return method4_2(prices);
    }

    private int method4_2(int[] prices)  {
        int buy1 = Integer.MAX_VALUE;
        int buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        // 这个是另一种理解， 第一次买卖是一样的， 可以认为是“借钱”来买卖
        // 第二次有点不同， 把买卖都当作 profit 来理解， 就是说第二次买花费了当前 price， 那么剩下的 profit 要尽可能的大
        // 第二次卖， 则是第二次买了之后的剩下的 profit 加上当前卖出得到的 profit， 即 卖出的价格
        for (int price : prices) {
            buy1 = Math.min(buy1, price);
            sell1 = Math.max(sell1, price - buy1);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }

        return sell2;
    }

    private int method4(int[] prices)  {
        int buy1 = Integer.MAX_VALUE;
        int buy2 = Integer.MAX_VALUE;
        int sell1 = 0, sell2 = 0;
        // 可以这么理解： 第一次买尽可能的让 cost 小， 第一次卖尽可能得到高的 profit
        // 那么第二次， 是在第一次买卖的基础上进行的， 所以实际上真正的花费 - buy2 是 price - 第一次的 profit， 并使其尽可能的小， 然后 profit 仍然是卖的价格 - 买的价格
        for (int price : prices) {
            buy1 = Math.min(buy1, price);
            sell1 = Math.max(sell1, price - buy1);
            buy2 = Math.min(buy2, price - sell1);
            sell2 = Math.max(sell2, price - buy2);
        }

        return sell2;
    }

    private int method3_2(int[] prices, int times) {
        // swap for loop 之后， 在 for k loop 中 i 只依赖于 i - 1 的结果， 和 k 没关系了， 或者说不依赖于 k - 1
        // 所以可以使用 k 大小的滚动数组来记录 i， 即上一轮的结果存在 f[k] 中， 然后下一轮更新它
        // O(K * N) time and O(K) space
        int n = prices.length;
        // definition
        int[] f = new int[times + 1];
        int[] max = new int[times + 1];
        // init
        Arrays.fill(max, 0 - prices[0]);
        // DP
        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= times; k++) {
                max[k] = Math.max(max[k], f[k - 1] - prices[i]);
                f[k] = Math.max(f[k], prices[i] + max[k]);
            }
        }
        // result
        return f[times];
    }

    private int method3(int[] prices, int times) {
        // swap for loop order in method2， 交换之后要用一个数组来记录不同 k 下的 max
        // 因为之前 max 是在同一个 k 下走完所有 i 的过程中的， 那现在是 for i 然后走 k
        // 所以要用数组把相同的 k 放在同一个地方让之后的 i 可以得到相同的 k
        // O(K * N) time and O(K * N + K) space
        int n = prices.length;
        // definition
        int[][] f = new int[times + 1][n];
        int[] max = new int[times + 1];
        // init
        // for (int i = 0; i < n; i++) {
        //     f[0][i] = 0;
        // }
        Arrays.fill(max, 0 - prices[0]);
        // DP
        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= times; k++) {
                max[k] = Math.max(max[k], f[k - 1][i - 1] - prices[i]);
                f[k][i] = Math.max(f[k][i - 1], prices[i] + max[k]);
            }
        }
        // result
        return f[times][n - 1];
    }

    private int method2_2(int[] prices, int times) {
        // 针对 method2 优化一下
        // 其中找 i 之前的 j max 是可以随着计算过程记录的， 因为就是 i 一路走过来的
        // O(K * N) time and O(K * N) space
        int n = prices.length;
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

    private int method2(int[] prices, int times) {
        // ref: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/135704/Detail-explanation-of-DP-solution
        // 每次在当天可以选择 trade 或者不 trade
        // 如果我们用 DP 来表示： f[k, i] := 在第 i 天交易 k 次的 max profit， 那么它应该等于 MAX{在第 i 天不交易， 在第 i 天交易}
        // 不交易就是前一天 i - 1 还有 k 次交易次数的 max profit
        // 交易就是和前面某一天 j 交易的 profit 加上那一天之前 k - 1 次交易的 max profit
        // f[k, i] = MAX{f[k, i - 1], f[k - 1, j - 1] + (prices[i] - prices[j])}, i := [0, n], j := [0, i - 1]
        // 我在计算 j 的 for loop 的时候做了点和 ref 不同的改变
        // O(K * N ^ 2) time and O(K * N) space
        int n = prices.length;
        // definition
        int[][] f = new int[times + 1][n];
        // init
        // for (int i = 0; i < n; i++) {
        //     f[0][i] = 0;
        // }
        // DP
        for (int k = 1; k <= times; k++) {
            for (int i = 1; i < n; i++) {
                // 去前面找一天交易可以得到的 max profit
                int max = 0 - prices[0];
                for (int j = 1; j < i; j++) {
                    max = Math.max(max, f[k - 1][j - 1] - prices[j]);
                }
                f[k][i] = Math.max(f[k][i - 1], prices[i] + max);
            }
        }
        // result
        return f[times][n - 1];
    }

    private int method1(int[] prices) {
        // 允许两次买卖，但同一时间只允许持有一支股票。也就意味着这两次买卖在时间跨度上不能有重叠（当然第一次的卖出时间和第二次的买入时间可以是同一天）。既然不能有重叠可以将整个序列以任意坐标i为分割点，分割成两部分：
        // prices[0 : n-1] => prices[0 : i] + prices[i : n-1]

        // 对于这个特定分割来说，最大收益为两段的最大收益之和。每一段的最大收益当然可以用I的解法来做。而 III 的解一定是对所有 0 <= i <= n-1 的分割的最大收益中取一个最大值。为了增加计算效率，考虑采用 dp 来做 bookkeeping 。目标是对每个坐标i：

        // 1. 计算 A[0 : i] 的收益最大值：用 minPrice 记录i左边的最低价格，用 maxLeftProfit 记录左侧最大收益
        // 2. 计算 A[i : n-1] 的收益最大值：用 maxPrices 记录i右边的最高价格，用 maxRightProfit 记录右侧最大收益。
        // 3. 最后这两个收益之和便是以i为分割的最大收益。将序列从左向右扫一遍可以获取1，从右向左扫一遍可以获取2。相加后取最大值即为答案。
        // ref: http://bangbingsyb.blogspot.com/2014/11/leetcode-best-time-to-buy-and-sell.html

        // O(N) time and space
        int n = prices.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int profit = 0;

        // from left to right
        int leftMin = prices[0];
        for (int i = 1; i < n; i++) {
            profit = Math.max(profit, prices[i] - leftMin);
            left[i] = profit;
            leftMin = Math.min(leftMin, prices[i]);
        }
        // from right to left
        // 我觉得这里自然是两边走的 DP， 也可以这么理解从右边是记录 max： 就是我们没办法从中间某一点 i 出发去知道以这一点为第二段起点的 profix 是多少，
        profit = 0;
        int rightMax = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            profit = Math.max(profit, rightMax - prices[i]);
            right[i] = profit;
            rightMax = Math.max(rightMax, prices[i]);
        }

        // check the total profix from [0, i] & [i, n]
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, left[i] + right[i]);
        }
        return result;
    }
}
