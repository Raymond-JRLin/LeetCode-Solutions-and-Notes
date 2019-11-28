// 211. Add and Search Word - Data structure design
// DescriptionHintsSubmissionsDiscussSolution
//
// Design a data structure that supports the following two operations:
//
// void addWord(word)
// bool search(word)
//
// search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
//
// Example:
//
// addWord("bad")
// addWord("dad")
// addWord("mad")
// search("pad") -> false
// search("bad") -> true
// search(".ad") -> true
// search("b..") -> true
//
// Note:
// You may assume that all words are consist of lowercase letters a-z.
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class WordDictionary {

    // 实际上看到这个就想到了要用 Trie
    // 那如果是 . 的话， 就遍历所有可能， 即做基于 Trie 的 DFS

    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.string = word;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        TrieNode curr = root;
        return dfs(word, 0, curr);
    }
    private boolean dfs(String word, int index, TrieNode curr) {
        if (curr == null) {
            return false;
        }
        if (index == word.length()) {
            return curr.string != null;
        }
        if (word.charAt(index) == '.') {
            // check all possibilities
            for (int i = 0; i < curr.children.length; i++) {
                if (dfs(word, index + 1, curr.children[i])) {
                    return true;
                }
            }
            return false;
        } else {
            // normal letter
            return dfs(word, index + 1, curr.children[word.charAt(index) - 'a']);
        }
    }


    private class TrieNode {
        TrieNode[] children;
        String string;

        TrieNode() {
            children = new TrieNode[26];
            string = null;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
