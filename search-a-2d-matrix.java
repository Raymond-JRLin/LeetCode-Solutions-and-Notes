// 74. Search a 2D Matrix
// DescriptionHintsSubmissionsDiscussSolution
// Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
//
// Integers in each row are sorted from left to right.
// The first integer of each row is greater than the last integer of the previous row.
// Example 1:
//
// Input:
// matrix = [
//   [1,   3,  5,  7],
//   [10, 11, 16, 20],
//   [23, 30, 34, 50]
// ]
// target = 3
// Output: true
// Example 2:
//
// Input:
// matrix = [
//   [1,   3,  5,  7],
//   [10, 11, 16, 20],
//   [23, 30, 34, 50]
// ]
// target = 13
// Output: false


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        // of course we can do O(N ^ 2) time to search whole matrix

        // return mytry(matrix, target);

        // return mytry2(matrix, target);

        return method3(matrix, target);
    }

    private boolean method3(int[][] matrix, int target) {
        // since "The first integer of each row is greater than the last integer of the previous row.", we can treat it as a sorted list (expand each row): O(log(M + N))
        // ref: https://leetcode.com/problems/search-a-2d-matrix/discuss/26220/Don't-treat-it-as-a-2D-matrix-just-treat-it-as-a-sorted-list
        int n = matrix.length;
        int m = matrix[0].length;
        int start = 0;
        int end = n * m - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int val = matrix[mid / m][mid % m];
            if (val == target) {
                return true;
            } else if (val > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (matrix[start / m][start % m] == target || matrix[end / m][end % m] == target) {
            return true;
        } else {
            return false;
        }
    }

    private boolean mytry2(int[][] matrix, int target) {
        // O(N + M)
        int n = matrix.length;
        int m = matrix[0].length;
        int i = n - 1;
        int j = 0;
        while (i >= 0 && j < m) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }
        return false;
    }

    private boolean mytry(int[][] matrix, int target) {
        // O(N + logM)
        int n = matrix.length;
        int m = matrix[0].length;
        int row = -1;
        for (int i = 0; i < n; i++) {
            if (matrix[i][m - 1] >= target) {
                row = i;
                break;
            }
        }
        return row == -1 ? false : bs(matrix[row], target);
    }
    private boolean bs(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target || nums[end] == target) {
            return true;
        } else {
            return false;
        }
    }
}
