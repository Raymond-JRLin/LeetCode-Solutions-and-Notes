// 198. House Robber
// DescriptionHintsSubmissionsDiscussSolution
// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
// Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
//
// Example 1:
//
// Input: [1,2,3,1]
// Output: 4
// Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//              Total amount you can rob = 1 + 3 = 4.
// Example 2:
//
// Input: [2,7,9,3,1]
// Output: 12
// Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
//              Total amount you can rob = 2 + 9 + 1 = 12.


class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // return mytry(nums);

        // decrease to O(1) space
        // return method2_variables(nums);

        // another version of 2 variables DP, but I think it's more difficult to understand compared to my method2
        // return method3_dp(nums);

        // 在 discussion 中还看到可以利用是否 index % 2 == 0 来进行不同的 variable 的加和：
        // index % 2 == 0 ? even = Math.max(even + curr, odd) : odd = Math.max(odd + curr, even), return Math.max(odd, even)

        // return method4(nums);

        return method5(nums);
    }

    private int method5(int[] nums) {
        // DP with idead of method4
        int n = nums.length;
        // definition: 注意和 mytry method2 定义不同， 仍然使用了 method4 的定义
        int[] memo = new int[n + 1]; // # money we can steal when use last i houses left
        // initialization
        memo[0] = 0;
        memo[1] = nums[n - 1]; // 1 house (the last one) left
        // DP
        for (int i = 2; i < n + 1; i++) {
            int selectFirst = nums[nums.length - i] + memo[i - 2];
            int unselectFirst = memo[i - 1];
            memo[i] = Math.max(selectFirst, unselectFirst);
        }
        // result
        return memo[n];
    }

    private int method4(int[] nums) {
        // recursion
        int n = nums.length;
        // definition
        int[] memo = new int[n + 1]; // # money we can steal when use last i houses left
        // initialization
        Arrays.fill(memo, -1);
        return recursion(memo, nums, n);
    }
    private int recursion(int[] memo, int[] nums, int i) {
        // invalid input
        if (i < 1) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int selectFirst = nums[nums.length - i] + recursion(memo, nums, i - 2); // cannot choose 1st and 2nd element
        int unselectFirst = recursion(memo, nums, i - 1); // cut the 1st element
        return memo[i] = Math.max(selectFirst, unselectFirst);
    }

    private int method3_dp(int[] nums) {
        // just another version of method2 with 2 variables
        int local = 0; // prev prev max - do not rob
        int global = 0; // prev max - rob
        // [..., local, global, current position, ...]
        for (int num : nums) {
            int temp = global;
            global = Math.max(local + num, global); // move global pointer to current position
            local = temp; // move local pointer to previous position
        }
        return global;
    }

    private int method2_variables(int[] nums) {
        // 这个就完全按照 mytry 二维 DP 降维处理
        int n = nums.length;
        // use 2 variables to record values of prevouse house of robbing and not robbing conditions
        int rob = nums[0];
        int not = 0;
        for (int i = 1; i < n; i++) {
            int temp = not;
            not = Math.max(not, rob); // case 1: do not rob
            rob = temp + nums[i]; // case 2: rob
        }
        return Math.max(not, rob);
    }

    private int mytry(int[] nums) {
        int n = nums.length;
        // use DP to solve, each position can be chose as rob or not rob
        // definition
        int[][] dp = new int[n][2];
        // initialization
        dp[0][1] = nums[0];
        // DP
        for (int i = 1; i < n; i++) {
            // 但是这类问题有个地方要注意： 并不一定要求一定是隔着一个抢， 也可以隔着俩， 只要不是抢连续的都行
            // case 1: do not rob this house
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]); // 取较大值， 因为上一个可抢可不抢
            // case 2: rob this house
            dp[i][1] = dp[i - 1][0] + nums[i]; // 这个抢了， 上一个只能不抢
        }
        // result
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
