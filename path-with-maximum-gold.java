// 1219. Path with Maximum Gold
// User Accepted:1627
// User Tried:2029
// Total Accepted:1668
// Total Submissions:3127
// Difficulty:Medium
// In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
//
// Return the maximum amount of gold you can collect under the conditions:
//
// Every time you are located in a cell you will collect all the gold in that cell.
// From your position you can walk one step to the left, right, up or down.
// You can't visit the same cell more than once.
// Never visit a cell with 0 gold.
// You can start and stop collecting gold from any position in the grid that has some gold.
//
//
// Example 1:
//
// Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
// Output: 24
// Explanation:
// [[0,6,0],
//  [5,8,7],
//  [0,9,0]]
// Path to get the maximum gold, 9 -> 8 -> 7.
// Example 2:
//
// Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
// Output: 28
// Explanation:
// [[1,0,7],
//  [2,0,6],
//  [3,4,5],
//  [0,3,0],
//  [9,0,20]]
// Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
//
//
// Constraints:
//
// 1 <= grid.length, grid[i].length <= 15
// 0 <= grid[i][j] <= 100
// There are at most 25 cells containing gold.


class Solution {
    public int getMaximumGold(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        return mytry(grid);
    }

    private int mytry(int[][] grid) {
        // DFS
        int n = grid.length;
        int m = grid[0].length;
        result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] > 0) {
                    dfs(grid, i, j, 0, new boolean[n][m]);
                }
            }
        }
        return result;
    }
    private int result;
    private void dfs(int[][] grid, int i, int j, int curr, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        if (grid[i][j] == 0) {
            return;
        }
        curr += grid[i][j];
        visited[i][j] = true;
        result = Math.max(result, curr);
        dfs(grid, i - 1, j, curr, visited);
        dfs(grid, i + 1, j, curr, visited);
        dfs(grid, i, j - 1, curr, visited);
        dfs(grid, i, j + 1, curr, visited);
        curr -= grid[i][j];
        visited[i][j] = false;
    }
}
