// 463. Island Perimeter
// DescriptionHintsSubmissionsDiscussSolution
// You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
//
// Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
//
// The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
//
//
//
// Example:
//
// Input:
// [[0,1,0,0],
//  [1,1,1,0],
//  [0,1,0,0],
//  [1,1,0,0]]
//
// Output: 16
//
// Explanation: The perimeter is the 16 yellow stripes in the image below:
//
//
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // return mytry(grid);

        return method2(grid);
    }

    private int method2(int[][] grid) {
        // actually we can find that every neighbor would cancel out 2 sides, so we can just calculate how many islands and neighbors, islands give 4 sides each, but neighbors cancel out 2 sides each
        // we can count islands and neighbors and then do islands * 4 - neighbors * 2, or just calculate with iteration
        int n = grid.length;
        int m = grid[0].length;
        // int islands = 0;
        // int neighbors = 0;
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    // islands++;
                    result += 4;
                    if (i + 1 < n && grid[i + 1][j] == 1) {
                        // neighbors++;
                        result -= 2;
                    }
                    if (j + 1 < m && grid[i][j + 1] == 1) {
                        // neighbors++;
                        result -= 2;
                    }
                }
            }
        }
        // return islands * 4 - neighbors * 2;
        return result;
    }

    private int mytry(int[][] grid) {
        // my thought: we should go BFS to go all island, and everytime when we meet a island, we add 4 sides, if it has a neighbor, then we should minus 2 sides, when 1 in finding this neighbor, another 1 in visited (back this index)
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    return bfs(grid, i, j);
                }
            }
        }
        return 0;
    }
    private int bfs(int[][] grid, int i, int j) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int result = 0;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            result += 4;
            for (int k = 0; k < 4; k++) {
                int x = curr[0] + dx[k];
                int y = curr[1] + dy[k];
                if (x < 0 || x >= n || y < 0 || y >= m) {
                    continue;
                }
                if (visited[x][y]) {
                    result--;
                    continue;
                }
                if (grid[x][y] == 0) {
                    continue;
                }
                result--;
                queue.offer(new int[]{x, y});
                visited[x][y] = true;
            }
        }
        return result;
    }
}
