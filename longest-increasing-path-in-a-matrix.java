// 329. Longest Increasing Path in a Matrix
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer matrix, find the length of the longest increasing path.
//
// From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
//
// Example 1:
//
// Input: nums =
// [
//   [9,9,4],
//   [6,6,8],
//   [2,1,1]
// ]
// Output: 4
// Explanation: The longest increasing path is [1, 2, 6, 9].
// Example 2:
//
// Input: nums =
// [
//   [3,4,5],
//   [3,2,6],
//   [2,2,1]
// ]
// Output: 4
// Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.


class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        return method1(matrix);
    }

    private int method1(int[][] matrix) {
        // pure DFS would TLE
        // use a path[][] to record result found so far and do pruning
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] visited = new boolean[n][m];
        int[][] paths = new int[n][m]; // record the max path of [i, j] can reach to do pruning
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int result = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int len = dfs(matrix, i, j, dx, dy, Integer.MAX_VALUE, visited, paths);
                result = Math.max(result, len);
            }
        }
        return result;
    }
    int result = 0;
    private int dfs(int[][] matrix, int i, int j, int[] dx, int[] dy, int prev, boolean[][] visited, int[][] paths) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length) {
            return 0;
        }
        if (visited[i][j]) {
            return 0;
        }
        if (matrix[i][j] >= prev) {
            // cannot flood
            return 0;
        }
        if (paths[i][j] != 0) {
            return paths[i][j];
        }

        visited[i][j] = true;
        int max = 1;
        for (int k= 0; k < 4; k++) {
            int curr = 1 + dfs(matrix, i + dx[k], j + dy[k], dx, dy, matrix[i][j], visited, paths);
            max = Math.max(max, curr);
        }
        visited[i][j] = false;
        return paths[i][j] = max;
    }
}
