// 97. Interleaving String
// DescriptionHintsSubmissionsDiscussSolution
// Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
//
// Example 1:
//
// Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
// Output: true
// Example 2:
//
// Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
// Output: false


class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {

        return method1(s1, s2, s3);
    }

    private boolean method1(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int len = s3.length();
        if (n + m != len) {
            return false;
        }
        // definition: f[i][j] = if first i char in s1 and first j char in s2 can interleave s3
        boolean[][] f = new boolean[n + 1][m + 1];
        // initialization
        f[0][0] = true;
        for (int i = 1; i < n + 1; i++) {
            f[i][0] = (s1.charAt(i - 1) == s3.charAt(i - 1)) && f[i - 1][0];
        }
        for (int j = 1; j < m + 1; j++) {
            f[0][j] = (s2.charAt(j - 1) == s3.charAt(j - 1)) && f[0][j - 1];
        }
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                f[i][j] = (s1.charAt(i - 1) == s3.charAt(i + j - 1) && f[i - 1][j]) || (s2.charAt(j - 1) == s3.charAt(i + j - 1) && f[i][j - 1]);
            }
        }
        // return
        return f[n][m];
    }
}
