// 868. Transpose Matrix
// User Accepted: 2100
// User Tried: 2156
// Total Accepted: 2145
// Total Submissions: 2823
// Difficulty: Easy
// Given a matrix A, return the transpose of A.
//
// The transpose of a matrix is the matrix flipped over it's main diagonal, switching the row and column indices of the matrix.
//
//
//
// Example 1:
//
// Input: [[1,2,3],[4,5,6],[7,8,9]]
// Output: [[1,4,7],[2,5,8],[3,6,9]]
// Example 2:
//
// Input: [[1,2,3],[4,5,6]]
// Output: [[1,4],[2,5],[3,6]]
//
//
// Note:
//
// 1 <= A.length <= 1000
// 1 <= A[0].length <= 1000


class Solution {
    public int[][] transpose(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return A;
        }

        return mytry(A);
    }

    private int[][] mytry(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[j][i] = grid[i][j];
            }
        }
        return result;
    }
}
