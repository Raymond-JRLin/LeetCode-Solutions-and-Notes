// 764. Largest Plus Sign
// DescriptionHintsSubmissionsDiscussSolution
// In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells in the given list mines which are 0. What is the largest axis-aligned plus sign of 1s contained in the grid? Return the order of the plus sign. If there is none, return 0.
//
// An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1 along with 4 arms of length k-1 going up, down, left, and right, and made of 1s. This is demonstrated in the diagrams below. Note that there could be 0s or 1s beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1s.
//
// Examples of Axis-Aligned Plus Signs of Order k:
//
// Order 1:
// 000
// 010
// 000
//
// Order 2:
// 00000
// 00100
// 01110
// 00100
// 00000
//
// Order 3:
// 0000000
// 0001000
// 0001000
// 0111110
// 0001000
// 0001000
// 0000000
// Example 1:
//
// Input: N = 5, mines = [[4, 2]]
// Output: 2
// Explanation:
// 11111
// 11111
// 11111
// 11111
// 11011
// In the above grid, the largest plus sign can only be order 2.  One of them is marked in bold.
// Example 2:
//
// Input: N = 2, mines = []
// Output: 1
// Explanation:
// There is no plus sign of order 2, but there is of order 1.
// Example 3:
//
// Input: N = 1, mines = [[0, 0]]
// Output: 0
// Explanation:
// There is no plus sign, so return 0.
// Note:
//
// N will be an integer in the range [1, 500].
// mines will have length at most 5000.
// mines[i] will be length 2 and consist of integers in the range [0, N-1].
// (Additionally, programs submitted in C, C++, or C# will be judged with a slightly smaller time limit.)
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        if (mines == null || mines.length == 0 || mines[0].length == 0) {
            return (N + 1) / 2;
        }

        // return mytry(N, mines);

        // return mytry2(N, mines);

        // return mytry3(N, mines);

        return mytry4(N, mines);
    }

    private int mytry4(int len, int[][] mines) {
        // iteration DP
        int[][] grid = new int[len][len];
        for (int[] row : grid) {
            Arrays.fill(row, len);
        }
        for (int[] mine : mines) {
            grid[mine[0]][mine[1]] = 0;
        }
        for (int i = 0; i < len; i++) {
            int left = 0;
            int right = 0;
            int up = 0;
            int down = 0;
            for (int j = 0; j < len; j++) {
                left = grid[i][j] == 0 ? 0 : left + 1;
                right = grid[i][len - 1 - j] == 0 ? 0 : right + 1;
                up = grid[j][i] == 0 ? 0 : up + 1;
                down = grid[len - 1 - j][i] == 0 ? 0 : down + 1;
                grid[i][j] = Math.min(grid[i][j], left);
                grid[i][len - 1 - j] = Math.min(grid[i][len - 1 - j], right);
                grid[j][i] = Math.min(grid[j][i], up);
                grid[len - 1 - j][i] = Math.min(grid[len - 1 - j][i], down);
            }
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                result = Math.max(result, grid[i][j]);
            }
        }
        return result;
    }

    private int mytry3(int len, int[][] mines) {
        // use DP to do pre-processing
        // 尝试着用 recursion memorization 来做， 修改多次后终于 AC 了， 保留 print 提示
        // 注意两个地方： 1- 递归传入的状态是什么以及向哪边递归， 递归方向（依赖方向）和数组名字一致， 那么传入的就是相反的最后一个状态； 2- 碰到 mine 的时候， 不可以直接返回， 要继续递归， 不然剩下的会不会被递归到， 只是在最后标记为 0， 然后返回
        // 地图及 mines
        int[][] grid = new int[len][len];
        for (int[] row : grid) {
            Arrays.fill(row, 1);
        }
        // set mine as 0
        for (int[] mine : mines) {
            grid[mine[0]][mine[1]] = 0;
        }
        // System.out.println("now print grid: ");
        // print(grid);
        // definition: 这里用 4 个数组来记录某个点向上下左右走最多能有多长（几个1）， 包括它自己， 注意是向这个方向看或者运动
        int[][] up = new int[len][len];
        int[][] down = new int[len][len];
        int[][] left = new int[len][len];
        int[][] right = new int[len][len];
        // initialization
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                up[i][j] = -1;
                down[i][j] = -1;
                left[i][j] = -1;
                right[i][j] = -1;
            }
        }
        // 4 directions
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        for (int i = 0; i < len; i++) {
            // System.out.println("go up: ");
            // 找向上的部分， 每列去做递归， 传入的状态是最底下， 然后依次向上递归
            recursion(up, len - 1, i, len, grid, 3, dx, dy);
            // System.out.println("go down: ");
            // 找向下的部分， 每列去做递归， 传入的状态是第一个， 然后依次向下递归
            recursion(down, 0, i, len, grid, 2, dx, dy);
            // System.out.println("go left: ");
            // 找向左的部分， 每行去做递归， 传入的状态是最右列， 然后依次向上递归
            recursion(left, i, len - 1, len, grid, 1, dx, dy);
            // System.out.println("go right: ");
            // 找向右的部分， 每行去做递归， 传入的状态是最左边一列， 然后依次向右递归
            recursion(right, i, 0, len, grid, 0, dx, dy);
        }
        // System.out.println("now print up array: ");
        // print(up);
        // System.out.println("now print down array: ");
        // print(down);
        // System.out.println("now print left array: ");
        // print(left);
        // System.out.println("now print right array: ");
        // print(right);
        int result = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                // 取 4 个方向的最小值， 再取其中的最大值
                result = Math.max(result, Math.min(up[i][j], Math.min(down[i][j], Math.min(left[i][j], right[i][j]))));
            }
        }
        return result;
    }
    private int recursion(int[][] memo, int i, int j, int n, int[][] grid, int ope, int[] dx, int[] dy) {
        // System.out.println("now we're checking " + i + " " +j);
        if (i < 0 || i >= n || j < 0 || j >= n) {
            // System.out.println("invalid");
            return 0;
        }

        if (memo[i][j] != -1) {
            // System.out.println("already got");
            return memo[i][j];
        }
        // 即使是 mine 为 0， 也要继续递归
        // 以 Example 1 为例： 最后一行 left 应该是 left[4] = {1, 2, 0, 1, 2}, 从 left[4][4] 向左边递归的时候， 要持续到第一列， 然后在返回的时候把 left[4][2] 设为 0， 这样 left[4][2] 两边都不会出错或者没被更新
        memo[i][j] = 1 + recursion(memo, i + dx[ope], j + dy[ope], n, grid, ope, dx, dy);
        // mine 设为 0
        if (grid[i][j] == 0) {
            // System.out.println("mine");
            memo[i][j] = 0;
        }
        return memo[i][j];
    }
    private void print(int[][] nums) {
        for (int[] row : nums) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    private int mytry2(int len, int[][] mines) {
        // BFS, but TLE
        int[][] grid = new int[len][len];
        // set mine as 1
        for (int[] mine : mines) {
            grid[mine[0]][mine[1]] = 1;
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j] == 0) {
                    result = Math.max(result, bfs(grid, i, j, len));
                }
            }
        }
        return result;
    }
    private int bfs(int[][] grid, int i, int j, int n) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<Coordinator> queue = new LinkedList<>();
        queue.offer(new Coordinator(i, j + 1, 0));
        queue.offer(new Coordinator(i, j - 1, 1));
        queue.offer(new Coordinator(i + 1, j, 2));
        queue.offer(new Coordinator(i - 1, j, 3));
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Coordinator curr = queue.poll();
                if (curr.x < 0 || curr.x >= n || curr.y < 0 || curr.y >= n || grid[curr.x][curr.y] == 1) {
                    return count;
                }
                int ope = curr.ope;
                queue.offer(new Coordinator(curr.x + dx[ope], curr.y + dy[ope], ope));
            }
        }
        return count;
    }
    private class Coordinator{
        private int x;
        private int y;
        private int ope;
        public Coordinator(int i, int j, int ope) {
            x = i;
            y = j;
            this.ope = ope;
        }
    }

    private int mytry(int len, int[][] mines) {
        // try to use DFS to calculate the length of 4 directions, but TLE
        int[][] grid = new int[len][len];
        // set mine as 1
        for (int[] mine : mines) {
            grid[mine[0]][mine[1]] = 1;
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j] == 0) {
                    int up = dfs(grid, i, j, i - 1, j, len, 0);
                    int down = dfs(grid, i, j, i + 1, j, len, 1);
                    int left = dfs(grid, i, j, i, j - 1, len, 2);
                    int right = dfs(grid, i, j, i, j + 1, len, 3);
                    result = Math.max(result, Math.min(Math.min(Math.min(up, down), left), right));
                }
            }
        }
        return result;
    }
    private int dfs(int[][] grid, int i, int j, int x, int y, int n, int ope) {
        if (x < 0 || x >= n || y < 0 || y >= n || grid[x][y] == 1) {
            return Math.abs(x - i) + Math.abs(y - j);
        }
        if (ope == 0) {
            return dfs(grid, i, j, x - 1, y, n, ope);
        } else if (ope == 1) {
            return dfs(grid, i, j, x + 1, y, n, ope);
        } else if (ope == 2) {
            return dfs(grid, i, j, x, y - 1, n, ope);
        } else {
            return dfs(grid, i, j, x, y + 1, n, ope);
        }
    }

}
