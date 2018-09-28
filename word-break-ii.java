// 140. Word Break II
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
//
// Note:
//
// The same word in the dictionary may be reused multiple times in the segmentation.
// You may assume the dictionary does not contain duplicate words.
// Example 1:
//
// Input:
// s = "catsanddog"
// wordDict = ["cat", "cats", "and", "sand", "dog"]
// Output:
// [
//   "cats and dog",
//   "cat sand dog"
// ]
// Example 2:
//
// Input:
// s = "pineapplepenapple"
// wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
// Output:
// [
//   "pine apple pen apple",
//   "pineapple pen apple",
//   "pine applepen apple"
// ]
// Explanation: Note that you are allowed to reuse a dictionary word.
// Example 3:
//
// Input:
// s = "catsandog"
// wordDict = ["cats", "dog", "sand", "and", "cat"]
// Output:
// []


class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {

        // return mytry(s, wordDict);

        return method2(s, wordDict);
    }

    private List<String> method2(String s, List<String> dict) {
        // another DFS with memorization pruning
        // ref: https://leetcode.com/articles/word-break-ii/
        Set<String> set = new HashSet<>();
        for (String str : dict) {
            set.add(str);
        }
        // use a HashMap to record, also we can return map.get() directly
        Map<Integer, List<String>> map = new HashMap<>(); // <start index, possible makeup>
        return dfs2(s, set, map, 0);
    }
    private List<String> dfs2(String s, Set<String> set, Map<Integer, List<String>> map, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        List<String> result = new ArrayList<>();
        if (start == s.length()) {
            result.add(""); // 我觉得这个是用来区分到最后一位是完成了， 还是不能够得到而没完成组合
        }
        for (int end = start + 1; end <= s.length(); end++) {
            String curr = s.substring(start, end);
            if (set.contains(curr)) {
                // get the possible right part, starting at "end" index
                List<String> list = dfs2(s, set, map, end);
                for (String next : list) {
                    if (next.equals("")) {
                        result.add(curr);
                    } else {
                        result.add(curr + " " + next);
                    }
                }
            }
        }
        map.put(start, result);
        return result;
    }

    private List<String> mytry(String s, List<String> dict) {
        // pruning with Word Break I dp
        int n = s.length();
        Set<String> set = new HashSet<>();
        for (String str : dict) {
            set.add(str);
        }
        // check which endpoint in s can be reachable
        boolean[] f = dp(s, set);
        // cannot make this whole string s
        if (!f[n]) {
            return Collections.emptyList();
        }
        // DFS to get all possible answer
        List<String> result = new ArrayList<>();
        dfs(s, set, result, 0, "", f);
        return result;
    }
    private void dfs(String s, Set<String> set, List<String> result, int index, String ans, boolean[] f) {
        // pure DFS would TLE, so we should memorize some extra info to do pruning
        if (index == s.length()) {
            result.add(ans.trim());
            return;
        }
        for (int i = index + 1; i <= s.length(); i++) {
            if (!f[i]) {
                // this endpoint of s[index, i] cannot be partitioned
                continue;
            }
            String curr = s.substring(index, i);
            if (set.contains(curr)) {
                ans += curr + " ";
                dfs(s, set, result, i, ans, f);
                ans = ans.substring(0, ans.length() - curr.length() - 1);
            }
        }
    }
    private boolean[] dp(String s, Set<String> set) {
        int n = s.length();
        // definition
        boolean[] f = new boolean[n + 1];
        // initialization
        f[0] = true;
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && set.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f;
    }
}
