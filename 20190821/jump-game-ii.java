// 45. Jump Game II
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
// Each element in the array represents your maximum jump length at that position.
//
// Your goal is to reach the last index in the minimum number of jumps.
//
// Example:
//
// Input: [2,3,1,1,4]
// Output: 2
// Explanation: The minimum number of jumps to reach the last index is 2.
//     Jump 1 step from index 0 to 1, then 3 steps to the last index.
// Note:
//
// You can assume that you can always reach the last index.
//
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // return mytry(nums);

        // return method2(nums);

        return method3(nums);
    }

    private int method3(int[] nums) {
        // another way to do implicit BFS
        int n = nums.length;
        int count = 0;
        int end = 0;
        int farthest = 0;
        for (int i = 0 ; i < n - 1; i++) {
            farthest = Math.max(farthest, nums[i] + i);
            if (i == end) {
                count++;
                end = farthest;
            }
        }
        return count;
    }

    private int method2(int[] nums) {
        // 有个评论说的很好， 可以看作是 implicit BFS
        // 每一层就是当前能够走到的最远的位置， 下一轮开始就加一个 count， 再去走最远
        int n = nums.length;
        int start = 0;
        int end = 0;
        int count = 0;
        while (end < n - 1) {
            count++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                farthest = Math.max(farthest, nums[i] + i);
            }
            start = end + 1;
            end = farthest;
        }
        return count;
    }

    private int mytry(int[] nums) {
        // DP
        int n = nums.length;
        // definition
        int[] f = new int[n]; // f[i] = 到达 i 所需的最少步数
        Arrays.fill(f, Integer.MAX_VALUE);
        // initialization
        f[0] = 0;
        // DP
        for (int i = 0; i < n; i++) {
            if (f[i] == Integer.MAX_VALUE) {
                continue;
            }
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j >= n) {
                    break;
                }
                f[i + j] = Math.min(f[i + j], f[i] + 1);
            }
        }
        // result
        return f[n - 1];
    }
}
