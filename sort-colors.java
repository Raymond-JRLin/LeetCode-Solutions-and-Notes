// 75. Sort Colors
// DescriptionHintsSubmissionsDiscussSolution
// Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
//
// Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
//
// Note: You are not suppose to use the library's sort function for this problem.
//
// Example:
//
// Input: [2,0,2,1,1,0]
// Output: [0,0,1,1,2,2]
// Follow up:
//
// A rather straight forward solution is a two-pass algorithm using counting sort.
// First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
// Could you come up with a one-pass algorithm using only constant space?


class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        // two pass: count # 0, 1, 2, and then overwrite the given array

        mytry(nums);

        // method2(nums);
    }

    private void method2(int[] nums) {
        // another version of 2 pointers
        int n = nums.length;
        int zero = 0;
        int two = n - 1;
        for (int i = 0; i <= two; i++) {
            while (i < two && nums[i] == 2) {
                swap(nums, i, two);
                two--;
            }
            while (i > zero && nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
            }
        }
    }

    private void mytry(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums, i, left);
                left++;
                i++; // left 在 i 的前面或者相同的位置， 所以换过来的都 0
            } else if (nums[i] == 1) {
                i++;
            } else {
                // nums[i] == 2
                swap(nums, i, right);
                right--;
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
