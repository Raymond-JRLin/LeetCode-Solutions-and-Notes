// 518. Coin Change 2
// DescriptionHintsSubmissionsDiscussSolution
// You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.
//
// Note: You can assume that
//
// 0 <= amount <= 5000
// 1 <= coin <= 5000
// the number of coins is less than 500
// the answer is guaranteed to fit into signed 32-bit integer
//
//
// Example 1:
//
// Input: amount = 5, coins = [1, 2, 5]
// Output: 4
// Explanation: there are four ways to make up the amount:
// 5=5
// 5=2+2+1
// 5=2+1+1+1
// 5=1+1+1+1+1
//
//
// Example 2:
//
// Input: amount = 3, coins = [2]
// Output: 0
// Explanation: the amount of 3 cannot be made up just with coins of 2.
//
//
// Example 3:
//
// Input: amount = 10, coins = [10]
// Output: 1



class Solution {
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        if (coins == null || coins.length == 0) {
            // no coin to choose
            return 0;
        }

        // return method1(amount, coins);

        return method2(amount, coins);
    }

    private int method2(int amount, int[] coins) {
        // 1D DP: f[i][j] 只和 i - 1 有关系， 先不看 j 的话， 那所以把 i 降维， f[j] 就作为上一轮的结果， 那看是否用这枚硬币 j 至少要有 coin 那么大才可以加前面其他硬币的结果， 不然就只是前一轮的结果
        Arrays.sort(coins);
        // definition
        int[] f = new int[amount + 1];
        // initialization
        f[0] = 1;
        // DP
        for (int coin : coins) {
            for (int j = coin; j < amount + 1; j++) {
                f[j] += f[j - coin];
            }
        }
        // result
        return f[amount];
    }

    private int method1(int amount, int[] coins) {
        // Knapsack: 2D DP
        Arrays.sort(coins);
        int n = coins.length;
        // definition: f[i][j] = the combination of j with first i coins
        int[][] f = new int[n + 1][amount + 1];
        // initialization
        for (int i = 0; i < f.length; i++) {
            f[i][0] = 1;
        }
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < amount + 1; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= coins[i - 1]) {
                    f[i][j] += f[i][j - coins[i - 1]];
                }
            }
        }
        // result
        return f[n][amount];
    }
}
