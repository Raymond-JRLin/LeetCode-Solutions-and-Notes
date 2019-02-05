// 88. Merge Sorted Array
// DescriptionHintsSubmissionsDiscussSolution
// Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
//
// Note:
//
// The number of elements initialized in nums1 and nums2 are m and n respectively.
// You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
// Example:
//
// Input:
// nums1 = [1,2,3,0,0,0], m = 3
// nums2 = [2,5,6],       n = 3
//
// Output: [1,2,2,3,5,6]


class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2 == null || n == 0) {
            return;
        }

        // O(n) space
        // my_try(nums1, m, nums2, n);

        method2(nums1, m, nums2, n);
    }

    private void method2(int[] nums1, int m, int[] nums2, int n) {
        // if we wanna do in-place swap, we should notice that nums1 has enough length of (m + n), so we can do from the tail of nums1 instead of head like my_try
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        // compare from the tail of 2 arrays, and which bigger would be put into the nums1
        while (i >= 0 && j >= 0) {
            nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
        while (i >= 0) {
            nums1[k--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    private void my_try(int[] nums1, int m, int[] nums2, int n) {
        int[] merge = new int[m + n];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < m && j < n) {
            merge[index++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }
        while (i < m) {
            merge[index++] = nums1[i++];
        }
        while (j < n) {
            merge[index++] = nums2[j++];
        }
        for (int k = 0; k < m + n; k++) {
            nums1[k] = merge[k];
        }
    }
}
