// 53. Maximum Subarray
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
//
// Example:
//
// Input: [-2,1,-3,4,-1,2,1,-5,4],
// Output: 6
// Explanation: [4,-1,2,1] has the largest sum = 6.
// Follow up:
//
// If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
//
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // O(n) time and space
        // return my_try(nums);

        // we can also use DP
        // return method2_dp(nums);

        // optimization of DP to O(1) space
        // return method3_dp_variables(nums);

        //
        return method4_dc(nums);
    }

    private int method3_dp_variables(int[] nums) {
        int sum = 0; // use sum to record the max subarray sum until current i-th value
        int max = Integer.MIN_VALUE; // record the global max result
        for (int num : nums) {
            // if (sum < 0) {
            //     sum = num;
            // } else {
            //     sum += num;
            // }
            // or we can do:
            // 当前 dp = Math.max(上一个 dp 加上当前 value， 当前 value)， 前面加了还不如当前一个大的话， 就不加， 另起一个新的 subarray
            sum = Math.max(sum + num, num);
            max = Math.max(max, sum);
        }
        return max;
    }

    private int method2_dp(int[] nums) {
        int n = nums.length;
        // definition
        int[] dp = new int[n + 1]; // max subarray ending with i-th number
        // initialization: just set dp[0] = 0, chose nothing
        // DP
        for (int i = 1; i < n + 1; i++) {
            // dp[i] = Math.max(nums[i - 1], dp[i - 1] + nums[i - 1]);
            // 虽然上面这个也可以得到正确的答案， 可是对于这个 dp 数组的定义， 这个状态转移方程是【不对】的
            // dp[i] 定义是： 以第 i 个元素结尾的 subarray 的最大和
            // 因此状态转移方程应该是： 如果前面得到的 dp 值小于 0， 那么当前元素就没必要加上之前的一起构成 subarray， 自己开就好了， 因为加上的话， 总会比当前的值还要小， 因此就以当前元素值为 dp 值； 若是大于 0， 那么就可以加上一起构成更长的 subarray， 因为对于定义来说， 是要以当前元素结尾的， 所以一定要加上当前值， 哪怕它小于 0 会使得前一个元素结尾的 dp 更小
            // 所以实际上也就是说： 当前 dp = Math.max(上一个 dp 加上当前 value， 当前 value)， 前面加了还不如当前一个大的话， 就不加， 另起一个新的 subarray
            // dp[i] = nums[i - 1] + Math.max(dp[i - 1], 0);
            if (dp[i - 1] < 0) {
                dp[i] = nums[i - 1];
            } else {
                dp[i] = nums[i - 1] + dp[i - 1];
            }
        }
        // result
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n + 1; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private int my_try(int[] nums) {
        // prefix sum
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        int min = prefix[0];
        int sum = Integer.MIN_VALUE;
        for (int i = 1; i < n + 1; i++) {
            sum = Math.max(sum, prefix[i] - min);
            min = Math.min(min, prefix[i]);
        }
        return sum;
    }

    private int method4_dc(int[] nums) {
        return subarray(nums, 0, nums.length - 1);
    }
    private int subarray(int[] nums, int left, int right) {
        if (right == left) {
            return nums[left];
        }
        int mid = left + (right - left) / 2;
        int left_sum = subarray(nums, left, mid);
        int right_sum = subarray(nums, mid + 1, right);
        int leftMax = nums[mid];
        int rightMax = nums[mid + 1];
        int temp = 0;
        for (int i = mid; i >= left; i--) {
            temp += nums[i];
            if (temp > leftMax) {
                leftMax = temp;
            }
        }
        temp = 0;
        for (int i = mid + 1; i <= right; i++) {
            temp += nums[i];
            rightMax = Math.max(rightMax, temp);
        }
        int cross_sum = leftMax + rightMax;
        return Math.max(Math.max(left_sum, right_sum), cross_sum);
    }
}
