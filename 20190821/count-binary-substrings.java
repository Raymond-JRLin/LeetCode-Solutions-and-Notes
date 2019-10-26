// 696. Count Binary Substrings
// DescriptionHintsSubmissionsDiscussSolution
//
// Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
//
// Substrings that occur multiple times are counted the number of times they occur.
//
// Example 1:
//
// Input: "00110011"
// Output: 6
// Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
//
// Notice that some of these substrings repeat and are counted the number of times they occur.
//
// Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
//
// Example 2:
//
// Input: "10101"
// Output: 4
// Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
//
// Note:
// s.length will be between 1 and 50,000.
// s will only consist of "0" or "1" characters.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public int countBinarySubstrings(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        // return mytry(s);

        // return method2(s);

        return method3(s);
    }

    private int method3(String s) {
        // 和 method2 相同， 只不过用 variables 存， O(N) time and O(1) space
        int n = s.length();
        int result = 0;
        int curr = 1; // index == 0 的 char 的个数
        int prev = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                // 和前一个相同的 char
                curr++;
            } else {
                // 不同的 char， 存下前面的结果
                result += Math.min(prev, curr);
                // 并新开一个 group, prev 就为刚刚走过的 char 的个数
                prev = curr;
                curr = 1;
            }
        }
        // 别忘了最后一个 char 还要再比一次
        // 因为在 for loop 中总是得到不同的 char 才加前面的结果
        return result + Math.min(prev, curr);
    }

    private int method2(String s) {
        // 可以把 s 中的 0 和 1 分别 group count 起来， 这样就可以很快地知道连续的 0 和 1 分别有多少
        // 类似于 prefix sum 的感觉
        // 然后就可以把相邻的 0 和 1 拿去组合， 可以组成的 string 的长度是两者的 min， 这个 min 也就是多少种的个数
        // O(N) time and O(N) space
        int n = s.length();
        int index = 0; // group 数组中有保存 group count 的末位 index
        int[] group = new int[n];
        group[index] = 1; // 第一位先有 1 个
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                // 和前一个相同的 char
                group[index]++;
            } else {
                // 新开一个 group
                index++;
                group[index] = 1;
            }
        }
        int result = 0;
        for (int i = 0; i < index; i++) {
            result += Math.min(group[i], group[i + 1]);
        }
        return result;
    }

    private int mytry(String s) {
        // 通过分析， 我觉得可以找两个相邻的不等字符， 即 10 或 01， 然后从两边展开， 能同时展开一步， 那么就多一个
        int n = s.length();
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                int left = i;
                int right = i + 1;
                while (left >= 0 && right < n) {
                    if (s.charAt(left) == s.charAt(i) && s.charAt(right) == s.charAt(i + 1)) {
                        result++;
                        left--;
                        right++;
                    } else {
                        break;
                    }
                }
            }
        }
        return result;
    }
}
