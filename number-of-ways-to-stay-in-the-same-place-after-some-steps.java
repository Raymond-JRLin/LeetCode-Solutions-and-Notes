// 1269. Number of Ways to Stay in the Same Place After Some Steps
//
//     User Accepted: 782
//     User Tried: 1159
//     Total Accepted: 813
//     Total Submissions: 2727
//     Difficulty: Hard
//
// You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array or stay in the same place  (The pointer should not be placed outside the array at any time).
//
// Given two integers steps and arrLen, return the number of ways such that your pointer still at index 0 after exactly steps steps.
//
// Since the answer may be too large, return it modulo 10^9 + 7.
//
//
//
// Example 1:
//
// Input: steps = 3, arrLen = 2
// Output: 4
// Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
// Right, Left, Stay
// Stay, Right, Left
// Right, Stay, Left
// Stay, Stay, Stay
//
// Example 2:
//
// Input: steps = 2, arrLen = 4
// Output: 2
// Explanation: There are 2 differents ways to stay at index 0 after 2 steps
// Right, Left
// Stay, Stay
//
// Example 3:
//
// Input: steps = 4, arrLen = 2
// Output: 8
//
//
//
// Constraints:
//
//     1 <= steps <= 500
//     1 <= arrLen <= 10^6
//


class Solution {
    public int numWays(int steps, int arrLen) {


        // return mytry(steps, arrLen);

        return method2(steps, arrLen);
    }

    private int method2(int steps, int len) {
        // iterative DP
        final int MOD = (int) Math.pow(10, 9) + 7;
        // definition: f[i] := # ways to stay in i index
        int[] f = new int[len]; // steps 更小的话， 后面的其实就为 0 了
        // init
        f[0] = 1; // stay at original position
        // DP
        // 走 steps 步
        for (int i = 1; i <= steps; i++) {
            int[] next = new int[len];
            for (int j = 0; j < Math.min(steps, len); j++) {
                // pruning: 每次查看 Math.min(steps, len) 这么多， 不然会 TLE
                // 待在原地， 就是上一轮的结果
                next[j] = f[j];
                // 从左边走过来 1 步
                if (j > 0) {
                    next[j] = (next[j] + f[j - 1] % MOD) % MOD;
                }
                // 从右边走过来 1 步
                if (j < len - 1) {
                    next[j] = (next[j] + f[j + 1] % MOD) % MOD;
                }
            }
            f = next;
        }
        // result
        return f[0];
    }

    private int mytry(int steps, int len) {
        // 没加 memo TLE， 加了 MLE
        // pruning 是 steps 以外的 len 其实都到不了
        final int MOD = (int) Math.pow(10, 9) + 7;
        int[][] memo = new int[steps + 1][Math.min(steps, len) + 1];
        for (int i = 1; i < steps + 1; i++) {
            Arrays.fill(memo[i], -1);
        }
        return recursion(steps, len, 0, MOD, memo);
    }

    private int recursion(int steps, int len, int index, int MOD, int[][] memo) {
        // out of bound
        if (index < 0 || index >= len) {
            return 0;
        }
        if (steps == 0) {
            if (index == 0) {
                return memo[steps][index] = 1;
            } else {
                return memo[steps][index] = 0;
            }
        }
        if (index > steps) {
            return memo[steps][index] = 0;
        }
        if (memo[steps][index] != -1) {
            return memo[steps][index];
        }
        int count = 0;
        count = (count + recursion(steps - 1, len, index - 1, MOD, memo) % MOD) % MOD;
        count = (count + recursion(steps - 1, len, index, MOD, memo) % MOD) % MOD;
        count = (count + recursion(steps - 1, len, index + 1, MOD, memo) % MOD) % MOD;
        return memo[steps][index] = count % MOD;
    }
}
