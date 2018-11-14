// 189. Rotate Array
// DescriptionHintsSubmissionsDiscussSolution
// Given an array, rotate the array to the right by k steps, where k is non-negative.
//
// Example 1:
//
// Input: [1,2,3,4,5,6,7] and k = 3
// Output: [5,6,7,1,2,3,4]
// Explanation:
// rotate 1 steps to the right: [7,1,2,3,4,5,6]
// rotate 2 steps to the right: [6,7,1,2,3,4,5]
// rotate 3 steps to the right: [5,6,7,1,2,3,4]
// Example 2:
//
// Input: [-1,-100,3,99] and k = 2
// Output: [3,99,-1,-100]
// Explanation:
// rotate 1 steps to the right: [99,-1,-100,3]
// rotate 2 steps to the right: [3,99,-1,-100]
// Note:
//
// Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
// Could you do it in-place with O(1) extra space?


class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k == 0) {
            return;
        }

        // O(n) time & space
        // my_try(nums, k);

        // O(n) time and O(1) space
        method2(nums, k);
    }

    private void method2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }
    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    private void my_try(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n];
        k = k % n;
        for (int i = 0; i < n; i++) {
            result[(i + k) % n] = nums[i];
        }
        for (int i = 0; i < n; i++) {
            nums[i] = result[i];
        }
        return;
    }
}
