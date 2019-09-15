// 1191. K-Concatenation Maximum Sum
// User Accepted: 1155
// User Tried: 2290
// Total Accepted: 1183
// Total Submissions: 7718
// Difficulty: Medium
// Given an integer array arr and an integer k, modify the array by repeating it k times.
//
// For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
//
// Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.
//
// As the answer can be very large, return the answer modulo 10^9 + 7.
//
//
//
// Example 1:
//
// Input: arr = [1,2], k = 3
// Output: 9
// Example 2:
//
// Input: arr = [1,-2,1], k = 5
// Output: 2
// Example 3:
//
// Input: arr = [-1,-2], k = 7
// Output: 0
//
//
// Constraints:
//
// 1 <= arr.length <= 10^5
// 1 <= k <= 10^5
// -10^4 <= arr[i] <= 10^4
//


class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return method1(arr, k);
    }

    private int method1(int[] arr, int k) {
        int n = arr.length;
        final long MOD = (long) Math.pow(10, 9) + 7;
        // use sum of whole array to determine if we need to repeat given array
        long sum = 0L;
        for (int num : arr) {
            sum += num;
        }
        // max subarray sum in the middle of give array, it could be 0 selecting subarray with length of 0
        // [a1, a2, .. (... max subarray ...), ..., a9, a10]
        long maxSubarraySum = getMaxSubarraySum(arr) % MOD;
        // concatenation max subarray
        // [(... max1 ...), ..., a9, a10] + [a1, a2, ... ,(... max2 ...)] => max1 + max2
        long prefixSum = getPrefixMax(arr) % MOD;
        long suffixSum = getSuffixMax(arr) % MOD;
        // if whole array sum is > 0 then can use them, otherwise just ignore repeating
        long repeatSum = sum > 0 ? sum * (k - 2) % MOD : 0;
        return (int) (Math.max(maxSubarraySum, (prefixSum + suffixSum + repeatSum) % MOD));
    }
    private long getSuffixMax(int[] nums) {
        long sum = 0;
        long result = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            sum += nums[i];
            result = Math.max(result, sum);
        }
        return result;
    }
    private long getPrefixMax(int[] nums) {
        long sum = 0;
        long result = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            result = Math.max(result, sum);
        }
        return result;
    }
    private long getMaxSubarraySum(int[] nums) {
        long sum = 0;
        long result = Integer.MIN_VALUE;
        for (int num : nums) {
            sum = Math.max(sum + num, num);
            result = Math.max(result, sum);
        }
        // 注意审题： Note that the length of the sub-array can be 0 and its sum in that case is 0.
        return Math.max(result, 0);
    }
}
