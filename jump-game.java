// 55. Jump Game
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
// Each element in the array represents your maximum jump length at that position.
//
// Determine if you are able to reach the last index.
//
// Example 1:
//
// Input: [2,3,1,1,4]
// Output: true
// Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
// Example 2:
//
// Input: [3,2,1,0,4]
// Output: false
// Explanation: You will always arrive at index 3 no matter what. Its maximum
//              jump length is 0, which makes it impossible to reach the last index.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }

        return mytry(nums);

        // return method1(nums);

        // return method1_2(nums);

        // return method2(nums);
    }

    private boolean method2(int[] nums) {
        // backward, 从最后一个位置往前找， 看能不能走到 0
        // ref: https://discuss.leetcode.com/topic/3443/simplest-o-n-solution-with-constant-space
        int n = nums.length;
        int last = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (i + nums[i] >= last) {
                last = i;
            }
        }
        return last == 0;
    }

    private boolean method1_2(int[] nums) {
        // 不一定要走到最后， 看能否到达最后一个位置， 如果在中间断了， 到不了中间某个位置， 那么也就无法继续下去了， 即可返回 false
        // ref: https://discuss.leetcode.com/topic/7661/java-solution-easy-to-understand/4
        int n = nums.length;
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            if (max < i) {
                return false;
            }
            max = Math.max(max, nums[i] + i);
        }
        return true;
    }

    private boolean method1(int[] nums) {
        // greedy
        int n = nums.length;
        int farthest = nums[0];
        for (int i = 1; i < n; i++) {
            if (i <= farthest && nums[i] + i > farthest) {
                farthest = nums[i] + i;
            }
        }
        return farthest >= n - 1; // 与 farthest 比的是 index
    }

    private boolean mytry(int[] nums) {
        // DP: O(n ^ 2) - TLE
        int n = nums.length;
        // definition
        boolean[] f = new boolean[n];
        // initialization
        f[0] = true;
        // DP
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && j + nums[j] >= i) {
                    f[i] = true;
                    break;
                }
            }
        }
        // result
        return f[n - 1];
    }
}
