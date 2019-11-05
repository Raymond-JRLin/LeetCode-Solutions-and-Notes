// 425. Word Squares
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a set of words (without duplicates), find all word squares you can build from them.
//
// A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
//
// For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
//
// b a l l
// a r e a
// l e a d
// l a d y
//
// Note:
//
//     There are at least 1 and at most 1000 words.
//     All words will have the exact same length.
//     Word length is at least 1 and at most 5.
//     Each word contains only lowercase English alphabet a-z.
//
// Example 1:
//
// Input:
// ["area","lead","wall","lady","ball"]
//
// Output:
// [
//   [ "wall",
//     "area",
//     "lead",
//     "lady"
//   ],
//   [ "ball",
//     "area",
//     "lead",
//     "lady"
//   ]
// ]
//
// Explanation:
// The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
//
// Example 2:
//
// Input:
// ["abat","baba","atan","atal"]
//
// Output:
// [
//   [ "baba",
//     "abat",
//     "baba",
//     "atan"
//   ],
//   [ "baba",
//     "abat",
//     "baba",
//     "atal"
//   ]
// ]
//
// Explanation:
// The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public List<List<String>> wordSquares(String[] words) {
        if (words == null || words.length == 0) {
            return Collections.emptyList();
        }

        // return method1(words);

        return method2(words);
    }

    // 422. Valid Word Square 是相似的
    // 但是用 DFS 找所有可能， 然后看是否 valid 肯定 TLE
    // 要找到更为巧妙的方法来找下一个 string
    // 实际上， 假设第一个是 ball， 根据定义， 下一个 row 肯定要以 a 开头
    // 所以这里需要预处理， 把每个 word 的 prefix 都找到
    // 可以用 map 或 Trie 来辅助

    private List<List<String>> method2(String[] words) {
        int n = words.length;
        int len = words[0].length();
        // use TrieNode and Trie to record prefix and words
        Trie trie = new Trie(words);
        List<List<String>> result = new ArrayList<>();
        for (String word : words) {
            List<String> list = new ArrayList<>();
            list.add(word);
            dfs2(word, trie, len, result, list, 1);
        }
        return result;
    }
    private void dfs2(String curr, Trie trie, int len, List<List<String>> result, List<String> list, int index) {
        if (index == len) {
            result.add(new ArrayList<>(list));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s.charAt(index));
        }
        String prefix = sb.toString();
        List<String> nexts = trie.getByPrefix(prefix);
        if (nexts.isEmpty()) {
            return;
        }
        for (String next : nexts) {
            list.add(next);
            dfs2(next, trie, len, result, list, index + 1);
            list.remove(list.size() - 1);
        }

    }
    class TrieNode {
        TrieNode[] nodes;
        List<String> strings;

        TrieNode() {
            nodes = new TrieNode[26];
            strings = new ArrayList<>();
        }
    }
    class Trie {
        TrieNode root;

        Trie(String[] words) {
            this.root = new TrieNode();
            for (String word : words) {
                TrieNode head = root;
                for (char c : word.toCharArray()) {
                    if (head.nodes[c - 'a'] == null) {
                        head.nodes[c - 'a'] = new TrieNode();
                    }
                    head.nodes[c - 'a'].strings.add(word);
                    head = head.nodes[c - 'a'];
                }
            }
        }

        List<String> getByPrefix(String prefix) {
            TrieNode head = root;
            for (char c : prefix.toCharArray()) {
                if (head.nodes[c - 'a'] == null) {
                    return new ArrayList<>();
                }
                head = head.nodes[c - 'a'];
            }
            return new ArrayList<>(head.strings);
        }
    }

    private List<List<String>> method1(String[] words) {
        int n = words.length;
        int len = words[0].length();
        // use HashMap to record prefix and words
        Map<String, Set<String>> map = new HashMap<>(); // <prefix, <set of word>>
        for (String word : words) {
            // get prefix
            for (int i = 1; i <= word.length(); i++) {
                String prefix = word.substring(0, i);
                Set<String> set = map.computeIfAbsent(prefix, dummy -> (new HashSet<String>()));
                set.add(word);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for (String word : words) {
            List<String> list = new ArrayList<>();
            list.add(word);
            dfs(word, map, len, result, list, 1);
        }
        return result;
    }
    private void dfs(String curr, Map<String, Set<String>> map, int len, List<List<String>> result, List<String> list, int index) {
        if (index == len) {
            result.add(new ArrayList<>(list));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s.charAt(index));
        }
        String prefix = sb.toString();
        if (!map.containsKey(prefix)) {
            return;
        }
        for (String next : map.get(prefix)) {
            list.add(next);
            dfs(next, map, len, result, list, index + 1);
            list.remove(list.size() - 1);
        }

    }
}
