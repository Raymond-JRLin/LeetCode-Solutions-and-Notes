// 26. Remove Duplicates from Sorted Array
// DescriptionHintsSubmissionsDiscussSolution
// Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
//
// Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
//
// Example 1:
//
// Given nums = [1,1,2],
//
// Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
//
// It doesn't matter what you leave beyond the returned length.
// Example 2:
//
// Given nums = [0,0,1,1,1,2,2,3,3,4],
//
// Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
//
// It doesn't matter what values are set beyond the returned length.
// Clarification:
//
// Confused why the returned value is an integer but your answer is an array?
//
// Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
//
// Internally you can think of this:
//
// // nums is passed in by reference. (i.e., without making a copy)
// int len = removeDuplicates(nums);
//
// // any modification to nums in your function would be known by the caller.
// // using the length returned by your function, it prints the first len elements.
// for (int i = 0; i < len; i++) {
//     print(nums[i]);
// }



class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        // return my_try(nums);

        // similar method, just different way
        // return method1_2(nums);

        return method1_3(nums);
    }

    private int method1_3(int[] nums) {
        // 或者我们也可以从第 2 个数开始， 然后先交换， 后左移左边的指针， 然后直接返回左指针的位置
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    private int method1_2(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                // 先 ++ 是因为只是 remove duplicate 也就是每个 element 是要保留一个的
                nums[++j] = nums[i];
            }
        }
        return ++j; // return 长度
    }

    private int my_try(int[] nums) {
        // 2 pointers
        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (nums[right] == nums[left]) {
                // 如果是一样的， right 一直往右走
                right++;
            } else {
                // 找到一个不一样的值， 先把 left 右移一位， 然后交换， right 还是继续往下走
                left++;
                swap(nums, left, right);
                right++;
            }
        }
        return left + 1; // return length
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
