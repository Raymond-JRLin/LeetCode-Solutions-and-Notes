// 238. Product of Array Except Self
// DescriptionHintsSubmissionsDiscussSolution
// Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
//
// Example:
//
// Input:  [1,2,3,4]
// Output: [24,12,8,6]
// Note: Please solve it without division and in O(n).
//
// Follow up:
// Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        return mytry(nums);

        // 如果能用 division 的话， 那很简单， 把所有的乘起来， 除以当前位数就可以了
        // 而且还只能 O(n), 也就意味着分开 for 一遍， 那就要把结果记录下来

        // return method1_forward_backward(nums);

        // method 2: 要求 O(1) space, 那么就考虑如果将数组缩减成几个 variable
        // return method2_variable(nums);
    }

    private int[] method1_forward_backward(int[] nums) {
        // 除了它自己以外的数相乘， 那就可以等于它前面的乘积✖️后面的乘积
        int n = nums.length;
        int[] forward = new int[n + 1];
        int[] backward = new int[n + 1];
        forward[0] = 1;
        backward[n] = 1;
        int product = 1;
        // 算的时候注意 index
        for (int i = 1; i < n + 1; i++) {
            forward[i] = forward[i - 1] * nums[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            backward[i] = backward[i + 1] * nums[i];
        }
        int[] result = new int[n];
        for (int i = 1; i < n + 1; i++) {
            result[i - 1] = forward[i - 1] * backward[i];
        }
        return result;
    }

    private int[] method2_variable(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        result[0] = 1;
        // forward process: 直接把 forward 的结果放入最终 result 中
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        // 用 right 一个 variable 来记录 backward 的乘积
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] = result[i] * right;
            right = right * nums[i];
        }
        return result;
    }

    private int[] mytry(int[] nums) {
        // brute force O(N ^ 2) time
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int total = 1;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (nums[j] == 0) {
                    total = 0;
                    break;
                }
                total *= nums[j];
            }
            result[i] = total;
        }
        return result;
    }
}
