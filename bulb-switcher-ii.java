// 672. Bulb Switcher II
// DescriptionHintsSubmissionsDiscussSolution
// There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m unknown operations towards buttons, you need to return how many different kinds of status of the n lights could be.
//
// Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:
//
// Flip all the lights.
// Flip lights with even numbers.
// Flip lights with odd numbers.
// Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
//
//
// Example 1:
//
// Input: n = 1, m = 1.
// Output: 2
// Explanation: Status can be: [on], [off]
//
//
// Example 2:
//
// Input: n = 2, m = 1.
// Output: 3
// Explanation: Status can be: [on, off], [off, on], [off, off]
//
//
// Example 3:
//
// Input: n = 3, m = 1.
// Output: 4
// Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].
//
//
// Note: n and m both fit in range [0, 1000].
//


class Solution {
    public int flipLights(int n, int m) {
//         和 I 一样， 感觉是要找规律的
//         对于题目所给的 4 种方式： 1 + 2 --> 3, 1 + 3 --> 2, 2 + 3 --> 1， 因此只有 8 种结果： All_on, 1, 2, 3, 4, 1+4, 2+4, 3+4
        if (n == 0 || m == 0) {
            return 1; // return 的是状态数， 而不是亮灯的灯泡数
        }
        if (n == 1) {
            return 2; // 1 个灯泡只有开、关两种状态
        }
        if (n == 2) {
            // 两个灯泡
            if (m == 1) {
                // all on， 奇数， 偶数
                return 3;
            } else {

                return 4;
            }
        }
        // n > 2
        if (m == 1) {
            return 4;
        } else if (m == 2) {
            return 7;
        } else {
            return 8;
        }
    }
}
