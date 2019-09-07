// 840. Magic Squares In Grid
// DescriptionHintsSubmissionsDiscussSolution
// A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.
//
// Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).
//
//
//
// Example 1:
//
// Input: [[4,3,8,4],
//         [9,5,1,9],
//         [2,7,6,2]]
// Output: 1
// Explanation:
// The following subgrid is a 3 x 3 magic square:
// 438
// 951
// 276
//
// while this one is not:
// 384
// 519
// 762
//
// In total, there is only one magic square inside the given grid.
// Note:
//
// 1 <= grid.length <= 10
// 1 <= grid[0].length <= 10
// 0 <= grid[i][j] <= 15
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        if (grid == null || grid.length < 3 || grid[0].length < 3) {
            return 0;
        }

        return mytry(grid);
    }

    private int mytry(int[][] grid) {
        // brute force to check every 3 * 3 grid
        int n = grid.length;
        int m = grid[0].length;
        int result = 0;
        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= m - 3; j++) {
                // starting point, and check 3 * 3 matrix
                if (isValid(grid, i, j)) {
                    result++;
                }
            }
        }
        return result;
    }
    private boolean isValid(int[][] grid, int i, int j) {
        // check rows
        // Set<Integer> set = new HashSet<>();
        int sum = 0;
        // System.out.println("start from " + i + ", " + j);

        for (int k = 0; k < 9; k++) {
            if (k != 0 && k % 3 == 0) {
                // set.add(sum);
                if (sum != 15) {
                    return false;
                }
                sum = 0;
            }
            int num = grid[i + k / 3][j + k % 3];
            if (num < 1 || num > 9) {
                return false;
            }
            sum += num;
        }
                // System.out.println("rows are good, check col ");
        // check columns
        sum = 0;
        for (int k = 0; k < 9; k++) {
            if (k != 0 && k % 3 == 0) {
                // set.add(sum);
                if (sum != 15) {
                    return false;
                }
                sum = 0;
            }
            int num = grid[i + k % 3][j + k / 3];
            if (num < 1 || num > 9) {
                return false;
            }
            sum += num;
        }
                // System.out.println("col are good, check dia ");
        // check diagonals
        sum = 0;
        for (int k = 0; k < 3; k++) {
            int num = grid[i + k][j + k];
            if (num < 1 || num > 9) {
                return false;
            }
            sum += num;
        }
        if (sum != 15) {
            return false;
        }
        sum = 0;
        for (int k = 0; k < 3; k++) {
            int num = grid[i + k][j + 2 - k];
            if (num < 1 || num > 9) {
                return false;
            }
            sum += num;
        }
        if (sum != 15) {
            return false;
        }
                // System.out.println("got it ");
        return true;
    }
}
