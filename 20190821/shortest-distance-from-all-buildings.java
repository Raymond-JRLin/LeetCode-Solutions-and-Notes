// 317. Shortest Distance from All Buildings
// DescriptionHintsSubmissionsDiscussSolution
//
// You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
//
//     Each 0 marks an empty land which you can pass by freely.
//     Each 1 marks a building which you cannot pass through.
//     Each 2 marks an obstacle which you cannot pass through.
//
// Example:
//
// Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
//
// 1 - 0 - 2 - 0 - 1
// |   |   |   |   |
// 0 - 0 - 0 - 0 - 0
// |   |   |   |   |
// 0 - 0 - 1 - 0 - 0
//
// Output: 7
//
// Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
//              the point (1,2) is an ideal empty land to build a house, as the total
//              travel distance of 3+3+1=7 is minimal. So return 7.
//
// Note:
// There will be at least one building. If it is not possible to build such house according to the above rules, return -1.


class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        return method1(grid);
    }

    private int method1(int[][] grid) {
        // BFS
        // 0 相对较多， 从每个 1 出发， 走遍整个 grid， 就可以得到所有 0 到所有 1 的总距离， 如果可以到达的话
        // 所以可以用另外一个 array 来记录这个 0 可以到达 1 的个数
        int n = grid.length;
        int m = grid[0].length;
        int totalBuilding = 0;
        int[][] dist = new int[n][m];
        int[][] count = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, dist, count);
                    totalBuilding++;
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && count[i][j] == totalBuilding) {
                    result = Math.min(result, dist[i][j]);
                }
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    private void bfs(int[][] grid, int i, int j, int[][] dist, int[][] count) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        int d = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                dist[curr[0]][curr[1]] += d;
                count[curr[0]][curr[1]]++;
                for (int l = 0; l < 4; l++) {
                    int x = curr[0] + dx[l];
                    int y = curr[1] + dy[l];
                    if (x < 0 || x >= n || y < 0 || y >= m) {
                        continue;
                    }
                    if (visited[x][y]) {
                        continue;
                    }
                    if (grid[x][y] != 0) {
                        continue;
                    }
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
            d++;
        }
    }
}
