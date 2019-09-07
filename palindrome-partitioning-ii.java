// 132. Palindrome Partitioning II
// DescriptionHintsSubmissionsDiscussSolution
// Given a string s, partition s such that every substring of the partition is a palindrome.
//
// Return the minimum cuts needed for a palindrome partitioning of s.
//
// Example:
//
// Input: "aab"
// Output: 1
// Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public int minCut(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        return mytry(s);
    }

    private int mytry(String s) {
        // 每次都判断 substring 是否是 palindrome 会 TLE， 所以我们需要用另一个二维数组来记录从 i 到 j 是否是 palindrome
        int n = s.length();
        boolean[][] isPal = new boolean[n][n]; // f[i][j] = 下表 i - j 是否是 palindrome
        // definition
        int[] f = new int[n]; // f[i] = 以 i 结尾的字符串所需要的最少切割数
        // initialization
        for (int i = 0; i < n; i++) {
            f[i] = i;
        }
        // DP
        for (int i = 0; i < n; i++) {
            for (int left = 0; left <= i; left++) {
                if (s.charAt(i) == s.charAt(left) && (i - left <= 1 || isPal[left + 1][i - 1])) {
                    // s[left, i] (i inclusive) is a palindrome
                    isPal[left][i] = true; // mark
                    if (left == 0) {
                        f[i] = 0;
                    } else {
                        f[i] = Math.min(f[i], f[left - 1] + 1);
                    }
                }
            }
        }
        // result
        return f[n - 1];
    }
    private boolean isPal(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
