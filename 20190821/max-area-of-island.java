// 695. Max Area of Island
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
//
// Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
//
// Example 1:
//
// [[0,0,1,0,0,0,0,1,0,0,0,0,0],
//  [0,0,0,0,0,0,0,1,1,1,0,0,0],
//  [0,1,1,0,1,0,0,0,0,0,0,0,0],
//  [0,1,0,0,1,1,0,0,1,0,1,0,0],
//  [0,1,0,0,1,1,0,0,1,1,1,0,0],
//  [0,0,0,0,0,0,0,0,0,0,1,0,0],
//  [0,0,0,0,0,0,0,1,1,1,0,0,0],
//  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
//
// Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
//
// Example 2:
//
// [[0,0,0,0,0,0,0,0]]
//
// Given the above grid, return 0.
//
// Note: The length of each dimension in the given grid does not exceed 50.
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        return mytry(grid);
    }

    private int mytry(int[][] grid) {
        int result = 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    // result = Math.max(result, bfs(grid, i, j, visited));
                    result = Math.max(result, dfs(grid, i, j, visited));
                }
            }
        }
        return result;
    }
    private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) {
            return 0;
        }
        if (visited[i][j]) {
            return 0;
        }
        if (grid[i][j] != 1) {
            return 0;
        }
        visited[i][j] = true;
        int sum = 1;
        sum += dfs(grid, i + 1, j, visited);
        sum += dfs(grid, i - 1, j, visited);
        sum += dfs(grid, i, j + 1, visited);
        sum += dfs(grid, i, j - 1, visited);
        return sum;
    }
    private int bfs(int[][] grid, int i, int j, boolean[][] visited) {
        int n = grid.length;
        int m = grid[0].length;
        int sum = 0;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            sum += size;
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                for (int l = 0; l < 4; l++) {
                    int x = curr[0] + dx[l];
                    int y = curr[1] + dy[l];
                    if (x < 0 || x >= n || y < 0 || y >= m) {
                        continue;
                    }
                    if (visited[x][y]) {
                        continue;
                    }
                    if (grid[x][y] == 0) {
                        continue;
                    }
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        return sum;
    }
}
