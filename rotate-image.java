// 48. Rotate Image
// DescriptionHintsSubmissionsDiscussSolution
// You are given an n x n 2D matrix representing an image.
//
// Rotate the image by 90 degrees (clockwise).
//
// Note:
//
// You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
//
// Example 1:
//
// Given input matrix =
// [
//   [1,2,3],
//   [4,5,6],
//   [7,8,9]
// ],
//
// rotate the input matrix in-place such that it becomes:
// [
//   [7,4,1],
//   [8,5,2],
//   [9,6,3]
// ]
// Example 2:
//
// Given input matrix =
// [
//   [ 5, 1, 9,11],
//   [ 2, 4, 8,10],
//   [13, 3, 6, 7],
//   [15,14,12,16]
// ],
//
// rotate the input matrix in-place such that it becomes:
// [
//   [15,13, 2, 5],
//   [14, 3, 4, 1],
//   [12, 6, 8, 9],
//   [16, 7,10,11]
// ]


class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) {
            return;
        }

        // mytry(matrix);

        // method1(matrix);

        // 规律：
        // clockwise rotate： first reverse up to down, then swap the symmetry
        // anticlockwise rotate: first reverse left to right, then swap the symmetry
        // symmetry 指的是 左下和右上交换
        method2(matrix);
    }

    private void method2(int[][] nums) {
        // reverse up to down
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = nums[i][j];
                nums[i][j] = nums[n - 1 - i][j];
                nums[n - 1 - i][j] = temp;
            }
        }
        // swap symmetry
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = nums[i][j];
                nums[i][j] = nums[j][i];
                nums[j][i] = temp;
            }
        }
    }

    private void method1(int[][] nums) {
        int n = nums.length;
        // rotate sequence: [i][j] -> [j, n - 1 - i] -> [n - 1 - i][n - 1 - j] -> [n - i - j][i]
        // 每次相当于 matrix 未变化的最外一圈进行， 所以 i j 的范围要有限制
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                // 替换的时候要倒过来写
                int temp = nums[i][j];
                nums[i][j] = nums[n - 1 - j][i];
                nums[n - 1 - j][i] = nums[n - 1 - i][n - 1 - j];
                nums[n - 1 - i][n - 1 - j] = nums[j][n - 1 - i];
                nums[j][n - 1 -i] = temp;
            }
        }
    }


    private void mytry(int[][] nums) {
        // brute with extra space O(n ^ 2);
        int n = nums.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][n - 1 - i] = nums[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = result[i][j];
            }
        }
    }
}
