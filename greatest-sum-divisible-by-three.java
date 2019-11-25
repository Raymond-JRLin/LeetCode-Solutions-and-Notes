// 1262. Greatest Sum Divisible by Three
//
//     User Accepted: 1076
//     User Tried: 1967
//     Total Accepted: 1108
//     Total Submissions: 4341
//     Difficulty: Medium
//
// Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.
//
//
//
// Example 1:
//
// Input: nums = [3,6,5,1,8]
// Output: 18
// Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
//
// Example 2:
//
// Input: nums = [4]
// Output: 0
// Explanation: Since 4 is not divisible by 3, do not pick any number.
//
// Example 3:
//
// Input: nums = [1,2,3,4,4]
// Output: 12
// Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
//
//
//
// Constraints:
//
//     1 <= nums.length <= 4 * 10^4
//     1 <= nums[i] <= 10^4
//


class Solution {
    public int maxSumDivThree(int[] nums) {

        // return mytry(nums);

        // return method1(nums);

        // return method2(nums);

        return method3(nums);
    }

    private int method3(int[] nums) {
        // Add all together, if sum % 3 == 0, return sum.
        // if sum % 3 == 1, remove the smallest number which has n % 3 == 1.
        // if sum % 3 == 2, remove the smallest number which has n % 3 == 2.
        int result = 0, leftOne = 20000, leftTwo = 20000;
        for(int num : nums){
            result += num;
            if(num % 3 == 1){
                leftTwo = Math.min(leftTwo, leftOne + num);
                leftOne = Math.min(leftOne, num);
            }
            if(num % 3 == 2) {
                leftOne = Math.min(leftOne, leftTwo + num);
                leftTwo = Math.min(leftTwo, num);
            }
        }

        if (result % 3 == 0) {
            return result;
        } else if (result % 3 == 1) {
            return result - leftOne;
        } else {
            return result - leftTwo;
        }
    }

    private int method2(int[] nums) {
        // 另一种 idea， 同时使用滚动数组
        // definition
        int[] f = new int[3];
        // init
        f[0] = 0;
        f[1] = Integer.MIN_VALUE;
        f[2] = Integer.MIN_VALUE;
        // DP
        for (int num : nums) {
            int[] next = new int[3];
            for (int i = 0; i < 3; i++) {
                next[(num + i) % 3] = Math.max(f[i] + num, f[(num + i) % 3]);
            }
            f = next;
        }
        // result
        return f[0];
    }

    private int method1(int[] nums) {
        // 求最大值， DFS 又 TEL， 那大概率就是要 DP 了， 而且这里又不要 subarray， 就是随便选
        // 如何 DP 是关键， contest 想了好久都没想到， 因为在 recursion 过程中， 似乎记录已经得到的 sum 或是怎样都不太对
        // 此时应该要想到 Divisible by Three 有什么特点
        // 就是 % 余数只有 0 1 2
        // 如果当前 num % 3 == 1， 那么可以和前面 sum % 3 == 2 和加在一起成为 newSum % 3 == 0， 然后和之前 sum % 3 == 0 的取较大值
        int n = nums.length;
        // definition: f[i][j] := 到 nums[i]， 能和 % 3 == j 组成的最大的 sum 是多少
        // f[i][0] 放的是 % 3 == 0
        // f[i][1] 放的是 % 3 == 2, 就是说如果当前 num % 3 == 1， 那我从前一轮 f[i][1] 中加到的要是 % 3 == 2 的 prev sum
        // f[i][2] 放的是 % 3 == 1
        int[][] f = new int[n + 1][3];
        // init
        f[0][0] = 0;
        f[0][1] = Integer.MIN_VALUE;
        f[0][2] = Integer.MIN_VALUE;
        // DP
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            f[i + 1][0] = Math.max(f[i][num % 3] + num, f[i][0]);
            f[i + 1][1] = Math.max(f[i][(num + 1) % 3] + num, f[i][1]);
            f[i + 1][2] = Math.max(f[i][(num + 2) % 3] + num, f[i][2]);
        }
        for (int[] num : f) {
            for (int a : num) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
        // result
        return f[n][0];
    }

    private int mytry(int[] nums) {
        // TLE
        result = 0;
        dfs(nums, 0, 0);
        return result;
    }
    private int result;
    private void dfs(int[] nums, int index, int curr) {
        if (curr != 0 && curr % 3 == 0) {
            result = Math.max(result, curr);
        }
        if (index == nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            dfs(nums, i + 1, curr + nums[i]);
        }
    }
}
