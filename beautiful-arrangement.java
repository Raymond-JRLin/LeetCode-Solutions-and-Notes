// 526. Beautiful Arrangement
// DescriptionHintsSubmissionsDiscussSolution
// Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:
//
// The number at the ith position is divisible by i.
// i is divisible by the number at the ith position.
//
//
// Now given N, how many beautiful arrangements can you construct?
//
// Example 1:
//
// Input: 2
// Output: 2
// Explanation:
//
// The first beautiful arrangement is [1, 2]:
//
// Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
//
// Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
//
// The second beautiful arrangement is [2, 1]:
//
// Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
//
// Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
//
//
// Note:
//
// N is a positive integer and will not exceed 15.


class Solution {
    public int countArrangement(int N) {
        if (N < 1) {
            return 0;
        }
//         method 1: 既然要求所有的组合数， 试试 DFS 找出所有的 valid 组合
        // return method1_DFS(N);

//         method 2: 可以看出其实是找全排列中符合条件的组合， 那么还有一种写法就是通过交换找出所有全排列， 再找符合条件的
        return method2_permutation(N);
    }
    private int method1_DFS(int n) {
        boolean[] visited = new boolean[n + 1];
        dfs(visited, n, 1);
        return count;
    }
    int count = 0; // global variable to track count
    private void dfs(boolean[] visited, int n, int pos) {
        // 把 position 传进去， 找每个位置的可能性以及所有的排列组合
        if (pos > n) {
            // set all positions
            count++;
            return;
        }
        for (int i = 1; i <= n; i++) {
            // 对每个位置， 遍历 1 - N， 找可以放置的数
            if (!visited[i] && (i % pos == 0 || pos % i == 0)) {
                // 没用过的数， 并且符合题目要求
                visited[i] = true;
                dfs(visited, n, pos + 1); // next position
                visited[i] = false;
            }
        }
    }
    private int method2_permutation(int n) {
        int[] nums = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            nums[i] = i;
        }
        permutation(nums, n);
        return result;
    }
    private int result = 0;
    private void permutation(int[] nums, int start) {
        if (start == 0) {
            result++;
            return;
        }
        for (int i = start; i > 0; i--) {
            swap(nums, i, start);
            if (nums[start] % start == 0 || start % nums[start] == 0) {
                permutation(nums, start - 1);
            }
            swap(nums, i, start);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
