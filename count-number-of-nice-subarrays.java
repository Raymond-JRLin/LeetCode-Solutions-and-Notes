// 1248. Count Number of Nice Subarrays
//
//     User Accepted: 1615
//     User Tried: 2144
//     Total Accepted: 1663
//     Total Submissions: 3799
//     Difficulty: Medium
//
// Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.
//
// Return the number of nice sub-arrays.
//
//
//
// Example 1:
//
// Input: nums = [1,1,2,1,1], k = 3
// Output: 2
// Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
//
// Example 2:
//
// Input: nums = [2,4,6], k = 1
// Output: 0
// Explanation: There is no odd numbers in the array.
//
// Example 3:
//
// Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
// Output: 16
//
//
//
// Constraints:
//
//     1 <= nums.length <= 50000
//     1 <= nums[i] <= 10^5
//     1 <= k <= nums.length
//


class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return 0;
        }

        return method1(nums, k);
    }

    private int method1(int[] nums, int k) {
        // 做的时候我知道是 sliding window， 可是中间却卡住了， 原因在于如何得到 exactly k 个
        // 回顾一下我们之前做的大多都是， 最多 k， 不超过 k 等等
        // 这里非常巧妙的套用 sliding window 的解法， 即 f(刚刚好 k 个) = f(最多 k 个) - f(最多 k - 1 个) => 992 题
        // 还有一个地方注意的是 atMost 中如何计算 result
        // 参考 992. Subarrays with K Different Integers
        return atMostKSubarray(nums, k) - atMostKSubarray(nums, k - 1);
    }
    private int atMostKSubarray(int[] nums, int k) {
        int left = 0;
        int count = 0;
        int result = 0;
        for (int right = 0; right < nums.length; right++) {
            count += nums[right] % 2;
            while (count > k) {
                count -= nums[left] % 2;
                left++;
            }
            // 这里计算的实际上是每移动一次 right 【增加】的 subarray 的结果， 因为有一些结果在上一轮已经计算过了
            // 每增加一个新的 right 数， 它可以和 window 里它前面的数字搭配组成 subarray 以及它自身， 即为 window 的长度
            // [x, x, x, x, x, x, x, x, x, x, x, x, ...] # 令x表示一格數字
            // [l=r]
            // [l, r]
            // [l, x, r]
            // [l, x, x, r] # 若 (l, r)區間中超過k個奇數，則要將left右移直到不超過k個
            // # 每一輪增加的子陣列個數為 r - l + 1
            // # 就是所有以r為終點的子陣列個數
            result += right + 1 - left;
        }
        return result;
    }
}
