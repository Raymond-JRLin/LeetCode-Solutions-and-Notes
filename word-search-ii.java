// 212. Word Search II
// DescriptionHintsSubmissionsDiscussSolution
// Given a 2D board and a list of words from the dictionary, find all words in the board.
//
// Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
//
// Example:
//
// Input:
// words = ["oath","pea","eat","rain"] and board =
// [
//   ['o','a','a','n'],
//   ['e','t','a','e'],
//   ['i','h','k','r'],
//   ['i','f','l','v']
// ]
//
// Output: ["eat","oath"]
// Note:
// You may assume that all inputs are consist of lowercase letters a-z.


class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
            return Collections.emptyList();
        }

        return method1(board, words);
    }

    private List<String> method1(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        return result;
    }
    private void dfs(char[][] board, int i, int j, TrieNode root, List<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            return;
        }
        char c = board[i][j];
        if (c == '#') {
            return;
        }
        if (!root.map.containsKey(c)) {
            return;
        }
        TrieNode next = root.map.get(c); // 拿到当前 char 的 node， 然后向下走一步
        if (next.word != null) {
            result.add(next.word);
            next.word = null; // de-dupe
        }
        // mark
        board[i][j] = '#';
        // DFS to 4 directions
        dfs(board, i, j + 1, next, result);
        dfs(board, i, j - 1, next, result);
        dfs(board, i + 1, j, next, result);
        dfs(board, i - 1, j, next, result);
        board[i][j] = c; // backtracking
    }
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode head = root;
            for (char c : word.toCharArray()) {
                if (head.map.containsKey(c)) {
                    head = head.map.get(c);
                } else {
                    head.map.put(c, new TrieNode());
                    head = head.map.get(c);
                }
            }
            head.word = word;
        }
        return root;
    }
    private class TrieNode {
        Map<Character, TrieNode> map;
        String word;
        public TrieNode() {
            map = new HashMap<>();
            word = null;
        }
    }
}
