// 1250. Check If It Is a Good Array
//
//     User Accepted: 563
//     User Tried: 847
//     Total Accepted: 582
//     Total Submissions: 1840
//     Difficulty: Hard
//
// Given an array nums of positive integers. Your task is to select some subset of nums, multiply each element by an integer and add all these numbers. The array is said to be good if you can obtain a sum of 1 from the array by any possible subset and multiplicand.
//
// Return True if the array is good otherwise return False.
//
//
//
// Example 1:
//
// Input: nums = [12,5,7,23]
// Output: true
// Explanation: Pick numbers 5 and 7.
// 5*3 + 7*(-2) = 1
//
// Example 2:
//
// Input: nums = [29,6,10]
// Output: true
// Explanation: Pick numbers 29, 6 and 10.
// 29*1 + 6*(-3) + 10*(-1) = 1
//
// Example 3:
//
// Input: nums = [3,6]
// Output: false
//
//
//
// Constraints:
//
//     1 <= nums.length <= 10^5
//     1 <= nums[i] <= 10^9
//


class Solution {
    public boolean isGoodArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        return method1(nums);
    }

    private boolean method1(int[] nums) {
        // 用贝祖定理比较好理解一点 https://www.youtube.com/watch?v=WGO4Tqx5owg
        // 对方程 ax + by = m, where a, b, m are intergers, x & y 有整数解的充要条件是： m 是 a 和 b 最大公约数的倍数
        // 对于这个题要找一个 subset sum == 1， 那么就是找 subset 的 GCD 为 1
        // 可以把所有的数拿去做 GCD， 我是这么理解的， 就让那些不需要的数的系数为 0
        if (nums.length == 1) {
            return nums[0] == 1;
        }
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int gcd = gcd(num, nums[i]);
            if (gcd == 1) {
                return true;
            }
            num = gcd;
        }
        return false;
    }
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
