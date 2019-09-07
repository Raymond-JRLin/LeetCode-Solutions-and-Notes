// 260. Single Number III
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
//
// Example:
//
// Input:  [1,2,1,3,2,5]
// Output: [3,5]
// Note:
//
// The order of the result is not important. So in the above example, [5, 3] is also correct.
// Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new int[2];
        }

//         method 1: use bit manipulation
        return method1_bit(nums);
    }
    private int[] method1_bit(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum ^= nums[i];
        }
        // get a number that only has one 1 which is in the last index of sum
        int last = sum - (sum & (sum - 1));
        int group0 = 0;
        int group1 = 0;
        for (int num : nums) {
            if ((num & last) == 0) {
                // the bit in that index of num is 0
                group0 ^= num;
            } else {
                group1 ^= num;
            }
        }
        return new int[]{group0, group1};
    }
}
