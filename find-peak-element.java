// 162. Find Peak Element
// DescriptionHintsSubmissionsDiscussSolution
// A peak element is an element that is greater than its neighbors.
//
// Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
//
// The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
//
// You may imagine that nums[-1] = nums[n] = -∞.
//
// Example 1:
//
// Input: nums = [1,2,3,1]
// Output: 2
// Explanation: 3 is a peak element and your function should return the index number 2.
// Example 2:
//
// Input: nums = [1,2,1,3,5,6,4]
// Output: 1 or 5
// Explanation: Your function can return either index number 1 where the peak element is 2,
//              or index number 5 where the peak element is 6.
// Note:
//
// Your solution should be in logarithmic complexity.


class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }


        // return mytry(nums);

        // return mytry_2(nums);

        // return method2(nums);

        // return method2_2(nums);

        return method2_3(nums);
    }

    private int method2_3(int[] nums) {
        // recursion verseion of BS with same idea
        return recursion(nums, 0, nums.length - 1);
    }
    private int recursion(int[] nums, int start, int end) {
        if (start >= end) {
            return start;
        }
        int mid = start + (end - start) / 2;
        if (nums[mid] < nums[mid + 1]) {
            return recursion(nums, mid + 1, end);
        } else {
            return recursion(nums, start, mid);
        }
    }

    private int method2_2(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] < nums[end]) {
            return end;
        } else {
            return start;
        }
    }

    private int method2(int[] nums) {
        // because we wanna do in O(logN), then we should use BS, the key is how to use BS. When we get mid index, then we should compare it's neighbors, then decide how to move start and end pointers
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] < nums[end]) {
            return end;
        } else {
            return start;
        }
    }

    private int mytry_2(int[] nums) {
        // improvement for O(N) method
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > nums[i]) {
                return i - 1;
            }
        }
        return n - 1;
    }

    private int mytry(int[] nums) {
        // O(N) time
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[n - 1] > nums[n - 2]) {
            return n - 1;
        }
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return -1;
    }
}
