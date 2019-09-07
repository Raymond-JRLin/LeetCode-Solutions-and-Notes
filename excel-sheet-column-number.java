// 171. Excel Sheet Column Number
// DescriptionHintsSubmissionsDiscussSolution
// Given a column title as appear in an Excel sheet, return its corresponding column number.
//
// For example:
//
//     A -> 1
//     B -> 2
//     C -> 3
//     ...
//     Z -> 26
//     AA -> 27
//     AB -> 28
//     ...
// Example 1:
//
// Input: "A"
// Output: 1
// Example 2:
//
// Input: "AB"
// Output: 28
// Example 3:
//
// Input: "ZY"
// Output: 701


class Solution {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return method1(s);
    }

    private int method1(String s) {
        // 一开始想倒过来求， 从后往前第一位没有乘 26， 第二位乘 1 个 26， 第三位乘 2 个 26， 但是这样很麻烦不好处理， 观察一下求和公式： given KLMN, sum = (N - 'A') + (M - 'A') * 26 + (L - 'A') * 26 * 26 + (K - 'A') * 26 * 26 * 26, 所以从前往后算， 每次多乘一个 26 就好
        int n = s.length();
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = result * 26 + s.charAt(i) -'A' + 1;;
        }
        return result;
    }
}
