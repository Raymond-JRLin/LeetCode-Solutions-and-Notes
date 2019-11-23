// 97. Interleaving String
// DescriptionHintsSubmissionsDiscussSolution
//
// Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
//
// Example 1:
//
// Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
// Output: true
//
// Example 2:
//
// Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
// Output: false
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        // return mytry(s1, s2, s3);

        return mytry2(s1, s2, s3);
    }

    private boolean mytry2(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int len = s3.length();
        // definition: f[i][j] := 前 i/j s1/s2 能否构成长度为 (i + j) 的 s3
        boolean[][] f = new boolean[n + 1][m + 1];
        // init
        f[0][0] = true;
        for (int i = 1; i < n + 1; i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1) && f[i - 1][0]) {
                f[i][0] = true;
            }
        }
        for (int j = 1; j < m + 1; j++) {
            if (s2.charAt(j - 1) == s3.charAt(j - 1) && f[0][j - 1]) {
                f[0][j] = true;
            }
        }
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && f[i - 1][j]) {
                    f[i][j] = true;
                }
                if (s2.charAt(j - 1) == s3.charAt(i + j - 1) && f[i][j - 1]) {
                    f[i][j] = true;
                }
            }
        }
        // result
        return f[n][m];
    }

    private boolean mytry(String s1, String s2, String s3) {
        return dfs(s1, s2, s3, 0, 0, 0);
    }
    private boolean dfs(String s1, String s2, String s3, int i, int j, int k) {
        if (k == s3.length()) {
            return i == s1.length() && j == s2.length();
        }

        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            if (dfs(s1, s2, s3, i + 1, j, k + 1)) {
                return true;
            }
        }
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            if (dfs(s1, s2, s3, i, j + 1, k + 1)) {
                return true;
            }
        }
        return false;
    }
}
