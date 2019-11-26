// 523. Continuous Subarray Sum
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
//
//
//
// Example 1:
//
// Input: [23, 2, 4, 6, 7],  k=6
// Output: True
// Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
//
// Example 2:
//
// Input: [23, 2, 6, 4, 7],  k=6
// Output: True
// Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
//
//
//
// Note:
//
//     The length of the array won't exceed 10,000.
//     You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        // return mytry(nums, k);

        return method2(nums, k);
    }

    // 这题的 corner case 非常多， 一定要注意
    private boolean method2(int[] nums, int k) {
        // sun 是 k 的整数倍， 那么如果 % k 就会 == 0
        // 要找一个这样 sum， 从 prefix sum 看出， 我们可以找两个 sum， 即 (a + b) % k == 0 => a % k == b % k
        // 所以类似于 2 sum
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(); // <sum, index>
        map.put(0, -1); // init: sum == 0 表示从 0 到 i 全部的 prefix sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (k != 0) {
                // k 可能会小于 0
                sum %= k;
            }
            if (map.containsKey(sum)) {
                if (i + 1 - map.get(sum) > 2) {
                    return true;
                }
            } else {
                // 有相同的 prefix sum， 不更新 index， 因为用最早的可以尽可能保证有 2 两个数
                // 有可能后面出现相同的， 但是和 i 相邻， 结果冲掉了之前的可能的结果
                map.put(sum, i);
            }

        }
        return false;
    }

    private boolean mytry(int[] nums, int k) {
        // 注意两点：
        // 1. 要求 subarray 至少是 2 个
        // 2. k 有可能 == 0
        int n = nums.length;
        int[] prefix = new int[n + 1];
        boolean hasZero = false;
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < i - 1; j++) {
                if (k == 0) {
                    if (prefix[i] - prefix[j] == 0) {
                        return true;
                    }
                } else if ((prefix[i] - prefix[j]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
