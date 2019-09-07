// 115. Distinct Subsequences
// DescriptionHintsSubmissionsDiscussSolution
// Given a string S and a string T, count the number of distinct subsequences of S which equals T.
//
// A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
//
// Example 1:
//
// Input: S = "rabbbit", T = "rabbit"
// Output: 3
// Explanation:
//
// As shown below, there are 3 ways you can generate "rabbit" from S.
// (The caret symbol ^ means the chosen letters)
//
// rabbbit
// ^^^^ ^^
// rabbbit
// ^^ ^^^^
// rabbbit
// ^^^ ^^^
// Example 2:
//
// Input: S = "babgbag", T = "bag"
// Output: 5
// Explanation:
//
// As shown below, there are 5 ways you can generate "bag" from S.
// (The caret symbol ^ means the chosen letters)
//
// babgbag
// ^^ ^
// babgbag
// ^^    ^
// babgbag
// ^    ^^
// babgbag
//   ^  ^^
// babgbag
//     ^^^


class Solution {
    public int numDistinct(String s, String t) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (t == null || t.length() == 0) {
            return 1;
        }

        // return method1(s, t);

        return method2(s, t);
    }

    private int method2(String s, String t) {
        // Stack Overflow
        int n = s.length();
        int m = t.length();
        // definition
        int[][] memo = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                memo[i][j] = -1;
            }
        }
        return recursion(memo, s, t, n, m);
    }
    private int recursion(int[][] memo, String s, String t, int i, int j) {
        if (i == 0 && j == 0) {
            return memo[i][j] = 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (i == 0) {
            return memo[i][j] = 0;
        }
        if (j == 0) {
            return memo[i][j] = 1;
        }
        memo[i][j] = recursion(memo, s, t, i - 1, j);
        if (s.charAt(s.length() - i) == t.charAt(t.length() - j)) {
            memo[i][j] += recursion(memo, s, t, i - 1, j -1);
        }

        return memo[i][j];
    }

    private int method1(String s, String t) {
        int n = s.length();
        int m = t.length();
        // definition: f[i][j] = s 剩余 i 个， t 剩余 j 个， 能匹配的个数
        int[][] f = new int[n + 1][m + 1];
        // initialization
        // for (int j = 0; j < m + 1; j++) {
        //     f[0][j] = 0;
        // }
        for (int i = 0; i < n + 1; i++) {
            f[i][0] = 1;
        }
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                f[i][j] = f[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    f[i][j] += f[i - 1][j - 1];
                }
            }
        }
        // result
        return f[n][m];
    }
}
