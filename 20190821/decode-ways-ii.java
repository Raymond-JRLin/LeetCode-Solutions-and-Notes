// 639. Decode Ways II
// DescriptionHintsSubmissionsDiscussSolution
//
// A message containing letters from A-Z is being encoded to numbers using the following mapping way:
//
// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
//
// Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
//
// Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
//
// Also, since the answer may be very large, you should return the output mod 109 + 7.
//
// Example 1:
//
// Input: "*"
// Output: 9
// Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
//
// Example 2:
//
// Input: "1*"
// Output: 9 + 9 = 18
//
// Note:
//
//     The length of the input string will fit in range [1, 105].
//     The input string will only contain the character '*' and digits '0' - '9'.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return method1(s);
    }

    private int method1(String s) {
        int n = s.length();
        final int MOD = (int) 1e9 + 7;
        // definition
        long[] f = new long[n + 1];
        // init
        f[0] = 1;
        f[1] = ways(s.charAt(0));
        // DP
        for (int i = 2; i < n + 1; i++) {
            long curr = (
                (f[i - 1] * ways(s.charAt(i - 1))) % MOD +
                (f[i - 2] * ways(s.charAt(i - 2), s.charAt(i - 1))) % MOD
            ) % MOD;
            f[i] = curr;
        }
        return (int) f[n];
    }
    private int ways(char c) {
        if (c == '0') {
            return 0;
        }
        if (c == '*') {
            return 9;
        }
        return 1;
    }
    private int ways(char c1, char c2) {
        if (c1 == '*' && c2 == '*') {
            return 15; // (1*) + (2*) == 9 + 6
        }
        if (c1 == '*') {
            if (c2 >= '0' && c2 <= '6') {
                return 2;
            } else {
                return 1;
            }
        } else if (c2 == '*') {
            if (c1 == '1') {
                return 9;
            } else if (c1 == '2') {
                return 6;
            } else {
                return 0;
            }
        } else {
            int prefix = (c1 - '0') * 10 + (c2 - '0');
            if (prefix >= 10 && prefix <= 26) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
