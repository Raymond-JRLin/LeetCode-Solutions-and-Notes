// 286. Walls and Gates
// DescriptionHintsSubmissionsDiscussSolution
// You are given a m x n 2D grid initialized with these three possible values.
//
// -1 - A wall or an obstacle.
// 0 - A gate.
// INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
// Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
//
// Example:
//
// Given the 2D grid:
//
// INF  -1  0  INF
// INF INF INF  -1
// INF  -1 INF  -1
//   0  -1 INF INF
// After running your function, the 2D grid should be:
//
//   3  -1   0   1
//   2   2   1  -1
//   1  -1   2  -1
//   0  -1   3   4


class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        // mytry(rooms);

        // method2(rooms);

        method3(rooms);
    }

    private void method3(int[][] rooms) {
        // DFS
        int n = rooms.length;
        int m = rooms[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }
    private void dfs(int[][] rooms, int i, int j, int dis) {
        // 注意第一个近来的点是 gate， 为 0 的， 所以下面一个判断不能用 <=
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length) {
            return;
        }
        if (rooms[i][j] < dis) {
            return;
        }
        rooms[i][j] = dis;
        dfs(rooms, i + 1, j, dis + 1);
        dfs(rooms, i - 1, j, dis + 1);
        dfs(rooms, i, j + 1, dis + 1);
        dfs(rooms, i, j - 1, dis + 1);
    }

    private void method2(int[][] rooms) {
        // mytry 还是太慢了， 原因就在于我们每走一个 gate 就去 BFS， 在 BFS 中查是否有更短的距离可以更新， 如果是题目中的 example， 两个 gate 都会基本走到所有能走的点， 所以不够好。 那么就要用 multi end BFS 代替 naive BFS, 重点在于， 每次每个 gate 以及到过的点都是同一层， 也就是说， 每次大家都只走一步
        int n = rooms.length;
        int m = rooms[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(i * m + j);
                }
            }
        }
        while (!queue.isEmpty()) {
            // 可以不用层级遍历， 因为本身所有 end 都放进去了， 所以一个个来就好
            // int size = queue.size();
            // for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                int row = curr / m;
                int col = curr % m;
                for (int j = 0; j < 4; j++) {
                    int x = row + dx[j];
                    int y = col + dy[j];
                    if (x < 0 || x >= n || y < 0 || y >= m) {
                        continue;
                    }
                    if (rooms[x][y] != Integer.MAX_VALUE) {
                        continue;
                    }
                    rooms[x][y] = rooms[row][col] + 1;
                    queue.offer(x * m + y);
                }
            // }
        }
    }

    private void mytry(int[][] rooms) {
        // I think we can do BFS for every empty room, but there's no return, we should modify to given 2D array, so we can think about flooding from gate, which is also a improvement on previous method
        int n = rooms.length;
        int m = rooms[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0) {
                    bfs(rooms, i, j);
                }
            }
        }
    }
    private void bfs(int[][] rooms, int i, int j) {
        int n = rooms.length;
        int m = rooms[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        boolean[][] visited = new boolean[n][m];
        visited[i][j] = true;
        int dis = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                for (int l = 0; l < 4; l++) {
                    int x = curr[0] + dx[l];
                    int y = curr[1] + dy[l];
                    if (x < 0 || x >= n || y < 0 || y >= m) {
                        continue;
                    }
                    if (rooms[x][y] == -1 || rooms[x][y] == 0) {
                        continue;
                    }
                    if (visited[x][y]) {
                        continue;
                    }
                    if (dis >= rooms[x][y]) {
                        continue;
                    }
                    rooms[x][y] = dis;
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
            dis++;
        }
    }
}
