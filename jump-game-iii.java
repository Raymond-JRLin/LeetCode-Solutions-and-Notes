// 1306. Jump Game III
//
//     User Accepted: 2346
//     User Tried: 2579
//     Total Accepted: 2579
//     Total Submissions: 4414
//     Difficulty: Medium
//
// Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
//
// Notice that you can not jump outside of the array at any time.
//
//
//
// Example 1:
//
// Input: arr = [4,2,3,0,3,1,2], start = 5
// Output: true
// Explanation:
// All possible ways to reach at index 3 with value 0 are:
// index 5 -> index 4 -> index 1 -> index 3
// index 5 -> index 6 -> index 4 -> index 1 -> index 3
//
// Example 2:
//
// Input: arr = [4,2,3,0,3,1,2], start = 0
// Output: true
// Explanation:
// One possible way to reach at index 3 with value 0 is:
// index 0 -> index 4 -> index 1 -> index 3
//
// Example 3:
//
// Input: arr = [3,0,2,1,2], start = 2
// Output: false
// Explanation: There is no way to reach at index 1 with value 0.
//
//
//
// Constraints:
//
//     1 <= arr.length <= 5 * 10^4
//     0 <= arr[i] < arr.length
//     0 <= start < arr.length
//


class Solution {
    public boolean canReach(int[] arr, int start) {

        // return mytry(arr, start);

        // return method2(arr, start);

        return method3(arr, start);
    }

    private boolean method3(int[] nums, int start) {
        // 改变原数组， 然后 recursion
        int n = nums.length;
        if (start < 0 || start >= n) {
            return false;
        }
        if (nums[start] >= n) {
            // visited
            return false;
        }
        int jump = nums[start];
        // 因为 0 <= arr[i] < arr.length， 所以加个 length 来表示已经访问过了
        nums[start] += n;
        return jump == 0 || method3(nums, start + jump) || method3(nums, start - jump);
    }

    private boolean method2(int[] nums, int start) {
        // 每次都可以继续往下走， 然后再往下， 有点 BFS 的层级遍历的意思
        int n = nums.length;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(start);
        set.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (nums[curr] == 0) {
                    return true;
                }
                int[] jumps = new int[]{curr + nums[curr], curr - nums[curr]};
                for (int next : jumps) {
                    if (next < 0 || next >= n) {
                        continue;
                    }
                    if (set.contains(next)) {
                        continue;
                    }
                    queue.offer(next);
                    set.add(next);
                }
            }
        }
        return false;
    }

    private boolean mytry(int[] nums, int start) {
        int[] memo = new int[nums.length];
        return dfs(nums, start, memo) == 1;
    }
    private int dfs(int[] nums, int index, int[] memo) {
        if (index < 0 || index >= nums.length) {
            return -1;
        }
        if (nums[index] == 0) {
            return memo[index] = 1;
        }
        // 1 表示能达到
        // 0 表示还没处理
        // -1 表示不能达到
        if (memo[index] != 0) {
            return memo[index];
        }
        // 其实这个地方我有一点点迷思， 就是到了当前 index 我还得继续往下走， 我并不知道之后能否走到， 现在就直接填 -1 会不会太早了点
        // 但是其实是这样的， 上面 value 是 0 了， 就返回 1， 所以当我们找到了就会在那个 index 标记为 1， 然后直接返回， 就会全部返回了
        // 在下面 line 34， 如果可以走通， 那么就会更新标记为 1
        // 之前标记的 -1 其实也没用， 而作用在于剪枝， 意味着我走过了， 不管最后能否到达这个点出发的路径不用再走了
        // 而且还有助于解决循环问题， 比如[3,0,2,1,2], 3, 从 3 开始会在 3 和 5 之间来回跳， 所以必须用 visited 防止 StackOverflow
        memo[index] = -1;
        // 每次可以有两种选择跳
        int jump1 = index + nums[index];
        int jump2 = index - nums[index];
        if (dfs(nums, jump1, memo) == 1 || dfs(nums, jump2, memo) == 1) {
            return memo[index] = 1;
        } else {
            return memo[index] = -1;
        }
    }
}
