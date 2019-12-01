// 1278. Palindrome Partitioning III
//
//     User Accepted: 538
//     User Tried: 761
//     Total Accepted: 588
//     Total Submissions: 1289
//     Difficulty: Hard
//
// You are given a string s containing lowercase letters and an integer k. You need to :
//
//     First, change some characters of s to other lowercase English letters.
//     Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
//
// Return the minimal number of characters that you need to change to divide the string.
//
//
//
// Example 1:
//
// Input: s = "abc", k = 2
// Output: 1
// Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
//
// Example 2:
//
// Input: s = "aabbc", k = 3
// Output: 0
// Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
//
// Example 3:
//
// Input: s = "leetcode", k = 8
// Output: 0
//
//
//
// Constraints:
//
//     1 <= k <= s.length <= 100.
//     s only contains lowercase English letters.
//


class Solution {
    public int palindromePartition(String s, int k) {

        // return mytry(s, k);

        // return mytry2(s, k);

        return method2(s, k);
    }

    private int method2(String s, int k) {
        if (k == s.length()) {
            return 0;
        }

        int n = s.length();
        // definition: f[i] := [i, j] 的 substring 变成 palindrome 所需要的最少 char 替换个数
        int[][] f = new int[n][n];
        // initialization
        for (int[] nums : f) {
            Arrays.fill(nums, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            f[i][i] = 0;
        }
        // DP
        for (int i = 0; i < n; i++) {
            for (int left = 0; left < i; left++) {
                if (i - left == 1) {
                    f[left][i] = Math.min(f[left][i], s.charAt(left) == s.charAt(i) ? 0 : 1);
                } else {
                    if (s.charAt(i) == s.charAt(left)) {
                        f[left][i] = Math.min(f[left][i], f[left + 1][i - 1]);
                    } else {
                        f[left][i] = Math.min(f[left][i], f[left + 1][i - 1] + 1);
                    }
                }
            }
        }

        // definition: dp[k][j] := 以 j index 结尾的 substring 分成 k 个 palindrome substring 的最少 char 替换数
        int[][] dp = new int[k + 1][n];
        // init: k = 1， 即自身为 1 整个 palindrome， 不分 substring
        for (int j = 0; j < n; j++) {
            dp[1][j] = f[0][j];
        }
        // DP
        for (int i = 2; i <= k; i++) {
            // 分成 k 个， 至少要 k - 1 为 ending index 的 substring
            for (int j = i - 1; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int p = j; p >= i - 1; p--) {
                    int curr = dp[i - 1][p - 1] + f[p][j];
                    min = Math.min(min, curr);
                }
                dp[i][j] = min;
            }
        }
        return dp[k][n - 1];
    }

    private int mytry2(String s, int k) {
        if (k == s.length()) {
            return 0;
        }

        int n = s.length();
        // definition: f[i] := [i, j] 的 substring 变成 palindrome 所需要的最少 char 替换个数
        int[][] f = new int[n][n];
        // initialization
        for (int[] nums : f) {
            Arrays.fill(nums, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            f[i][i] = 0;
        }
        // DP
        for (int i = 0; i < n; i++) {
            for (int left = 0; left < i; left++) {
                if (i - left == 1) {
                    f[left][i] = Math.min(f[left][i], s.charAt(left) == s.charAt(i) ? 0 : 1);
                } else {
                    if (s.charAt(i) == s.charAt(left)) {
                        f[left][i] = Math.min(f[left][i], f[left + 1][i - 1]);
                    } else {
                        f[left][i] = Math.min(f[left][i], f[left + 1][i - 1] + 1);
                    }
                }
            }
        }

        // memo[i][j][k] := [i, j] 的 substring 分成 k 个 palindrome substring 的最少 char 替换数
        int[][][] memo = new int[n][n][k + 1];
        for (int[][] me : memo) {
            for (int[] m : me) {
                Arrays.fill(m, -1);
            }
        }
        return recursion(f, 0, n - 1, k, memo);
    }
    private int recursion(int[][] f, int start, int end, int k, int[][][] memo) {
        if (k == 1) {
            // System.out.println("stop at " + (index - 1) + ", need to modify: " + sum + " + " + f[0][index]);
            return memo[start][end][k] = f[start][end];
        }
        if (end == start) {
            if (k <= 1) {
                return memo[start][end][k] = 0; // 不必替换了
            } else {
                return memo[start][end][k] = Integer.MAX_VALUE; // 用 MAX 表示无法达成
            }
        }
        if (memo[start][end][k] != -1) {
            return memo[start][end][k];
        }
        int min = Integer.MAX_VALUE;
        // 从 [start, end] 中选一个 [start, i] 的 substring
        for (int i = start; i < end; i++) {
            int num1 = recursion(f, start, i, 1, memo);
            int num2 = recursion(f, i + 1, end, k - 1, memo);
            if (num1 == Integer.MAX_VALUE || num2 == Integer.MAX_VALUE) {
                continue;
            }
            min = Math.min(min, num1 + num2);
        }
        // System.out.println("min = " + min);
        return memo[start][end][k] = min;
    }


    private int mytry(String s, int k) {
        // TLE
        if (k == s.length()) {
            return 0;
        }

        int n = s.length();
        boolean[][] isPal = new boolean[n][n]; // f[i][j] = 下表 i - j 是否是 palindrome
        // definition
        int[][] f = new int[n][n]; // f[i] := [i, j] 的 substring 变成 palindrome 所需要的最少 char 替换个数
        // initialization
        for (int[] nums : f) {
            Arrays.fill(nums, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            f[i][i] = 0;
        }
        // DP
        for (int i = 0; i < n; i++) {
            for (int left = 0; left < i; left++) {
                if (i - left == 1) {
                    f[left][i] = Math.min(f[left][i], s.charAt(left) == s.charAt(i) ? 0 : 1);
                } else {

                    if (s.charAt(i) == s.charAt(left)) {
                        f[left][i] = Math.min(f[left][i], f[left + 1][i - 1]);
                    } else {
                        f[left][i] = Math.min(f[left][i], f[left + 1][i - 1] + 1);
                    }
                }
            }
        }
        // for (int[] nums : f) {
        //     for (int num : nums) {
        //         System.out.print(num + " ");
        //     }
        //     System.out.println();
        // }

        result = n;
        dfs(f, 0, k - 1, 0);
        return result;
    }
    private int result;
    private void dfs(int[][] f, int curr, int k, int sum) {
        if (curr >= f.length) {
            return;
        }
        // System.out.println("chop at " + curr + ", with k = " + k + ", and sum = " + sum);
        if (k == 0) {
            // System.out.println("need to modify: " + sum);
            result = Math.min(result, sum + f[curr][f.length - 1]);
        }
        if (sum > result) {
            return;
        }
        for (int i = curr; i < f.length; i++) {
            dfs(f, i + 1, k - 1, sum + f[curr][i]);
        }
    }
}
