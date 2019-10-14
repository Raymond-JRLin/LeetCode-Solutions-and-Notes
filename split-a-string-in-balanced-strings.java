// 1221. Split a String in Balanced Strings
//
//     User Accepted: 3476
//     User Tried: 3673
//     Total Accepted: 3631
//     Total Submissions: 4611
//     Difficulty: Easy
//
// Balanced strings are those who have equal quantity of 'L' and 'R' characters.
//
// Given a balanced string s split it in the maximum amount of balanced strings.
//
// Return the maximum amount of splitted balanced strings.
//
//
//
// Example 1:
//
// Input: s = "RLRRLLRLRL"
// Output: 4
// Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
//
// Example 2:
//
// Input: s = "RLLLLRRRLR"
// Output: 3
// Explanation: s can be split into "RL", "LLLRRR", "LR", each substring contains same number of 'L' and 'R'.
//
// Example 3:
//
// Input: s = "LLLLRRRR"
// Output: 1
// Explanation: s can be split into "LLLLRRRR".
//
//
//
// Constraints:
//
//     1 <= s.length <= 1000
//     s[i] = 'L' or 'R'
//


class Solution {
    public int balancedStringSplit(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return mytry(s);
    }

    private int mytry(String s) {
        int sum = 0;
        int result = 0;
        for (char c : s.toCharArray()) {
            if (c == 'L') {
                sum--;
            } else {
                sum++;
            }
            if (sum == 0) {
                result++;
            }
        }
        return result;
    }
}
