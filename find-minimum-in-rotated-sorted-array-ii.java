// 154. Find Minimum in Rotated Sorted Array II
// DescriptionHintsSubmissionsDiscussSolution
// Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
// (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
//
// Find the minimum element.
//
// The array may contain duplicates.
//
// Example 1:
//
// Input: [1,3,5]
// Output: 1
// Example 2:
//
// Input: [2,2,2,0,1]
// Output: 0
// Note:
//
// This is a follow up problem to Find Minimum in Rotated Sorted Array.
// Would allow duplicates affect the run-time complexity? How and why?


class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        return method1(nums);
    }

    private int method1(int[] nums) {
        // like search in rotated sorted array II
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // 和 end 比较， 因为有可能是没有 rotate 的 sorted array
            if (nums[mid] > nums[end]) {
                start = mid;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                // 如果相等， 跳过 end
                end--;
            }
        }
        if (nums[start] < nums[end]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }
}
