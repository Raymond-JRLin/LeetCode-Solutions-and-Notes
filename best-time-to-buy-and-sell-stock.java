// 121. Best Time to Buy and Sell Stock
// DescriptionHintsSubmissionsDiscussSolution
// Say you have an array for which the ith element is the price of a given stock on day i.
//
// If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
//
// Note that you cannot sell a stock before you buy one.
//
// Example 1:
//
// Input: [7,1,5,3,6,4]
// Output: 5
// Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
//              Not 7-1 = 6, as selling price needs to be larger than buying price.
// Example 2:
//
// Input: [7,6,4,3,1]
// Output: 0
// Explanation: In this case, no transaction is done, i.e. max profit = 0.


class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // return my_try(prices); // 每次都去当前 price 后面找更大的值， TLE， we can update final result as traversing

        return method1(prices);
    }

    private int method1(int[] prices) {
        int profit = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return profit;
    }

    // private int my_try(int[] prices) {
    //     int n = prices.length;
    //     int sum = Integer.MIN_VALUE;
    //     for (int i = 0; i < n; i++) {
    //         for (int j = i + 1; j < n; j++) {
    //             if (prices[j] > prices[i]) {
    //                 sum = Math.max(sum, prices[j] - prices[i]);
    //             }
    //         }
    //     }
    //     if (sum == Integer.MIN_VALUE) {
    //         return 0;
    //     } else {
    //         return sum;
    //     }
    // }
}
