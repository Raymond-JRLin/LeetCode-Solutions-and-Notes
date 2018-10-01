// 63. Unique Paths II
// DescriptionHintsSubmissionsDiscussSolution
// A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
// The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
//
// Now consider if some obstacles are added to the grids. How many unique paths would there be?
//
//
//
// An obstacle and empty space is marked as 1 and 0 respectively in the grid.
//
// Note: m and n will be at most 100.
//
// Example 1:
//
// Input:
// [
//   [0,0,0],
//   [0,1,0],
//   [0,0,0]
// ]
// Output: 2
// Explanation:
// There is one obstacle in the middle of the 3x3 grid above.
// There are two ways to reach the bottom-right corner:
// 1. Right -> Right -> Down -> Down
// 2. Down -> Down -> Right -> Right


class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        if (obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1) {
            return 0;
        }

        // return mytry(obstacleGrid);

        // return method2(obstacleGrid);

        return method3(obstacleGrid);
    }

    private int method3(int[][] grid) {
        // iteration DP with 1D array
        int n = grid.length;
        int m = grid[0].length;
        // definition
        int[] f = new int[m]; // 每次只记住这一行
        // initialization
        f[0] = 1;
        // DP
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    f[j] = 0;
                } else if (j > 0) {
                    f[j] += f[j - 1];
                }
            }
        }
        // result
        return f[m - 1];
    }

    private int method2(int[][] grid) {
        // iteration DP
        int n = grid.length;
        int m = grid[0].length;
        // definition
        int[][] f = new int[n][m];
        // initialization
        f[0][0] = 1;
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) {
                break;
            }
            f[i][0] = 1;
        }
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1) {
                break;
            }
            f[0][j] = 1;
        }
        // DP
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (grid[i][j] == 1) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                }
            }
        }
        // result
        return f[n - 1][m - 1];
    }

    private int mytry(int[][] grid) {
        // recursion with memorization
        int n = grid.length;
        int m = grid[0].length;
        int[][] memo = new int[n][m];
        return recursion(memo, grid, n - 1, m - 1);
    }
    private int recursion(int[][] memo, int[][] grid, int i, int j) {
        // invalid
        if (i < 0 || j < 0) {
            return 0;
        }
        // obstacle
        if (grid[i][j] == 1) {
            return memo[i][j] = 0;
        }
        // calculated already
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        // at starting position, we cannot use || since there may be a line obstacle, then we cannot come from top to bottom, but we would get border then return 1 to cause mistackes
        if (i == 0 && j == 0) {
            return memo[i][j] = 1;
        }
        memo[i][j] = recursion(memo, grid, i - 1, j) + recursion(memo, grid, i, j - 1);
        return memo[i][j];
    }
}
