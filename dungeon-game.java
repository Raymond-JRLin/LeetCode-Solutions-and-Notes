// 174. Dungeon Game
// DescriptionHintsSubmissionsDiscussSolution
// The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
//
// The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
//
// Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
//
// In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
//
//
//
// Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
//
// For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
//
// -2 (K)	-3	3
// -5	-10	1
// 10	30	-5 (P)
//
//
// Note:
//
// The knight's health has no upper bound.
// Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.


class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 1;
        }

        // return mytry(dungeon);

        return method2(dungeon);
    }

    private int method2(int[][] nums) {
        // iteration DP
        int n = nums.length;
        int m = nums[0].length;
        // definition: f[i][j] = the min hp at (i, j) point
        int[][] f = new int[n][m];
        // initialization
        f[n - 1][m - 1] = Math.max(1, 1 - nums[n - 1][m - 1]);
        // initialize bottom row and right most column
        for (int i = n - 2; i >= 0; i--) {
            f[i][m - 1] = Math.max(f[i + 1][m - 1] - nums[i][m - 1], 1);
        }
        for (int j = m - 2; j >= 0; j--) {
            f[n - 1][j] = Math.max(f[n - 1][j + 1] - nums[n - 1][j], 1);
        }
        // DP: from right bottom to top left
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                int right = Math.max(f[i][j + 1] - nums[i][j], 1);
                int down = Math.max(f[i + 1][j] - nums[i][j], 1);
                f[i][j] = Math.min(right, down);
            }
        }
        // result
        return f[0][0];
    }

    private int mytry(int[][] nums) {
        int n = nums.length;
        int m = nums[0].length;
        int[][] memo = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        return recursion(nums, memo, visited, 0, 0, n, m);
    }
    private int recursion(int[][] nums, int[][] memo, boolean[][] visited, int i, int j, int n, int m) {
        if (i == n - 1 && j == m - 1) {
            return Math.max(1, 1 - nums[n - 1][m - 1]); // 至少要有 1
        }
        // pruning, otherwise TLE
        if (visited[i][j]) {
            return memo[i][j];
        }
        if (i == n - 1) {
            return Math.max(recursion(nums, memo, visited, i, j + 1, n, m) - nums[i][j], 1);
        }
        if (j == m - 1) {
            return Math.max(recursion(nums, memo, visited, i + 1, j, n, m) - nums[i][j], 1);
        }
        int right = Math.max(recursion(nums, memo, visited, i, j + 1, n, m) - nums[i][j], 1);
        int down = Math.max(recursion(nums, memo, visited, i + 1, j, n, m) - nums[i][j], 1);
        // mark it as visited just before we return
        visited[i][j] = true;
        return memo[i][j] = Math.min(right, down);
    }
}
