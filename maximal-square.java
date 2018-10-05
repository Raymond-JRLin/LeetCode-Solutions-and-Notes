// 221. Maximal Square
// DescriptionHintsSubmissionsDiscussSolution
// Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
//
// Example:
//
// Input:
//
// 1 0 1 0 0
// 1 0 1 1 1
// 1 1 1 1 1
// 1 0 0 1 0
//
// Output: 4


class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        return mytry(matrix);
    }

    private int mytry(char[][] matrix) {
        // 看到这题就想到 max rectangle 和最大十字星， 就想到因为是正方形， 所以只要从左上走到右下， 去找最大的边长就好了。 但是做的时候还是有点坎坷， 想得太复杂， 什么时候要 + 1 什么时候不要， f[i - 1][j - 1] 的值的影响。 其实感觉可以这样想， 因为是正方形， 所以边长相当于画圆， 所以要考虑上边， 左边， 和左上角的， 取最小值 + 1 就好， + 1 是因为当前 char 是 1，
        int n = matrix.length;
        int m = matrix[0].length;
        // definition: f[i][j] = max square border at (i, j)
        int[][] f = new int[n][m];
        // initialization
        f[0][0] = matrix[0][0] == '1' ? 1 : 0;
        int result = f[0][0];
        for (int i = 1; i < n; i++) {
            if (matrix[i][0] == '1') {
                f[i][0] = 1;
            }
            result = Math.max(result, f[i][0]);
        }
        for (int j = 1; j < m; j++) {
            if (matrix[0][j] == '1') {
                f[0][j] = 1;

            }
            result = Math.max(result, f[0][j]);
        }
        // DP
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1') {
                    f[i][j] = Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i][j - 1])) + 1;
                }
                result = Math.max(result, f[i][j]);
            }
        }
        // result
        return result * result;
    }
}
