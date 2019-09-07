// 750. Number Of Corner Rectangles
// DescriptionHintsSubmissionsDiscussSolution
// Given a grid where each entry is only 0 or 1, find the number of corner rectangles.
//
// A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.
//
//
//
// Example 1:
//
// Input: grid =
// [[1, 0, 0, 1, 0],
//  [0, 0, 1, 0, 1],
//  [0, 0, 0, 1, 0],
//  [1, 0, 1, 0, 1]]
// Output: 1
// Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
//
//
// Example 2:
//
// Input: grid =
// [[1, 1, 1],
//  [1, 1, 1],
//  [1, 1, 1]]
// Output: 9
// Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
//
//
// Example 3:
//
// Input: grid =
// [[1, 1, 1, 1]]
// Output: 0
// Explanation: Rectangles must have four distinct corners.
//
//
// Note:
//
// The number of rows and columns of grid will each be in the range [1, 200].
// Each grid[i][j] will be either 0 or 1.
// The number of 1s in the grid will be at most 6000.
//
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int countCornerRectangles(int[][] grid) {
        if (grid == null || grid.length < 2 || grid[0].length < 2) {
            return 0;
        }

        // StackOverflow
        // return my_try(grid);

        // TLE: straightforward to check every possibility
        // return method1(grid);

        return method2(grid);
    }

    private int method2(int[][] grid) {
        // 固定两边的宽（竖直方向）， 去找有没有两个点为 1 构成一条长（水平方向）， 再用组合 2Cn = n * (n - 1) / 2 求有多少个不同的矩形
        // https://discuss.leetcode.com/topic/114177/short-java-ac-solution-o-m-2-n-with-explanation
        int n = grid.length;
        int m = grid[0].length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int count = 0;
                for (int k = 0; k < m; k++) {
                    if (grid[i][k] == 1 && grid[j][k] == 1) {
                        count++;
                    }
                }
                if (count > 0) {
                    result += count * (count - 1) / 2;
                }
            }
        }
        return result;
    }

    // TLE
    private int method1(int[][] grid) {
        // straightforward method: check every possibility
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                if (grid[i][j] == 1) {
                    // get 1st point - left-top
                    for (int k = j + 1; k < m; k++) {
                        if (grid[i][k] == 1) {
                            // get right-top, go to check bottom line
                            for (int l = i + 1; l < n; l++) {
                                if (grid[l][j] == 1 && grid[l][k] == 1) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    // StackOverflow
    private int my_try(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                int bottom = i + 1;
                int right = j + 1;
                // if (!isValid(right, bottom, grid)) {
                //     continue;
                // }
                // while (isValid(right, bottom, grid) && grid[i][right] == 1 && grid[bottom][right] == 1) {
                //     if (grid[bottom][right] == 1) {
                //         count++;
                //     }
                //     bottom++;
                //     right++;
                // }
                count += recursion(grid, i, j, bottom, right);
            }
        }
        return count;
    }
    private boolean isValid(int right, int bottom, int[][] grid) {
        if (right < 0 || right >= grid[0].length || bottom < 0 || bottom >= grid.length) {
            return false;
        }
        return true;
    }
    private int recursion(int[][] grid, int i, int j, int bottom, int right) {
        if (isValid(right, bottom, grid) && grid[i][right] == 1 && grid[bottom][right] == 1) {
            return 1;
        }
        int count = 0;
        count += recursion(grid, i, j, bottom + 1, right + 1);
        count += recursion(grid, i, j, bottom, right + 1);
        count += recursion(grid, i, j, bottom + 1, right);
        return count;
    }
}
