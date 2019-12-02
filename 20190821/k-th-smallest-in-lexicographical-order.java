// 440. K-th Smallest in Lexicographical Order
// DescriptionHintsSubmissionsDiscussSolution
//
// Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.
//
// Note: 1 ≤ k ≤ n ≤ 109.
//
// Example:
//
// Input:
// n: 13   k: 2
//
// Output:
// 10
//
// Explanation:
// The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int findKthNumber(int n, int k) {

        return method1(n, k);
    }

    private int method1(int n, int k) {
        // 看了 discussion， 基本思路是把题设看成一个图， 字典序的图， 然后做 preorder traversal
        // 优化的点在于， 如果我们知道左子树下的个数不足 k 个， 那么整个左子树不需要遍历， 可以直接跳到同一层下一个
        // ref: https://youtu.be/yMnR63e3KLo
        long curr = 1;
        while (k > 1) {
            long leftCount = getLeftSubtreeCount(curr, n);
            // 当前 curr 下的子树不足 k 个， 那么 curr 移动到 curr + 1， 整个子树都不需要查看
            if (leftCount < k) {
                curr += 1;
                k -= leftCount;
            } else {
                // 超过 k 个， 说明答案在此子树下， 移动 curr 到下一层的开头
                curr *= 10;
                k--;
            }
        }
        return (int) curr;
    }
    private long getLeftSubtreeCount(long curr, int n) {
        long result = 0;
        long next = curr + 1;
        while (curr <= n) {
            // n + 1 是因为 个数 = end + 1 - start, next 已经加过了
            result += Math.min(n + 1, next) - curr;
            curr *= 10;
            next *= 10;
        }
        return result;
    }
}
