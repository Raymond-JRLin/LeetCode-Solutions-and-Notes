// 361. Bomb Enemy
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
// The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
// Note: You can only put the bomb at an empty cell.
//
// Example:
//
// Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
// Output: 3
// Explanation: For the given grid,
//
// 0 E 0 0
// E 0 W E
// 0 E 0 0
//
// Placing a bomb at (1,1) kills 3 enemies.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // return mytry(grid);

        return method2(grid);
    }

    private int method2(char[][] grid) {
        // 优化就在于， 当算完一行/列的时候， 没被 wall 阻挡的其实是一样的， 不用去算了， 所以存下来直接用
        int n = grid.length;
        int m = grid[0].length;
        int rowHit = 0;
        int[] colHit = new int[m];
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'W') {
                    continue;
                }
                // 如果是第一列或是前面是 wall， 则重新开始计算这一行从 j 列一直到可以炸到的位置的 hit
                if (j == 0 || grid[i][j - 1] == 'W') {
                    rowHit = 0;
                    for (int k = j; k < m && grid[i][k] != 'W'; k++) {
                        rowHit += grid[i][k] == 'E' ? 1 : 0;
                    }
                }
                // 如果是第一行或是前面是 wall， 则重新开始计算这一列从 i 行一直到可以炸到的位置的 hit
                if (i == 0 || grid[i - 1][j] == 'W') {
                    colHit[j] = 0;
                    for (int k = i; k < n && grid[k][j] != 'W'; k++) {
                        colHit[j] += grid[k][j] == 'E' ? 1 : 0;
                    }
                }
                // 其他情况下， 只要用之前算过的结果就好了， 如果是可以放置的位置， 那么更新一下
                if (grid[i][j] == '0') {
                    result = Math.max(result, rowHit + colHit[j]);
                }
            }
        }
        return result;
    }

    private int mytry(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] count = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'E') {
                    markRow(grid, i, j, count);
                    markCol(grid, i, j, count);
                }
            }
        }
        int result = 0;
        for (int i= 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0') {
                    result = Math.max(result, count[i][j]);
                }
            }
        }
        return result;
    }
    private void markCol(char[][] grid, int row, int col, int[][] count) {
        for (int i = row - 1; i >= 0; i--) {
            if (!mark(grid, i, col, count)) {
                break;
            }
        }
        for (int i = row + 1; i < grid.length; i++) {
            if (!mark(grid, i, col, count)) {
                break;
            }
        }
    }
    private void markRow(char[][] grid, int row, int col, int[][] count) {
        for (int j = col - 1; j >= 0; j--) {
            if (!mark(grid, row, j, count)) {
                break;
            }
        }
        for (int j = col + 1; j < grid[row].length; j++) {
            if (!mark(grid, row, j, count)) {
                break;
            }
        }
    }
    private boolean mark(char[][] grid, int i, int j, int[][] count) {
        if (grid[i][j] == '0') {
            count[i][j]++;
            return true;
        } else if (grid[i][j] == 'W') {
            return false;
        } else {
            return true;
        }
    }
}
