// 139. Word Break
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
//
// Note:
//
//     The same word in the dictionary may be reused multiple times in the segmentation.
//     You may assume the dictionary does not contain duplicate words.
//
// Example 1:
//
// Input: s = "leetcode", wordDict = ["leet", "code"]
// Output: true
// Explanation: Return true because "leetcode" can be segmented as "leet code".
//
// Example 2:
//
// Input: s = "applepenapple", wordDict = ["apple", "pen"]
// Output: true
// Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
//              Note that you are allowed to reuse a dictionary word.
//
// Example 3:
//
// Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
// Output: false
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        // return mytry(s, wordDict);

        return method2(s, wordDict);
    }

    private boolean method2(String s, List<String> wordDict) {
        int n = s.length();
        // definition: f[i] = if we can break word with wordDict to first i-th character
        boolean[] f = new boolean[n + 1];
        // initialization
        f[0] = true;
        // DP
        for (int i = 1; i < n + 1; i++) {
            // i is the end
            for (String word : wordDict) {
                if (i - word.length() >= 0 && s.substring(i - word.length(), i).equals(word)
                    && f[i - word.length()]) {
                    f[i] = true;
                    break;
                }
            }
        }
        // result
        return f[n];
    }

    private boolean mytry(String s, List<String> wordDict) {
        if (dfs(s, wordDict, 0, new HashSet<Integer>())) {
            return true;
        }
        return false;
    }
    private boolean dfs(String s, List<String> wordDict, int pos, Set<Integer> set) {
        if (pos == s.length()) {
            return true;
        }
        if (set.contains(pos)) {
            return false;
        }
        for (String word : wordDict) {
            if (pos + word.length() > s.length()) {
                continue;
            }
            if (!s.substring(pos, pos + word.length()).equals(word)) {
                continue;
            }
            if (dfs(s, wordDict, pos + word.length(), set)) {
                return true;
            } else {
                set.add(pos);
            }
        }
        return false;
    }
}
