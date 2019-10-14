// 1223. Dice Roll Simulation
//
//     User Accepted: 528
//     User Tried: 1089
//     Total Accepted: 549
//     Total Submissions: 1915
//     Difficulty: Medium
//
// A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint to the generator such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times.
//
// Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be obtained with exact n rolls.
//
// Two sequences are considered different if at least one element differs from each other. Since the answer may be too large, return it modulo 10^9 + 7.
//
//
//
// Example 1:
//
// Input: n = 2, rollMax = [1,1,2,2,2,3]
// Output: 34
// Explanation: There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations. In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively, therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
//
// Example 2:
//
// Input: n = 2, rollMax = [1,1,1,1,1,1]
// Output: 30
//
// Example 3:
//
// Input: n = 3, rollMax = [1,1,1,2,2,3]
// Output: 181
//
//
//
// Constraints:
//
//     1 <= n <= 5000
//     rollMax.length == 6
//     1 <= rollMax[i] <= 15
//


class Solution {
    public int dieSimulator(int n, int[] rollMax) {

        // return mytry(n, rollMax);

        return method1(n, rollMax);

        // return method2(n, rollMax);
    }

    private int method2(int n, int[] roll) {
        // recursion
        // https://leetcode.com/problems/dice-roll-simulation/discuss/403736/ChineseC++-1223.
        final long MOD = (long) Math.pow(10, 9) + 7;
        // definition: memo[k][index][n] = 骰子面为 index 出现了 k 次， 还剩下 n 次骰子可以掷， 此时的个数
        long[][][] memo = new long[16][6][n + 1];
        for (long[][] m : memo) {
            for (long[] nums : m) {
                Arrays.fill(nums, -1);
            }
        }
        return (int) recursion(n, roll, memo, 0, 0, n, MOD);
    }
    private long recursion(int n, int[] roll, long[][][] memo, int k, int index, int dice, long MOD) {
        if (dice == 0) {
            return 1;
        }
        if (memo[k][index][dice] != -1) {
            return memo[k][index][dice];
        }
        long sum = 0;
        for (int i = 0; i < 6; i++) {
            if (i != index) {
                // 不同的骰子面， 不会有任何冲突
                sum += recursion(n, roll, memo, 1, i, dice - 1, MOD) % MOD;
            } else {
                // 相同的骰子面， 需要考虑是否达到了连续的个数限制
                if (k < roll[i]) {
                    // 还没达到， 继续走
                    sum += recursion(n, roll, memo, k + 1, i, dice - 1, MOD) % MOD;
                }
                // 达到了， 就忽略
            }
        }
        memo[k][index][dice] = (sum % MOD);
        return memo[k][index][dice];
    }

    private int method1(int n, int[] roll) {
        // ref: https://leetcode.com/problems/dice-roll-simulation/discuss/403756/Java-Share-my-DP-solution
        final long MOD = (long) Math.pow(10, 9) + 7;
        Map<Integer, Long> map = new HashMap<>(); // <i 个骰子, 总个数>
        // definition: f[i][j] = 掷 i 个骰子， 以 j 结尾有多少种
        long[][] f = new long[n + 1][6];
        // initialization
        for (int j = 0; j < 6; j++) {
            f[1][j] = 1L;
        }
        long prev = 6L;
        map.put(1, prev); //记下每一轮的总个数结果， 下面需要倒回去找
        // DP
        for (int i = 2; i <= n; i++) {
            long curr = 0L;
            for (int j = 0; j < 6; j++) {
                if (i - roll[j] < 1) {
                    // 还没有达到连续的限制个数， 直接就是上一轮结果 * 1
                    f[i][j] = prev % MOD;
                } else {
                    if (i - roll[j] == 1) {
                        // 达到限制个数， 正好从第一个骰子开始， 那么只需要减 1 即可， 即减掉前 n 个都为 j 的
                        f[i][j] = (prev - 1) % MOD;
                    } else {
                        // 如果中间某段达到连续个数的限制， e.g. _ _ _ j, roll[1] = 2
                        // 此时如果考虑哪些情况可以， 会很麻烦， 所以这么想：
                        // 只需要减掉不成立的情况即可， 即 _11j， 不需要考虑 11_j 因为这应该在上一轮处理过了， 而且很简单， 直接 prev - 1 就好
                        // 因为我们 f[i][j] 的定义是以 j 结尾， 所以意味着 f[i][1] 这个 j 一定是 1， 即 _11[1]
                        // 那么合法的 _11 有多少种呢， 就是 _ (n == 1) 有多少种， 因为 11 是固定的， 只需要考虑第一个位置有多少
                        // 注意这里 _ 不能是 1， 否则不合法， 所以问题变成： 当第一个 _ 不是 j 的时候有多少种
                        // 就是 SUM{f[i - rollMax[j][X]] (X != j)} - f[i - rollMax[j] - 1][j]
                        // 即 i - rollMax[j] - 1 轮不以 j 结尾的个数
                        f[i][j] = (prev - (map.get(i - roll[j] - 1) - f[i - roll[j] - 1][j]) + MOD) % MOD; // 做了减法， 记得补一下 MOD
                    }
                }
                curr = (curr + f[i][j] % MOD) % MOD;
            }

            prev = curr % MOD;
            map.put(i, prev);
        }
        return (int) prev;
    }

    private int mytry(int n, int[] roll) {
        // wrong!
        // 错在当达到限制个数的时候， 状态转移方程不对
        final int MOD = (int) Math.pow(10, 9) + 7;
        // System.out.println(MOD);
        long[][] f = new long[n + 1][6];
        for (int j = 0; j < 6; j++) {
            f[1][j] = 1;
        }
        long prev = 6;
        for (int i = 2; i <= n; i++) {
            long curr = 0L;
            for (int j = 0; j < 6; j++) {
                if (i - roll[j] < 1) {
                    f[i][j] = prev % MOD;
                } else {
                    f[i][j] = prev - f[i - roll[j]][j];
                //     if (f[i][j] < 0) {
                //     System.out.println(" f[i][j] = " + prev + " - " + f[i -roll[j]][j]);
                // }
                }
                // long temp = curr;
                curr += f[i][j];
//                 if (i >= 16) {
//                     System.out.println(" curr = " + temp + " + " + f[i][j] + " = " + curr);
//                 }

            }
            // for (int j = 0; j < 6; j++) {
            //     System.out.print(f[i][j] +" ");
            // }

            prev = (curr);
            // System.out.println(" curr, prev = " + curr + " " + prev);
        }
        // result
        return (int) (prev % MOD);
    }
}
