// 371. Sum of Two Integers
// DescriptionHintsSubmissionsDiscussSolution
// Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
//
// Example 1:
//
// Input: a = 1, b = 2
// Output: 3
// Example 2:
//
// Input: a = -2, b = 3
// Output: 1
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int getSum(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        // if we are not allowed to use the operator + and -, we should consider bit manipulation
        while (b != 0) {
            int carry = a & b; // 找到进位的地方
            a = a ^ b; // 找不同， 0 + 1 = 1， 所以模拟这个加法的过程
            b = carry << 1; // 向前进一位
        }
        return a;
    }
}
