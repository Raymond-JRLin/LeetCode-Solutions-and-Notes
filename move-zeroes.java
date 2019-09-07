// 283. Move Zeroes
// DescriptionHintsSubmissionsDiscussSolution
// Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
//
// Example:
//
// Input: [0,1,0,3,12]
// Output: [1,3,12,0,0]
// Note:
//
// You must do this in-place without making a copy of the array.
// Minimize the total number of operations.
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // use 2 pointers in the same directions
        // my_try(nums);

        // actually we can do more concisely, just use 2 pointers - one for zeroes and another for non-zero, whatever we meet, jsut swap zero and non-zero
        method2(nums);
    }

    private void method2(int[] nums) {
        int zero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // i 碰到不是 0 的就往前面交换， 不管前面 zero 指针指的是不是 0
                int temp = nums[i];
                nums[i] = nums[zero];
                nums[zero] = temp;
                zero++;
            }
            // i 碰到 0 则跳过
        }
    }

    private void my_try(int[] nums) {
        int n = nums.length;
        int left = -1; // to find the 1st index of zero
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                left = i;
                break;
            }
        }
        if (left == -1) {
            return;
        }
        int right = left + 1;
        while (right < n) {
            if (nums[right] == 0) {
                right++;
                continue;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            while (left < n && nums[left] != 0) {
                left++;
            }
            right++;
        }
    }
}
