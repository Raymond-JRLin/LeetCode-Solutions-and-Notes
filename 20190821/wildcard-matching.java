// 44. Wildcard Matching
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
//
// '?' Matches any single character.
// '*' Matches any sequence of characters (including the empty sequence).
//
// The matching should cover the entire input string (not partial).
//
// Note:
//
//     s could be empty and contains only lowercase letters a-z.
//     p could be empty and contains only lowercase letters a-z, and characters like ? or *.
//
// Example 1:
//
// Input:
// s = "aa"
// p = "a"
// Output: false
// Explanation: "a" does not match the entire string "aa".
//
// Example 2:
//
// Input:
// s = "aa"
// p = "*"
// Output: true
// Explanation: '*' matches any sequence.
//
// Example 3:
//
// Input:
// s = "cb"
// p = "?a"
// Output: false
// Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
//
// Example 4:
//
// Input:
// s = "adceb"
// p = "*a*b"
// Output: true
// Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
//
// Example 5:
//
// Input:
// s = "acdcb"
// p = "a*c?b"
// Output: false
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public boolean isMatch(String s, String p) {

        // return method1(s, p);

        return method2(s, p);
    }

    private boolean method2(String s, String p) {
        // 2 pointer
        // O(N * M), e.g. worst case: s = "aaaaaaaaaa", p = "*aaa"
        int n = s.length();
        int m = p.length();
        int i = 0, j = 0;
        int prev = 0, startIndex = -1;
        while (i < n) {
            if (j < m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < m && p.charAt(j) == '*') {
                // p 是 *， 把 * 的位置记下来， 以及对应的 s 的位置
                // 然后只移动 p 指针, 因为我们不知道这个 * 要匹配掉多少
                // 如果 s 的下一个和 p 的下一个可以匹配， 那么就可以是 empty
                // 如果还不匹配， 但有了 startIndex， s 可以继续往下匹配， p 再来过
                startIndex = j;
                prev = i;
                j++;
            } else if (startIndex != -1) {
                // 此时 s.charAt(i) != p.charAt(j), 但 p 前面有个 *
                // 所以 j 又退回 * 下一个
                j = startIndex + 1;
                // s 中再用 * 匹配抹掉一个
                prev++;
                // i 退回， 重新试着匹配
                i = prev;
            } else {
                // 怎么都匹配不了
                return false;
            }
        }
        // i 走完了， j 可能没走完
        // 只有 * 可以当作 empty 继续
        while (j < m && p.charAt(j) == '*') {
            j++;
        }
        // 如果 j 还有其他 char， 则不能匹配
        return j == m;
    }

    private boolean method1(String s, String p) {
        // O(N * M) time and space

        //              { match     f[i - 1][j - 1]
        //      char {
        //              { not match False
        // p {  ?                   f[i - 1][j - 1]
        //              { empty     f[i][j - 1]
        //      * {
        //              { multple   f[i - 1][j]

        int n = s.length();
        int m = p.length();
        // definition: f[i][j] := s 前 i 个和 p 前 j 个能否匹配
        boolean[][] f = new boolean[n + 1][m + 1];
        // init
        f[0][0] = true;
        // 没 pattern， s 只要有 char 就是 false
        for (int i = 1; i < n + 1; i++) {
            f[i][0] = false;
        }
        // 当 s 为空的时候， p 只有 * (empty sequence) 才可以为 true
        for (int j = 1; j < m + 1; j++) {
            if (p.charAt(j - 1) != '*') {
                break;
            }
            f[0][j] = f[0][j - 1];
        }
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                // 两个 char 匹配， 或者 p 此时为 ？ => 总之， 两者此时匹配， 可以抹掉
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    f[i][j] = f[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    // p 为 *， 可以有两种情况： 取 empty sequence || 取 multiple
                    // empty 那就看 p 前面一个和当下的 s 能否匹配
                    // multiple 说明把当前 s.charAt(i - 1) 匹配掉了， 而 p 的 * 还保留， 因为还可以继续往前匹配 s 更前面的 char
                    f[i][j] = f[i][j - 1] || f[i - 1][j];
                }
            }
        }
        // result
        return f[n][m];
    }
}
