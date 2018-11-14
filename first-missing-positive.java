// 41. First Missing Positive
// DescriptionHintsSubmissionsDiscussSolution
// Given an unsorted integer array, find the smallest missing positive integer.
//
// Example 1:
//
// Input: [1,2,0]
// Output: 3
// Example 2:
//
// Input: [3,4,-1,1]
// Output: 2
// Example 3:
//
// Input: [7,8,9,11,12]
// Output: 1
// Note:
//
// Your algorithm should run in O(n) time and uses constant extra space.


public class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        // return mytry(nums);

        return method1(nums);
    }
    private int method1(int[] nums) {
        // O(N) time and O(1) space
        // 看了一些 discussion 和答案，应该这么理解题意： 给定的数组可以有任意的 integer， 但是 positive int 从 1 开始， 所以我们不论给的数组是怎么样的， 我们就从 1 开始比对， 找到以 1， 2， 3 。。。 这个顺序第一个没出现的正整数就好了
        if (nums == null || nums.length == 0) {
            return 1; // return the 1st positive integer: 1
        }
        // 让 A[i] = i + 1 可以： 如果 0 就不出现， 那么可以直接返回 1， 并且 e.g. [1, 2]， 2 就没有 A[2] 可以放置了
        for (int i = 0; i < nums.length; i++) {
            // 交换的 3 个条件
            // 1: nums[i] is in the range;
            // 2: nums[i] > 0.
            // 3: The target is different;
            // e.g. [-1, 4, 2, 1, 9, 10], 那就让负数和超过 range 的数保持不动， 让符合条件的数交换过去， 再把它们换走
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                // int temp = nums[i];
                // nums[i] = nums[nums[i] - 1];
                // nums[nums[i] - 1] = temp;
                // above would cause TLE since nums[i] has been changed when we calculate nums[nums[i] - 1], so we can use swap method to fix 2 indices

                // swap(nums, i, nums[i] - 1);
                // or change command sequences
                // 利用 int 的大小和 value 与 index 的关系， 不断将 value 放到它该在的位置
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        // check for result
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 如果全部排布完整， 那么第一个没出现的就是全数的下一个
        return nums.length + 1;
    }
    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    private int mytry(int[] nums) {
        // O(N) time and space
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0 && num <= n) {
                set.add(num);
            }
        }
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return n + 1;
    }
}
