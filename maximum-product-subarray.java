// 152. Maximum Product Subarray
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
//
// Example 1:
//
// Input: [2,3,-2,4]
// Output: 6
// Explanation: [2,3] has the largest product 6.
// Example 2:
//
// Input: [-2,0,-1]
// Output: 0
// Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // return mytry(nums);

        // return method2(nums);

        return method3(nums);
    }

    private int method3(int[] nums) {
        // decreased space DP: O(n) time and O(1) space
        int prevMin = nums[0];
        int prevMax = nums[0];
        int currMin;
        int currMax;
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currMin = Math.min(nums[i], Math.min(nums[i] * prevMin, nums[i] * prevMax));
            currMax = Math.max(nums[i], Math.max(nums[i] * prevMin, nums[i] * prevMax));
            result = Math.max(result, currMax);
            prevMin = currMin;
            prevMax = currMax;
        }
        return result;
    }

    private int method2(int[] nums) {
        // same DP idea, but more concise and resonable， O(n) time and O(n) space
        int n = nums.length;
        // definition
        int[] max = new int[n];
        int[] min = new int[n];
        // initialization
        max[0] = nums[0];
        min[0] = nums[0];
        int result = nums[0]; // update with DP process
        // DP
        for (int i = 1; i < n; i++) {
            // 不管正负， 把 3 个全拿来比较， 大的取大， 小的取小就好了
            max[i] = Math.max(nums[i], Math.max(max[i - 1] * nums[i], min[i - 1] * nums[i]));
            min[i] = Math.min(nums[i], Math.min(max[i - 1] * nums[i], min[i - 1] * nums[i]));
            result = Math.max(result, max[i]);
        }
        // result
        return result;
    }

    private int mytry(int[] nums) {
        // use DP to record max and min since there's positive and negative when we do multiplications, O(n) time and O(n) space
        int n = nums.length;
        // definition
        int[] max = new int[n];
        int[] min = new int[n];
        // initialization
        max[0] = nums[0];
        min[0] = nums[0];
        // DP
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) {
                max[i] = Math.max(nums[i], max[i - 1] * nums[i]);
                min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
            } else if (nums[i] < 0) {
                min[i] = Math.min(nums[i], max[i - 1] * nums[i]);
                max[i] = Math.max(nums[i], min[i - 1] * nums[i]);
            } else {
                max[i] = 0;
                min[i] = 0;
            }
        }
        // result: subarray, so max[n - 1] may not be the answer
        int result = max[0];
        for (int i = 1; i < n; i++) {
            result = Math.max(result, max[i]);
        }
        return result;
    }
}
