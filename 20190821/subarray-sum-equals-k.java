// 560. Subarray Sum Equals K
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
//
// Example 1:
//
// Input:nums = [1,1,1], k = 2
// Output: 2
//
// Note:
//
//     The length of the array is in range [1, 20,000].
//     The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // return mytry(nums, k);

        return method2(nums, k);
    }

    // 这题【不能】用 sliding window!

    private int method2(int[] nums, int k) {
        // 解法其实和 325. Maximum Size Subarray Sum Equals k 很像， 思路其实是一样的， 因为求 max size， 所以 prefix sum map 的 value 记录的是 index
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(); // <prefix sum, freq>
        int result = 0;
        int sum = 0;
        map.put(sum, 1); // 初始状态， 即从 index = 0 到 i， 前面不需要减 subarray， 从一开始算的就是
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                result += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    private int mytry(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                if (prefix[j] - prefix[i] == k) {
                    result++;
                }
            }
        }
        return result;
    }
}
