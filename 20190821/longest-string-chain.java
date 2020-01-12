// 1048. Longest String Chain
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a list of words, each word consists of English lowercase letters.
//
// Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
//
// A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
//
// Return the longest possible length of a word chain with words chosen from the given list of words.
//
//
//
// Example 1:
//
// Input: ["a","b","ba","bca","bda","bdca"]
// Output: 4
// Explanation: one of the longest word chain is "a","ba","bda","bdca".
//
//
//
// Note:
//
//     1 <= words.length <= 1000
//     1 <= words[i].length <= 16
//     words[i] only consists of English lowercase letters.
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int longestStrChain(String[] words) {

        // return mytry(words);

        // return method2(words);

        return method3(words);
    }

    // mytry 有几个可以改进的地方：
    // 1. DFS 过程中可以用 memo 记下来 word 及其最长的 chain
    // 2. 找 prev 其实不需要用 set， 双指针也可以， 因为是任意加入， 也可以用更长的 string 来取所有的 substring 看是否在 words 中

    private int method3(String[] words) {
        // iterative DP
        Arrays.sort(words, (o1, o2) -> (Integer.compare(o1.length(), o2.length())));
        // definition
        Map<String, Integer> f = new HashMap<>();
        int result = 0;
        // init
        // DP
        for (String word : words) {
            int chain = 0;
            for (int i = 0; i < word.length(); i++) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                chain = Math.max(chain, f.getOrDefault(prev, 0) + 1);
            }
            f.put(word, chain);
            result = Math.max(result, chain);
        }
        // result
        return result;
    }

    private int method2(String[] words) {
        // 带 memo 的 recursive DP
        Set<String> set = new HashSet<>(Arrays.asList(words));
        Map<String, Integer> memo = new HashMap<>();
        int result = 0;
        for (String word : words) {
            result = Math.max(result, recursion(word, set, memo));
        }
        return result;
    }
    private int recursion(String word, Set<String> set, Map<String, Integer> memo) {
        if (!set.contains(word)) {
            return 0;
        }
        if (memo.containsKey(word)) {
            return memo.get(word);
        }
        int result = 0;
        for (int i = 0; i < word.length(); i++) {
            String prev = word.substring(0, i) + word.substring(i + 1);
            result = Math.max(result, recursion(prev, set, memo));
        }
        memo.put(word, result + 1);
        return result + 1;
    }

    private int mytry(String[] words) {
        Map<Integer, List<String>> map = new HashMap<>(); // <string length, list<string>>
        int minLen = Integer.MAX_VALUE;
        int maxLen = 0;
        for (String word : words) {
            int len = word.length();
            minLen = Math.min(minLen, len);
            maxLen = Math.max(maxLen, len);
            map.computeIfAbsent(len, dummy -> (new ArrayList<>())).add(word);
        }
        int result = 0;
        for (int i = minLen; i <= maxLen; i++) {
            if (!map.containsKey(i)) {
                continue;
            }
            for (String word : map.get(i)) {
                curr = 0;
                dfs(map, word, i + 1, 1);
                result = Math.max(result, curr);
            }
        }
        return result;
    }
    private int curr;
    private void dfs(Map<Integer, List<String>> map, String word, int len, int chain) {
        if (!map.containsKey(len)) {
            curr = Math.max(curr, chain);
            return;
        }
        for (String next : map.get(len)) {
            if (!isPre(word, next)) {
                // 注意这里如果没能继续下去， 当前已经得到的也需要比较记录一下
                curr = Math.max(curr, chain);
                continue;
            }
            dfs(map, next, len + 1, chain + 1);
        }
    }
    private boolean isPre(String prev, String curr) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : curr.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : prev.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) {
                map.remove(c);
            }
        }
        return map.size() == 1;
    }

}
