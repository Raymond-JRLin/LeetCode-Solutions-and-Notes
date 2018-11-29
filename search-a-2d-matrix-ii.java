// 240. Search a 2D Matrix II
// DescriptionHintsSubmissionsDiscussSolution
// Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
//
// Integers in each row are sorted in ascending from left to right.
// Integers in each column are sorted in ascending from top to bottom.
// Example:
//
// Consider the following matrix:
//
// [
//   [1,   4,  7, 11, 15],
//   [2,   5,  8, 12, 19],
//   [3,   6,  9, 16, 22],
//   [10, 13, 14, 17, 24],
//   [18, 21, 23, 26, 30]
// ]
// Given target = 5, return true.
//
// Given target = 20, return false.


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        // of course we can do O(N ^ 2) time to search whole matrix

        return mytry(matrix, target);

        // can check article: https://leetcode.com/articles/search-a-2d-matrix-ii/ and dicuss for more solutions
    }

    private boolean mytry(int[][] matrix, int target) {
        // O(N + M)
        int n = matrix.length;
        int m = matrix[0].length;
        int i = n - 1;
        int j = 0;
        while (i >= 0 && j < m) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }
        return false;
    }
}
