// 126. Word Ladder II
// DescriptionHintsSubmissionsDiscussSolution
// Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
//
// Only one letter can be changed at a time
// Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
// Note:
//
// Return an empty list if there is no such transformation sequence.
// All words have the same length.
// All words contain only lowercase alphabetic characters.
// You may assume no duplicates in the word list.
// You may assume beginWord and endWord are non-empty and are not the same.
// Example 1:
//
// Input:
// beginWord = "hit",
// endWord = "cog",
// wordList = ["hot","dot","dog","lot","log","cog"]
//
// Output:
// [
//   ["hit","hot","dot","dog","cog"],
//   ["hit","hot","lot","log","cog"]
// ]
// Example 2:
//
// Input:
// beginWord = "hit"
// endWord = "cog"
// wordList = ["hot","dot","dog","lot","log"]
//
// Output: []
//
// Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.


class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        return method1(beginWord, endWord, wordList);
    }

    private List<List<String>> method1(String begin, String end, List<String> wordList) {
        Set<String> wordSet = new HashSet<>();
        for (String s : wordList) {
            wordSet.add(s);
        }
        if (!wordSet.contains(end)) {
            return Collections.emptyList();
        }
        // add begin word into wordSet
        wordSet.add(begin);

        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> neighbors = new HashMap<>(); // <string, strings that can transform to key>
        Map<String, Integer> dist = new HashMap<>(); // <distances from endWord>

        // use BFS to find the shortest path's length, we start from the end word
        bfs(end, begin, wordSet, neighbors, dist);
        // use DFS to find all paths with length of shortest length found by BFS, we start from begin word,
        dfs(begin, end, wordSet, neighbors, dist, result, new ArrayList<String>());

        return result;
    }
    private void dfs(String curr, String end, Set<String> wordSet, Map<String, List<String>> neighbors, Map<String, Integer> dist, List<List<String>> result, List<String> list) {
        // add current word
        list.add(curr);
        if (curr.equals(end)) {
            // get end
            result.add(new ArrayList<String>(list));
        } else {
            // go next word
            for (String next : neighbors.get(curr)) {
                if (dist.containsKey(next) && dist.get(curr) == dist.get(next) + 1) {
                    // attention: dist is the distance from endWord, i.e. curr -> next, curr is farther one
                    dfs(next, end, wordSet, neighbors, dist, result, list);
                }
            }
        }
        // backtracking
        list.remove(list.size() - 1);
    }
    private void bfs(String begin, String end, Set<String> wordSet, Map<String, List<String>> neighbors, Map<String, Integer> dist) {
        for (String s : wordSet) {
            neighbors.put(s, new ArrayList<String>());
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(begin); // actually it's endWord
        dist.put(begin, 0);
        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                List<String> nexts = getNext(curr, wordSet); // get next word, or actually we can understand as get those words which can transform to this current word
                for (String next : nexts) {
                    // because we go from end to begin, so transformation is next -> curr
                    neighbors.get(next).add(curr);
                    if (!dist.containsKey(next)) {
                        // get the shortest path, work as visited
                        dist.put(next, dist.get(curr) + 1);
                        queue.offer(next);
                    }
                    if (next.equals(end)) {
                        found = true;
                    }
                }

            }
            // if we get end word on this level, no need to go further
            if (found) {
                return; // or we can break
            }
        }
    }
    private List<String> getNext(String start, Set<String> wordSet) {
        List<String> result = new ArrayList<>();
        char[] s = start.toCharArray();
        for (int i = 0; i < s.length; i++) {
            char ori = s[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == ori) {
                    continue;
                }
                s[i] = c;
                String next = String.valueOf(s);
                if (wordSet.contains(next)) {
                    result.add(next);
                }
            }
            s[i] = ori;
        }
        return result;
    }
}
