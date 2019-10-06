// 1220. Count Vowels Permutation
// User Accepted:1293
// User Tried:1544
// Total Accepted:1340
// Total Submissions:2740
// Difficulty:Hard
// Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
//
// Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
// Each vowel 'a' may only be followed by an 'e'.
// Each vowel 'e' may only be followed by an 'a' or an 'i'.
// Each vowel 'i' may not be followed by another 'i'.
// Each vowel 'o' may only be followed by an 'i' or a 'u'.
// Each vowel 'u' may only be followed by an 'a'.
// Since the answer may be too large, return it modulo 10^9 + 7.
//
//
//
// Example 1:
//
// Input: n = 1
// Output: 5
// Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
// Example 2:
//
// Input: n = 2
// Output: 10
// Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
// Example 3:
//
// Input: n = 5
// Output: 68
//
//
// Constraints:
//
// 1 <= n <= 2 * 10^4


class Solution {
    public int countVowelPermutation(int n) {
        if (n == 0) {
            return 0;
        }

        // return mytry(n);

        return method1(n);
    }

    // 这里题目没有理清楚， 这个 following 只是针对相邻的 char， right after (immediate) ， 而不是之后都必须要满足

    private int method1(int n) {
        // DP
        // 实际上可以把他们之间的变换规则想象成一个图， 从一点出发， 经过 n 个点， 可以有多少种不同的走法
        // 依赖关系就变成了 DP 的关系转换方程， 譬如说， 现在选择 e， 那么前一个只能从 a 和 i 走过来

        final int MOD = (int) Math.pow(10, 9) + 7;

        // definition: f[i][j] = i 个字符以 j 结尾的有多少种， 0 <= j <= 5, 按顺序指代 aeiou
        long[][] f = new long[n + 1][5];
        // initialization
        for (int j = 0; j < 5; j++) {
            f[1][j] = 1L;
        }
        // DP
        for (int i = 2; i <= n; i++) {
            // 'a' 前面只能是 'e', 'i', 'u'
            f[i][0] = (f[i - 1][1] + f[i - 1][2] + f[i - 1][4]) % MOD;
            // 'e' 前面只能是 'a', 'i'
            f[i][1] = (f[i - 1][0] + f[i - 1][2]) % MOD;
            // 'i' 前面只能是 'e', 'o'
            f[i][2] = (f[i - 1][1] + f[i - 1][3]) % MOD;
            // 'o' 前面只能是 'i'
            f[i][3] = (f[i - 1][2]) % MOD;
            // 'u' 前面只能是 'i', 'o'
            f[i][4] = (f[i - 1][2] + f[i - 1][3]) % MOD;
        }
        // result
        int result = 0;
        for (int i = 0; i < 5; i++) {
            result = (int) (result + f[n][i]) % MOD;
        }
        return result;
    }

    private int mytry(int n) {
        // DFS
        // 纯 DFS 肯定会 TLE， 这里问有多少种， 那么就会想到 DP
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('a', Arrays.asList('e'));
        map.put('e', Arrays.asList('a', 'i'));
        map.put('i', Arrays.asList('a', 'e', 'o', 'u'));
        map.put('o', Arrays.asList('i', 'u'));
        map.put('u', Arrays.asList('a'));
        final int MOD = (int) Math.pow(10, 9) + 7;
        int[] result = new int[1];
        for (char c : "aeiou".toCharArray()) {
            dfs(n - 1, c, map, result, MOD);
        }
        return result[0];
    }
    private void dfs(int n, char prev, Map<Character, List<Character>> map, int[] result, int MOD) {
        if (n == 0) {
            result[0] = (result[0] + 1) % MOD;
            return;
        }
        for (char next : map.get(prev)) {
            dfs(n - 1, next, map, result, MOD);
        }
    }

}
