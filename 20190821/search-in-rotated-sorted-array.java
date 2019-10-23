// 33. Search in Rotated Sorted Array
// DescriptionHintsSubmissionsDiscussSolution
// Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
// (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
//
// You are given a target value to search. If found in the array return its index, otherwise return -1.
//
// You may assume no duplicate exists in the array.
//
// Your algorithm's runtime complexity must be in the order of O(log n).
//
// Example 1:
//
// Input: nums = [4,5,6,7,0,1,2], target = 0
// Output: 4
// Example 2:
//
// Input: nums = [4,5,6,7,0,1,2], target = 3
// Output: -1


class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // return mytry(nums, target);

        // return method2(nums, target);

        return method3(nums, target);
    }

    private int method3(int[] nums, int target) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        // find the smallest number first
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        // start & end is the index of smallest number
        int index = start;
        start = 0;
        end = n - 1;
        while (start < end) {
            int num = start + (end - start) / 2;
            int mid = (num + index) % n;
            // 相当于把 rotate 那一段移到非 rotate 后面, 或是想像成 cycle
            if (nums[mid] > target) {
                end = num - 1;

            } else {
                start = num;
            }
        }
        return nums[(start + index) % n] == target ? (start + index) % n : -1;
    }

    private int method2(int[] nums, int target) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return nums[start] == target ? start : -1;
        // 或者 while 判断改为 start <= end, 这里就直接返回 -1， 因为最后一次查 start 也在 while 中完成了
    }

    private int mytry(int[] nums, int target) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (nums[start] == target) {
            return start;
        } else if (nums[end] == target) {
            return end;
        } else {
            return -1;
        }
    }
}
