// 1031. Maximum Sum of Two Non-Overlapping Subarrays
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)
//
// Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:
//
//     0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
//     0 <= j < j + M - 1 < i < i + L - 1 < A.length.
//
//
//
// Example 1:
//
// Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
// Output: 20
// Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
//
// Example 2:
//
// Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
// Output: 29
// Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
//
// Example 3:
//
// Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
// Output: 31
// Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.
//
//
//
// Note:
//
//     L >= 1
//     M >= 1
//     L + M <= A.length <= 1000
//     0 <= A[i] <= 1000
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {

        // return method1(A, L, M);

        return method2(A, L, M);
    }

    // 我的想法是可以去 DFS， 先取 L， 然后在其两边取 M
    // DFS 其实是会很复杂， 而且 subarray 需要连续， 所以 sliding window 或者 prefix sum 可以得到 subarray sum
    // 那我觉得其实可以做 precalculation, 事先算好 window 为 L 和 M 的 sum， 然后去求解

    private int method2(int[] nums, int L, int M) {
        int n = nums.length;
        return Math.max(getMax(nums, L, M), getMax(nums, M, L));

    }
    private int getMax(int[] nums, int L, int M) {
        int n = nums.length;
        // 类似于 DP， 不是直接的前后两个方向的 prefix
        // forward[i] := max sum with range of L from left to right
        int[] forward = new int[n + 1];
        // backward[i] := max sum with range of M from right to left
        int[] backward = new int[n + 1];
        int leftSum = 0, rightSum = 0; // prefix sum from left/right to right/left
        for (int i = 0; i < n; i++) {
            leftSum += nums[i];
            if (i >= L) {
                leftSum -= nums[i - L];
            }
            forward[i + 1] = Math.max(forward[i], leftSum);
        }
        for (int i = n - 1; i >= 0; i--) {
            rightSum += nums[i];
            if (i <= n - 1 - M) {
                rightSum -= nums[i + M];
            }
            backward[i] = Math.max(backward[i + 1], rightSum);
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            // 注意理解一下这里 index 和 两个 DP 数组的关系
            result = Math.max(result, forward[i] + backward[i]);
        }
        return result;
    }

    private int method1(int[] nums, int L, int M) {
        // prefix sum
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        int left = maxSubarraySum(prefix, L, M);
        int right = maxSubarraySum(prefix, M, L);
        return Math.max(left, right);
    }
    private int maxSubarraySum(int[] prefix, int left, int right) {
        // take this example: [3,8,1,3,2,1,8,9,0], L(left) = 3, M(right) = 2
        // 分成： [{3, 8, 1}, {3, 2}, 1, 8, 9, 0] => [3, {8, 1, 3}, {2, 1}, 8, 9, 0]
        // 从 left + right 位置前往后扫, 以 right 为 range， 查找每一个 right sum
        // 但是对于 leftSum， 对应的是 right range 左边能取到的最大的 left sum， currLeftSum 则是每次移动新得到的 left sum
        int result = 0;
        int leftSum = 0;
        for (int i = left + right; i < prefix.length; i++) {
            // 注意这里 prefix[] 的 index， left 和 right 都是个数， 相加得到的 i 也是个数， 所以可以直接带入 prefix
            int currLeftSum = prefix[i - right] - prefix[i - right - left];
            leftSum = Math.max(leftSum, currLeftSum);
            int rightSum = prefix[i] - prefix[i - right];
            result = Math.max(result, leftSum + rightSum);
        }
        return result;
    }
}
