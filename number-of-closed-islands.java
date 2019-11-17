// 1254. Number of Closed Islands
//
//     User Accepted: 1358
//     User Tried: 1602
//     Total Accepted: 1377
//     Total Submissions: 2494
//     Difficulty: Medium
//
// Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
//
// Return the number of closed islands.
//
//
//
// Example 1:
//
// Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
// Output: 2
// Explanation:
// Islands in gray are closed because they are completely surrounded by water (group of 1s).
//
// Example 2:
//
// Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
// Output: 1
//
// Example 3:
//
// Input: grid = [[1,1,1,1,1,1,1],
//                [1,0,0,0,0,0,1],
//                [1,0,1,1,1,0,1],
//                [1,0,1,0,1,0,1],
//                [1,0,1,1,1,0,1],
//                [1,0,0,0,0,0,1],
//                [1,1,1,1,1,1,1]]
// Output: 2
//
//
//
// Constraints:
//
//     1 <= grid.length, grid[0].length <= 100
//     0 <= grid[i][j] <=1
//


class Solution {
    public int closedIsland(int[][] grid) {

        return mytry(grid);
    }

    private int mytry(int[][] grid) {
        // 不连接边界的就是 closed
        int n = grid.length;
        int m = grid[0].length;
        Set<Integer> set = new HashSet<>();
        int result = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (grid[i][j] == 0 && !set.contains(i * m + j)) {
                    boolean isClosed = bfs(grid, n, m, set, i, j);
                    if (isClosed) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
    private boolean bfs(int[][] grid, int n, int m, Set<Integer> set, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        set.add(i * m + j);
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        boolean isClosed = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                for (int l = 0; l < 4; l++) {
                    int x = curr[0] + dx[l];
                    int y = curr[1] + dy[l];
                    if ((x == 0 || x == n - 1 || y == 0 || y == m - 1) && grid[x][y] == 0) {
                        isClosed = false;
                        continue;
                    }
                    if (grid[x][y] == 1) {
                        continue;
                    }
                    if (set.contains(x * m + y)) {
                        continue;
                    }
                    queue.offer(new int[]{x, y});
                    set.add(x * m + y);
                }
            }
        }
        return isClosed;
    }
}
