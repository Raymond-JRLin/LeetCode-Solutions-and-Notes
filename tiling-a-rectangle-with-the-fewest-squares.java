// 1240. Tiling a Rectangle with the Fewest Squares
//
//     User Accepted: 215
//     User Tried: 392
//     Total Accepted: 232
//     Total Submissions: 718
//     Difficulty: Hard
//
// Given a rectangle of size n x m, find the minimum number of integer-sided squares that tile the rectangle.
//
//
//
// Example 1:
//
// Input: n = 2, m = 3
// Output: 3
// Explanation: 3 squares are necessary to cover the rectangle.
// 2 (squares of 1x1)
// 1 (square of 2x2)
//
// Example 2:
//
// Input: n = 5, m = 8
// Output: 5
//
// Example 3:
//
// Input: n = 11, m = 13
// Output: 6
//
//
//
// Constraints:
//
//     1 <= n <= 13
//     1 <= m <= 13
//


class Solution {
    public int tilingRectangle(int n, int m) {

        // return method1(n, m);

        return method2(n, m);
    }

    private int method2(int n, int m) {
        // 要想不受特殊情况限制， 可以 general 地得出所有答案， 那么应该用搜索， 这道题本质也就是搜索， 不断的去试不同的填充方式
        // 比如可以划分成 n * m 的网格， 然后填充不同的 size
        // 假设我们从底部开始填充， 总是从左下往右上， 去选取不同的矩形填充
        if (n == m) {
            return 1;
        }
        if (m > n) {
            return method2(m, n);
        }
        // 不用网格来记录， 而是记录高度， 类似于 skyline 这样
        int[] height = new int[m];
        result = n * m;
        dfs(n, m, height, 0);
        return result;
    }
    private int result;
    private void dfs(int n, int m, int[] height, int curr) {
        // pruning
        if (curr >= result) {
            return;
        }

        // 假设我们遇到下面这种情况， 可以选择填大一点的， 也可以填个小的
        // 如果填的是小的， 那么下一轮就会从刚刚填的那个小的右边填一个相同大小的矩形
        // |--------|                       |--------|========              |--------|
        // |        |                       |        |        |             |        |====
        // |        |                       |        |        |             |        |    |
        // |        |________       =>      |        |________|     or      |        |____|___
        // |                 |              |                 |             |                 |
        // |                 |              |                 |             |                 |
        // |                 |              |                 |             |                 |
        // |_________________|              |_________________|             |_________________|

        int minHeight = n;
        int left = 0;
        for (int i = 0; i < m; i++) {
            if (height[i] < minHeight) {
                minHeight = height[i];
                left = i;
            }
        }
        // 全填满了
        if (minHeight == n) {
            // System.out.println("found curr = " + curr);
            result = Math.min(result, curr);
        }
        // 现在最低高度是 minHeight， index 是 left
        // 从 left 向右找， 看能够填的最大的矩形
        // 条件是： right index 的 height 和 minHeight 一样 && 最大的正方形的边不能超过 n （已经有 minHeight 了）
        int right = left;
        while (right < m && height[right] == height[left] && right + 1 - left + minHeight <= n) {
            // 注意 while 里面的条件
            right++;
        }
        right--; // while loop 中最后符合条件的时候会多 + 1
        // 那么从 left 到 right 之间都可以填正方形， 如上面图示， 我们从最大的开始
        for (int i = right; i >= left; i--) {
            int h = i + 1 - left;
            // update height[]
            int[] temp = Arrays.copyOf(height, m);
            for (int j = left; j <= i; j++) {
                temp[j] += h;
            }
            dfs(n, m, temp, curr + 1);
        }
    }

    private int method1(int n, int m) {
        // DP + cheat
        // 严格来说， 这个解法并不完全正确， 因为有个特殊 case， 就是 (11, 13)。 但正好题目 n 和 m 的范围就是到 13， 使得可以通过。 也就是正好在这个范围内， 只有这一个特殊情况， 当 n m 更大的时候， 应该会有更多的特殊情况
        // 从例子来看， 每次更大的矩形， 我们都拿去切割， 切成一个矩形（或正方形）先填满， 然后把小的再拿去切割
        // 这其实就是 DP， 因为切割之后剩下的也是同样地做法
        // 所以我们可以去试每个切割的位置， 去找最优解。 这个切割位置， 可以是横着切， 也可以是竖着切
        // 但是要注意， 从 example 3 可以发现就没办法 DP 了

        // special case when n, m := [1, 13]
        if (n == 11 && m == 13 || n == 13 && m == 11) {
            return 6;
        }
        // definition: f[i][j] = min # square to tile squaure of i * j
        int[][] f = new int[n + 1][m + 1];
        // initialization
        for (int[] row : f) {
            Arrays.fill(row, n * m); // 最多就是用 1 * 1 的填满整个， 即 n * m 个
        }
        // 正方形就是直接自己这 1 个
        for (int i = 1; i <= Math.min(n, m); i++) {
            f[i][i] = 1;
        }
        // DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int row = 1; row <= i / 2; row++) {
                    f[i][j] = Math.min(f[i][j], f[row][j] + f[i - row][j]);
                }
                for (int col = 1; col <= j / 2; col++) {
                    f[i][j] = Math.min(f[i][j], f[i][col] + f[i][j - col]);
                }
            }
        }
        // result
        return f[n][m];
    }
}
