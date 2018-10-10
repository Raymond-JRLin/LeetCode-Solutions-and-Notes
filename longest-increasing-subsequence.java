// 300. Longest Increasing Subsequence
// DescriptionHintsSubmissionsDiscussSolution
// Given an unsorted array of integers, find the length of longest increasing subsequence.
//
// Example:
//
// Input: [10,9,2,5,3,7,101,18]
// Output: 4
// Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
// Note:
//
// There may be more than one LIS combination, it is only necessary for you to return the length.
// Your algorithm should run in O(n2) complexity.
// Follow up: Could you improve it to O(n log n) time complexity?
//


class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // return method1(nums);

        // return method2(nums);

        return method3(nums);
    }

    private int method3(int[] nums) {
        // 和 method2 思路是一样的， 不过在这个过程中不断记录这个 subsequence 的长度
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    private int method2(int[] nums) {
        // O(NlogN) time, 要做到 logN 那么可以想到的就是 BS， 但是如何 BS 是个关键
        // 原数组是不能 sort 的， 但是最终的 increasing subsequence 递增的， 所以可以从这里入手
        // 所以基本思路是， 每次去找到当前数（或者比他大的第一个数），插入到那个位置
        int n = nums.length;
        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int num : nums) {
            int index = Arrays.binarySearch(f, num);
            if (index < 0) {
                index = - index - 1;
            }
            f[index] = num;
        }

        for (int i = n - 1; i >= 0; i--) {
            if (f[i] != Integer.MAX_VALUE) {
                return i + 1; // length
            }
        }
        return 0;
    }

    private int method1(int[] nums) {
        // O(N ^ 2) time and O(N) time
        int n = nums.length;
        // definition
        int[] f = new int[n];
        // initialization
        Arrays.fill(f, 1);
        // DP
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        // result
        int max = f[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, f[i]);
        }
        return max;
    }
}
