// 6. ZigZag Conversion
// DescriptionHintsSubmissionsDiscussSolution
// The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
//
// P   A   H   N
// A P L S I I G
// Y   I   R
// And then read line by line: "PAHNAPLSIIGYIR"
//
// Write the code that will take a string and make this conversion given a number of rows:
//
// string convert(string s, int numRows);
// Example 1:
//
// Input: s = "PAYPALISHIRING", numRows = 3
// Output: "PAHNAPLSIIGYIR"
// Example 2:
//
// Input: s = "PAYPALISHIRING", numRows = 4
// Output: "PINALSIGYAHRPI"
// Explanation:
//
// P     I    N
// A   L S  I G
// Y A   H R
// P     I
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public String convert(String s, int numRows) {
        if (numRows <= 1 || s.length() <= numRows) {
            return s;
        }

        // return method1(s, numRows);

        return method2(s, numRows);
    }

    private String method2(String s, int row) {
        // 每行用 sb 来建立
        StringBuilder[] sbs = new StringBuilder[row];
        for (int i = 0; i < row; i++) {
            sbs[i] = new StringBuilder();
        }
        int n = s.length();
        // 每一个先向下再向上的V型 (2 * row - 2) 都是一个循环， 所以算出当前 char 应该在哪一行
        for (int i = 0; i < n; i++) {
            // go to find the index of current char
            int index = i % (2 * row - 2);
            // 注意从下往上的是倒序
            index = index < row ? index : 2 * row - 2 - index;
            sbs[index].append(s.charAt(i));
        }
        for (int i = 1; i < row; i++) {
            sbs[0].append(sbs[i].toString());
        }
        return sbs[0].toString();
    }

    private String method1(String s, int row) {
        // https://leetcode.com/problems/zigzag-conversion/discuss/3435/If-you-are-confused-with-zigzag-patterncome-and-see!
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            // 每一行正序之间的距离 （行内对应的char的距离） 是 2n - 2, 第二列开始与前一个倒序的 char 距离 2i
            int len1 = 2 * row - 2 - 2 * i;
            int len2 = 2 * i;
            int pos = i;
            // if (pos < n) {
                // get the first char in each row
                sb.append(s.charAt(pos));
            // }
            while (true) {
                pos += len1;
                // over string length, no need to go further
                if (pos >= n) {
                    break;
                }
                if (len1 != 0) {
                    // the 1st row doesn't have inverted zigzag char
                    sb.append(s.charAt(pos));
                }
                pos += len2;
                if (pos >= n) {
                    break;
                }
                if (len2 != 0) {
                    // the last row doesn't have inverted zigzag char
                    sb.append(s.charAt(pos));
                }
            }
        }
        return sb.toString();
    }
}
