// 85. Maximal Rectangle
// DescriptionHintsSubmissionsDiscussSolution
// Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
//
// Example:
//
// Input:
// [
//   ["1","0","1","0","0"],
//   ["1","0","1","1","1"],
//   ["1","1","1","1","1"],
//   ["1","0","0","1","0"]
// ]
// Output: 6


class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        return method1(matrix);
    }

    private int method1(char[][] matrix) {
        // DP, 看了答案， 让我觉得像是最大十字星那题， 用 DP 的方法做一个计算的预处理
        int n = matrix.length;
        int m = matrix[0].length;
        int result = 0;
        // definition
        // we only count current level
        int[] left = new int[m];
        int[] right = new int[m];
        int[] h = new int[m]; // since we calculate from top to down, so we keep cumulating the height
        // initialization
        Arrays.fill(right, m);
        // DP
        for (int i = 0; i < n; i++) {
            // get the height
            for (int j = 0; j < m; j++) {
                if (matrix[i][j]== '0') {
                    // cannot be a point for rectangle, reset to 0
                    h[j] = 0;
                } else {
                    h[j]++;
                }
            }

            // keep searchnig the left bound
            int l = 0;
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], l); // use the righter left (current left bound or previous level)
                } else {
                    left[j] = 0; // use the left-most (min-value)
                    l = j + 1; // righter position
                }
            }
            // keep searchnig the right bound
            int r = m;
            for (int j = m - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], r); // the left-most right (current right bound or previous level)
                } else {
                    right[j] = m; // use the right-most value (max-value)
                    r = j; // don't - 1 here because we calculate length from left[j] to right[j] like below
                }
            }
            // check result
            for (int j = 0; j < m; j++) {
                result = Math.max(result, (right[j] - left[j]) * h[j]);
            }

            // System.out.println("it's " + i + "'s level:");
            // for (int j = 0; j < m; j++) {
            //     System.out.print(left[j] + " ");
            // }
            // System.out.println();
            // for (int j = 0; j < m; j++) {
            //     System.out.print(right[j] + " ");
            // }
            // System.out.println();
            // for (int j = 0; j < m; j++) {
            //     System.out.print(h[j] + " ");
            // }
            // System.out.println();
        }
        // result
        return result;
    }
}
