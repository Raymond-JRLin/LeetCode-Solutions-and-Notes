// 50. Pow(x, n)
// DescriptionHintsSubmissionsDiscussSolution
// Implement pow(x, n), which calculates x raised to the power n (xn).
//
// Example 1:
//
// Input: 2.00000, 10
// Output: 1024.00000
// Example 2:
//
// Input: 2.10000, 3
// Output: 9.26100
// Example 3:
//
// Input: 2.00000, -2
// Output: 0.25000
// Explanation: 2-2 = 1/22 = 1/4 = 0.25
// Note:
//
// -100.0 < x < 100.0
// n is a 32-bit signed integer, within the range [−231, 231 − 1]


class Solution {
    public double myPow(double x, int n) {
        if (x == 0.0) {
            return 0.0;
        }
        if (n == 0) {
            return 1.0;
        }
        if (n == 1) {
            return x;
        }
        if (n < 0) {
            // in case of MIN_VALUE to OverFlow so plus 1 and then multiply x
            return (1 / (x * myPow(x, - (n + 1))));
        }

        // return mytry(x, n);

        return method2(x, n);
    }

    private double method2(double x, int n) {
        // iteration: apparently cannot multipy directly one by one, we should also use the idea of D&C, that is multiply double and double and so on, and finally get the final result
        boolean isNegative = false;
        if (n < 0) {
            isNegative = true;
            // x = 1 / x;
            n = - (n + 1);
        }
        double result = 1;
        double temp = x;
        while (n != 0) {
            if (n % 2 == 1) {
                result *= temp;
            }
            temp *= temp;
            n /= 2;
        }
        if (isNegative) {
            result *= x;
            result = 1 / result;
        }
        return result;
    }

    private double mytry(double x, int n) {
        // cannot be simple loop => D&C
        // ref: http://www.cnblogs.com/yuzhangcmu/p/4174683.html
        if (n == 0) {
            return 1.0;
        }
        if (n == 1) {
            return x;
        }
        // calculate first to avoid OverFlow, if directly return mytry(x, n / 2) * mytry(x, n / 2) * mytry(x, n % 2) would have a very very deep stack since it will hold the all the result in the stack for the first recursion and then do the 2nd recursion
        double half = mytry(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }

    }
}
