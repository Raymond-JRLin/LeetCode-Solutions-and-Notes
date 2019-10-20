// 1234. Replace the Substring for Balanced String
//
//     User Accepted: 885
//     User Tried: 2291
//     Total Accepted: 909
//     Total Submissions: 5246
//     Difficulty: Medium
//
// You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.
//
// A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.
//
// Return the minimum length of the substring that can be replaced with any other string of the same length to make the original string s balanced.
//
// Return 0 if the string is already balanced.
//
//
//
// Example 1:
//
// Input: s = "QWER"
// Output: 0
// Explanation: s is already balanced.
//
// Example 2:
//
// Input: s = "QQWE"
// Output: 1
// Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.
//
// Example 3:
//
// Input: s = "QQQW"
// Output: 2
// Explanation: We can replace the first "QQ" to "ER".
//
// Example 4:
//
// Input: s = "QQQQ"
// Output: 3
// Explanation: We can replace the last 3 'Q' to make s = "QWER".
//
//
//
// Constraints:
//
//     1 <= s.length <= 10^5
//     s.length is a multiple of 4
//     s contains only 'Q', 'W', 'E' and 'R'.
//


class Solution {
    public int balancedString(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return method1(s);
    }

    // 注意这里找的是 substring， 而不可以跳着改

    public int method1(String s) {
        // ref: https://leetcode.com/problems/replace-the-substring-for-balanced-string/discuss/408978/JavaC%2B%2BPython-Sliding-Window/368193
        // 这里的 window 是去找可以替换的， 也就是说替换的 substring 是这个 window， 找最短的 window
        // 在这个 window 外面， 是 4 个字母全 <= n / 4
        int n = s.length();
        int[] count = new int[128];
        // 先计算 4 个字母的频率
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        int i = 0;
        int result = n;
        // window [i, j]
        for (int j = 0; j < n; j++) {
            count[s.charAt(j)]--; // 把 j 囊括进 window， 此时外面的 string 少了 j 位置的 char
            // 当 4 个字母全 <= n / 4， 也就意味着此时 window 可以整个替换
            // 注意这里 i 的取值， 当 i = j + 1 的时候， window 长度为 0， 是可能的， 此时整体 s 不需要替换即满足条件
            // 但是 j 取到 n - 1, 也就意味着 i 会越界， 所以这个要防止一下
            while (i < n && count['Q'] <= n / 4 && count['W'] <= n / 4
                         && count['E'] <= n / 4 && count['R'] <= n / 4) {
                result = Math.min(result, j + 1 - i);
                count[s.charAt(i)]++;
                i++;
            }
        }
        return result;
    }
}
