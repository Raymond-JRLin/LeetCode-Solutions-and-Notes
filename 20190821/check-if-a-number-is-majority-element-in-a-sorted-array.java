// 1150. Check If a Number Is Majority Element in a Sorted Array
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array nums sorted in non-decreasing order, and a number target, return True if and only if target is a majority element.
//
// A majority element is an element that appears more than N/2 times in an array of length N.
//
//
//
// Example 1:
//
// Input: nums = [2,4,5,5,5,5,5,6,6], target = 5
// Output: true
// Explanation:
// The value 5 appears 5 times and the length of the array is 9.
// Thus, 5 is a majority element because 5 > 9/2 is true.
//
// Example 2:
//
// Input: nums = [10,100,101,101], target = 101
// Output: false
// Explanation:
// The value 101 appears 2 times and the length of the array is 4.
// Thus, 101 is not a majority element because 2 > 4/2 is false.
//
//
//
// Note:
//
//     1 <= nums.length <= 1000
//     1 <= nums[i] <= 10^9
//     1 <= target <= 10^9
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public boolean isMajorityElement(int[] nums, int target) {

        return mytry(nums, target);
    }

    private boolean mytry(int[] nums, int target) {
        int start = firstElement(nums, target);
        int end = lastElement(nums, target);
        if (start == -1 || end == -1) {
            return false;
        }
        int count = end + 1 - start;
        return count > nums.length / 2;
    }
    private int firstElement(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return nums[start] == target ? start : -1;
    }
    private int lastElement(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] == target) {
            return end;
        } else if (nums[start] == target) {
            return start;
        }  else {
            return -1;
        }
    }
}
