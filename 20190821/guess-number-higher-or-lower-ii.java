// 375. Guess Number Higher or Lower II
// DescriptionHintsSubmissionsDiscussSolution
//
// We are playing the Guess Game. The game is as follows:
//
// I pick a number from 1 to n. You have to guess which number I picked.
//
// Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
//
// However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
//
// Example:
//
// n = 10, I pick 8.
//
// First round:  You guess 5, I tell you that it's higher. You pay $5.
// Second round: You guess 7, I tell you that it's higher. You pay $7.
// Third round:  You guess 9, I tell you that it's lower. You pay $9.
//
// Game over. 8 is the number I picked.
//
// You end up paying $5 + $7 + $9 = $21.
//
// Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int getMoneyAmount(int n) {

        // return method1(n);

        return method2(n);
    }

    // 看到这个题呢， 直觉就只想到 DP， 细想一下也确实如此
    // 问的其实是最小值， 也就是保证能赢的情况下， 最小的花费是多少
    // 但我感觉有点博弈， 或者是 min-max 的味道
    // 就是要保证能赢， 也就意味着最坏情况下要赢， 那么这个就是 max
    // 最坏最坏的情况就是从 1 一路猜上去， 一定能猜到 => max
    // 或者如 I 一样， 用二分法来猜， 也能猜到 => max
    // 在这么多不同的最坏情况下猜到的的方法中， 花费最少的是多少 -> min
    // 对于 DP 来说， 这是一个区间类型的， 就是 从 1 - n
    // 所以我们可以设一个 2D 数组 f[i][j]， 来存 [i, j] 范围内保证能赢的最少花费
    // 假设猜 k， i <= k <= j, cost = k + MAX{f[i][k - 1], f[k + 1][j]}
    // 然后在 [i, j] 区间取不同的 k， 找其最小值， 即为 f[i][j]

    private int method2(int n) {
        // recursion 感觉更好理解一点
        int[][] memo = new int[n + 1][n + 1];
        return recursion(n, memo, 1, n);
    }
    private int recursion(int n, int[][] memo, int start, int end) {
        if (start == end) {
            return 0;
        }
        if (start + 1 == end) {
            return start;
        }
        if (memo[start][end] != 0) {
            return memo[start][end];
        }
        int minCost = Integer.MAX_VALUE;
        for (int k = start + 1; k < end; k++) {
            int curr = k + Math.max(recursion(n, memo, start, k - 1), recursion(n, memo, k + 1, end));
            minCost = Math.min(minCost, curr);
        }
        return memo[start][end] = minCost;
    }

    private int method1(int n) {
        // definition: f[i][j] = min cost to gurantee to win when guess range is [i, j]
        int[][] f = new int[n + 1][n + 1];
        // initiliazation
        // DP
        for (int j = 2; j <= n; j++) {
            // 从后往前， 因为前面的算过了， 而后面的还没有
            for (int i = j - 1; i >= 1; i--) {
                if (i + 1 == j) {
                    f[i][j] = i;
                    continue;
                }
                int minCost = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int curr = k + Math.max(f[i][k - 1], f[k + 1][j]);
                    minCost = Math.min(minCost, curr);
                }
                // 相邻的 i 和 j 取不到中间的一个数， 自然选小的 cost
                f[i][j] = minCost;
            }
        }
        // result
        return f[1][n];
    }
}
