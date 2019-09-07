// 442. Find All Duplicates in an Array
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
//
// Find all the elements that appear twice in this array.
//
// Could you do it without extra space and in O(n) runtime?
//
// Example:
// Input:
// [4,3,2,7,8,2,3,1]
//
// Output:
// [2,3]


class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
//         the key point is 1 ≤ a[i] ≤ n
        // return method1(nums);
        return method2(nums);
    }
//     method 2: flip the i - 1 to negative, and if we found a already negative number then it's a duplicate
    private List<Integer> method2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                result.add(Math.abs(nums[i]));
            }
            nums[index] = -nums[index]; // flip it to negative
        }
        return result;
    }
//     method 1: swap values to the position where you should be
    private List<Integer> method1(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
                i--; // current position may not be the corrent one even after swapping
            }
        }
//         double check final result
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                result.add(nums[i]);
            }
        }
        return result;
    }
    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
