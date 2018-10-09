// 213. House Robber II
// DescriptionHintsSubmissionsDiscussSolution
// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
// Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
//
// Example 1:
//
// Input: [2,3,2]
// Output: 3
// Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
//              because they are adjacent houses.
// Example 2:
//
// Input: [1,2,3,1]
// Output: 4
// Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//              Total amount you can rob = 1 + 3 = 4.


class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }


        // return mytry(nums);

        // return method2(nums);

        return method3(nums);
    }

    private int method3(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // use the dp method in I
        int n = nums.length;
        return Math.max(dp(nums, 0, n - 2), dp(nums, 1, n - 1));
    }
    private int dp(int[] nums, int start, int end) {
        int rob = 0;
        int not = 0;
        for (int i = start; i <= end; i++) {
            int temp = not;
            not = Math.max(not, rob); // case 1: do not rob
            rob = temp + nums[i]; // case 2: rob
        }
        return Math.max(not, rob);
    }

    private int method2(int[] nums) {
        // recusion 2: use different range, just don't consider if we steal the 1st or last one, just cut it off
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[] nums1 = new int[n - 1]; // get rid of the 1st house
        int[] nums2 = new int[n - 1]; // get rid of the last house
        for (int i = 0; i < n - 1; i++) {
            nums1[i] = nums[i];
        }
        for (int i = 0; i < n - 1; i++) {
            nums2[i] = nums[i + 1];
        }
        int[] memo1 = new int[n];
        int[] memo2 = new int[n];
        Arrays.fill(memo1, -1);
        Arrays.fill(memo2, -1);
        return Math.max(recursion(memo1, nums1, n - 1), recursion(memo2, nums2, n - 1));
    }

    private int mytry(int[] nums) {
        // 照搬 I， 改动偷的范围
        // 因为下面 - 3， 所以这里 edge case 处理一下 => 不过太细了
        if (nums.length <= 3) {
            int result = Integer.MIN_VALUE;
            for (int num : nums) {
                result = Math.max(result, num);
            }
            return result;
        }

        int n = nums.length;
        int[] nums1 = new int[n - 3]; // rob the the 1st house
        int[] nums2 = new int[n - 1]; // do not rob the 1st house
        for (int i = 0; i < n - 3; i++) {
            nums1[i] = nums[i + 2];
        }
        for (int i = 0; i < n - 1; i++) {
            nums2[i] = nums[i + 1];
        }
        return Math.max(getMemo(nums1) + nums[0], getMemo(nums2));
    }
    private int getMemo(int[] nums) {
        // recursion
        int n = nums.length;
        // definition
        int[] memo = new int[n + 1]; // # money we can steal when use last i houses left
        // initialization
        Arrays.fill(memo, -1);
        return recursion(memo, nums, n);
    }
    private int recursion(int[] memo, int[] nums, int i) {
        // invalid input
        if (i < 1) {
            return 0;
        }
        // alreday got
        if (memo[i] != -1) {
            return memo[i];
        }
        int selectFirst = nums[nums.length - i] + recursion(memo, nums, i - 2); // cannot choose 1st and 2nd element
        int unselectFirst = recursion(memo, nums, i - 1); // cut the 1st element
        return memo[i] = Math.max(selectFirst, unselectFirst);
    }
}
