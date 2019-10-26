// 977. Squares of a Sorted Array
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
//
//
//
// Example 1:
//
// Input: [-4,-1,0,3,10]
// Output: [0,1,9,16,100]
//
// Example 2:
//
// Input: [-7,-3,2,3,11]
// Output: [4,9,9,49,121]
//
//
//
// Note:
//
//     1 <= A.length <= 10000
//     -10000 <= A[i] <= 10000
//     A is sorted in non-decreasing order.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) {
            return new int[0];
        }

        // return mytry(A);

        // return mytry2(A);

        return method3(A);
    }

    private int[] method3(int[] nums) {
        // method2 是找最后一个负数， 也就是从绝对值最小的开始
        // 那为什么不从绝对值最大的开始呢？
        // 因为原数组是非递减的， 从负到正， 那么可以想象他们的平方数应该是一个开口向上的抛物线
        // 即两端最大， 中间最小
        // 所以我们可以从两端开始， 找最大的放在最后面
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (Math.abs(nums[left]) >= Math.abs(nums[right])) {
                result[i] = nums[left] * nums[left];
                left++;
            } else {
                result[i] = nums[right] * nums[right];
                right--;
            }
        }
        return result;
    }

    private int[] mytry2(int[] nums) {
        int n = nums.length;
        // 找到最小的那个
        // 但是通过比较两边的方法是错误的！因为可能有重复 => 因为原数组是非递减的
        // => 直接去找最后一个负数就好了
        // int index = Math.abs(nums[0]) < Math.abs(nums[n - 1]) ? 0 : n - 1;
        // for (int i = 1; i < n - 1; i++) {
        //     if (Math.abs(nums[i]) < Math.abs(nums[i - 1]) && Math.abs(nums[i]) < Math.abs(nums[i + 1])) {
        //         index = i;
        //         break;
        //     }
        // }
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                index = i;
            } else {
                break;
            }
        }
        int left = index; //  最后一个负数
        int right = index + 1; // 第一个非负数
        int[] result = new int[n];
        int p = 0;
        while (left >= 0 || right < n) {
            int leftValue = left >= 0 ? Math.abs(nums[left]) : Integer.MAX_VALUE;
            int rightValue = right < n ? Math.abs(nums[right]) : Integer.MAX_VALUE;
            if (leftValue <= rightValue) {
                result[p] = leftValue * leftValue;
                left--;
            } else {
                result[p] = rightValue * rightValue;
                right++;
            }
            p++;
        }
        return result;
    }

    private int[] mytry(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = nums[i] * nums[i];
        }
        Arrays.sort(result);
        return result;
    }
}
