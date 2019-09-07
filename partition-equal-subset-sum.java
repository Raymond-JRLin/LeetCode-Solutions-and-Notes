// 416. Partition Equal Subset Sum
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
//
// Note:
//
// Each of the array element will not exceed 100.
// The array size will not exceed 200.
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
// Difficulty:Medium


class Solution {
    public boolean canPartition(int[] nums) {

        // return mytry(nums);

        // return method2(nums);

        return method3(nums);
    }

    private boolean method3(int[] nums) {
        // 1D DP imporoved from method2
        int n = nums.length;
        Arrays.sort(nums);

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        if (nums[n - 1] > target) {
            return false;
        }

        // definition: f[i] = whether all items can fulfill knapsack with size of i or not
        boolean[] f = new boolean[target + 1];
        // initialization
        f[0] = true;
        // DP
        for (int i = 1; i <= n; i++) {
            for (int j = target; j >= 0; j--) {
                // at least scenario, unpick this i item: no need to operate, just itself
                // pick this i item with size of nums[i], current size of knapsack is j
                if (j - nums[i - 1] >= 0) {
                    f[j] = f[j] || f[j - nums[i - 1]];
                }
            }
        }
        // result
        return f[target];
    }

    private boolean method2(int[] nums) {
        // DP - 0/1 knapsack
        // ref: https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
        int n = nums.length;
        Arrays.sort(nums);

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        if (nums[n - 1] > target) {
            return false;
        }

        // definition: f[i][j] = whether previous i items can fulfill knapsack with size of j or not
        boolean[][] f = new boolean[n + 1][target + 1];
        // initialization
        f[0][0] = true;
        for (int i = 1; i <= n; i++) {
            f[i][0] = true; // unpick whatever
        }
        for (int j = 1; j <= target; j++) {
            // attention: 0/1 knapsack is going to fulfill thee kanpsack
            f[0][j] = false; // no items, cannot fulfill knapsack
        }
        // DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                // at least scenario, unpick this i item
                f[i][j] = f[i - 1][j];
                // pick this i item with size of nums[i], current size of knapsack is j
                if (j - nums[i - 1] >= 0) {
                    f[i][j] = f[i][j] || f[i - 1][j - nums[i - 1]];
                }
            }
        }
        // result
        return f[n - 1][target];
    }

    private boolean mytry(int[] nums) {
        // use method in 698. Partition to K Equal Sum Subsets
        int n = nums.length;
        Arrays.sort(nums);

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        if (nums[n - 1] > target) {
            return false;
        }

        return dfs(nums, new int[2], target, n - 1);
    }
    private boolean dfs(int[] nums, int[] sums, int target, int index) {
        if (index < 0) {
            for (int sum : sums) {
                if (sum != target) {
                    return false;
                }
            }
            return true;
        }
        int val = nums[index];
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] + val <= target) {
                sums[i] += val;
                if (dfs(nums, sums, target, index - 1)) {
                    return true;
                }
                sums[i] -= val;
            }
            if (sums[i] == 0) {
                return false;
            }
        }
        return false;
    }
}
