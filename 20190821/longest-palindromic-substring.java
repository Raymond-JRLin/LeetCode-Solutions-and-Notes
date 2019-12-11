// 5. Longest Palindromic Substring
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
//
// Example 1:
//
// Input: "babad"
// Output: "bab"
// Note: "aba" is also a valid answer.
//
// Example 2:
//
// Input: "cbbd"
// Output: "bb"
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        // return mytry(s);

        return method2(s);
    }

    private String method2(String s) {
        int n = s.length();
        int start = 0, end = 0;
        // definition
        boolean[][] f = new boolean[n][n];
        // init
        for (int i = 0; i < n; i++) {
            f[i][i] = true;
        }
        // DP
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if ((j - i < 2 || f[i + 1][j - 1]) && s.charAt(i) == s.charAt(j)) {
                    f[i][j] = true;
                }
                if (f[i][j] && j + 1 - i > end + 1 - start) {
                    start = i;
                    end = j;
                }
            }
        }
        // result
        return s.substring(start, end + 1);
    }

    private String mytry(String s) {
        // 优化一点就是把 function 独立出来， 然后每次不取 substring 而是记录 start end 最后返回 substring
        int n = s.length();
        String result = s.substring(0, 1);
        for (int i = 0; i < n; i++) {
            int left = i - 1, right = i + 1;
            while (left >= 0 && right < n) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                if (s.substring(left, right + 1).length() + 1 > result.length()) {
                    result = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
            left = i;
            right = i + 1;
            while (left >= 0 && right < n) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                if (s.substring(left, right + 1).length() > result.length()) {
                    result = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
        }
        return result;
    }
}
