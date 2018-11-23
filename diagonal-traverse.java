// 498. Diagonal Traverse
// DescriptionHintsSubmissionsDiscussSolution
// Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
//
//
//
// Example:
//
// Input:
// [
//  [ 1, 2, 3 ],
//  [ 4, 5, 6 ],
//  [ 7, 8, 9 ]
// ]
//
// Output:  [1,2,4,7,5,3,6,8,9]
//
// Explanation:
//
//
//
// Note:
//
// The total number of elements of the given matrix will not exceed 10,000.
//

class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        // 感觉是做过的， 可还是记不住 =。=
        return my_try(matrix);
    }
    private int[] my_try(int[][] matrix) {
        // 这里关键考虑边界条件如何处理和如何转向吧
        int[][] directions = {{-1, 1}, {1, -1}};
        int dir = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[] result = new int[n * m];
        int row = 0;
        int col = 0;
        for (int i = 0; i < n * m; i++) {
            result[i] = matrix[row][col];
            row += directions[dir][0];
            col += directions[dir][1];
            // 超出右上角和左下角的要变 2 格， 所以要先考虑
            if (row >= n) {
                // left bottom
                row = n - 1; // move back
                col += 2; // move column
                dir = 1 - dir; // change direction
            }
            if (col >= m) {
                col = m - 1;
                row += 2;
                dir = 1 - dir;
            }
            if (row < 0) {
                // up border
                row = 0; // step down by 1
                dir = 1 - dir; // change direction
            }
            if (col < 0) {
                // left border
                col = 0; // step right by 1
                dir = 1 - dir; // change direction
            }
        }
        return result;
    }
}
