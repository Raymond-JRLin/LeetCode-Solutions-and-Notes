// 718. Maximum Length of Repeated Subarray
// DescriptionHintsSubmissionsDiscussSolution
// Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
//
// Example 1:
//
// Input:
// A: [1,2,3,2,1]
// B: [3,2,1,4,7]
// Output: 3
// Explanation:
// The repeated subarray with maximum length is [3, 2, 1].
//
//
// Note:
//
// 1 <= len(A), len(B) <= 1000
// 0 <= A[i], B[i] < 100
//
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int findLength(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return 0;
        }

        // return my_try(A, B);

        return method2(A, B);
    }

    private int method2(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        if (n > m) {
            return method2(B, A);
        }
        // n <= m
        int[] dp = new int[n + 1];
        int max = 0;
        for (int i = 1; i < m + 1; i++) {
            for (int j = n; j >= 1; j--) {
                // 注意 n m 对应的是哪个数组
                if (B[i - 1] == A[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                    max = Math.max(max, dp[j]);
                } else {
                    dp[j] = 0;
                }
            }
        }
        return max;
    }

    private int my_try(int[] A, int[] B) {
        int len_A = A.length;
        int len_B = B.length;
        int max = 0;
        // definition
        int[][] dp = new int[len_A + 1][len_B + 1];
        // initialization
        // DP
        for (int i = 1; i < len_A + 1; i++) {
            for (int j = 1; j < len_B + 1; j++) {
                // 注意这里解法和 LCS 一样， 但是问题在于是求 subarray 而不是 subsequence， 所以必须是相连的， 也就意味着当两个字符串的字符不想等的时候， 直接归零， 也就因此我们需要一个全局变量来记录这个过程中的最大值
                if (A[i- 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        // result
        return max;
    }
}
