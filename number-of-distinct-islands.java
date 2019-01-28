// 694. Number of Distinct Islands
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
//
// Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
//
// Example 1:
// 11000
// 11000
// 00011
// 00011
// Given the above grid map, return 1.
// Example 2:
// 11011
// 10000
// 00001
// 11011
// Given the above grid map, return 3.
//
// Notice that:
// 11
// 1
// and
//  1
// 11
// are considered different island shapes, because we do not consider reflection / rotation.
// Note: The length of each dimension in the given grid does not exceed 50.


class Solution {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        return method1(grid);
    }

    private int method1(int[][] grid) {
        // 不能够选择或者翻转， 所以就是要把形状 hash 出去
        int n = grid.length;
        int m = grid[0].length;
        Set<String> paths = new HashSet<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, 'o', sb, visited);
                    paths.add(sb.toString());
                }
            }
        }
        return paths.size();
    }
    private void dfs(int[][] grid, int i, int j, char mark, StringBuilder sb, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        sb.append(mark);
        visited[i][j] = true;
        dfs(grid, i, j + 1, 'r', sb, visited);
        dfs(grid, i, j - 1, 'l', sb, visited);
        dfs(grid, i + 1, j, 'd', sb, visited);
        dfs(grid, i - 1, j, 'u', sb, visited);
        sb.append('#');
    }
}
