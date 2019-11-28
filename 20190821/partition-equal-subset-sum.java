// 416. Partition Equal Subset Sum
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
//
// Note:
//
//     Each of the array element will not exceed 100.
//     The array size will not exceed 200.
//
//
//
// Example 1:
//
// Input: [1, 5, 11, 5]
//
// Output: true
//
// Explanation: The array can be partitioned as [1, 5, 5] and [11].
//
//
//
// Example 2:
//
// Input: [1, 2, 3, 5]
//
// Output: false
//
// Explanation: The array cannot be partitioned into equal sum subsets.
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        // return mytry(nums);

        // return mytry2(nums);

        return method3(nums);
    }

    private boolean method3(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;

        // definition
        boolean[][] f = new boolean[n + 1][target + 1];
        // init
        f[0][0] = true;
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j <= target; j++) {
                f[i][j] = f[i - 1][j]; // 不选
                if (j - nums[i - 1] >= 0) {
                    f[i][j] = f[i][j] || f[i - 1][j - nums[i - 1]]; // 选当前数加入 sum
                }
            }
        }
        // result
        return f[n][target];
    }

    private boolean mytry2(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        Arrays.sort(nums);
        int target = sum / 2;
        int[][] memo = new int[n][target + 1];
        return dfs2(nums, 0, 0, target, memo) == 1;
    }
    private int dfs2(int[] nums, int index, int curr, int target, int[][] memo) {
        if (index == nums.length) {
            return curr == target ? 1 : -1;
        }
        if (curr > target) {
            return -1;
        }
        if (curr == target) {
            return memo[index][curr] = 1;
        }
        if (memo[index][curr] != 0) {
            return memo[index][curr];
        }
        if (dfs2(nums, index + 1, curr + nums[index], target, memo) == 1 ||
            dfs2(nums, index + 1, curr, target, memo) == 1) {
            return memo[index][curr] = 1;
        }
        return memo[index][curr] = -1;
    }

    private boolean mytry(int[] nums) {
        // 如果直接只是 memo[n] 会 TLE， 因为我们没办法判断 false 的时候可以返回， 因为有其他路径到当前 index
        // 而这样的话， 只能在 true 的时候可以直接 pruning 返回， 那么就会 TLE
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        int[][] memo = new int[n][target + 1];
        return dfs(nums, 0, 0, target, memo) == 1;
    }
    private int dfs(int[] nums, int index, int curr, int target, int[][] memo) {
        if (index == nums.length) {
            return curr == target ? 1 : -1;
        }
        if (curr > target) {
            return -1;
        }
        if (curr == target) {
            return memo[index][curr] = 1;
        }
        if (memo[index][curr] != 0) {
            return memo[index][curr];
        }
        for (int i = index; i < nums.length; i++) {
            if (dfs(nums, i + 1, curr + nums[i], target, memo) == 1) {
                return memo[i][curr] = 1;
            }
        }
        return memo[index][curr] = -1;
    }
}
