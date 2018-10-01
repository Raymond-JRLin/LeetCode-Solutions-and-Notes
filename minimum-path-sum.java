// 64. Minimum Path Sum
// DescriptionHintsSubmissionsDiscussSolution
// Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
//
// Note: You can only move either down or right at any point in time.
//
// Example:
//
// Input:
// [
//   [1,3,1],
//   [1,5,1],
//   [4,2,1]
// ]
// Output: 7
// Explanation: Because the path 1→3→1→1→1 minimizes the sum.


class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // return mytry(grid);

        // return method2(grid);

        return method3(grid);
    }

    private int method3(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        // definition
        int[] f = new int[m];
        // initialization
        f[0] = grid[0][0];
        for (int j = 1; j < m; j++) {
            f[j] = f[j - 1] + grid[0][j];
        }
        // DP
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    f[j] += grid[i][j];
                } else {
                    f[j] = Math.min(f[j], f[j - 1]) + grid[i][j];
                }
            }
        }
        // result
        return f[m - 1];
    }

    private int method2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        // definition
        int[][] f = new int[n][m];
        // initialization
        f[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            f[i][0] = f[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < m; j++) {
            f[0][j] = f[0][j - 1] + grid[0][j];
        }
        // DP
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];
            }
        }
        // result
        return f[n - 1][m - 1];
    }

    private int mytry(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] memo = new int[n][m];
        // initialization
        memo[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            memo[i][0] = memo[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < m; j++) {
            memo[0][j] = memo[0][j - 1] + grid[0][j];
        }
        // 把未计算的先标记为 -1
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                memo[i][j] = -1;
            }
        }

        return recursion(memo, grid, n - 1, m - 1);
    }
    private int recursion(int[][] memo, int[][] grid, int i, int j) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        return memo[i][j] = Math.min(recursion(memo, grid, i - 1, j), recursion(memo, grid, i, j - 1)) + grid[i][j];
    }
}
