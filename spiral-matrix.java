// 54. Spiral Matrix
// DescriptionHintsSubmissionsDiscussSolution
// Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
//
// Example 1:
//
// Input:
// [
//  [ 1, 2, 3 ],
//  [ 4, 5, 6 ],
//  [ 7, 8, 9 ]
// ]
// Output: [1,2,3,6,9,8,7,4,5]
// Example 2:
//
// Input:
// [
//   [1, 2, 3, 4],
//   [5, 6, 7, 8],
//   [9,10,11,12]
// ]
// Output: [1,2,3,4,8,12,11,10,9,5,6,7]


class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<Integer>();
        }

        // return mytry(matrix);

        return method2(matrix);
    }

    private List<Integer> method2(int[][] matrix) {
        // use 4 virables to track ranges
        // ref: https://leetcode.com/problems/spiral-matrix/discuss/20599/Super-Simple-and-Easy-to-Understand-Solution
        int row_begin = 0;
        int row_end = matrix.length - 1;
        int col_begin = 0;
        int col_end = matrix[0].length - 1;
        List<Integer> result = new ArrayList<>();
        while (row_begin <= row_end && col_begin <= col_end) {
            // upper row
            for (int j = col_begin; j <= col_end; j++) {
                result.add(matrix[row_begin][j]);
            }
            row_begin++;
            // right col
            for (int i = row_begin; i <= row_end; i++) {
                result.add(matrix[i][col_end]);
            }
            col_end--;
            // prevent duplicate
            if (row_begin > row_end || col_begin > col_end) {
                break;
            }
            // bottom row
            // if (row_begin <= row_end) {
                for (int j = col_end; j >= col_begin; j--) {
                    result.add(matrix[row_end][j]);
                }
            // }
            row_end--;
            // left col
            // if (col_begin <= col_end) {
                for (int i = row_end; i >= row_begin; i--) {
                    result.add(matrix[i][col_begin]);
                }
            // }
            col_begin++;
        }
        return result;
    }

    private List<Integer> mytry(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        int layer = 0;
        while (layer < Math.min(n, m) / 2) {
            for (int j = layer; j < n - layer; j++) {
                // System.out.println("upper row");
                result.add(matrix[layer][j]);
            }
            for (int i = layer + 1; i < m - layer; i++) {
                // System.out.println("right col");
                result.add(matrix[i][n - 1 - layer]);
            }
            for (int j = n - 2 - layer; j >= layer; j--) {
                // System.out.println("bottom row");
                result.add(matrix[m - 1 - layer][j]);
            }
            for (int i = m - 2 - layer; i >= 1 + layer; i--) {
                // System.out.println("left col");
                result.add(matrix[i][layer]);
            }
            layer++;
        }
        // last col or row
        for (int i = layer; i < m - layer; i++) {
            for (int j = layer; j < n - layer; j++) {
                result.add(matrix[i][j]);
            }
        }
        return result;
    }
}
