// 1318. Minimum Flips to Make a OR b Equal to c
//
//     User Accepted: 2969
//     User Tried: 3227
//     Total Accepted: 3040
//     Total Submissions: 5432
//     Difficulty: Medium
//
// Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
// Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
//
//
//
// Example 1:
//
// Input: a = 2, b = 6, c = 5
// Output: 3
// Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
//
// Example 2:
//
// Input: a = 4, b = 2, c = 7
// Output: 1
//
// Example 3:
//
// Input: a = 1, b = 2, c = 3
// Output: 0
//
//
//
// Constraints:
//
//     1 <= a <= 10^9
//     1 <= b <= 10^9
//     1 <= c <= 10^9
//


class Solution {
    public int minFlips(int a, int b, int c) {
        return mytry(a, b, c);
    }

    private int mytry(int a, int b, int c) {
        int res = a | b;
        int diff = res ^ c;
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (((1 << i) & diff) == 0) {
                continue;
            }
            if (((1 << i) & res) == 0) {
                // a | b => ith == 0, ith of a/b == 0, ith of c == 1
                result += 1;
            } else {
                // a | b => ith == 1, ith of c == 0
                // ith of a and b == 1
                if (((1 << i) & a) != 0 && ((1 << i) & b) != 0) {
                    result += 2;
                } else {
                    // one of ith of a/b == 1
                    result += 1;
                }
            }
        }
        return result;
    }
}
