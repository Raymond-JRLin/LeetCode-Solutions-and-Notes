// 377. Combination Sum IV
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
//
// Example:
//
// nums = [1, 2, 3]
// target = 4
//
// The possible combination ways are:
// (1, 1, 1, 1)
// (1, 1, 2)
// (1, 2, 1)
// (1, 3)
// (2, 1, 1)
// (2, 2)
// (3, 1)
//
// Note that different sequences are counted as different combinations.
//
// Therefore the output is 7.
// Follow up:
// What if negative numbers are allowed in the given array?
// How does it change the problem?
// What limitation we need to add to the question to allow negative numbers?


class Solution {
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target < 0) {
            return 0;
        }

        // return mytry(nums, target);

        // return method1(nums, target);

        // return method2(nums, target);

        return method3(nums, target);
    }

    private int method3(int[] nums, int target) {
        // recursion DP
        // definition
        int[] memo = new int[target + 1];
        // initialiization
        Arrays.fill(memo, -1);
        memo[0] = 1;
        // DP and result
        return recursion(nums, target, memo);
    }
    private int recursion(int[] nums, int target, int[] memo) {
        if (target < 0) {
            return 0;
        }
        if (memo[target] != -1) {
            return memo[target];
        }
        int sum = 0; // 用一个先记录一下， 避免在 recursion 的过程中用到， 然后立马值就变了
        for (int i = 0; i < nums.length; i++) {
            sum += recursion(nums, target - nums[i], memo);
        }
        return memo[target] = sum;
    }

    private int method2(int[] nums, int target) {
        // recursion: TLE
        if (target == 0) {
            return 1;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target - nums[i] >= 0) {
                sum += method2(nums, target - nums[i]);
            }
        }
        return sum;
    }

    private int method1(int[] nums, int target) {
        // use DP: since we just need to get how many way we can get the target, no need to know the exact combination; also, we can reuse those numbers, so we just care the sum of numbers we picked
        int n = nums.length;
        // definition
        int[] f = new int[target + 1];
        // initializaiton
        f[0] = 1;
        // DP
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < n; j++) {
                if (i - nums[j] >= 0) {
                    f[i] += f[i - nums[j]];
                }
            }
        }
        // result
        return f[target];
    }

    private int result;
    private int mytry(int[] nums, int target) {
        // every time we start from the index of 0, then TLE
        result = 0;
        dfs(nums, target, 0);
        return result;
    }
    private void dfs(int[] nums, int target, int sum) {
        if (sum == target) {
            result++;
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            dfs(nums, target, sum + nums[i]);
        }
    }
}
