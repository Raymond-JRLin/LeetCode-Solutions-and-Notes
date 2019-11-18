// 611. Valid Triangle Number
// DescriptionHintsSubmissionsDiscussSolution
// Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
//
// Example 1:
//
// Input: [2,2,3,4]
// Output: 3
// Explanation:
// Valid combinations are:
// 2,3,4 (using the first 2)
// 2,3,4 (using the second 2)
// 2,2,3
//
// Note:
//
//     The length of the given array won't exceed 1000.
//     The integers in the given array are in the range of [0, 1000].
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        // return mytry(nums);

        return method2(nums);
    }

    private int method2(int[] nums) {
        // 依旧是 两边之和 > 第三边
        // 类似于 3 sum closest， 3 pointers, 固定 1 个， 然后 2 pointers
        // O(NlogN + N ^ 2)
        int n = nums.length;
        Arrays.sort(nums);
        int result = 0;
        for (int i = 2; i < n; i++) {
            int left = 0, right = i - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                // 如果当前 sum > nums[i], 那么从 left 到 right 都可以和 right 组成两边
                if (sum > nums[i]) {
                    // ending index: right - 1, 因为每个要和 right 构成两边， 所以固定 right， 一共有 [left, right - 1] 的范围
                    int len = (right - 1) + 1 - left;
                    result += len;
                    right--;
                } else {
                    // 反之则均不可， 移动一边使 sum 更大一些
                    left++;
                }
            }
        }
        return result;
    }

    private int mytry(int[] nums) {
        // 我想的是 两边之和 > 第三边
        // 类似于 3 sum， 固定两个， 找比它们和小的最后一个位置
        // O(NlogN + N ^ 2 * logN)
        int n = nums.length;
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int sum = nums[i] + nums[j];
                // index 是最后一个 nums[index] < sum 的
                int index = lastSmaller(nums, j + 1, n - 1, sum);
                if (index == -1) {
                    continue;
                }
                int len = index + 1 - (j + 1);
                result += len;
            }
        }
        return result;
    }
    private int lastSmaller(int[] nums, int start, int end, int target) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] < target) {
            return end;
        } else if (nums[start] < target) {
            return start;
        } else {
            return -1;
        }
    }
}
