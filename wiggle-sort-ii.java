// 324. Wiggle Sort II
// DescriptionHintsSubmissionsDiscussSolution
// Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
//
// Example 1:
//
// Input: nums = [1, 5, 1, 1, 6, 4]
// Output: One possible answer is [1, 4, 1, 5, 1, 6].
// Example 2:
//
// Input: nums = [1, 3, 2, 2, 3, 1]
// Output: One possible answer is [2, 3, 1, 3, 1, 2].
// Note:
// You may assume all input has valid answer.
//
// Follow Up:
// Can you do it in O(n) time and/or in-place with O(1) extra space?
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // method1(nums);

        method2(nums);
    }

    private void method2(int[] nums) {
        // combine find kth largest number (quick select) and sort color
        int median = findKthLargestNumber(nums);
        mappingColorSort(nums, median);
    }
    private void mappingColorSort(int[] nums, int median) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        int index = 0;
        while (index <= end) {
            int i = mapping(index, n);
            if (nums[i] > median) {
                swap(nums, i, mapping(start, n));
                start++;
                index++;
            } else if (nums[i] < median) {
                swap(nums, i, mapping(end, n));
                end--;
            } else {
                index++;
            }
        }
    }
    private int mapping(int i, int n) {
        return (2 * i + 1) % (n | 1);
    }
    private int findKthLargestNumber(int[] nums) {
        int n = nums.length;
        int k = (n + 1) / 2; // k-th
        int start = 0;
        int end = n - 1;
        int index = n - k;
        while (start < end) {
            int pivot = partition(nums, start, end);
            if (pivot < index) {
                start = pivot + 1;
            } else if (pivot > index) {
                end = pivot - 1;
            } else {
                return nums[pivot];
            }
        }
        return nums[start];
    }
    private int partition(int[] nums, int start, int end) {
        int pivot = start;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot]) {
                start++;
            }
            while (start <= end && nums[end] > nums[pivot]) {
                end--;
            }
            if (start > end) {
                break;
            }
            swap(nums, start, end);
        }
        swap(nums, end, pivot);
        return end;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void method1(int[] nums) {
        // sort array and pick one from front-half array and another from back-half, [<----|<----]
        int n = nums.length;
        Arrays.sort(nums);
        int[] copy = Arrays.copyOf(nums, n);
        int index = n / 2;
        if (n % 2 == 0) {
            index--;
        }
        int end = n - 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = copy[index--];
            } else {
                nums[i] = copy[end--];
            }
        }
    }
}
