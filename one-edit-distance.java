// 161. One Edit Distance
// DescriptionHintsSubmissionsDiscussSolution
// Given two strings s and t, determine if they are both one edit distance apart.
//
// Note:
//
// There are 3 possiblities to satisify one edit distance apart:
//
// Insert a character into s to get t
// Delete a character from s to get t
// Replace a character of s to get t
// Example 1:
//
// Input: s = "ab", t = "acb"
// Output: true
// Explanation: We can insert 'c' into s to get t.
// Example 2:
//
// Input: s = "cab", t = "ad"
// Output: false
// Explanation: We cannot get t from s by only one step.
// Example 3:
//
// Input: s = "1203", t = "1213"
// Output: true
// Explanation: We can replace '0' with '1' to get t.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null && t == null) {
            return false;
        }
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        if (s.equals(t)) {
            return false;
        }

        // return mytry(s, t);

        // return mytry2(s, t);

        return method1(s, t);
    }

    private boolean method1(String s, String t) {
        // 我想太受 72 Edit Distance 的影响了, 这题其实不要想得太复杂， 但是有点儿 tricky， 不同的就是这题只能有【一个】不同的 char， 所以 在我们找到一个不同之后， 就可以进行 3 种操作， 去比较剩下的 string 是否 equals， 如果不是的话， 那么已经有一个不同了， 那就过肯定就是 false。 3 种方法－ replace, insert, remove － 会用到不同的 string 上， 只能 remove 长的 string， 只能 insert 短的 string， 只有两个 string 相同的时候才能 replace， 这样操作之后， 只需要判断剩下的 string 是否 equals 就好了: O(min{N, M})
        // ref: https://leetcode.com/problems/one-edit-distance/discuss/50098/My-CLEAR-JAVA-solution-with-explanation
        int n = s.length();
        int m = t.length();
        for (int i = 0; i < Math.min(n, m); i++) {
            // check the same length part
            if (s.charAt(i) != t.charAt(i)) {
                if (n == m) {
                    // we can replace one, if we do other 2 operation, then they will get different lengths => must have 1 more difference
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else {
                    if (n > m) {
                        // we can only remove on the longer string
                        return s.substring(i + 1).equals(t.substring(i));
                    } else {
                        return s.substring(i).equals(t.substring(i + 1));
                    }
                }
            }
        }
        // first parts are equal, check their length difference is only 1 or not
        return Math.abs(n - m) == 1;
    }

     private boolean mytry2(String s, String t) {
        // we can directly return true or false without extra space to record states, and pruning when get false: O(NM)
        int n = s.length();
        int m = t.length();

        return dfs(s, t, n, m, n, m, false);
    }
    private boolean dfs(String s, String t, int n, int m, int i, int j, boolean hasOne) {
        if (i == 0 && j == 0) {
            // both get end of string, even though hasOne is true, then we just get 1 difference, still true
            return true;
        }
        if (i == 0 || j == 0) {
            // one string has longer length, check if we already got 1 difference
            return !hasOne;
        }

        if (s.charAt(n - i) != t.charAt(m - j)) {
            if (hasOne) {
                // already has 1 difference
                return false;
            } else {
                // check 3 scenarios
                return dfs(s, t, n, m, i - 1, j - 1, true) || dfs(s, t, n, m, i - 1, j, true) || dfs(s, t, n, m, i, j - 1, true);
            }
        } else {
            // attention here we should carry hasOne value down
            return dfs(s, t, n, m, i - 1, j - 1, hasOne);
        }

    }

    private boolean mytry(String s, String t) {
        // 发现并不能直接判断 char 的个数， 因为还和 order 有关
        // do as 72 Edit Distance: MLE, so I think we don't need to think about it as too complicated
        int n = s.length();
        int m = t.length();
        int[][] memo = new int[n + 1][m + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return recursion(s, t, n, m, memo, n, m) <= 1;
    }
    private int recursion(String s, String t, int n, int m, int[][] memo, int i, int j) {
        if (i == 0 && j == 0) {
            return 0;
        }
        if (i == 0) {
            return memo[i][j] = j;
        }
        if (j == 0) {
            return memo[i][j] = i;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s.charAt(n - i) != t.charAt(m - j)) {
            return memo[i][j] = Math.min(recursion(s, t, n, m, memo, i - 1, j - 1),
                                Math.min(recursion(s, t, n, m, memo, i, j - 1), recursion(s, t, n, m, memo, i - 1, j))) + 1;
        } else {
            return memo[i][j] = recursion(s, t, n, m, memo, i - 1, j - 1);
        }
    }
}
