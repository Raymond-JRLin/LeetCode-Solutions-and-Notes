// 172. Factorial Trailing Zeroes
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer n, return the number of trailing zeroes in n!.
//
// Example 1:
//
// Input: 3
// Output: 0
// Explanation: 3! = 6, no trailing zero.
// Example 2:
//
// Input: 5
// Output: 1
// Explanation: 5! = 120, one trailing zero.
// Note: Your solution should be in logarithmic time complexity.


class Solution {
    public int trailingZeroes(int n) {
        if (n < 1) {
            return 0;
        }

        // return my_try(n);

        // recursion
        return method2_recursion(n);
    }

    private int method2_recursion(int n) {
        if (n <= 0) {
            return 0;
        }
        return n / 5 + method2_recursion(n / 5);
        // return n > 0 ? (n / 5) + method2_recursion(n / 5) : 0;
    }

    private int my_try(int n) {
        // 我记得之前做过这题： 只有 10 才会有 0， 那算它的质因数 2 * 5 的个数， 而 2 的个数远超过 5， 因为 2 会是很多数的因子， 所以可以算 5 是因子的数的个数
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n /= 5;
        }
        return count;

    }
}
