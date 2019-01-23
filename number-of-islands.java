// 200. Number of Islands
// DescriptionHintsSubmissionsDiscussSolution
// Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//
// Example 1:
//
// Input:
// 11110
// 11010
// 11000
// 00000
//
// Output: 1
// Example 2:
//
// Input:
// 11000
// 11000
// 00100
// 00011
//
// Output: 3


class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // BFS
        // return mytry(grid);

        // DFS
        // return method2(grid);

        // Union Find
        return method3(grid);
    }

    private int method3(char[][] grid) {
        // Union Find
        int n = grid.length;
        int m = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        // Union Find 是不需要 visited 记录的， 因为如果是 connected 的话， 在 check a 和 b 的时候就会有相同的总结点
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x < 0 || x >= n || y < 0 || y >= m) {
                            continue;
                        }
                        if (grid[x][y] != '1') {
                            continue;
                        }
                        uf.connect(i * m + j, x * m + y);
                    }
                }
            }
        }
        return uf.count;
    }
    private class UnionFind {
        private int nums[];
        private int count;
        public UnionFind(char[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            nums = new int[n * m];
            Arrays.fill(nums, -1);
            count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == '1') {
                        nums[i * m + j] = i * m + j;
                        count++;
                    }
                }
            }
        }
        public int find(int i) {
            while (i != nums[i]) {
                nums[i] = nums[nums[i]];
                i = nums[i];
            }
            return i;
        }
        public boolean connect(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                nums[rootB] = rootA;
                count--;
                return true;
            }
            return false;
        }
    }

    private int method2(char[][] grid) {
        // recursion: DFS
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            // exit of recursion
            return;
        }
        // change the value
        grid[i][j] = '0';
        // go 4 directions
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }


    private int mytry(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        // boolean[][] visited = new boolean[n][m];
        // 不用 visited 数组， 直接 flooding， 把走过的 1 变成 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    bfs(new Coordinate(i, j), grid);
                    count++;
                }
            }
        }
        return count;
    }
    private void bfs(Coordinate root, char[][] grid) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<Coordinate> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Coordinate coor = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = coor.x + dx[i];
                int y = coor.y + dy[i];
                if (!isValid(x, y, grid)) {
                    continue;
                }
                if (grid[x][y] == '1') {
                    grid[x][y] = '0';
                    Coordinate nei = new Coordinate(x, y);
                    queue.offer(nei);
                }

            }
        }
    }
    private boolean isValid(int x, int y, char[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return false;
        }
        return true;
    }
}
class Coordinate {
    public int x;
    public int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
