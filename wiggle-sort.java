// 280. Wiggle Sort
// DescriptionHintsSubmissionsDiscussSolution
// Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
//
// Example:
//
// Input: nums = [3,5,2,1,6,4]
// Output: One possible answer is [3,5,1,6,2,4]
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        // mytry(nums);

        method2(nums);
    }

    private void method2(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 1) && nums[i] < nums[i - 1]) {
                swap(nums, i, i - 1);
            }
            if ((i % 2 == 0) && nums[i] > nums[i - 1]) {
                swap(nums, i, i - 1);
            }
        }
    }

    private void mytry(int[] nums) {
        // use library
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 1; i < n - 1; i += 2) {
            swap(nums, i, i + 1);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
