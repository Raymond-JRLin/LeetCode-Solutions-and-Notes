// 1260. Shift 2D Grid
//
//     User Accepted: 2600
//     User Tried: 2818
//     Total Accepted: 2656
//     Total Submissions: 4838
//     Difficulty: Easy
//
// Given a 2D grid of size n * m and an integer k. You need to shift the grid k times.
//
// In one shift operation:
//
//     Element at grid[i][j] becomes at grid[i][j + 1].
//     Element at grid[i][m - 1] becomes at grid[i + 1][0].
//     Element at grid[n - 1][m - 1] becomes at grid[0][0].
//
// Return the 2D grid after applying shift operation k times.
//
//
//
// Example 1:
//
// Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
// Output: [[9,1,2],[3,4,5],[6,7,8]]
//
// Example 2:
//
// Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
// Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
//
// Example 3:
//
// Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
// Output: [[1,2,3],[4,5,6],[7,8,9]]
//
//
//
// Constraints:
//
//     1 <= grid.length <= 50
//     1 <= grid[i].length <= 50
//     -1000 <= grid[i][j] <= 1000
//     0 <= k <= 100
//


class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        // return mytry(grid, k);

        return method2(grid, k);
    }

    private List<List<Integer>> method2(int[][] grid, int k) {
        // 一样的思路， 写起来更简洁一点
        int n = grid.length;
        int m = grid[0].length;
        int total = n * m;
        // k 可能会超过总个数, 个数是 [0, total - 1]
        k %= total;
        // 从倒数第 k 个开始
        int start = total - k; // 0-based
        List<List<Integer>> result = new ArrayList<>();
        // 一共有 total 个, 可以绕一圈回来
        for (int i = start; i < total + start; i++) {
            if ((i - start) % m == 0) {
                result.add(new ArrayList<>());
            }
            int j = i % total; // circular
            int row = j / m;
            int col = j % m;
            // 提前把 list 放入 result， 加入最后一个
            result.get(result.size() - 1).add(grid[row][col]);
        }

        return result;
    }

    private List<List<Integer>> mytry(int[][] grid, int k) {
        // 把 row/col 拉成 index 来做
        int n = grid.length;
        int m = grid[0].length;
        int total = n * m;
        // k 可能会超过总个数
        k %= total;
        // 从倒数第 k 个开始绕一圈回来
        int index = total - k;
        int count = 0;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        while (count < total) {
            // circular 从头再开始
            if (index >= total) {
                index %= total;
            }
            // index -> [row, col]
            int row = index / m;
            int col = index - row * m; // 就是 %， 不知道在想什么 =。=

            list.add(grid[row][col]);
            index++;
            count++;
            // 完成移动后的一行了
            if (count % m == 0 && list.size() > 0) {
                result.add(list);
                list = new ArrayList<>();
            }
        }
        return result;
    }
}
