// 324. Wiggle Sort II
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
//
// Example 1:
//
// Input: nums = [1, 5, 1, 1, 6, 4]
// Output: One possible answer is [1, 4, 1, 5, 1, 6].
//
// Example 2:
//
// Input: nums = [1, 3, 2, 2, 3, 1]
// Output: One possible answer is [2, 3, 1, 3, 1, 2].
//
// Note:
// You may assume all input has valid answer.
//
// Follow Up:
// Can you do it in O(n) time and/or in-place with O(1) extra space?
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        // method1(nums);

        method2(nums);
    }

    private void method2(int[] nums) {
        int n = nums.length;
        int k = (n + 1) / 2;
        int mid = kthLargest(nums, k); // median

        int odd = 1; // larger numbers
        int even = (n - 1) / 2 * 2; // smaller numbers
        int index = even; // 先查找偶数位置， index 用来看 nums[index] 与 mid 的大小， 然后放到 odd 或者 even 位置上
        for (int i = 0; i < n; i++) {
            // 用 i 控制走遍整个数组， 但是只控制个数
            // System.out.println("odd, even, index: " + odd + ", " + even + ", " + index);
            if (nums[index] < mid) {
                // 小于 mid， 所以要放 even 位置上
                swap(nums, index, even);
                index -= 2;
                even -= 2;
                if (index < 0) {
                    // 走完所有的偶数位置了， 换到最后一个奇数位置去重新开始查找奇数位置
                    index = n / 2 * 2 - 1;
                }
            } else if (nums[index] > mid) {
                swap(nums, index, odd);
                odd += 2;
            } else {
                index -= 2;
                if (index < 0) {
                    index = n / 2 * 2 - 1;
                }
            }

        }
    }
    private int kthLargest(int[] nums, int k) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        int index = n - k;
        while (start < end) {
            int pivot = partition(nums, start, end);
            if (pivot == index) {
                return nums[pivot];
            } else if (pivot < index) {
                start = pivot + 1;
            } else {
                end = pivot - 1;
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
        swap(nums, pivot, end);
        return end;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void method1(int[] nums) {
        // 做过了， 知道怎么做， 但忘了拿数组的方向
        // 从左往右的 [--->|--->], 如果只有 4 个数， e.g. [4,5,5,6], 那第二个第三个就都是 5 了
        // 所以第一个数应该拿中间的， 那么就倒过来从中间远离： [<---|<---]
        int n = nums.length;
        int[] arr = Arrays.copyOf(nums, n);
        Arrays.sort(arr);

        int left = (n + 1) / 2 - 1;
        int right = n - 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = arr[left--];
            } else {
                nums[i] = arr[right--];
            }
        }
    }
}
