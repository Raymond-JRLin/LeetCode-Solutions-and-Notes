// 29. Divide Two Integers
// DescriptionHintsSubmissionsDiscussSolution
// Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
//
// Return the quotient after dividing dividend by divisor.
//
// The integer division should truncate toward zero.
//
// Example 1:
//
// Input: dividend = 10, divisor = 3
// Output: 3
// Example 2:
//
// Input: dividend = 7, divisor = -3
// Output: -2
// Note:
//
// Both dividend and divisor will be 32-bit signed integers.
// The divisor will never be 0.
// Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.



class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }

        return method1(dividend, divisor);
    }

    private int method1(long dividend, long divisor) {
        int isNegative = 1;
        if (dividend < 0) {
            isNegative *= -1;
            dividend = -dividend;
        }
        if (divisor < 0) {
            isNegative *= -1;
            divisor = -divisor;
        }
        long result = divide(dividend, divisor);
        if (result >= Integer.MAX_VALUE) {
            if (isNegative < 0) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        return (int) result * isNegative;

    }
    private long divide(long dividend, long divisor) {
        if (dividend < divisor) {
            return 0;
        }
        long sum = divisor;
        long count = 1;
        while ((sum + sum) <= dividend) {
            sum += sum;
            count += count;
        }
        return count + divide(dividend - sum, divisor);
    }
}
