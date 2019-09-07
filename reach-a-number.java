// 754. Reach a Number
// DescriptionHintsSubmissionsDiscussSolution
// You are standing at position 0 on an infinite number line. There is a goal at position target.
//
// On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.
//
// Return the minimum number of steps required to reach the destination.
//
// Example 1:
// Input: target = 3
// Output: 2
// Explanation:
// On the first move we step from 0 to 1.
// On the second step we step from 1 to 3.
// Example 2:
// Input: target = 2
// Output: 3
// Explanation:
// On the first move we step from 0 to 1.
// On the second move we step  from 1 to -1.
// On the third move we step from -1 to 2.
// Note:
// target will be a non-zero integer in the range [-10^9, 10^9].
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public int reachNumber(int target) {
        if (target == 0) {
            return 0;
        }
        if (target < 0) {
            return reachNumber(-target);
        }

        // return my_try(target);

        return method1(target);
    }

    private int method1(int target) {
        int sum = 0;
        int step = 1;
        while (sum < target || (sum - target) % 2 != 0) {
            sum += step;
            step++;
        }
        return step - 1; // step
    }

    private int my_try(int target) {
        // TLE
        return dfs(0, target, 0);
    }
    private int dfs(int source, int target, int step) {
        if (Math.abs(source) > target) {
            // cannot find
            return Integer.MAX_VALUE;
        }
        if (source == target) {
            return step;
        }
        int forward = dfs(source + step + 1, target, step + 1);
        int backward = dfs(source - step - 1, target, step + 1);
        return Math.min(forward, backward);
    }
    // private int my_try(int target) {
    //     target = Math.abs(target);
    //     int[] f = new int[target + 1];
    //     f[0] = 0;
    //     int sum = 1;
    //     int index = 1;
    //     for (int i = 1; i < target + 1; i++) {
    //         if (sum == i) {
    //             f[i] = index;
    //             index++;
    //             sum += index;
    //         } else {
    //             f[i] = f[i - 1] + 2;
    //         }
    //     }
    //     return f[target];
    // }
}
