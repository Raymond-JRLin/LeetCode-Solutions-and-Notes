// 5. Longest Palindromic Substring
// DescriptionHintsSubmissionsDiscussSolution
// Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
//
// Example 1:
//
// Input: "babad"
// Output: "bab"
// Note: "aba" is also a valid answer.
// Example 2:
//
// Input: "cbbd"
// Output: "bb"
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public String longestPalindrome(String s) {
        // return method1(s);

//         use DP to solve this problem
        return method2(s);
    }

//     method 1: use extend palindrome way to conduct all possible palindromes
    private String method1(String s) {
        int n = s.length();
        String result = s.substring(0, 1);
        for (int i = 0; i < n; i++) {
            // i is the middle position
            String temp = extendPalindrome(s, i, i + 1); // even
            if (temp.length() > result.length()) {
                result = temp;
            }
            temp = extendPalindrome(s, i, i); // odd
            if (temp.length() > result.length()) {
                result = temp;
            }
        }
        return result;
    }
    private String extendPalindrome(String s, int left, int right) {
        int n = s.length();
        while (left >= 0 && right < n) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        // because we return outside of while loop, the left and right woulbe be after left-- and right++
        return s.substring(left + 1, right);
    }
//     method 2: use DP
    private String method2(String s) {
        int n = s.length();
        int maxLen = 1;
        String result = s.substring(0, 1);
        boolean[][] f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if ((i - j < 2 || f[j + 1][i - 1]) && s.charAt(i) == s.charAt(j)) {
                    f[j][i] = true;
                }
                if (i - j + 1 > maxLen && f[j][i]) {
                    maxLen = i - j;
                    result = s.substring(j, i + 1);
                }
            }
            f[i][i] = true;
        }
        return result;
    }
}
