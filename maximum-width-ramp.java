// 962. Maximum Width Ramp
// User Accepted: 968
// User Tried: 1710
// Total Accepted: 990
// Total Submissions: 4722
// Difficulty: Medium
// Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.
//
// Find the maximum width of a ramp in A.  If one doesn't exist, return 0.
//
//
//
// Example 1:
//
// Input: [6,0,8,2,1,5]
// Output: 4
// Explanation:
// The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
// Example 2:
//
// Input: [9,8,1,0,1,9,4,0,4,1]
// Output: 7
// Explanation:
// The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.
//
//
// Note:
//
// 2 <= A.length <= 50000
// 0 <= A[i] <= 50000


class Solution {
    public int maxWidthRamp(int[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }

        // return mytry(A);

        return method2(A);
    }

    private int method2(int[] nums) {
        // 一看到这个题就想到了应该要用 stack， 和有一题最长上升/下降序列还是什么的很相似， 在 stack 里面构造一个非上升序列， 然后倒过来比较
        // 这里要注意 1- 只要满足区间头尾大小的关系就可以， 内部大小无所谓， 2- stack 中放的是 index， 这样既可以拿到原数组中比较大小， 也可以得到 width
        int n = nums.length;
        Stack<Integer> stack = new Stack<>(); // <index>
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty() || nums[i] < nums[stack.peek()]) {
                // nums[i] == nums[stack.peek()] 可以跳过， 因为这个相等的值在更后面， 和其后的距离一定更小
                stack.push(i);
            }
        }
        int result = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                result = Math.max(result, i - stack.pop());
            }
        }
        return result;
    }

    private int mytry(int[] nums) {
        // brute force: O(N ^ 2) time and O(1) space
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = n - 1; j > i; j--) {
                if (nums[j] >= nums[i]) {
                    result = Math.max(result, j - i);
                    break;
                }
            }
        }
        return result;
    }
}
