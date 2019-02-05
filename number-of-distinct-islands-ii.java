// 711. Number of Distinct Islands II
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
//
// Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).
//
// Example 1:
// 11000
// 10000
// 00001
// 00011
// Given the above grid map, return 1.
//
// Notice that:
// 11
// 1
// and
//  1
// 11
// are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
// Example 2:
// 11100
// 10001
// 01001
// 01110
// Given the above grid map, return 2.
//
// Here are the two distinct islands:
// 111
// 1
// and
// 1
// 1
//
// Notice that:
// 111
// 1
// and
// 1
// 111
// are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.
// Note: The length of each dimension in the given grid does not exceed 50.
//



class Solution {
    public int numDistinctIslands2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        return method1(grid);
    }

    private int method1(int[][] grid) {
        //canonical hash
        int n = grid.length;
        int m = grid[0].length;

        Set<String> set = new HashSet<>();
        int mark = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    // it's an island
                    List<int[]> paths = new ArrayList<>();
                    dfs(grid, i, j, paths, mark++);
                    set.add(normalize(paths));
                }
            }
        }
        return set.size();
    }
    private void dfs(int[][] grid, int i, int j, List<int[]> paths, int mark) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) {
            return;
        }
        if (grid[i][j] != 1) {
            return;
        }
        grid[i][j] = mark;
        paths.add(new int[]{i, j});
        // 4 directions
        dfs(grid, i, j + 1, paths, mark);
        dfs(grid, i, j - 1, paths, mark);
        dfs(grid, i + 1, j, paths, mark);
        dfs(grid, i - 1, j, paths, mark);
    }
    private String normalize(List<int[]> paths) {
        List<String> shapes = new ArrayList<>();
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                List<int[]> l1 = new ArrayList<>();
                List<int[]> l2 = new ArrayList<>();
                for (int[] path : paths) {
                    int x = path[0];
                    int y = path[1];
                    l1.add(new int[]{x * i, y * j});
                    l2.add(new int[]{y * i, x * j});
                    // System.out.println("[" + x + ", " + y + "]" + ", l1 and l2 add " + l1.get(l1.size() - 1)[0] + " " + l1.get(l1.size() - 1)[1] + ", " + l2.get(l2.size() - 1)[0] + " " + l2.get(l2.size() - 1)[1]);
                }
                // int count = 0;
                List<List<int[]>> list = new ArrayList<>();
                list.add(l1);
                list.add(l2);
                for (List<int[]> s : list) {
                    // System.out.println(count++ + " s: ");
                    // print(s);
                    s.sort((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
                    int x = s.get(0)[0], y = s.get(0)[1];
                    StringBuilder sb = new StringBuilder();
                    for (int[] p : s)
                        sb.append(p[0] - x).append(":").append(p[1] - y).append(":");
                    shapes.add(sb.toString());
                }
            }
        }
        Collections.sort(shapes);
        return shapes.get(0);
    }
    private void print(List<int[]> list ) {
        for (int[] row : list) {
            System.out.println(row[0] + " " + row[1]);
        }
    }
}
