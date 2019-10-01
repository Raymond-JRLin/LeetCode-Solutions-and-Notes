// 1210. Minimum Moves to Reach Target with Rotations
// User Accepted: 505
// User Tried: 791
// Total Accepted: 523
// Total Submissions: 1628
// Difficulty: Hard
// In an n*n grid, there is a snake that spans 2 cells and starts moving from the top left corner at (0, 0) and (0, 1). The grid has empty cells represented by zeros and blocked cells represented by ones. The snake wants to reach the lower right corner at (n-1, n-2) and (n-1, n-1).
//
// In one move the snake can:
//
// Move one cell to the right if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.
// Move down one cell if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.
// Rotate clockwise if it's in a horizontal position and the two cells under it are both empty. In that case the snake moves from (r, c) and (r, c+1) to (r, c) and (r+1, c).
//
// Rotate counterclockwise if it's in a vertical position and the two cells to its right are both empty. In that case the snake moves from (r, c) and (r+1, c) to (r, c) and (r, c+1).
//
// Return the minimum number of moves to reach the target.
//
// If there is no way to reach the target, return -1.
//
//
//
// Example 1:
//
//
//
// Input: grid = [[0,0,0,0,0,1],
//                [1,1,0,0,1,0],
//                [0,0,0,0,1,1],
//                [0,0,1,0,1,0],
//                [0,1,1,0,0,0],
//                [0,1,1,0,0,0]]
// Output: 11
// Explanation:
// One possible solution is [right, right, rotate clockwise, right, down, down, down, down, rotate counterclockwise, right, down].
// Example 2:
//
// Input: grid = [[0,0,1,1,1,1],
//                [0,0,0,0,1,1],
//                [1,1,0,0,0,1],
//                [1,1,1,0,0,1],
//                [1,1,1,0,0,1],
//                [1,1,1,0,0,0]]
// Output: 9
//
//
// Constraints:
//
// 2 <= n <= 100
// 0 <= grid[i][j] <= 1
// It is guaranteed that the snake starts at empty cells.


class Solution {
    public int minimumMoves(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        return method1(grid);
    }

    private int method1(int[][] grid) {
        // 其实这道题并不是很难， 就是个 BFS （不同方向走找最少步） （自己做的时候居然在想 DFS 然后找最少步数 =。=）
        // 但是繁琐的地方在于 snake 占两格， 会有不同的形态： 垂直和水平， 并且可以做顺逆时针转动， 这样垂直和水平的顺逆时针的结果不同的
        // 所以要分类讨论
        // 同时对 cell 做 hash 去 check 是否 visited 其实并不好， 因为 snake 占两个 cell， 可能会有不同的形态但是两个 cell hash 和是一样的
        // ref: https://leetcode.com/problems/minimum-moves-to-reach-target-with-rotations/discuss/393082/Java-Concise-BFS-Solution-with-comments
        int n = grid.length;
        int result = 0;

        Set<String> visited = new HashSet<>();
        Queue<int[][]> queue = new LinkedList<>();
        int[][] root = new int[][]{{0, 0}, {0, 1}};
        int[][] dest = new int[][]{{n - 1, n - 2}, {n - 1, n - 1}};
        queue.offer(root);
        visited.add(getHash(root));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[][] curr = queue.poll();
                if (curr[0][0] == dest[0][0] && curr[0][1] == dest[0][1] &&
                    curr[1][0] == dest[1][0] && curr[1][1] == dest[1][1]) {
                    return result;
                }
                int[] tail = curr[0];
                int[] head = curr[1];
                if (head[0] == tail[0]) {
                    // horizaontal
                    // right
                    if (head[1] < n - 1 && grid[head[0]][head[1] + 1] == 0) {
                        int[][] right = new int[][]{{head[0], head[1]}, {head[0], head[1] + 1}};
                        if (visited.add(getHash(right))) {
                            queue.offer(right);
                        }
                    }

                    if (head[0] < n - 1 && grid[tail[0] + 1][tail[1]] == 0 && grid[head[0] + 1][head[1]] == 0) {
                        // down
                        int[][] down = new int[][]{{tail[0] + 1, tail[1]}, {head[0] + 1, head[1]}};
                        if (visited.add(getHash(down))) {
                            queue.offer(down);
                        }
                        // clockwise
                        int[][] clockwise = new int[][]{{tail[0], tail[1]}, {tail[0] + 1, tail[1]}};
                        if (visited.add(getHash(clockwise))) {
                            queue.offer(clockwise);
                        }
                    }
                } else {
                    // vertical
                    if (tail[1] < n - 1 && grid[tail[0]][tail[1] + 1] == 0 && grid[head[0]][head[1] + 1] == 0) {
                        // right
                        int[][] right = new int[][]{{tail[0], tail[1] + 1}, {head[0], head[1] + 1}};
                        if (visited.add(getHash(right))) {
                            queue.offer(right);
                        }
                        // counterclockwise
                        int[][] counter = new int[][]{{tail[0], tail[1]}, {tail[0], head[1] + 1}};
                        if (visited.add(getHash(counter))) {
                            queue.offer(counter);
                        }
                    }
                    // down
                    if (head[0] < n - 1 && grid[head[0] + 1][head[1]] == 0) {
                        int[][] down = new int[][]{{head[0], head[1]}, {head[0] + 1, head[1]}};
                        if (visited.add(getHash(down))) {
                            queue.offer(down);
                        }
                    }
                }
            }
            result++;
        }
        return -1;
    }
    private String getHash(int[][] nums){
        return nums[0][0] + ":" + nums[0][1] + "-" + nums[1][0] + ":" + nums[1][1];
    }
}
