// 868. Binary Gap
// User Accepted: 2029
// User Tried: 2123
// Total Accepted: 2075
// Total Submissions: 3180
// Difficulty: Easy
// Given a positive integer N, find and return the longest distance between two consecutive 1's in the binary representation of N.
//
// If there aren't two consecutive 1's, return 0.
//
//
//
// Example 1:
//
// Input: 22
// Output: 2
// Explanation:
// 22 in binary is 0b10110.
// In the binary representation of 22, there are three ones, and two consecutive pairs of 1's.
// The first consecutive pair of 1's have distance 2.
// The second consecutive pair of 1's have distance 1.
// The answer is the largest of these two distances, which is 2.
// Example 2:
//
// Input: 5
// Output: 2
// Explanation:
// 5 in binary is 0b101.
// Example 3:
//
// Input: 6
// Output: 1
// Explanation:
// 6 in binary is 0b110.
// Example 4:
//
// Input: 8
// Output: 0
// Explanation:
// 8 in binary is 0b1000.
// There aren't any consecutive pairs of 1's in the binary representation of 8, so we return 0.
//
//
// Note:
//
// 1 <= N <= 10^9


class Solution {
    public int binaryGap(int N) {

        // return mytry(N);

        return method2(N);
    }

    private int method2(int n) {
        // no need to convert to string, just check from right to left 1 by 1
        int result = 0;
        int prev = -1;
        int pos = 0;
        while (n > 0) {
            pos++;
            if ((n & 1) == 1) {
                // the last (rightmost) digit is 1
                if (prev != -1) {
                    result = Math.max(result, pos - prev);
                }
                prev = pos;
            }
            n >>= 1; // move right
        }
        return result;
    }

    private int mytry(int n) {
        String s = "";
        while (n > 0) {
            s = ((n & 1) == 1 ? "1" : "0") + s;
            n = (n >> 1);
        }
        int result = 0;
        int prev = -1;
        for (int i = 0; i < s.length(); i++) {
            if (prev != -1 && s.charAt(i) == '1') {
                result = Math.max(result, i - prev);
                prev = i;
            } else if (prev == -1) {
                prev = i;
            }
        }
        return result;
    }
}
