// 325. Maximum Size Subarray Sum Equals k
// DescriptionHintsSubmissionsDiscussSolution
// Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
//
// Note:
// The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
//
// Example 1:
//
// Input: nums = [1, -1, 5, -2, 3], k = 3
// Output: 4
// Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
// Example 2:
//
// Input: nums = [-2, -1, 2, 1], k = 1
// Output: 2
// Explanation: The subarray [-1, 2] sums to 1 and is the longest.
// Follow Up:
// Can you do it in O(n) time?
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // return mytry(nums, k);

        return method2(nums, k);
    }

    private int method2(int[] nums, int k) {
        // O(N) time complexity, use HashMap to record the sum and its corresponding index
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(); // <sum, index of getting sum>
        int result = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i]; // 从一开始 index == 0 累加
            if (sum == k) {
                // 如果正好 == k， 那就是从一开始 index == 0 就有， 即是最长的
                result = Math.max(result, i + 1);
            } else if (map.containsKey(sum - k)) {
                // there's prefix sum equaling to sum - k, so SUM{[map.get(sum - k), i]} == k
                result = Math.max(result, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return result;
    }

    private int mytry(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        int result = 0;
        for (int i = 0; i < n + 1; i++) {
            int target = prefix[i] + k;
            for (int j = n; j > i; j--) {
                if (prefix[j] == target) {
                    result = Math.max(result, j - i);
                    break;
                }
            }
        }
        return result;
    }
}
