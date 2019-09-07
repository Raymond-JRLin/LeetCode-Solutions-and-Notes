// 343. Integer Break
// DescriptionHintsSubmissionsDiscussSolution
// Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.
//
// Example 1:
//
// Input: 2
// Output: 1
// Explanation: 2 = 1 + 1, 1 × 1 = 1.
// Example 2:
//
// Input: 10
// Output: 36
// Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
// Note: You may assume that n is not less than 2 and not larger than 58.
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        return my_try(n);
    }
    private int my_try(int n) {
        // 在纸上走几个数， 发现后面的数和前面的有关， 当前的数可以从 1 开始拆成两个数， 直到 n / 2, 而这两个数又可以分别拆成它们作 n 时候的最大值， 因此可以试试 DP
        // definition
        int[] f = new int[n + 1];
        // initialization
        f[1] = 1; // 为了计算方便， 乘以 1 就是 1
        f[2] = 2; // real result
        // DP
        for (int i = 3; i <= n; i++) {
            f[i] = 1; // at least, we have 1
            for (int j = 1; j <= i / 2; j++) {
                // 注意这里有多种情况
                // 就是直接的两数相乘
                // f[i] = Math.max(f[i], j * (i - j));
                // // 其中一个数字拆开， 两个数字都拆开
                // int combination = Math.max(f[j] * f[i - j], Math.max(j * f[i - j], f[j] * (i - j)));
                // f[i] = Math.max(f[i], combination);

                // or we can do: 当 i 拆成 j 与 i - j 的时候， 找 j 与 i - j 的最大值相乘即可
                f[i] = Math.max(f[i], Math.max(j, f[j]) * Math.max(i - j, f[i - j]));
            }
        }
        // result
        return f[n];
    }
}
