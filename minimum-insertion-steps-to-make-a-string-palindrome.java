// 1312. Minimum Insertion Steps to Make a String Palindrome
//
//     User Accepted: 1121
//     User Tried: 1493
//     Total Accepted: 1198
//     Total Submissions: 2539
//     Difficulty: Hard
//
// Given a string s. In one step you can insert any character at any index of the string.
//
// Return the minimum number of steps to make s palindrome.
//
// A Palindrome String is one that reads the same backward as well as forward.
//
//
//
// Example 1:
//
// Input: s = "zzazz"
// Output: 0
// Explanation: The string "zzazz" is already palindrome we don't need any insertions.
//
// Example 2:
//
// Input: s = "mbadm"
// Output: 2
// Explanation: String can be "mbdadbm" or "mdbabdm".
//
// Example 3:
//
// Input: s = "leetcode"
// Output: 5
// Explanation: Inserting 5 characters the string becomes "leetcodocteel".
//
// Example 4:
//
// Input: s = "g"
// Output: 0
//
// Example 5:
//
// Input: s = "no"
// Output: 1
//
//
//
// Constraints:
//
//     1 <= s.length <= 500
//     All characters of s are lower case English letters.
//


class Solution {
    public int minInsertions(String s) {

        // return mytry(s);

        // return mytry2(s);

        return mytry3(s);
    }

    private int mytry3(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        for (int[] m : memo) {
            Arrays.fill(m, n);
        }
        return recursion(s.toCharArray(), memo, 0, n - 1);
    }
    private int recursion(char[] arr, int[][] memo, int left, int right) {
        if (left >= right) {
            return 0;
        }
        if (memo[left][right] != arr.length) {
            return memo[left][right];
        }

        if (arr[left] == arr[right]) {
            return memo[left][right] = recursion(arr, memo, left + 1, right - 1);
        } else {
            return memo[left][right] = Math.min(
                recursion(arr, memo, left + 1, right),
                recursion(arr, memo, left, right - 1)
            ) + 1;
        }
    }

    private int mytry2(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        // definition
        int[][] f = new int[n][n];
        // init
        // DP
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                // 因为我们一步步计算来的， 所以不需要和 f[j][i] 本身去比较取 min
                if (arr[j] == arr[i]) {
                    // 是 mytry 版本的修改， 取消了 init 为 n， 然后这里交错了就为 0， 对应了 mytry3 recursive 版本的退出条件
                    f[j][i] = f[j + 1][i - 1];
                } else {
                    f[j][i] = Math.min(f[j][i - 1], f[j + 1][i]) + 1;
                }
            }
        }
        // result
        return f[0][n - 1];
    }

    private int mytry(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        // definition
        int[][] f = new int[n][n];
        // init
        for (int[] row : f) {
            Arrays.fill(row, n);
        }
        for (int i = 0; i < n; i++) {
            f[i][i] = 0;
        }
        // DP
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] == arr[i]) {
                    // 搞了很久没有对， 是因为这里 index 交错而计算错误
                    // 当 i/j 相邻的时候， + 1/ - 1 会交错到还没有计算的下一行， e.g. f[4][5] = f[5][4]
                    // 如果 arr[4] == arr[5] 那么本应该为 0 的却会变成 n
                    // 所以我觉得不应该初始化成 n， 参见修改后的 mytry2
                    f[j][i] = Math.min(f[j][i], j + 1 == i ? 0 : f[j + 1][i - 1]);
                } else {
                    f[j][i] = Math.min(f[j][i], Math.min(f[j][i - 1], f[j + 1][i]) + 1);
                }
            }
        }
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         System.out.print(f[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // result
        return f[0][n - 1];
    }
}
