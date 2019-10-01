// 1208. Get Equal Substrings Within Budget
// User Accepted: 2350
// User Tried: 3264
// Total Accepted: 2412
// Total Submissions: 8560
// Difficulty: Medium
// You are given two strings s and t of the same length. You want to change s to t. Changing the i-th character of s to i-th character of t costs |s[i] - t[i]| that is, the absolute difference between the ASCII values of the characters.
//
// You are also given an integer maxCost.
//
// Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of twith a cost less than or equal to maxCost.
//
// If there is no substring from s that can be changed to its corresponding substring from t, return 0.
//
//
//
// Example 1:
//
// Input: s = "abcd", t = "bcdf", cost = 3
// Output: 3
// Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum length is 3.
// Example 2:
//
// Input: s = "abcd", t = "cdef", cost = 3
// Output: 1
// Explanation: Each charactor in s costs 2 to change to charactor in t, so the maximum length is 1.
// Example 3:
//
// Input: s = "abcd", t = "acde", cost = 0
// Output: 1
// Explanation: You can't make any change, so the maximum length is 1.
//
//
// Constraints:
//
// 1 <= s.length, t.length <= 10^5
// 0 <= maxCost <= 10^6
// s and t only contain lower case English letters.


class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // return mytry(s, t, maxCost);

        return method2(s, t, maxCost);
    }

    private int method2(String s, String t, int maxCost) {
        // 其实也就是 sliding window
        int n = s.length(), i = 0, j;
        for (j = 0; j < n; ++j) {
            k -= Math.abs(s.charAt(j) - t.charAt(j));
            if (k < 0) {
                k += Math.abs(s.charAt(i) - t.charAt(i));
                ++i;
            }
        }
        // 这里可以直接返回 j - i
        // 原因在于 sliding window 的宽度不会变的比之前的更短， 因为每当 ++i 同时 j 都会 ++
        // 所以 window 的宽度一定是保持或增长的
        return j - i;
    }

    private int mytry(String s, String t, int maxCost) {
        // 个人想法是去找一个最长的 substring 拥有最多 maxCost 不同的 char (cost 定义是不同 char 的 ASCII 差值)， 就想到 2 pointers
        int diff = 0;
        int left = 0;
        // int right = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                diff += Math.abs(s.charAt(i) - t.charAt(i));
            }
            if (diff > maxCost) {
                diff -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
            result = Math.max(i - left + 1, result);
        }
        return result;
    }
}
