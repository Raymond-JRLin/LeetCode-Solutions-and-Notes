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
        // return mytry(dividend, divisor);

        return method1(dividend, divisor);
    }

    private int method1(int dividend, int divisor) {
        int sign = 1;
        if (dividend < 0) {
            sign *= -1;
        }
        if (divisor < 0) {
            sign *= -1;
        }
        long dividendLong = Math.abs((long) dividend);
        long divisorLong = Math.abs((long) divisor);
        long result = divide(dividendLong, divisorLong);
        if (result >= Integer.MAX_VALUE) {
            if (sign < 0) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        return (int) result * sign;
    }
    private long divide(long dividendLong, long divisorLong) {
        if (dividendLong < divisorLong) {
            return 0;
        }
        long sum = divisorLong;
        long count = 1;
        while ((sum + sum) <= dividendLong) {
            // 这样是指数级的增长
            // 最后得到的是 count 个 divisor， 其和值 <= dividend， 剩下的部分继续用相同的方法得到
            sum += sum;
            count += count;
        }
        return count + divide(dividendLong - sum, divisorLong);
    }

    private int mytry(int dividend, int divisor) {
        // TLE when input is [-2147483648, 1]
        int sign = 1;
        sign *= dividend < 0 ? -1 : 1;
        sign *= divisor < 0 ? -1 : 1;
        long dividendLong = Math.abs((long) dividend);
        long divisorLong = Math.abs((long) divisor);
        long result = 0;
        while (dividendLong >= divisorLong) {
            dividendLong -= divisorLong;
            result++;
        }
        if (result >= Integer.MAX_VALUE) {
            if (sign < 0) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        return (int) result * sign;
    }
}
