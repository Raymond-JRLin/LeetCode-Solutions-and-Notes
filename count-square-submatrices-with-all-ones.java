// 1277. Count Square Submatrices with All Ones
//
//     User Accepted: 1442
//     User Tried: 1676
//     Total Accepted: 1493
//     Total Submissions: 2482
//     Difficulty: Medium
//
// Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
//
//
//
// Example 1:
//
// Input: matrix =
// [
//   [0,1,1,1],
//   [1,1,1,1],
//   [0,1,1,1]
// ]
// Output: 15
// Explanation:
// There are 10 squares of side 1.
// There are 4 squares of side 2.
// There is  1 square of side 3.
// Total number of squares = 10 + 4 + 1 = 15.
//
// Example 2:
//
// Input: matrix =
// [
//   [1,0,1],
//   [1,1,0],
//   [1,1,0]
// ]
// Output: 7
// Explanation:
// There are 6 squares of side 1.
// There is 1 square of side 2.
// Total number of squares = 6 + 1 = 7.
//
//
//
// Constraints:
//
//     1 <= arr.length <= 300
//     1 <= arr[0].length <= 300
//     0 <= arr[i][j] <= 1
//


class Solution {
    public int countSquares(int[][] matrix) {

        return mytry(matrix);
    }

    private int mytry(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        // 我做的时候， 其实想的和大家做的答案是一样的， 但是我不知道为什么这么做， 也不知道我怎么就这样想了 =。=
        // definition: f[i][j] := 以 [i][j] 坐标为右下角的 square 的 max side length
        int[][] f = new int[n][m];
        // init: 原给定数组就是基础答案， 即变长为 1 的每个 cell 自己
        for (int i = 0; i < n; i++) {
            f[i] = matrix[i].clone();
        }
        // DP
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (f[i][j] == 0) {
                    continue;
                }
                int min = Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i][j - 1]));
                f[i][j] = Math.max(f[i][j], min + 1);
            }
        }
        // result
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result += f[i][j];
            }
        }
        return result;
    }
}
