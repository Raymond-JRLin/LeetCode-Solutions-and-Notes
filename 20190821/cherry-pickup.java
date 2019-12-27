// 741. Cherry Pickup
// DescriptionHintsSubmissionsDiscussSolution
//
// In a N x N grid representing a field of cherries, each cell is one of three possible integers.
//
//
//
//     0 means the cell is empty, so you can pass through;
//     1 means the cell contains a cherry, that you can pick up and pass through;
//     -1 means the cell contains a thorn that blocks your way.
//
//
//
// Your task is to collect maximum number of cherries possible by following the rules below:
//
//
//
//     Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
//     After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
//     When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
//     If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
//
//
//
//
//
// Example 1:
//
// Input: grid =
// [[0, 1, -1],
//  [1, 0, -1],
//  [1, 1,  1]]
// Output: 5
// Explanation:
// The player started at (0, 0) and went down, down, right right to reach (2, 2).
// 4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
// Then, the player went left, up, up, left to return home, picking up one more cherry.
// The total number of cherries picked up is 5, and this is the maximum possible.
//
//
//
// Note:
//
//     grid is an N by N 2D array, with 1 <= N <= 50.
//     Each grid[i][j] is an integer in the set {-1, 0, 1}.
//     It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
//
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int cherryPickup(int[][] grid) {

        // return method1(grid);

        return method2(grid);
    }

    private int method2(int[][] grid) {
        // recursive way of method1
        int n = grid.length;
        int[][][] memo = new int[n][n][n];
        for (int[][] m : memo) {
            for (int[] row : m) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
        }
        return Math.max(0, recursion(grid, memo, n - 1, n - 1, n - 1));
    }
    private int recursion(int[][] grid, int[][][] memo, int x1, int y1, int x2) {
        // 同样想成两个人从 [n - 1, n - 1] 往回走到 [0, 0]
        // 两个人走的坐标是互相独立的， 但是每次只能往左或往上走 1 步， 两个人同时出发也就有了两个坐标之间的关系
        int y2 = x1 + y1 - x2;
        // out of bound
        if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0) {
            return -1;
        }
        // reach the starting position
        if (x1 == 0 && y1 == 0) {
            return grid[x1][y1];
        }
        // cannot go through
        if (grid[x1][y1] == -1 || grid[x2][y2] == -1) {
            return -1;
        }
        // already visited
        if (memo[x1][y1][x2] != Integer.MIN_VALUE) {
            return memo[x1][y1][x2];
        }
        int cherry = Math.max(
            Math.max(
                recursion(grid, memo, x1 - 1, y1, x2),
                recursion(grid, memo, x1, y1 - 1, x2)
            ), // MAX{A 向下 B 向右, A 向右 B 向右}
            Math.max(
                recursion(grid, memo, x1 - 1, y1, x2 - 1),
                recursion(grid, memo, x1, y1 - 1, x2 - 1)
            ) // MAX{A 向下 B 向下, A 向右 B 向下}
        );
        if (cherry < 0) {
            return memo[x1][y1][x2] = -1;
        }
        cherry += grid[x1][y1];
        if (x1 != x2 && y1 != y2) {
            cherry += grid[x2][y2];
        }
        return memo[x1][y1][x2] = cherry;
    }

    private int method1(int[][] grid) {
        // 这个题呢我觉得难点在于从头走到尾， 又要倒过来走一遍， 同时重点是走过的 cherry 被 pick up 了之后就没有 cherry 了
        // 也就意味着从后往前走的第二遍是依赖于第一遍走的路径， 这样的话是很难 DP 的
        // 想象一下两个人一起同时从 [0, 0] 走到 [n - 1, n - 1]， 用第二个人来模拟第二遍的往回走
        // 如果两个人同时走到一个 cell， 那么 cherry 只能被加一次
        int n = grid.length;
        // definition: f[i][j][k] (f[x1][y1][x2]) := 第一个人走到 [i, j], 第二个人走到 [k, i + j - k] 的时候能拿到的最多的 cherry 数
        // 因为只能向下向右走， 所以 x1 + y1 == x2 + y2
        // 那么两个人一共有 4 种走法：(下面的 f 表示从哪一种状态走过来到 f[i][j][k])
        // 1. A 向下， B 向右 => f[i - 1][j][k]
        // 2. A 向下， B 向下 => f[i - 1][j][k - 1]
        // 3. A 向右， B 向右 => f[i][j - 1][k]
        // 4. A 向右， B 向下 => f[i][j - 1][k - 1]
        int[][][] f = new int[n + 1][n + 1][n + 1];
        // init
        for (int[][] rows : f) {
            for (int[] row : rows) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
        }
        f[1][1][1] = grid[0][0];
        // DP
        for (int i = 1; i <= n; i++) { // x1
            for (int j = 1; j <= n; j++) { // y1
                for (int k = 1; k <= n; k++) { // x2
                    int l = i + j - k; // y2
                    // out of bound
                    if (l < 1 || l > n) {
                        continue;
                    }
                    // cannot go through
                    if (grid[i - 1][j - 1] == -1 || grid[k - 1][l - 1] == -1) {
                        continue;
                    }
                    int cherry = Math.max(
                        Math.max(f[i - 1][j][k], f[i][j - 1][k]), // MAX{A 向下 B 向右, A 向右 B 向右}
                        Math.max(f[i - 1][j][k - 1], f[i][j - 1][k - 1]) // MAX{A 向下 B 向下, A 向右 B 向下}
                    );
                    if (cherry < 0) {
                        continue;
                    }
                    f[i][j][k] = cherry + grid[i - 1][j - 1];
                    // come from different paths
                    if (i != k && j != l) {
                        f[i][j][k] += grid[k - 1][l - 1];
                    }
                }
            }
        }
        // result
        return f[n][n][n] == Integer.MIN_VALUE ? 0 : f[n][n][n];
    }
}
