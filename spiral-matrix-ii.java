// 59. Spiral Matrix II
// DescriptionHintsSubmissionsDiscussSolution
// Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
//
// Example:
//
// Input: 3
// Output:
// [
//  [ 1, 2, 3 ],
//  [ 8, 9, 4 ],
//  [ 7, 6, 5 ]
// ]


class Solution {
    public int[][] generateMatrix(int n) {
        if (n < 1) {
            return new int[0][0];
        }

        // return mytry(n);

        return method2(n);
    }

    public int[][] method2(int n) {
        // Declaration
        int[][] matrix = new int[n][n];

        // Edge Case
        if (n == 0) {
            return matrix;
        }

        // Normal Case
        int rowStart = 0;
        int rowEnd = n-1;
        int colStart = 0;
        int colEnd = n-1;
        int num = 1; //change

        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i ++) {
                matrix[rowStart][i] = num ++; //change
            }
            rowStart ++;

            for (int i = rowStart; i <= rowEnd; i ++) {
                matrix[i][colEnd] = num ++; //change
            }
            colEnd --;

            for (int i = colEnd; i >= colStart; i --) {
                if (rowStart <= rowEnd)
                    matrix[rowEnd][i] = num ++; //change
            }
            rowEnd --;

            for (int i = rowEnd; i >= rowStart; i --) {
                if (colStart <= colEnd)
                    matrix[i][colStart] = num ++; //change
            }
            colStart ++;
        }

        return matrix;
    }

    private int[][] mytry(int n) {
        // same as I
        int[][] result = new int[n][n];
        int layer = 0;
        int val = 1;
        while (layer < n / 2) {
            for (int j = layer; j < n - layer; j++) {
                // System.out.println("upper row");
                result[layer][j] = val++;
            }
            for (int i = layer + 1; i < n - layer; i++) {
                // System.out.println("right col");
                result[i][n - 1 - layer] = val++;
            }
            for (int j = n - 2 - layer; j >= layer; j--) {
                // System.out.println("bottom row");
                result[n - 1 - layer][j] = val++;
            }
            for (int i = n - 2 - layer; i >= 1 + layer; i--) {
                // System.out.println("left col");
                result[i][layer] = val++;
            }
            layer++;
        }
        for (int i = layer; i < n - layer; i++) {
            for (int j = layer; j < n - layer; j++) {
                result[i][j] = val++;
            }
        }
        return result;
    }
}
