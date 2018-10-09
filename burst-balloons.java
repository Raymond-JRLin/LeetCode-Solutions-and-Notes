// 312. Burst Balloons
// DescriptionHintsSubmissionsDiscussSolution
// Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
//
// Find the maximum coins you can collect by bursting the balloons wisely.
//
// Note:
//
// You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
// 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
// Example:
//
// Input: [3,1,5,8]
// Output: 167
// Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
//              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167


class Solution {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // return mytry(nums);

        return method2(nums);
    }

    private int method2(int[] arr) {
        // same idea, but just created a new array to store the original array and set the border as 1
        int n = arr.length;
        // create a new array
        int[] nums = new int[n + 2];
        nums[0] = nums[n + 1] = 1; // set the start and end border as 1
        for (int i = 1; i < n + 1; i++) {
            nums[i] = arr[i - 1];
        }
        // definition
        int[][] memo = new int[n + 2][n + 2];
        // initialization
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // DP
        helper(memo, nums, 0, n + 1);
        // result
        return memo[0][n + 1];
    }
    private int helper(int[][] memo, int[] nums, int start, int end) {
        // 注意这里的 start 和 end 是不取的， 所以里面 for 的 i 不能够取到， 只当作边界来看
        if (start + 1 == end) {
            return 0;
        }
        if (memo[start][end] != -1) {
            // already got
            return memo[start][end];
        }

        int result = 0;
        for (int i = start + 1; i < end; i++) {
            // i 已经乘掉了， 所以把 i 也当做了下一层递归的边界
            result = Math.max(result, nums[start] * nums[i] * nums[end] + helper(memo, nums, start, i) + helper(memo, nums, i, end));
        }
        return memo[start][end] = result;
    }


    private int mytry(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return recursion(memo, nums, 0, n - 1, 1, 1);
    }
    private int recursion(int[][] memo, int[] nums, int start, int end, int left, int right) {
        if (start < 0 || end >= nums.length || start > end) {
            return 0;
        }
        if (memo[start][end] != -1) {
            // already got
            return memo[start][end];
        }
        if (start == end) {
            // only one balloon left
            return memo[start][end] = left * nums[start] * right;
        }

        int result = 0;
        for (int i = start; i <= end; i++) {
            result = Math.max(result, left * nums[i] * right + recursion(memo, nums, start, i - 1, left, nums[i]) + recursion(memo, nums, i + 1, end, nums[i], right));
        }
        return memo[start][end] = result;
    }
}
