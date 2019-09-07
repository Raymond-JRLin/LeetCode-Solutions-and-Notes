// 556. Next Greater Element III
// DescriptionHintsSubmissionsDiscussSolution
// Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.
//
// Example 1:
//
// Input: 12
// Output: 21
//
//
// Example 2:
//
// Input: 21
// Output: -1
//
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int nextGreaterElement(int n) {
        // actually it's going to find the next permutation
        if (n >= Integer.MAX_VALUE) {
            return -1;
        }
        // convert to array - attention, the element's type is char
        char[] nums = String.valueOf(n).toCharArray();
        int len = nums.length;
        // find the index of first smaller one
        int index = -1;
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return -1;
        }
        // find the first bigger val after index
        int firstBigger = index + 1;
        for (int i = len - 1; i > index; i--) {
            if (nums[i] > nums[index]) {
                firstBigger = i;
                break;
            }
        }
        // swap
        swap(nums, index, firstBigger);
        reverse(nums, index + 1, len - 1);
        long result = Long.parseLong(String.valueOf(nums));
        if (result >= Integer.MAX_VALUE) {
            return -1;
        } else {
            return (int) result;
        }
    }
    private void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private void reverse(char[] nums, int start, int end) {
        while (start <= end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
