// 712. Minimum ASCII Delete Sum for Two Strings
// DescriptionHintsSubmissionsDiscussSolution
// Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.
//
// Example 1:
// Input: s1 = "sea", s2 = "eat"
// Output: 231
// Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
// Deleting "t" from "eat" adds 116 to the sum.
// At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
// Example 2:
// Input: s1 = "delete", s2 = "leet"
// Output: 403
// Explanation: Deleting "dee" from "delete" to turn the string into "let",
// adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
// At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
// If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
// Note:
//
// 0 < s1.length, s2.length <= 1000.
// All elements of each string will have an ASCII value in [97, 122].
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
//         definition
        int[][] f = new int[len1 + 1][len2 + 1];
//         initialization
        f[0][0] = 0;
        for (int i = 1; i < len1 + 1; i++) {
            f[i][0] = f[i - 1][0] + (int) s1.charAt(i - 1);
        }
        for (int j = 1; j < len2 + 1; j++) {
            f[0][j] = f[0][j - 1] + (int) s2.charAt(j - 1);
        }
//         DP
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2+ 1; j++) {
                char c1 = s1.charAt(i - 1);
                char c2 = s2.charAt(j - 1);
                if (c1 == c2) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = Math.min(f[i - 1][j] + c1, f[i][j - 1] + c2);
                }
            }
        }
//         result
        return f[len1][len2];
    }
    private int my_try(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
//         definition
        int[][] f = new int[len1 + 1][len2 + 1];
//         initialization
        f[0][0] = 0;
        for (int i = 1; i < len1 + 1; i++) {
            f[i][0] = f[i - 1][0] + (int) s1.charAt(i - 1);
        }
        for (int j = 1; j < len2 + 1; j++) {
            f[0][j] = f[0][j - 1] + (int) s2.charAt(j - 1);
        }
//         DP
        // 通过作图可以得到以下的关系， 但是有个问题就是在这个计算过程中， 只考虑了 subsequence 而题目要求是 substring， 因此 当 c1 != c2 的时候的状态转移方程是错误的
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2+ 1; j++) {
                char c1 = s1.charAt(i - 1);
                char c2 = s2.charAt(j - 1);
                if (c1 == c2) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = f[i - 1][j] + f[i][j - 1] - f[i - 1][j - 1];
                }
            }
        }
//         result
        return f[len1][len2];
    }
}
