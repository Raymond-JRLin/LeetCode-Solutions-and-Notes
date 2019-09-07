// 311. Sparse Matrix Multiplication
// DescriptionHintsSubmissionsDiscussSolution
// Given two sparse matrices A and B, return the result of AB.
//
// You may assume that A's column number is equal to B's row number.
//
// Example:
//
// Input:
//
// A = [
//   [ 1, 0, 0],
//   [-1, 0, 3]
// ]
//
// B = [
//   [ 7, 0, 0 ],
//   [ 0, 0, 0 ],
//   [ 0, 0, 1 ]
// ]
//
// Output:
//
//      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
// AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
//                   | 0 0 1 |
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return A;
        }

        // return mytry(A, B);

        // return mytry2(A, B);

        // return method2(A, B);

        return method3(A, B);
    }

    private int[][] method3(int[][] grid1, int[][] grid2) {
        // record the non-zero position in grid1, and do multiplication on those postition only with grid2
        // ref: https://leetcode.com/problems/sparse-matrix-multiplication/discuss/76154/Easiest-JAVA-solution
        int n = grid1.length; // grid1's row
        int m = grid1[0].length; // grid1' col = grid2's row
        int len = grid2[0].length; // grid2's col
        List[] nonZero = new List[n]; // nonZero col_number and its value for each row in grid1, or we can also do the same on grid2, and multiply on 2 tables
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                if (grid1[i][j] != 0) {
                    list.add(j);
                    list.add(grid1[i][j]);
                }
            }
            nonZero[i] = list;
        }

        int[][] result = new int[n][len];
        for (int i = 0; i < n; i++) {
            List<Integer> list = nonZero[i];
            for (int k = 0; k < list.size(); k += 2) {
                // only iterate those non-zero position in grid1
                int col = list.get(k);
                int val = list.get(k + 1);
                for (int j = 0; j < len; j++) {
                    if (grid2[col][j] != 0) {
                        result[i][j] += val * grid2[col][j];
                    }
                }

            }
        }
        return result;
    }

    private int[][] method2(int[][] grid1, int[][] grid2) {
        // modified of mytry2
        int n = grid1.length; // grid1's row
        int m = grid1[0].length; // grid1' col = grid2's row
        int len = grid2[0].length; // grid2's col
        int[][] result = new int[n][len];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < m; k++) {
                // change the order of k and j (range): iterate grid2's row first
                if (grid1[i][k] == 0) {
                    continue;
                }
                for (int j = 0; j < len; j++) {
                    // if anyone of two is 0, then no need to calculate
                    if (grid2[k][j] == 0) {
                        continue;
                    }
                    result[i][j] += grid1[i][k] * grid2[k][j];
                }
            }
        }
        return result;
    }

    private int[][] mytry2(int[][] grid1, int[][] grid2) {
        int n = grid1.length; // grid1's row
        int m = grid1[0].length; // grid1' col = grid2's row
        int len = grid2[0].length; // grid2's col
        int[][] result = new int[n][len];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < len; k++) {
                // iterate grid2's col
                int sum = 0;
                for (int j = 0; j < m; j++) {
                    // if anyone of two is 0, then no need to calculate
                    if (grid1[i][j] == 0 || grid2[j][k] == 0) {
                        continue;
                    }
                    sum += grid1[i][j] * grid2[j][k];
                }
                result[i][k] = sum;
            }
        }
        return result;
    }

    private int[][] mytry(int[][] grid1, int[][] grid2) {
        // brute force as matrix multiplication: TLE obviously
        int n = grid1.length; // grid1's row
        int m = grid1[0].length; // grid1' col = grid2's row
        int len = grid2[0].length; // grid2's col
        int[][] result = new int[n][len];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < len; k++) {
                // iterate grid2's col
                int sum = 0;
                for (int j = 0; j < m; j++) {
                    // each multiplication of grid1's row and grid2's col
                    sum += grid1[i][j] * grid2[j][k];
                }
                result[i][k] = sum;
            }
        }
        return result;
    }
}
