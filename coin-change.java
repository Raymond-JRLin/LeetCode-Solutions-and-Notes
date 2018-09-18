// 322. Coin Change

// You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
//
// Example 1:
//
// Input: coins = [1, 2, 5], amount = 11
// Output: 3
// Explanation: 11 = 5 + 5 + 1
// Example 2:
//
// Input: coins = [2], amount = 3
// Output: -1
// Note:
// You may assume that you have an infinite number of each kind of coin.


public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }

        // return mytry(coins, amount);

        // return method2(coins, amount);

        return method3(coins, amount);
    }

    private int method3(int[] coins, int amount) {
        Arrays.sort(coins); // so we can start from the 1st element in coin
        int n = coins.length;
        // definition
        int[] f = new int[amount + 1];
        // initialization: use 0 as "not assigned or cannot arrange coins for that amount"
        // DP
        for (int i = coins[0]; i < amount + 1; i++) {
            for (int coin : coins) {
                if (coin > i) {
                    // coin is already larger than current amount, so we can break directly
                    break;
                }
                if (coin == i) {
                    // if the amount just equal the coin number, then just use 1 such coin
                    f[i] = 1;
                    break;
                }
                if (f[i - coin] == 0) {
                    // (i - coin) is not signed yet or it cannot be made up
                    continue;
                }
                if (f[i] == 0 || f[i - coin] + 1 < f[i]) {
                    f[i] = f[i - coin] + 1;
                }
            }
        }
        // result
        if (f[amount] == 0) {
            return -1;
        } else {
            return f[amount];
        }
    }

    private int method2(int[] coins, int amount) {
        int n = coins.length;
        // state
        int[] f = new int[amount + 1]; // 要用到下标为amount
        // init
        f[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            f[i] = Integer.MAX_VALUE;
            // DP
            for (int j = 0; j < n; j++) {
                // if 的 3 个条件
                // 1: 能用给的coin拼出来
                // 2: 前一个如果是无穷大直接略过，也为了避免数组出现负index
                // 3: 更优
                if (i >= coins[j] && f[i - coins[j]] != Integer.MAX_VALUE && f[i - coins[j]] + 1 < f[i]) {
                    f[i] = f[i - coins[j]] + 1;
                }
            }
        }
        if (f[amount] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return f[amount];
        }
    }

    private int mytry(int[] coins, int amount) {
        int n = coins.length;
        // definition
        int[] f = new int[amount + 1];
        // initialization
        f[0] = 0;
        for (int i = 0; i < n; i++) {
            if (coins[i] < amount + 1) {
                f[coins[i]] = 1;
            }
        }
        for (int i = 1; i < amount + 1; i++) {
            if (f[i] == 0) {
                f[i] = Integer.MAX_VALUE;
            }
        }
        // DP
        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < n; j++) {
                if (f[i] == 1) {
                    continue;
                }
                if (i - coins[j] >= 0 && f[i - coins[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i], f[i - coins[j]] + 1);
                }
            }
        }
        // result
        return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
    }
}
