// 70. Climbing Stairs
// DescriptionHintsSubmissionsDiscussSolution
// You are climbing a stair case. It takes n steps to reach to the top.
//
// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//
// Note: Given n will be a positive integer.
//
// Example 1:
//
// Input: 2
// Output: 2
// Explanation: There are two ways to climb to the top.
// 1. 1 step + 1 step
// 2. 2 steps
// Example 2:
//
// Input: 3
// Output: 3
// Explanation: There are three ways to climb to the top.
// 1. 1 step + 1 step + 1 step
// 2. 1 step + 2 steps
// 3. 2 steps + 1 step


class Solution {
    public int climbStairs(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }

        // return method1(n);

        return method2(n);
    }

    private int method2(int n) {
        int[] memo = new int[n + 1];
        return recursion(memo, n);
    }
    private int recursion(int[] memo, int i) {
        if (memo[i] != 0) {
            return memo[i];
        }
        if (i == 0 || i == 1) {
            memo[i] = 1;
            return memo[i];
        }
        memo[i] = recursion(memo, i - 1) + recursion(memo, i - 2);
        return memo[i];
    }

    private int method1(int n) {
        // definition
        int[] f = new int[n + 1];
        // initialization
        f[0] = 1;
        f[1] = 1;
        // DP
        for (int i = 2; i < n + 1; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        // result
        return f[n];
    }
}
