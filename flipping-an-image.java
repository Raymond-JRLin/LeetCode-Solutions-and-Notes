// 832. Flipping an Image
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
//
// To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].
//
// To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].
//
// Example 1:
//
// Input: [[1,1,0],[1,0,1],[0,0,0]]
// Output: [[1,0,0],[0,1,0],[1,1,1]]
// Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
// Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
// Example 2:
//
// Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
// Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
// Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
// Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
// Notes:
//
// 1 <= A.length = A[0].length <= 20
// 0 <= A[i][j] <= 1
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return A;
        }

        return mytry(A);
    }

    private int[][] mytry(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        for (int[] nums : grid) {
            int left = 0;
            int right = m - 1;
            while (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }

            for (int j = 0; j < m; j++) {
                nums[j] = nums[j] == 0 ? 1 : 0;
            }
        }

        return grid;
    }
}