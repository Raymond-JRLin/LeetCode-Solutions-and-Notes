// 96. Unique Binary Search Trees
// DescriptionHintsSubmissionsDiscussSolution
// Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
//
// Example:
//
// Input: 3
// Output: 5
// Explanation:
// Given n = 3, there are a total of 5 unique BST's:
//
//    1         3     3      2      1
//     \       /     /      / \      \
//      3     2     1      1   3      2
//     /     /       \                 \
//    2     1         2                 3


class Solution {
    public int numTrees(int n) {
        if (n == 1) {
            return 1;
        }

        // return mytry(n);

        // return method2(n);

        return method3(n);
    }

    private int method3(int n) {
        // iteration DP
        // definition
        int[] f = new int[n + 1];
        // initialization
        f[0] = 1;
        f[1] = 1;
        // DP
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                f[i] += f[j] * f[i - 1 - j];
            }
        }
        // result
        return f[n];
    }

    private int method2(int n) {
        // 发现一个特性： 树的个数和起始点的大小无关， 与多少个数拿来建树有关， 因此可以降维处理， 将数组记录为
        int[] memoLen = new int[n + 1]; // memoLen[i] = 用 i 个数字能 build 多少 BST
        return recursion_length(memoLen, n, n);
    }
    private int recursion_length(int[] memoLen, int n, int len) {
        // invalid input
        if (len < 0 || len > n) {
            return 1;
        }
        // edge cases
        if (len == 0 || len == 1) {
            return memoLen[len] = 1;
        }
        // already counted
        if (memoLen[len] != 0) {
            return memoLen[len];
        }
        // e.g. 4 = 0/3 + 1/2 + 2/1 + 3/0, (left/right)
        for (int i = 0; i < len; i++) {
            memoLen[len] += recursion_length(memoLen, n, i) * recursion_length(memoLen, n, len - i - 1);
        }
        return memoLen[len];
    }

    private int mytry(int n) {
        int[][] memo = new int[n + 1][n + 1]; // memo[i]][j] = how many BST using number [i, j]
        return recursion(memo, n, 1, n);
    }
    private int recursion(int[][] memo, int n, int start, int end) {
        // invalid position
        if (start < 0 || start > n || end < 0 || end > n) {
            return 1; // 返回 1 是因为在下面我们是在做乘法， 或者理解成 base case like f[0]
        }
        // invalid position, but we should assign 1
        if (start >= end) {
            return memo[start][end] = 1;
        }
        // if we already counted, then return directly, otherwise it will be added again
        if (memo[start][end] != 0) {
            return memo[start][end];
        }
        for (int i = start; i <= end; i++) {
            // left subtrees * right subtrees
            // memo[start][end] += memo[start][i - 1] * memo[i + 1][end]; // we should use recursion
            memo[start][end] += recursion(memo, n, start, i - 1) * recursion(memo, n, i + 1, end);
        }
        return memo[start][end];
    }
}
