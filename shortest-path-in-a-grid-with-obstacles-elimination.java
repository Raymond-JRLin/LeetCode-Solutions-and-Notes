// 1293. Shortest Path in a Grid with Obstacles Elimination
//
//     User Accepted: 537
//     User Tried: 1006
//     Total Accepted: 565
//     Total Submissions: 2330
//     Difficulty: Hard
//
// Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.
//
// Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
//
//
//
// Example 1:
//
// Input:
// grid =
// [[0,0,0],
//  [1,1,0],
//  [0,0,0],
//  [0,1,1],
//  [0,0,0]],
// k = 1
// Output: 6
// Explanation:
// The shortest path without eliminating any obstacle is 10.
// The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
//
//
//
// Example 2:
//
// Input:
// grid =
// [[0,1,1],
//  [1,1,1],
//  [1,0,0]],
// k = 1
// Output: -1
// Explanation:
// We need to eliminate at least two obstacles to find such a walk.
//
//
//
// Constraints:
//
//     grid.length == m
//     grid[0].length == n
//     1 <= m, n <= 40
//     1 <= k <= m*n
//     grid[i][j] == 0 or 1
//     grid[0][0] == grid[m-1][n-1] == 0
//


class Solution {
    public int shortestPath(int[][] grid, int k) {

        // return mytry(grid, k);

        return method2(grid, k);
    }

    private int method2(int[][] grid, int k) {
        // 另一种空间少一点的 BFS
        // 想象 k 为人的 HP， 经过 obstacles 就会减少一点， 如果不同方式到达同一个点的时候 HP 更多， 那么意味着这种方式更好
        // 就是说， 我做的是层级遍历， 所以保证了当我走到终点的时候一点是 shortest distance
        // 那么如果更好的 HP 能够更新， 那就更新好了， 如果 detour 更远， 那么也不会在同一层走到， 如果有办法走到， 那么更高的 k 可选择的更多
        // O(N * M * K) time and O(N * M) space
        int n = grid.length;
        int m = grid[0].length;
        Queue<Pos> queue = new LinkedList<>();
        int[][] visited = new int[n][m];
        queue.offer(new Pos(0, 0, k));
        for (int[] v : visited) {
            Arrays.fill(v, -1);
        }
        visited[0][0] = k;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pos curr = queue.poll();
                if (curr.x == n - 1 && curr.y == m - 1) {
                    return result;
                }
                for (int j = 0; j < 4; j++) {
                    int x = curr.x + dx[j];
                    int y = curr.y + dy[j];
                    if (x < 0 || x >= n || y < 0 || y >= m) {
                        continue;
                    }
                    if (grid[x][y] == 1 && !curr.canEliminate()) {
                        continue;
                    }

                    Pos next = new Pos(x, y, curr.k);
                    if (grid[x][y] == 1) {
                        next.eliminate();
                    }
                    if (visited[x][y] >= next.k) {
                        continue;
                    }
                    queue.offer(next);
                    visited[x][y] = next.k;
                }
            }
            result++;
        }
        return -1;
    }

    private int mytry(int[][] grid, int k) {
        // BFS
        // 这里有点不同就是有个 k， 表示能够穿过多少个 obstacles
        // 所以更新点的时候也要带着这个 k 的信息
        // O(N * M * K) time and space, 想象一下 3D 的 grid， 同一个 k 每个 cell 至多访问 1 次， 有 k 层 （3D 中的高度）
        int n = grid.length;
        int m = grid[0].length;
        Queue<Pos> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][k + 1];
        queue.offer(new Pos(0, 0, k));
        visited[0][0][k] = true;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int result = 0;
        // 记录到达某一个点 with k 时候的最短距离
        int[][][] dist = new int[n][m][k + 1];
        for (int[][] d : dist) {
            for (int[] nums : d) {
                Arrays.fill(nums, Integer.MAX_VALUE);
            }
        }
        dist[0][0][k] = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pos curr = queue.poll();
                int d = dist[curr.x][curr.y][curr.k];
                if (curr.x == n - 1 && curr.y == m - 1) {
                    return d;
                }

                for (int j = 0; j < 4; j++) {
                    int x = curr.x + dx[j];
                    int y = curr.y + dy[j];
                    if (x < 0 || x >= n || y < 0 || y >= m) {
                        continue;
                    }
                    if (grid[x][y] == 1 && !curr.canEliminate()) {
                        continue;
                    }

                    Pos next = new Pos(x, y, curr.k);
                    if (grid[x][y] == 1) {
                        next.eliminate();
                    }
                    if (visited[x][y][next.k]) {
                        continue;
                    }
                    if (d + 1 >= dist[x][y][next.k]) {
                        continue;
                    }
                    queue.offer(next);
                    visited[x][y][next.k] = true;
                    dist[x][y][next.k] = d + 1;
                }
            }
        }
        return -1;
    }

    private class Pos {
        int x;
        int y;
        int k;

        Pos(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }

        boolean canEliminate() {
            return k > 0;
        }

        boolean eliminate() {
            if (!this.canEliminate()) {
                return false;
            }
            this.k--;
            return true;
        }
    }
}
