// 91. Decode Ways
// DescriptionHintsSubmissionsDiscussSolution
//
// A message containing letters from A-Z is being encoded to numbers using the following mapping:
//
// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
//
// Given a non-empty string containing only digits, determine the total number of ways to decode it.
//
// Example 1:
//
// Input: "12"
// Output: 2
// Explanation: It could be decoded as "AB" (1 2) or "L" (12).
//
// Example 2:
//
// Input: "226"
// Output: 3
// Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.charAt(0) == '0') {
            return 0;
        }

        // return method1(s);

        return method2(s);
    }

    private int method2(String s) {
        int n = s.length();
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return recursion(s, memo, n, n);
    }
    private int recursion(String s, int[] memo, int n, int i) {
        if (i == 0) {
            // 0 left
            return memo[i] = 1;
        }
        // 不能以 0 开头， 只能说 10， 20
        if (s.charAt(n - i) == '0') {
            return memo[i] = 0;
        }
        if (i == 1) {
            return memo[i] = 1;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int result = recursion(s, memo, n, i - 1);
        if (s.charAt(n - i) == '1' || s.charAt(n - i) == '2' && s.charAt(n - i + 1) <= '6') {
            result += recursion(s, memo, n, i - 2);
        }
        return memo[i] = result;
    }

    private int method1(String s) {
        int n = s.length();
        // definition
        int[] f = new int[n + 1];
        // initialziation
        f[0] = 1;
        f[1] = s.charAt(0) == '0' ? 0 : 1;
        // DP
        for (int i = 2; i < n + 1; i++) {
            int curr = s.charAt(i - 1) - '0';
            int prev = s.charAt(i - 2) - '0';
            // 1 - 9
            if (1 <= curr && curr <= 9) {
                f[i] = f[i - 1];
            }
            // 10 - 26
            if (prev == 1 || curr <= 6 && prev == 2) {
                f[i] += f[i - 2];
            }
        }
        // result
        return f[n];
    }
}
