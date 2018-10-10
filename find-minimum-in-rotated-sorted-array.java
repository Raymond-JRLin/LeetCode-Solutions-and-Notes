// 153. Find Minimum in Rotated Sorted Array
// DescriptionHintsSubmissionsDiscussSolution
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
// Example 2:
//
// Input: [4,5,6,7,0,1,2]
// Output: 0


class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // return mytry(nums);

        // return method1(nums);

        // return method2(nums);

        return method3(nums);
    }

    private int method3(int[] nums) {
        // idea from II
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // 和末尾比较
            if (nums[mid] > nums[end]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] < nums[end]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }

    private int method2(int[] nums) {
        // BS: O(logN)
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            // we get the normal-order (no rotation) part, so can return directly
            if (nums[start] < nums[end]) {
                return nums[start];
            }

            int mid = start + (end - start) / 2;
            if (nums[mid] >= nums[start]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] < nums[end]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }

    private int method1(int[] nums) {
        // BS: O(logN)
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= nums[start]) {
                if (nums[end] < nums[start]) {
                    // rotated
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                if (nums[end] < nums[start]) {
                    // rotated
                    end = mid;
                } else {
                    start = mid;
                }
            }
        }
        if (nums[start] < nums[end]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }

    private int mytry(int[] nums) {
        // brute force: iteration O(N) time and O(1) space
        int result = Integer.MAX_VALUE;
        for (int num : nums) {
            result = Math.min(result, num);
        }
        return result;
    }
}
