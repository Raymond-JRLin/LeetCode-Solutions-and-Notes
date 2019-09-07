// 485. Max Consecutive Ones
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary array, find the maximum number of consecutive 1s in this array.
//
// Example 1:
// Input: [1,1,0,1,1,1]
// Output: 3
// Explanation: The first two digits or the last three digits are consecutive 1s.
//     The maximum number of consecutive 1s is 3.
// Note:
//
// The input array will only contain 0 and 1.
// The length of input array is a positive integer and will not exceed 10,000
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 0;
        int count = 0;
//         for (int i = 0; i < nums.length; i++) {
//             if (nums[i] == 1) {
//                 count++;
//             } else {
//                 result = Math.max(result, count);
//                 count = 0;
//             }
//         }
//         don't forget to check at last time
//         result = Math.max(result, count);

//         if we wanna avoid the final check, we can update result everytime when get count ++
        for (int num : nums) {
            if (num == 1) {
                count++;
                result = Math.max(result, count);
            } else {
                count = 0;
            }
        }
        return result;
    }
}
