// 692. Top K Frequent Words
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a non-empty list of words, return the k most frequent elements.
//
// Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
//
// Example 1:
//
// Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
// Output: ["i", "love"]
// Explanation: "i" and "love" are the two most frequent words.
//     Note that "i" comes before "love" due to a lower alphabetical order.
//
// Example 2:
//
// Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
// Output: ["the", "is", "sunny", "day"]
// Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
//     with the number of occurrence being 4, 3, 2 and 1 respectively.
//
// Note:
//
//     You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
//     Input words contain only lowercase letters.
//
// Follow up:
//
//     Try to solve it in O(n log k) time and O(n) extra space.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0) {
            return Collections.emptyList();
        }

        // return mytry(words, k);

        return method2(words, k);
    }

    private List<String> method2(String[] words, int k) {
        // 用 bucket 和 Trie 来做
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        // 把相同频率的放到同一个 bucket 中， Trie 先遇到的就是 alphabetical 小的
        int n = words.length;
        TrieNode[] bucket = new TrieNode[n + 1];
        for (String key : map.keySet()) {
            int freq = map.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new TrieNode();
            }
            addWord(bucket[freq], key);
        }

        List<String> result = new ArrayList<>();
        for (int i = n; i >= 0 && result.size() < k; i--) {
            if (bucket[i] == null) {
                continue;
            }
            findWord(bucket[i], result, k);
        }
        return result;
    }
    private void findWord(TrieNode root, List<String> result, int k) {
        // 找所有的， recursion
        if (root == null) {
            return;
        }
        // 同一频率可能并不需要所有的
        if (result.size() >= k) {
            return;
        }
        if (root.s != null) {
            result.add(root.s);
        }
        for (int i = 0; i < root.array.length; i++) {
            if (root.array[i] != null) {
                findWord(root.array[i], result, k);
            }
        }
    }
    private boolean addWord(TrieNode root, String key) {
        TrieNode head = root;
        for (char c : key.toCharArray()) {
            int i = c - 'a';
            if (head.array[i] == null) {
                head.array[i] = new TrieNode();
            }
            head =  head.array[i];
        }
        head.s = key;
        return true;
    }
    private class TrieNode {
        TrieNode[] array;
        String s;

        TrieNode() {
            this.array = new TrieNode[26];
            this.s = null;
        }
    }

    private List<String> mytry(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (String key : map.keySet()) {
            pq.offer(new Pair(key, map.get(key)));
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            // pq 是 min heap 放正确了， 但是这里要倒着加入 list， 因为先拿出来是小的
            result.add(0, pq.poll().s);
        }
        return result;
    }
    private class Pair implements Comparable<Pair> {
        String s;
        int freq;

        Pair(String s, int freq) {
            this.s = s;
            this.freq = freq;
        }

        // 要是 min heap， 所以这里要反过来写
        @Override
        public int compareTo(Pair o2) {
            if (this.freq == o2.freq) {
                return o2.s.compareTo(this.s);
            } else {
                return Integer.compare(this.freq, o2.freq);
            }
        }
    }
}
