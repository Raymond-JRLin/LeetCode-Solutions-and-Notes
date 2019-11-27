// 516. Longest Palindromic Subsequence
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
//
// Example 1:
// Input:
//
// "bbbab"
//
// Output:
//
// 4
//
// One possible longest palindromic subsequence is "bbbb".
//
// Example 2:
// Input:
//
// "cbbd"
//
// Output:
//
// 2
//
// One possible longest palindromic subsequence is "bb".
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // return method1(s);

        return method2(s);
    }

    // 注意是 subsequence

    private int method2(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        return recursion(s, 0, n - 1, memo);
    }
    private int recursion(String s, int start, int end, int[][] memo) {
        if (start < 0 || start >= s.length() || end < 0 || end >= s.length()) {
            return 0;
        }
        if (start > end) {
            return memo[start][end] = 0;
        }
        if (start == end) {
            return memo[start][end] = 1;
        }
        if (memo[start][end] != 0) {
            return memo[start][end];
        }
        if (s.charAt(start) == s.charAt(end)) {
            return memo[start][end] = Math.max(memo[start][end], 2 + recursion(s, start + 1, end - 1, memo));
        } else {
            return memo[start][end] = Math.max(recursion(s, start + 1, end, memo), recursion(s, start, end - 1, memo));
        }
    }

    private int method1(String s) {
        int n = s.length();
        // definition
        int[][] f = new int[n][n];
        // init
        for (int i = 0; i < n; i++) {
            f[i][i] = 1;
        }
        // DP
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                // j 要反着走
                // 我认为原因有 2 ：
                // 1. 从 DP 状态转移方程来看， f[j][i] 与 i - 1 和 j + 1 有关， i - 1 是上一层， 即外层 for loop， 而 j + 1 是 j 后面一个， 所以要先算一下 j + 1 的， 才能够正确地得到 j
                // 2. 从 palindrome 来看， 总是要贴着起始 char 向两边走， 即中心向两边， 所以 j 要从 i - 1 开始远离 i
                if (s.charAt(i) == s.charAt(j)) {
                    f[j][i] = Math.max(f[j][i], 2 + f[j + 1][i - 1]);
                } else {
                    f[j][i] = Math.max(f[j + 1][i], f[j][i - 1]);
                }
            }
        }
        // result
        return f[0][n - 1];
    }
}
