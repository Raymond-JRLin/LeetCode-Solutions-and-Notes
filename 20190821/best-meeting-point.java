// 296. Best Meeting Point
// DescriptionHintsSubmissionsDiscussSolution
//
// A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
//
// Example:
//
// Input:
//
// 1 - 0 - 0 - 0 - 1
// |   |   |   |   |
// 0 - 0 - 0 - 0 - 0
// |   |   |   |   |
// 0 - 0 - 1 - 0 - 0
//
// Output: 6
//
// Explanation: Given three people living at (0,0), (0,4), and (2,2):
//              The point (0,2) is an ideal meeting point, as the total travel distance
//              of 2+2+2=6 is minimal. So return 6.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        // return mytry(grid);

        // return method1(grid);

        return method2(grid);
    }

    public int method2(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[] rows = new int[n], cols = new int[m];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                rows[i] += grid[i][j];
                cols[j] += grid[i][j];
            }
        }
        return minDistance1D(rows) + minDistance1D(cols);
    }

    public int minDistance1D(int[] vector) {
        // 注意这里的 1D 最短距离和的求法
        int i = -1, j = vector.length;
        int d = 0, left = 0, right = 0;
        // 这里更好的展示了： 往人少的方向去移动
        while (i != j) {
            if (left < right) {
                d += left;
                left += vector[++i];

            } else {
                d += right;
                right += vector[--j];
            }
        }
        return d;
    }

    private int method1(int[][] grid) {
        // BFS TLE 的话， 就要考虑看看是不是做预处理， DP， 还是其他的方法？
        // 这里强调的是曼哈顿距离， 平面上的距离， 并且其中没有障碍物阻挡， 所以并不需要 BFS 来做
        // 对于平面上的 2D 曼哈顿距离， 就是 row 的差值 + col 的差值， 只要看坐标差就好了
        // 那么这里所有的点到其他的点都有曼哈顿距离， 怎样可以快速求解呢？
        // 这里有个点是： 假设是平面上 1D 的距离， 比如一个 row 中找到到所有人家的最小距离和， 就是找中点
        // 这个中点不是指这一 row index 的中点， 而是把所有人按照 col 顺序排好， 在所有人中的中点
        // 这么说， 这个点左右两边有不同的人家， 尽可能的使左右两边的人数相等， 所以我个人认为可以理解成重心
        // 每一 col 也是如此， 找到所有的 col 上的人， 找中点， 中点 row 和 中点 col 汇聚的点就是 best meeting point

        int n = grid.length;
        int m = grid[0].length;
        // 1. 找到所有 row 和 col 上的人， 把 row 和 col 对应的 index 分别存下
        List<Integer> rows = new ArrayList<>(); // <row index>
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                }
            }
        }
        // 按照 col 自身的顺序
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (grid[i][j] == 1) {
                    cols.add(j);
                }
            }
        }
        // 2. 找中点到所有人的距离和
        return getMedian(rows) + getMedian(cols);
    }
    private int getMedian(List<Integer> list) {
        int i = 0;
        int j = list.size() - 1;
        int sum = 0;
        // 中点即是这个 list 的中点， 和原 grid 不相干
        while (i < j) {
            // 这里其实是简化了的
            // 假设中点是 x0, sum = SUM{(right of x) - x0} + SUM{x0 - (left of x)}
            sum += list.get(j) - list.get(i);
            i++;
            j--;
        }
        return sum;
    }

    private int mytry(int[][] grid) {
        // BFS flooding: TLE
        int n = grid.length;
        int m = grid[0].length;
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, dist);
                }
            }
        }
        // 注意这里某个房子 1 也可以是汇合点， 所以不需要考虑 1 能不能走到之类的
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result = Math.min(result, dist[i][j]);
            }
        }
        return result;
    }
    private void bfs(int[][] grid, int i, int j, int[][] dist) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        int d = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                dist[curr[0]][curr[1]] += d;
                for (int l = 0; l < 4; l++) {
                    int x = curr[0] + dx[l];
                    int y = curr[1] + dy[l];
                    if (x < 0 || x >= n || y < 0 || y >= m) {
                        continue;
                    }
                    if (visited[x][y]) {
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
