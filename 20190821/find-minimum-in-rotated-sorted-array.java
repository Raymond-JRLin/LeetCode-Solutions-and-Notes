// 153. Find Minimum in Rotated Sorted Array
// DescriptionHintsSubmissionsDiscussSolution
//
// Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
// (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
//
// Find the minimum element.
//
// You may assume no duplicate exists in the array.
//
// Example 1:
//
// Input: [3,4,5,1,2]
// Output: 1
//
// Example 2:
//
// Input: [4,5,6,7,0,1,2]
// Output: 0
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int findMin(int[] nums) {
        if (nums == null && nums.length == 0) {
            return -1;
        }

        // return mytry(nums);

        return method2(nums);
    }

    private int method2(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            // 这里没有 duplicate， 所以 nums[start], nums[mid], nums[end] 肯定都不一样
            if (nums[mid] <= nums[end]) {
                // mid <= end 是单调递增的
                end = mid;
            } else {
                // nums[mid] > nums[end], mid 肯定不是答案
                start = mid + 1;
            }
            // 保证了各种情况区间一定是减小的
        }
        return nums[start];
    }

    private int mytry(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[start] <= nums[mid] && nums[mid] <= nums[end]) {
                end = mid;
            } else if (nums[mid] > nums[start] && nums[mid] > nums[end]) {
                start = mid;
            } else if (nums[mid] < nums[start] && nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] < nums[end]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }
}
