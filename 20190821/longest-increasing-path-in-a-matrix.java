// 329. Longest Increasing Path in a Matrix
// DescriptionHintsSubmissionsDiscussSolution
//
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
//
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
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        return mytry(matrix);
    }

    private int mytry(int[][] matrix) {
        // DFS
        int result = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] longest = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int curr = dfs(matrix, i, j, new boolean[n][m], matrix[i][j] - 1, longest);
                result = Math.max(result, curr);
            }
        }
        return result;
    }
    private int dfs(int[][] matrix, int i, int j, boolean[][] visited, int prev, int[][] longest) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length) {
            return 0;
        }
        if (visited[i][j]) {
            return 0;
        }
        if (matrix[i][j] <= prev) {
            return 0;
        }
        if (longest[i][j] != 0) {
            return longest[i][j];
        }
        int len = 0;
        visited[i][j] = true;
        len = Math.max(len, dfs(matrix, i + 1, j, visited, matrix[i][j], longest) + 1);
        len = Math.max(len, dfs(matrix, i - 1, j, visited, matrix[i][j], longest) + 1);
        len = Math.max(len, dfs(matrix, i, j + 1, visited, matrix[i][j], longest) + 1);
        len = Math.max(len, dfs(matrix, i, j - 1, visited, matrix[i][j], longest) + 1);
        visited[i][j] = false;
        return longest[i][j] = len;
    }
}
