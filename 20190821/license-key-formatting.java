// 482. License Key Formatting
// DescriptionHintsSubmissionsDiscussSolution
//
// You are given a license key represented as a string S which consists only alphanumeric character and dashes. The string is separated into N+1 groups by N dashes.
//
// Given a number K, we would want to reformat the strings such that each group contains exactly K characters, except for the first group which could be shorter than K, but still must contain at least one character. Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.
//
// Given a non-empty string S and a number K, format the string according to the rules described above.
//
// Example 1:
//
// Input: S = "5F3Z-2e-9-w", K = 4
//
// Output: "5F3Z-2E9W"
//
// Explanation: The string S has been split into two parts, each part has 4 characters.
// Note that the two extra dashes are not needed and can be removed.
//
// Example 2:
//
// Input: S = "2-5g-3-J", K = 2
//
// Output: "2-5G-3J"
//
// Explanation: The string S has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.
//
// Note:
//
//     The length of string S will not exceed 12,000, and K is a positive integer.
//     String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
//     String S is non-empty.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public String licenseKeyFormatting(String S, int K) {

        return mytry(S, K);
    }

    private String mytry(String s, int k) {
        // 不需要/不能 split s, 因为最后的结果不根据给定的 s 的 dash 来分的
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int len = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '-') {
                continue;
            }
            if (len != 0 && len % k == 0) {
                sb.append("-");
            }
            len++;
            sb.append(s.charAt(i));

        }
        return sb.reverse().toString().toUpperCase();
    }
}
