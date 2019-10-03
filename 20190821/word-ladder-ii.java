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
        if (wordList == null || wordList.isEmpty()) {
            return Collections.emptyList();
        }
        if (!wordList.contains(endWord)) {
            return Collections.emptyList();
        }

        // The question is to find ALL SHORTEST paths, so we need to
        // 1. know what the length of the shortest path is, or those node's distance from beginWord who are on the shortest path
        // 2. how we can go from current level to next level
        // =>
        // 1. find the shortest: BFS
        // 2. get all shortest: DFS based on BFS records
        return mytry(beginWord, endWord, wordList);
    }

    private List<List<String>> mytry(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> neighbors = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        bfs(beginWord, endWord, wordList, neighbors, distance);
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add(beginWord);
        dfs(beginWord, endWord, wordList, result, list, neighbors, distance);
        return result;
    }
    private void bfs(String beginWord, String endWord, List<String> wordList, Map<String, List<String>> neighbors,
                     Map<String, Integer> distance) {
        wordList.add(beginWord);
        for (String word : wordList) {
            neighbors.put(word, new ArrayList<>());
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                int dist = distance.get(curr);
                List<String> nexts = getNext(curr, wordList);
                // 取所有可能的 next， neighbors 要更新所有
                for (String next : nexts) {
                    neighbors.get(curr).add(next);
                    // distance 已经有了， 说明已经有了最短距离
                    if (distance.containsKey(next)) {
                        continue;
                    }
                    distance.put(next, dist + 1);
                    queue.offer(next);
                    if (next.equals(endWord)) {
                        found = true;
                    }
                }
            }
            if (found) {
                return;
            }
        }
    }
    private List<String> getNext(String curr, List<String> wordList) {
        List<String> list = new ArrayList<>();
        int count = 0;
        for (String next : wordList) {
            if (!canChange(curr, next)) {
                continue;
            }
            list.add(next);
        }
        return list;
    }
    private boolean canChange(String curr, String next) {
        int count = 0;
        for (int i = 0; i < curr.length(); i++) {
            if (curr.charAt(i) != next.charAt(i)) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return true;
    }
    private void dfs(String beginWord, String endWord, List<String> wordList, List<List<String>> result, List<String> list,
                     Map<String, List<String>> neighbors, Map<String, Integer> distance) {
        if (beginWord.equals(endWord)) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (String next : neighbors.get(beginWord)) {
            if (distance.get(next) == distance.get(beginWord) + 1) {
                list.add(next);
                dfs(next, endWord, wordList, result, list, neighbors, distance);
                list.remove(list.size() - 1);
            }
        }
    }
}
