// 1283. Find the Smallest Divisor Given a Threshold
//
//     User Accepted: 1683
//     User Tried: 2377
//     Total Accepted: 1745
//     Total Submissions: 6257
//     Difficulty: Medium
//
// Given an array of integers nums and an integer threshold, we will choose a positive integer divisor and divide all the array by it and sum the result of the division. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
//
// Each result of division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
//
// It is guaranteed that there will be an answer.
//
//
//
// Example 1:
//
// Input: nums = [1,2,5,9], threshold = 6
// Output: 5
// Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
// If the divisor is 4 we can get a sum to 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
//
// Example 2:
//
// Input: nums = [2,3,5,7,11], threshold = 11
// Output: 3
//
// Example 3:
//
// Input: nums = [19], threshold = 5
// Output: 4
//
//
//
// Constraints:
//
//     1 <= nums.length <= 5 * 10^4
//     1 <= nums[i] <= 10^6
//     nums.length <= threshold <= 10^6
//


class Solution {
    public int smallestDivisor(int[] nums, int threshold) {

        return mytry(nums, threshold);
    }

    private int mytry(int[] nums, int threshold) {
        // contest 的时候有一点点迷惑LOL， 就是二分时候的移动
        // 因为这里涉及到两个量： 1. 被 BS 的 divisor， 2. threshold
        // 应该要注意到， divisor 越大， 最后的结果 sum 会越小， 也就离 threshold 越远
        int n = nums.length;
        Arrays.sort(nums);
        int start = 1;
        int end = nums[n - 1];
        while (start < end) {
            int mid = start + (end - start) / 2;
            int sum = getSum(nums, mid);
            if (sum <= threshold) {
                // valid， 此时 sum 满足条件， 那么此 divisor 是备选答案， 可以尝试减小 divisor 去获得更大的 sum， 即减小 end
                end = mid;
            } else {
                // > threshold， sum 太大了， divisor 太小了
                start = mid + 1;
            }
        }
        return start;
    }
    private int getSum(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            int res = num / divisor;
            sum += (num % divisor == 0 ? res : res + 1);
            // or:
            // sum += (num + divisor - 1) % divisor
        }
        return sum;
    }
}
