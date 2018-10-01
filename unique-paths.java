// 62. Unique Paths
// DescriptionHintsSubmissionsDiscussSolution
// A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
// The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
//
// How many possible unique paths are there?
//
//
// Above is a 7 x 3 grid. How many possible unique paths are there?
//
// Note: m and n will be at most 100.
//
// Example 1:
//
// Input: m = 3, n = 2
// Output: 3
// Explanation:
// From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
// 1. Right -> Right -> Down
// 2. Right -> Down -> Right
// 3. Down -> Right -> Right
// Example 2:
//
// Input: m = 7, n = 3
// Output: 28


class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 0 && n == 0) {
            return 1;
        }
        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }

        // return mytry(m, n);

        // return method2(m, n);

        // return method3(m, n);

        return method4(m, n);
    }

    private int method4(int m, int n) {
        // 1D DP
        // definition
        int[] f = new int[m]; // 设为 row 的长度， 即 col 个数； 设为 n 也是可以的， 下面的 for 循环 correspondingly 改变即可
        // initialization
        Arrays.fill(f, 1); // 初始都为 1
        // DP
        for (int i = 1; i < n; i++) {
            // 先 for row 数， 即一行一行走
            for (int j = 1; j < m; j++) {
                // f[j - 1] 是上一轮 （上一行） 的结果， 即 go down； for m 即 go right 的结果
                f[j] += f[j - 1];
            }
        }
        // result
        return f[m - 1];
    }

    private int method3(int m, int n) {
        int[][] memo = new int[m][n];
        return recursion(memo, m - 1, n - 1);
    }
    private int recursion(int[][] memo, int i, int j) {
        // achieve the 1st row or 1st column
        if (i == 0 || j == 0) {
            return memo[i][j] = 1;
        }
        // pruning if we already get it
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        memo[i][j] = recursion(memo, i - 1, j) + recursion(memo, i, j - 1);
        return memo[i][j];
    }

    private int method2(int m, int n) {
        // 数学算概率: 一共要向下走 m - 1 步， 要向右走 n - 1 步， 所以可以做一个 combination: (total)C(down) = (total!) / (down!(total - down)!) = ((total - down + 1) * (total - down + 2) * ... * total) / (down!)
        int total = n + m - 2;
        int down = m - 1;
        double result = 1.0;
        for (int i = 1; i <= down; i++) {
            // 这里要注意一下乘除的顺序， 因为是 int 直接的除法， 所以要先乘一下之前的 result 再除当前的 i， 先做分子再除分母， 这样不会有余数被 cut
            result = result * (total - down + i) / i;
        }
        return (int) result;
    }

    private int mytry(int m, int n) {
        // DP
        // definition
        int[][] f = new int[m][n];
        // initializaiton
        f[0][0] = 1;
        for (int i = 1; i < m; i++) {
            f[i][0] = 1;
        }
        for (int j = 1; j < n; j++) {
            f[0][j] = 1;
        }
        // DP
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        // result
        return f[m - 1][n - 1];
    }
}
