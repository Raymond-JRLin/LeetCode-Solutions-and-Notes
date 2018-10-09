// 34. Find First and Last Position of Element in Sorted Array
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
//
// Your algorithm's runtime complexity must be in the order of O(log n).
//
// If the target is not found in the array, return [-1, -1].
//
// Example 1:
//
// Input: nums = [5,7,7,8,8,10], target = 8
// Output: [3,4]
// Example 2:
//
// Input: nums = [5,7,7,8,8,10], target = 6
// Output: [-1,-1]


class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        // return mytry(nums, target);

        // return method1(nums, target);

        return method2(nums, target);
    }

    private int[] method2(int[] nums, int target) {
        // O(logN) with 1 BS method: use a flag or to find the 1st equal or greater number
        int start = firstEqualGreater(nums, target);
        if (start == nums.length || nums[start] != target) {
            // 注意可能会越界
            return new int[]{-1, -1};
        }
        int end = firstEqualGreater(nums, target + 1) - 1;
        return new int[]{start, end};
    }
    private int firstEqualGreater(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] >= target) {
            return start;
        } else if (nums[end] >= target) {
            return end;
        } else {
            // 因为我们总是要返回第一个等于或者第一个大于的数的位置， 所以两个都小的话， 那么要返回下一位， 哪怕越界
            return end + 1;
        }
    }

    private int[] method1(int[] nums, int target) {
        // O(logN): if it's required logN then we must use BS, but there's duplicates so we gonna use 2 little bit different BS method to search start and end respectively by decide "=" in different conditions
        int start = bs_start(nums, target);
        if (start == -1) {
            return new int[]{-1, -1};
        }
        int end = bs_end(nums, target);
        return new int[]{start, end};
    }
    private int bs_start(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid;
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
    private int bs_end(int[] nums, int target) {
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
        } else {
            return -1;
        }
    }

    private int[] mytry(int[] nums, int target) {
        // O(N)
        int n = nums.length;
        int start = -1;
        int end = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                start = i;
                end = i;
                i++;
                while (i < n && nums[i] == target) {
                    end = i++;
                }
                break;
            }
        }
        int[] result = new int[2];
        result[0] = start;
        result[1] = end;
        return result;
    }
}
