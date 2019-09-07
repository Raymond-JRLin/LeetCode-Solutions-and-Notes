// 806. Number of Lines To Write String
// DescriptionHintsSubmissionsDiscussSolution
// We are to write the letters of a given string S, from left to right into lines. Each line has maximum width 100 units, and if writing a letter would cause the width of the line to exceed 100 units, it is written on the next line. We are given an array widths, an array where widths[0] is the width of 'a', widths[1] is the width of 'b', ..., and widths[25] is the width of 'z'.
//
// Now answer two questions: how many lines have at least one character from S, and what is the width used by the last such line? Return your answer as an integer list of length 2.
//
//
//
// Example :
// Input:
// widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
// S = "abcdefghijklmnopqrstuvwxyz"
// Output: [3, 60]
// Explanation:
// All letters have the same length of 10. To write all 26 letters,
// we need two full lines and one line with 60 units.
// Example :
// Input:
// widths = [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
// S = "bbbcccdddaaa"
// Output: [2, 4]
// Explanation:
// All letters except 'a' have the same length of 10, and
// "bbbcccdddaa" will cover 9 * 10 + 2 * 4 = 98 units.
// For the last 'a', it is written on the second line because
// there is only 2 units left in the first line.
// So the answer is 2 lines, plus 4 units in the second line.
//
//
// Note:
//
// The length of S will be in the range [1, 1000].
// S will only contain lowercase letters.
// widths is an array of length 26.
// widths[i] will be in the range of [2, 10].
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int[] numberOfLines(int[] widths, String S) {
        if (S == null || S.length() == 0) {
            return new int[0];
        }

        return mytry(widths, S);
    }

    private int[] mytry(int[] widths, String s) {
        int count = 1;
        int sum = 0;
        for (char c : s.toCharArray()) {
            // System.out.print("current char is: " + c + ", ");
            int wid = widths[c - 'a'];
            // System.out.println("and wid is: " + wid);
            if (sum + wid > 100) {
                // System.out.println("add will exceed to " + (sum + wid));
                sum = wid;
                count++;
            } else {
                sum += wid;
                // System.out.println("will not exceed and sum would be " + sum);
            }
        }
        return new int[]{count, sum};
    }
}
