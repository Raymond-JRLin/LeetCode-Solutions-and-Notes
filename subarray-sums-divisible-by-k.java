// 974. Subarray Sums Divisible by K
// User Accepted: 999
// User Tried: 1811
// Total Accepted: 1035
// Total Submissions: 4365
// Difficulty: Medium
// Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
//
//
//
// Example 1:
//
// Input: A = [4,5,0,-2,-3,1], K = 5
// Output: 7
// Explanation: There are 7 subarrays with a sum divisible by K = 5:
// [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
//
//
// Note:
//
// 1 <= A.length <= 30000
// -10000 <= A[i] <= 10000
// 2 <= K <= 10000
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int subarraysDivByK(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // return mytry(A, K);

        // return method1(A, K);

        return method2(A, K);
    }

    private int method2(int[] nums, int k) {
        // 对 k 取余的结果不会超过 k， 所以也可以用数组
        int[] sums = new int[k];
        sums[0] = 1; // 初始化一下， 这样得到第一个可以整除的才能加 1
        int result = 0;
        int sum = 0;
        for (int num : nums) {
            sum = (sum + num % k + k) % k; // 注意这里可能会有负
            // 直接加上之前更新过的结果， 因为当前的 [i, j] 可以匹配前面 sum - 1 个， 再加它自身
            result += sums[sum];
            sums[sum]++;
        }
        return result;
    }

    private int method1(int[] nums, int k) {
        // 如果[0, i] 的 sum % k == [0, j] 的 sum % k， 那么 [i, j] 的 sum 就能被 k 整除, 因为 sum[i, j] % k == 0.
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 初始化一下， 这样得到第一个可以整除的才能加 1
        int result = 0;
        int sum = 0;
        for (int num : nums) {
            sum = (sum + num % k + k) % k; // 注意这里可能会有负
            // 直接加上之前更新过的结果， 因为当前的 [i, j] 可以匹配前面 sum - 1 个， 再加它自身
            result += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    private int mytry(int[] nums, int k) {
        // TLE
        int n = nums.length;
        int result = 0;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = (prefix[i] + nums[i]);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                if ((prefix[j] - prefix[i]) %  k == 0) {
                    result++;
                }
            }
        }
        return result;
    }
}
