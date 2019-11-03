// 72. Edit Distance
// DescriptionHintsSubmissionsDiscussSolution
//
// Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
//
// You have the following 3 operations permitted on a word:
//
//     Insert a character
//     Delete a character
//     Replace a character
//
// Example 1:
//
// Input: word1 = "horse", word2 = "ros"
// Output: 3
// Explanation:
// horse -> rorse (replace 'h' with 'r')
// rorse -> rose (remove 'r')
// rose -> ros (remove 'e')
//
// Example 2:
//
// Input: word1 = "intention", word2 = "execution"
// Output: 5
// Explanation:
// intention -> inention (remove 't')
// inention -> enention (replace 'i' with 'e')
// enention -> exention (replace 'n' with 'x')
// exention -> exection (replace 'n' with 'c')
// exection -> execution (insert 'u')
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int minDistance(String word1, String word2) {

        return method1(word1, word2);
    }

    private int method1(String w1, String w2) {
        int n = w1.length();
        int m = w2.length();
        int[][] memo = new int[n + 1][m + 1];
        for (int[] nums : memo) {
            Arrays.fill(nums, -1);
        }
        return recursion(w1, w2, memo);
    }
    private int recursion(String w1, String w2, int[][] memo) {
        int i = w1.length();
        int j = w2.length();
        if (i == 0 && j == 0) {
            return memo[i][j] = 0;
        }
        if (i == 0) {
            return memo[i][j] = j;
        }
        if (j == 0) {
            return memo[i][j] = i;
        }
        int min = Integer.MAX_VALUE;
        if (w1.equals(w2)) {
            return memo[i][j] = 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (w1.charAt(0) == w2.charAt(0)) {
            min = Math.min(min, recursion(w1.substring(1), w2.substring(1), memo));
        } else {
            // insert
            min = Math.min(min, recursion(w1.substring(1), w2, memo) + 1);
            // delete
            min = Math.min(min, recursion(w1, w2.substring(1), memo) + 1);
            // replace
            min = Math.min(min, recursion(w1.substring(1), w2.substring(1), memo) + 1);

        }
        return memo[i][j] = min;
    }
}
