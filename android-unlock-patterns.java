// 351. Android Unlock Patterns
// DescriptionHintsSubmissionsDiscussSolution
// Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
//
// Rules for a valid pattern:
//
// Each pattern must connect at least m keys and at most n keys.
// All the keys must be distinct.
// If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
// The order of keys used matters.
//
//
//
// Explanation:
//
// | 1 | 2 | 3 |
// | 4 | 5 | 6 |
// | 7 | 8 | 9 |
// Invalid move: 4 - 1 - 3 - 6
// Line 1 - 3 passes through key 2 which had not been selected in the pattern.
//
// Invalid move: 4 - 1 - 9 - 2
// Line 1 - 9 passes through key 5 which had not been selected in the pattern.
//
// Valid move: 2 - 4 - 1 - 3 - 6
// Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
//
// Valid move: 6 - 5 - 4 - 1 - 9 - 2
// Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
//
// Example:
//
// Input: m = 1, n = 1
// Output: 9


class Solution {
    public int numberOfPatterns(int m, int n) {

        return method1(m, n);
    }

    private int method1(int m, int n) {
        // skip[i][j] record when we go from i to j, then we would skip one element on the board
        // ref: https://leetcode.com/problems/android-unlock-patterns/discuss/82463/Java-DFS-solution-with-clear-explanations-and-optimization-beats-97.61-(12ms)
        int skip[][] = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        boolean visited[] = new boolean[10];
        int result = 0;
        for (int i = m; i <= n; i++) {
            // i 是个数
            result += dfs(skip, visited, 1, i - 1) * 4; // 1, 3, 5, 7 对称
            result += dfs(skip, visited, 2, i - 1) * 4; // 2, 4, 6, 8 对称
            result += dfs(skip, visited, 5, i - 1);
        }
        return result;
    }
    private int dfs(int[][] skip, boolean[] visited, int pos, int n) {
        if (n < 0) {
            // no more position can go
            return 0;
        }
        if (n == 0) {
            // complete
            return 1;
        }
        visited[pos] = true;
        int sum = 0;
        // i 是键盘数字, pos -> i
        for (int i = 1; i <= 9; i++) {
            if (visited[i]) {
                continue;
            }
            if (skip[pos][i] == 0 || visited[skip[pos][i]]) {
                // 如果 pos -> i 中间没有经过任何数字， 或者经过的数字已经被用了， 即不会连到这个经过的数字， 才能完成 pos -> i 的连线
                sum += dfs(skip, visited, i, n - 1);
            }
        }
        visited[pos] = false; // backtracking
        return sum;
    }
}
