// 376. Wiggle Subsequence
// DescriptionHintsSubmissionsDiscussSolution
// A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.
//
// For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.
//
// Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.
//
// Example 1:
//
// Input: [1,7,4,9,2,5]
// Output: 6
// Explanation: The entire sequence is a wiggle sequence.
// Example 2:
//
// Input: [1,17,5,10,13,15,10,5,16,8]
// Output: 7
// Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
// Example 3:
//
// Input: [1,2,3,4,5,6,7,8,9]
// Output: 2
// Follow up:
// Can you do it in O(n) time?
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums.length;
        }

        // return method1(nums);

        // return method2(nums);

        return method2_2(nums);
    }

    private int method2_2(int[] nums) {
        // DP: O(n) time and O(1) space
        int n = nums.length;
        // definition, initialization
        int up = 1;
        int down = 1;
        // DP
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                // increasing
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                // decreasing
                down = up + 1;
            }
        }
        // result
        return Math.max(up, down);
    }

    private int method2(int[] nums) {
        // DP: O(n) time and O(n) space
        int n = nums.length;
        // definition
        int[] up = new int[n];
        int[] down = new int[n];
        // initialization
        up[0] = 1; // at least there's 1 digit
        down[0] = 1;
        // DP
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                // increasing
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                // decreasing
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                // equal
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        // result
        return Math.max(up[n - 1], down[n - 1]);
    }

    private int method1(int[] nums) {
        // straightforward: O(n) time and O(1) space, I think it's kinda greedy
        // https://discuss.leetcode.com/topic/51946/very-simple-java-solution-with-detail-explanation
        int n = nums.length;
        int index = 0;
        while (index < n - 1 && nums[index] == nums[index + 1]) {
            // remove heading duplicates
            index++;
        }
        // index 处在了第一个数或者开头重复数的最后一个
        if (index == n - 1) {
            // reached the end of the array => all are duplicates
            return 1;
        }
        int result = 2; // at least we have 2
        boolean increasing = nums[index + 1] > nums[index];
        for (int i = index + 1; i < n - 1; i++) {
            if ((increasing && nums[i + 1] < nums[i]) || (!increasing && nums[i + 1] > nums[i])) {
                result++;
                increasing = !increasing;
            }
        }
        return result;
    }
}
