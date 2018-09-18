// 892. Surface Area of 3D Shapes
// User Accepted: 1327
// User Tried: 1488
// Total Accepted: 1353
// Total Submissions: 2353
// Difficulty: Easy
// On a N * N grid, we place some 1 * 1 * 1 cubes.
//
// Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
//
// Return the total surface area of the resulting shapes.
//
//
//
// Example 1:
//
// Input: [[2]]
// Output: 10
// Example 2:
//
// Input: [[1,2],[3,4]]
// Output: 34
// Example 3:
//
// Input: [[1,0],[0,2]]
// Output: 16
// Example 4:
//
// Input: [[1,1,1],[1,0,1],[1,1,1]]
// Output: 32
// Example 5:
//
// Input: [[2,2,2],[2,1,2],[2,2,2]]
// Output: 46
//
//
// Note:
//
// 1 <= N <= 50
// 0 <= grid[i][j] <= 50


class Solution {
    public int surfaceArea(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        return method1(grid);
    }

    private int method1(int[][] nums) {
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] > 0) {
                    // 注意不为 0 才要算， 不然会多加 2
                    // 这样做更简单， 就是 cube 一圈 4 面加顶和底， 不要 * 6 然后去减合并的
                    result += nums[i][j] * 4 + 2;
                }
                if (i > 0) {
                    // 和前一个的重复的， 注意要减掉 2， 是两面
                    result -= Math.min(nums[i][j], nums[i - 1][j]) * 2;
                }
                if (j > 0) {
                    result -= Math.min(nums[i][j], nums[i][j - 1]) * 2;
                }
            }
        }
        return result;
    }
}
