// 122. Best Time to Buy and Sell Stock II
// DescriptionHintsSubmissionsDiscussSolution
// Say you have an array for which the ith element is the price of a given stock on day i.
//
// Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
//
// Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
//
// Example 1:
//
// Input: [7,1,5,3,6,4]
// Output: 7
// Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
//              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
// Example 2:
//
// Input: [1,2,3,4,5]
// Output: 4
// Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
//              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
//              engaging multiple transactions at the same time. You must sell before buying again.
// Example 3:
//
// Input: [7,6,4,3,1]
// Output: 0
// Explanation: In this case, no transaction is done, i.e. max profit = 0.


class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // 因为可以买卖不限， 并且先卖后买的话， 那就是碰到一个能赚钱的就卖出即可 - greedy
        return method1_greedy(prices);

        // 那如果说更加的说得明白的话， 我们要去找一个 local minimum 然后 在它后面找一个 local maximum， 然后得到 profit， 这样既可以完成， 也可以找到 best time， 同时更加 make sense
        // return method1(prices);

    }
    private int method1_greedy(int[] prices) {
        int sum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                sum += prices[i + 1] - prices[i];
            }
        }
        return sum;
    }

    private int method2(int[] prices) {
        int sum = 0;
        int n = prices.length;
        int i = 0;
        while (i < n - 1) {
            while (i < n - 1 && prices[i + 1] < prices[i]) {
                i++;
            }
            int local_min = prices[i]; // local minimum
            while (i < n - 1 && prices[i + 1] >= prices[i]) {
                i++;
            }
            if (i < n) {
                sum += prices[i] - local_min;
            }
        }
        return sum;
    }
}
