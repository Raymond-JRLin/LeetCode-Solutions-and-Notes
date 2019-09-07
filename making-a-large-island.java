// 827. Making A Large Island
// DescriptionHintsSubmissionsDiscussSolution
// In a 2D grid of 0s and 1s, we change at most one 0 to a 1.
//
// After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).
//
// Example 1:
//
// Input: [[1, 0], [0, 1]]
// Output: 3
// Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
// Example 2:
//
// Input: [[1, 1], [1, 0]]
// Output: 4
// Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
// Example 3:
//
// Input: [[1, 1], [1, 1]]
// Output: 4
// Explanation: Can't change any 0 to 1, only one island with area = 4.
//
//
// Notes:
//
// 1 <= grid.length = grid[0].length <= 50.
// 0 <= grid[i][j] <= 1.
//
//
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public int largestIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // return mytry(grid);

        return method2(grid);
    }

    private int method2(int[][] grid) {
        // another way from Qingyun: O(N ^ 2)
        int n = grid.length;
        int m = grid[0].length;

        // 1- 找到所有的岛屿， 并将联通的岛屿同一编号， 不同的岛屿联通块用不同的编号, 同时将岛屿块的编号同大小存入 map
        int[][] islandIndex = new int[n][m];
        int index = 0;
        Map<Integer, Integer> islandSize = new HashMap<>(); // <index of island, size of these whole connected islands>
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    int size = bfsAndIndex(grid, i, j, islandIndex, index, visited);
                    // System.out.println("size is " + size);
                    islandSize.put(index, size);
                    index++;
                }
            }
        }

        // 2- 从每个 0 出发， 找它相邻的 4 个点， 如果是岛屿的话， 把它们对应的 size 加起来， 取最大值
        // 这里因为我们实现用不同的 index 标记了所有岛屿联通块， 所以只需要找相邻 4 点就可以了， 无需深入 BFS 的去找
        int result = islandSize.getOrDefault(0, 0); // 这样就不需要看是否有 0 了， 因为如果没有 0， map 中 index 为 0 的岛屿就是整个 grid
        if (result == n * m) {
            return result;
        }
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    int size = 0;
                    Set<Integer> set = new HashSet<>(); // 记录 4 个相邻点是否已经是联通的了， 并且已经加过了
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        // invalid
                        if (x < 0 || x >= n || y < 0 || y >= m) {
                            continue;
                        }
                        // not an island
                        if (grid[x][y] == 0) {
                            continue;
                        }
                        int number = islandIndex[x][y];
                        // counted already, it's connected to another neighbor island
                        if (set.contains(number)) {
                            continue;
                        }
                        size += islandSize.get(number);
                        set.add(number);
                    }
                    result = Math.max(result, size + 1);
                }
            }
        }
        // for (int i= 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         System.out.print(islandIndex[i][j] + " (" + islandSize.get(islandIndex[i][j]) + ") ");
        //     }
        //     System.out.println();
        // }

        return result;
    }
    private int bfsAndIndex(int[][] grid, int i, int j, int[][] islandIndex, int index, boolean[][] visited) {
        // 这个 BFS 做了： 1- 找到这个联通块的大小； 2- 标记了所有联通块内的岛屿编号
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;

        int size = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            // System.out.println("current position is " + curr[0] + ", " + curr[1]);
            islandIndex[curr[0]][curr[1]] = index;
            size++;
            for (int k = 0; k < 4; k++) {
                int x = curr[0] + dx[k];
                int y = curr[1] + dy[k];
                // invalid
                if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
                    continue;
                }
                // visited
                if (visited[x][y]) {
                    continue;
                }
                // it's not an island
                if (grid[x][y] == 0) {
                    continue;
                }
                queue.offer(new int[]{x, y});
                visited[x][y] = true;
            }
        }
        return size;
    }

    private int mytry(int[][] grid) {
        // start from each 0, and do BFS to check the size: O(N ^ 4) time
        int n = grid.length;
        int m = grid[0].length;
        int result = 0;
        boolean hasZero = false; // record if there's zero in grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    hasZero = true;
                    result = Math.max(result, bfs(grid, i, j));
                }
            }
        }
        return hasZero ? result : n * m; // if there's no zero in grid, then the answer is the grid size
    }
    private int bfs(int[][] grid, int i, int j) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int count = 0;
        boolean[][] visited = new boolean[n][m];
        visited[i][j] = true;
        Queue<Coordinator> queue = new LinkedList<>();
        Coordinator root = new Coordinator(i, j);
        queue.offer(root);
        while (!queue.isEmpty()) {
            Coordinator curr = queue.poll();
            count++;
            for (int k = 0; k < 4; k++) {
                int x = curr.x + dx[k];
                int y = curr.y + dy[k];
                if (x < 0 || x >= n || y < 0 || y >= m) {
                    continue;
                }
                if (grid[x][y] == 0) {
                    continue;
                }
                if (visited[x][y]) {
                    continue;
                }
                Coordinator coor = new Coordinator(x, y);
                queue.offer(coor);
                visited[x][y] = true;
            }
        }
        return count;
    }
    private class Coordinator {
        private int x;
        private int y;
        public Coordinator(int i, int j) {
            x = i;
            y = j;
        }
    }
}
