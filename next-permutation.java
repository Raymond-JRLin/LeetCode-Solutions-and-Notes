// 31. Next Permutation
// DescriptionHintsSubmissionsDiscussSolution
// Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
//
// If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
//
// The replacement must be in-place and use only constant extra memory.
//
// Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
//
// 1,2,3 → 1,3,2
// 3,2,1 → 1,2,3
// 1,1,5 → 1,5,1


class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        mytry(nums);
    }

    private void mytry(int[] nums) {
        int n = nums.length;
        // 1. find the number to put later
        int index = n - 1;
        for (int i = n - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                index = i - 1;
                break;
            }
        }
        if (index == n - 1) {
            reverse(nums, 0, n - 1);
            return;
        }
        // 2. find the numebr to swap with nums[index]
        int cand = n - 1;
        for (int i = n - 1; i > index; i--) {
            if (nums[i] > nums[index]) {
                cand = i;
                break;
            }
        }
        swap(nums, index, cand);
        // 3. reverse latter part
        reverse(nums, index + 1, n - 1);
    }
    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
