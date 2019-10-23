// 154. Find Minimum in Rotated Sorted Array II
// DescriptionHintsSubmissionsDiscussSolution
//
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
//
// Example 2:
//
// Input: [2,2,2,0,1]
// Output: 0
//
// Note:
//
//     This is a follow up problem to Find Minimum in Rotated Sorted Array.
//     Would allow duplicates affect the run-time complexity? How and why?
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int findMin(int[] nums) {

        // return method1(nums);

        return method2(nums);
    }

    private int method2(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            // 这里有 duplicate， 所以和找 rotated 是否存在那题一样， 和一个端点比较， 相等就只 --
            if (nums[mid] < nums[end]) {
                // mid < end 是单调递增的
                end = mid;
            } else if (nums[mid] > nums[end]) {
                // nums[mid] > nums[end], mid 肯定不是答案
                start = mid + 1;
            } else {
                end--;
            }
        }
        return nums[start];
    }

    private int method1(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                end--;
            }
        }
        return Math.min(nums[start], nums[end]);
    }
}
