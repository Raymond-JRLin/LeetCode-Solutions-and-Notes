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

        return method2(nums, target);
    }

    private int method2(int[] nums, int target) {
        // https://discuss.leetcode.com/topic/34491/clever-idea-making-it-simple
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int num = nums[mid];
            if ((num < nums[0]) != (target < nums[0])) {
                if (target < nums[0]) {
                    num = Integer.MIN_VALUE;
                } else {
                    num = Integer.MAX_VALUE;
                }
            }
            if (num == target) {
                return mid;
            } else if (num < target) {
                start = mid;
            } else {
                end = mid;
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

    private int mytry(int[] nums, int target) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // if (nums[mid] == target) {
            //     return mid;
            // } else if (nums[mid] < target) {
            //     if (nums[mid] >= nums[start]) {
            //         start = mid;
            //     } else {
            //         end = mid;
            //     }
            // } else {
            //     if (target >= nums[start]) {
            //         end = mid;
            //     } else {
            //         start = mid;
            //     }
            // }
            // mid 和 target 都是可以在两边的分段中变化， 不好比， 选一个和固定的 start 来比: 先把 mid 分为在大的那部分还是小的那部分， 然后把 target 分成需要跨越的和不需要跨越的
            if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (target <= nums[end] && target > nums[mid]) {
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
