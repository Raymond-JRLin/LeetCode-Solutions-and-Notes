// 883. Projection Area of 3D Shapes
// User Accepted: 1707
// User Tried: 1754
// Total Accepted: 1730
// Total Submissions: 2337
// Difficulty: Easy
// On a N * N grid, we place some 1 * 1 * 1 cubes that are axis-aligned with the x, y, and z axes.
//
// Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
//
// Now we view the projection of these cubes onto the xy, yz, and zx planes.
//
// A projection is like a shadow, that maps our 3 dimensional figure to a 2 dimensional plane.
//
// Here, we are viewing the "shadow" when looking at the cubes from the top, the front, and the side.
//
// Return the total area of all three projections.
//
//
//
// Example 1:
//
// Input: [[2]]
// Output: 5
// Example 2:
//
// Input: [[1,2],[3,4]]
// Output: 17
// Explanation:
// Here are the three projections ("shadows") of the shape made with each axis-aligned plane.
//
// Example 3:
//
// Input: [[1,0],[0,2]]
// Output: 8
// Example 4:
//
// Input: [[1,1,1],[1,0,1],[1,1,1]]
// Output: 14
// Example 5:
//
// Input: [[2,2,2],[2,1,2],[2,2,2]]
// Output: 21
//
//
// Note:
//
// 1 <= grid.length = grid[0].length <= 50
// 0 <= grid[i][j] <= 50
//


class Solution {
    public int projectionArea(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        // return mytry(grid);

        return method2(grid);
    }

    private int method2(int[][] grid) {
        // same idea, combine them into one pase
        int n = grid.length;
        int x = 0;
        int y = 0;
        int z = 0;
        for (int i = 0; i < n; i++) {
            int xMax = 0;
            int yMax = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    z++;
                }
                xMax = Math.max(xMax, grid[j][i]);
                yMax = Math.max(yMax, grid[i][j]);
            }
            x += xMax;
            y += yMax;
        }
        return x + y + z;
    }

    private int mytry(int[][] grid) {
        int n = grid.length;
        int xSum = 0;
        for (int[] row : grid) {
            // 底部阴影， 只要这个 pos 有就 + 1
            for (int num : row) {
                if (num != 0) {
                    xSum++;
                }
            }
        }
        int ySum = 0;
        for (int j = 0; j < n; j++) {
            // 固定 y， 找每一个 col 的最大值
            int max = 0;
            for (int i = 0; i < n; i++) {
                max = Math.max(max, grid[i][j]);
            }
            ySum += max;
        }
        int zSum = 0;
        for (int i = 0; i < n; i++) {
            // 固定 x， 找每一个 row 的最大值
            int max = 0;
            for (int j = 0; j < n; j++) {
                max = Math.max(max, grid[i][j]);
            }
            zSum += max;
        }
        return xSum + ySum + zSum;
    }
}
