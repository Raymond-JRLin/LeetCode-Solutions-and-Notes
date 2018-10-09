// 91. Decode Ways
// DescriptionHintsSubmissionsDiscussSolution
// A message containing letters from A-Z is being encoded to numbers using the following mapping:
//
// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// Given a non-empty string containing only digits, determine the total number of ways to decode it.
//
// Example 1:
//
// Input: "12"
// Output: 2
// Explanation: It could be decoded as "AB" (1 2) or "L" (12).
// Example 2:
//
// Input: "226"
// Output: 3
// Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).


class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        // return method1(s);

        return method2(s);
    }

    private int method2(String s) {
        int n = s.length();
        int[] memo = new int[n + 1]; // use the last n chars to make decode ways
        return recursion(memo, s, n);
    }
    private int recursion(int[] memo, String s, int i) {
        if (i == 0) {
            return memo[i] = 1;
        }

        if (s.charAt(s.length() - i) == '0') {
            return 0;
        }
        if (i == 1) {
            return memo[i] = 1;
        }

        memo[i] = recursion(memo, s, i - 1);
        if (s.charAt(s.length() - i) == '1' || s.charAt(s.length() - i) == '2' && s.charAt(s.length() - i + 1) <= '6') {
            memo[i] += recursion(memo, s, i - 2);
        }
        return memo[i];
    }

    private int method1(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        // definition
        int[] f = new int[n + 1]; // f[i] = # decode ways at i
        // initialization
        f[0] = 1;
        f[1] = 1;
        // DP
        for (int i = 2; i < n + 1; i++) {
            if (arr[i - 1] != '0') {
                f[i] = f[i - 1];
            }
            if (arr[i - 2] == '1' || arr[i - 2] == '2' && arr[i - 1] <= '6') {
                f[i] += f[i - 2];
            }
        }
        // result
        return f[n];
    }
}
