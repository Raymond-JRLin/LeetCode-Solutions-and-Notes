// 1063. Number of Valid Subarrays
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array A of integers, return the number of non-empty continuous subarrays that satisfy the following condition:
//
// The leftmost element of the subarray is not larger than other elements in the subarray.
//
//
//
// Example 1:
//
// Input: [1,4,2,5,3]
// Output: 11
// Explanation: There are 11 valid subarrays: [1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3].
//
// Example 2:
//
// Input: [3,2,1]
// Output: 3
// Explanation: The 3 valid subarrays are: [3],[2],[1].
//
// Example 3:
//
// Input: [2,2,2]
// Output: 6
// Explanation: There are 6 valid subarrays: [2],[2],[2],[2,2],[2,2],[2,2,2].
//
//
//
// Note:
//
//     1 <= A.length <= 50000
//     0 <= A[i] <= 100000
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int validSubarrays(int[] nums) {

        // return mytry(nums);

        return method2(nums);
    }

    private int method2(int[] nums) {
        // 单调栈, O(N) time and space
        int n = nums.length;
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] < stack.peek()) {
                stack.pop();
            }
            stack.push(nums[i]);
            // + size 可以这么理解： stack 里每一个数都可以作为左起点， 和栈顶的数（新 push 进去的） 成为一个 valid subarray
            // e.g. [1, 4]: size == 2, i.e. adding new {1, 4} and {4}, ({1} is already counted when stack has 1 only)
            // e.g. [1, 4, 2] => [1, 2], size == 2, i.e. adding new {1, 4, 2} and {2}
            // e.g. [1, 2, 5], size == 3, i.e. adding new {1, 4, 2, 5}, {2, 5} and {5}
            // e.g. [1, 2, 5, 3] => [1, 2, 3], size == 3, i.e. {1, 4, 2, 5, 3}, {2, 4, 5, 3} and {3}
            result += stack.size();
        }
        return result;
    }

    private int mytry(int[] nums) {
        // brute force, O(N ^ 2) time
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            // 当下一位比起始 i 的 val 大的时候， 才往下移
            while (j < n - 1 && nums[j + 1] >= nums[i]) {
                j++;
            }
            // 以 i 为左起点， 每个 index 包括 i 自己， 都可以作为右终点的 subarray 的个数
            result += j + 1 - i;
        }
        return result;
    }
}
