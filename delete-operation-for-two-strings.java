// 583. Delete Operation for Two Strings
// DescriptionHintsSubmissionsDiscussSolution
// Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.
//
// Example 1:
// Input: "sea", "eat"
// Output: 2
// Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
// Note:
// The length of given words won't exceed 500.
// Characters in given words can only be lower-case letters.


class Solution {
    public int minDistance(String word1, String word2) {
        if ((word1 == null || word1.length() == 0) && (word2 == null || word2.length() == 0)) {
            return 0;
        }
        if (word1 == null || word1.length() == 0) {
            return word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }
//         similar to 712. Minimum ASCII Delete Sum for Two Strings
        int len1 = word1.length();
        int len2 = word2.length();
        // 2 strings: we can use DP
        // definition
        int[][] dp = new int[len1 + 1][len2 + 1];
        // initialization
        dp[0][0] = 0;
        for (int i = 1; i < len1 + 1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int j = 1; j < len2 + 1; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }
        // DP
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        // result
        return dp[len1][len2];
    }
}
