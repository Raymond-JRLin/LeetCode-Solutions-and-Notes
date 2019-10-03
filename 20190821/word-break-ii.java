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
// Seen this question in a real interview before?
// Difficulty:Hard



class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {

        // return mytry(s, wordDict);

        return method2(s, wordDict);
    }

    private List<String> method2(String s, List<String> dict) {
        List<String> result = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();
        return dfs2(s, dict, 0, map);
    }
    private List<String> dfs2(String s, List<String> dict, int pos, Map<Integer, List<String>> map) {
        if (map.containsKey(pos)) {
            return map.get(pos);
        }
        List<String> list = new ArrayList<>();
        if (pos == s.length()) {
            list.add("");
            return list;
        }
        for (String word : dict) {
            if (pos + word.length() > s.length()) {
                continue;
            }
            if (!s.substring(pos, pos + word.length()).equals(word)) {
                continue;
            }
            List<String> nexts = dfs2(s, dict, pos + word.length(), map);
            for (String next : nexts) {
                list.add(word + (next.isEmpty() ? "" : " " + next));
            }
        }
        map.put(pos, list);
        return map.get(pos);
    }

    private List<String> mytry(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        dfs(s, wordDict, 0, new HashSet<Integer>(), result, "");
        return result;
    }
    private boolean dfs(String s, List<String> wordDict, int pos, Set<Integer> set, List<String> result, String curr) {
        if (pos == s.length()) {
            result.add(new String(curr.trim()));
            return true;
        }
        if (set.contains(pos)) {
            return false;
        }
        boolean doable = false;
        for (String word : wordDict) {
            if (pos + word.length() > s.length()) {
                continue;
            }
            if (!s.substring(pos, pos + word.length()).equals(word)) {
                continue;
            }
            if (dfs(s, wordDict, pos + word.length(), set, result, curr + " " + word)) {
                doable = true;
            }
        }
        // went through all possibilities, cannot break it
        if (!doable) {
            set.add(pos);
        }
        return doable;
    }
}
