// 81. Search in Rotated Sorted Array II
// DescriptionHintsSubmissionsDiscussSolution
// Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
// (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
//
// You are given a target value to search. If found in the array return true, otherwise return false.
//
// Example 1:
//
// Input: nums = [2,5,6,0,0,1,2], target = 0
// Output: true
// Example 2:
//
// Input: nums = [2,5,6,0,0,1,2], target = 3
// Output: false
// Follow up:
//
// This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
// Would this affect the run-time complexity? How and why?


class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        // return mytry(nums, target);

        // return method2(nums, target);

        return method3(nums, target);
    }

    private boolean method3(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        // check each num so we will check start == end
        // We always get a sorted part and a half part
        // we can check sorted part to decide where to go next
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > nums[start]) {
                // start - mid is sorted
                //             /------
                //          mid
                //           /
                // start------
                //                             /------end
                //                            /
                //                           /
                //                    ------
                if (target < nums[start] || target > nums[mid]) {
                    // target is in the unsorted side - right of mid
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else if (nums[mid] < nums[start]) {
                // mid - end is sorted
                //              /------
                //             /
                //            /
                // start------
                //                             /------end
                //                           mid
                //                           /
                //                    ------
                if (target < nums[mid] || target > nums[end]) {
                    // target is in the unsorted side - left of mid
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // here we know nums[mid] != target and nums[mid] == nums[start], so nums[start] != target, move start to right by 1
                //based on current information, we can only move left pointer to skip one cell, thus in the worest case, we would have target: 2, and array like 11111111, then the running time would be O(n)
                start++;
            }
        }
        return false;
    }

    private boolean method2(int[] nums, int target) {
        // BS
        // ref: https://discuss.leetcode.com/topic/25487/neat-java-solution-using-binary-search
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > nums[start] || nums[mid] > nums[end]) {
                //If we know for sure right side is sorted or left side is unsorted
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
                //If we know for sure left side is sorted or right side is unsorted
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                //If we get here, that means nums[start] == nums[mid] == nums[end], then shifting out any of the two sides won't change the result but can help remove duplicate from consideration, here we just use end-- but left++ works too
                end--;
            }
        }
        if (nums[start] == target || nums[end] == target) {
            return true;
        } else {
            return false;
        }
    }

    private boolean mytry(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return true;
            }
        }
        return false;
    }
}
