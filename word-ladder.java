// 127. Word Ladder
// DescriptionHintsSubmissionsDiscussSolution
// Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
//
// Only one letter can be changed at a time.
// Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
// Note:
//
// Return 0 if there is no such transformation sequence.
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
// Output: 5
//
// Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
// return its length 5.
// Example 2:
//
// Input:
// beginWord = "hit"
// endWord = "cog"
// wordList = ["hot","dot","dog","lot","log"]
//
// Output: 0
//
// Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.


class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) {
            return 0;
        }

        // return mytry(beginWord, endWord, wordList);

        // return method2(beginWord, endWord, wordList);

        return method3(beginWord, endWord, wordList);
    }


    private int method3(String begin, String end, List<String> words) {
        // 2-end BFS, faster
        // "The idea behind bidirectional search is to run two simultaneous searches—one forward from the initial state and the other backward from the goal—hoping that the two searches meet in the middle. The motivation is that b^(d/2) + b^(d/2) is much less than b^d. b is branch factor, d is depth. "
        // the intuition is that you are replacing a big search tree in the one-end solution with two smaller trees in the two-end solution. Both solutions have the same TOTAL depth, yet tree width grows exponentially w.r.t. depths, and the two-end solutions results in less nodes being visited.
        Set<String> starts = new HashSet<>();
        Set<String> ends = new HashSet<>();
        Set<String> visited = new HashSet<>();
        starts.add(begin);
        ends.add(end);
        int steps = 1;
        Set<String> wordSet = new HashSet<>();
        for (String str : words) {
            wordSet.add(str);
        }
        while (!starts.isEmpty() && !ends.isEmpty()) {
            steps++;
            if (starts.size() > ends.size()) {
                Set<String> temp = new HashSet<>();
                temp = starts;
                starts = ends;
                ends = temp;
            }
            Set<String> set = new HashSet<>();
            for (String s : starts) {
                char[] array = s.toCharArray();
                for (int i = 0; i < array.length; i++) {
                    char ori = array[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == ori) {
                            continue;
                        }
                        array[i] = c;
                        String next = String.valueOf(array);
                        if (ends.contains(next)) {
                            return steps;
                        }
                        if (visited.contains(next)) {
                            continue;
                        }
                        if (wordSet.contains(next)) {
                            set.add(next);
                            // It's safe to share a visited set for both ends since if they meet same string it terminates early. (vis is useful since we cannot remove word from dict due to bidirectional search)
                            visited.add(next);
                        }
                    }
                    array[i] = ori;
                }
            }
            starts = set;
        }
        return 0;
    }

    private int method2(String begin, String end, List<String> words) {
        // we can do some different: 1- don't need to store all possibilities to change next words in advance, 2- use another way to get the next word, 3- don't use another set to record what words we already reached but just remove those words from original given words set
        // because the problem changed from given set to given list, so we should use another set to determine contains(), otherwise it would cause TLE
        Set<String> wordSet = new HashSet<>(); // so we can use contains() in O(1)
        for (String str : words) {
            wordSet.add(str);
        }
        // Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(begin);
        wordSet.remove(begin); // 用过的就从里面删掉
        int steps = 1;
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                for (String next : getNext(curr, wordSet)) {
                    if (!wordSet.contains(next)) {
                        // 不能够再变换了
                        continue;
                    }
                    if (next.equals(end)) {
                       return steps;
                    }
                    queue.offer(next);
                    wordSet.remove(next);
                }
            }
        }
        return 0;
    }
    private List<String> getNext(String curr, Set<String> wordSet) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < curr.length(); i++) {

            for (char c = 'a'; c <= 'z'; c++) {
                char[] array = curr.toCharArray();
                array[i] = c;
                String next = String.valueOf(array);
                if (wordSet.contains(next)) {
                    result.add(next);
                }
            }
        }
        return result;
    }

    private int mytry(String begin, String end, List<String> words) {
        // find those word can change by 1 char
        // begin change to next
        Map<String, List<String>> map = new HashMap<>();
        List<String> temp = new ArrayList<>();
        for (String s : words) {
            if (!begin.equals(s) && canTrans(begin, s)) {
                temp.add(s);
            }
        }
        map.put(begin, temp);
        // all other candidate can change to next
        for (int i = 0; i < words.size(); i++) {
            String curr = words.get(i);
            // System.out.print("current string is: " + curr + ", can change to " );
            List<String> list = new ArrayList<>();
            for (String s : words) {
                if (!curr.equals(s) && canTrans(curr, s)) {
                    // System.out.print(s + " ");
                    list.add(s);
                }
            }
            // System.out.println();
            map.put(curr, list);
        }

        // BSF
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(begin);
        set.add(begin);
        int steps = 1;
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (!map.containsKey(curr)) {
                    continue;
                }
                for (String next : map.get(curr)) {
                    if (next.equals(end)) {
                        return steps;
                    }
                    if (set.contains(next)) {
                        continue;
                    }
                    queue.offer(next);
                    set.add(next);
                }
            }
        }
        return 0;
    }
    private boolean canTrans(String ori, String target) {
        int count = 0;
        for (int i = 0; i < ori.length(); i++) {
            if (ori.charAt(i) != target.charAt(i)) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }
}
