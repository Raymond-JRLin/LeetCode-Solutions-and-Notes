// 650. 2 Keys Keyboard
// DescriptionHintsSubmissionsDiscussSolution
// Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:
//
// Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
// Paste: You can paste the characters which are copied last time.
//
//
// Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.
//
// Example 1:
//
// Input: 3
// Output: 3
// Explanation:
// Intitally, we have one character 'A'.
// In step 1, we use Copy All operation.
// In step 2, we use Paste operation to get 'AA'.
// In step 3, we use Paste operation to get 'AAA'.
//
//
// Note:
//
// The n will be in the range [1, 1000].


class Solution {
    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 2;
        }
        // 写出来有个规律： n 的操作数 = n 最大的因子的操作数 + n / 这个因子
        // 所以涉及到当前的值会依赖于前面算过的值， 可以用 DP
        // return method1_dp(n);

        //
        return method2_greedy(n);
    }
    private int method1_dp(int n) {
        // definition
        int[] dp = new int[n + 1];
        // initialization
        dp[1] = 0;
        dp[2] = 2;
        // DP
        for (int i = 3; i < n + 1; i++) {
            for (int j = i - 1; j > 0; j--) {
                if (i % j == 0) {
                    dp[i] = dp[j] + i / j;
                    break; // don't forget to break, because we just need to find the MAX fact
                }
            }
        }
        // result
        return dp[n];
    }

    private int method2_greedy(int n) {
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                n /= i;
                sum += i;
            }
        }
        return sum;
    }
}
