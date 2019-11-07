// 518. Coin Change 2
// DescriptionHintsSubmissionsDiscussSolution
//
// You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.
//
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
// Example 2:
//
// Input: amount = 3, coins = [2]
// Output: 0
// Explanation: the amount of 3 cannot be made up just with coins of 2.
//
// Example 3:
//
// Input: amount = 10, coins = [10]
// Output: 1
//
//
//
// Note:
//
// You can assume that
//
//     0 <= amount <= 5000
//     1 <= coin <= 5000
//     the number of coins is less than 500
//     the answer is guaranteed to fit into signed 32-bit integer
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int change(int amount, int[] coins) {


        // return method1(amount, coins);

        return method2(amount, coins);
    }

    private int method2(int amount, int[] coins) {
        int n = coins.length;
        // no need to sort
        // definition
        int[] f = new int[amount + 1];
        // initialization
        f[0] = 1;
        // DP
        for (int coin : coins) {
            // 这里 coins loop 一定要放在外层， 因为如果把 amount 放在外层， 那么就会有重复
            // 比如说 amount = 5, [1, 2, 5], 5 拿个 1 的话，就会加上 amount = 4 的取法， 拿个 2 的话， 就会加上 amount = 3 的取法
            // 然而 amount = 4 已经加过了 amount = 3 的取法， 比如 4 = 2 + 2， 3 = 1 + 2， 最后是 {1, 2, 2} 和 {2, 1, 2}
            // 我猜这也是为什么 DFS 是错的原因
            for (int i = 1; i <= amount; i++) {
                if (i >= coin && f[i - coin] > 0) {
                    f[i] += f[i - coin];
                }
            }
        }
        // result
        return f[amount];
    }

    private int method1(int amount, int[] coins) {
        int n = coins.length;
        Arrays.sort(coins);
        int[][] memo = new int[n][amount + 1]; // pruning
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        // 感觉就是 DP 的 recursion
        return dfs(amount, coins, 0, memo);
    }
    private int dfs(int amount, int[] coins, int index, int[][] memo) {
        if (amount == 0) {
            return 1;
        }
        if (index >= coins.length) {
            return 0;
        }
        if (amount < 0) {
            return 0;
        }
        if (memo[index][amount] != -1) {
            return memo[index][amount];
        }
        // 取当前 coin 或者不取
        int count = dfs(amount - coins[index], coins, index, memo) + dfs(amount, coins, index + 1, memo);
        return memo[index][amount] = count;
    }

}
