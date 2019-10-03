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


class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        // return method1(nums);

        return method2(nums);
    }

    private int method2(int[] nums) {
        // 另一种方法是把 nums 里面的数字作为 index 对应过去， 然后 mark nums[nums[i]]
        // 这种方法我记得在某一题也用过， 这里是取正负， 因为所有的都是 positive number， 出现过了就 mark 成负数
        // 对于已有的负数或超过 range 的正数， 就先 mark 成 1， 成 0 就没法取负了
        // 所以在做上述变换之前， 得先 check 是否有 1
        int n = nums.length;
        boolean hasOne = false;
        for (int num : nums) {
            if (num == 1) {
                hasOne = true;
                break;
            }
        }
        if (!hasOne) {
            return 1;
        }
        // 把不符合条件的全变成 1
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1;
            }
        }
        // 如果 nums[i] = n, 就变换 nums[0]
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num == n) {
                nums[0] = - Math.abs(nums[0]);
            } else {
                nums[num] = - Math.abs(nums[num]);
            }
        }
        // check 1 -> n - 1
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }
        // check n (at 0 index)
        if (nums[0] > 0) {
            return n;
        }
        return n + 1;
    }

    private int method1(int[] nums) {
        // 关键点是让 nums[i] = i + 1
        // 这个思路是让符合条件的 nums[i] 移动到正确的位置上， 即 i = nums[i] - 1
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
