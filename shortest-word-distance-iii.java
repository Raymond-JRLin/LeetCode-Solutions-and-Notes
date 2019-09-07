// 245. Shortest Word Distance III
// DescriptionHintsSubmissionsDiscussSolution
// Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
//
// word1 and word2 may be the same and they represent two individual words in the list.
//
// Example:
// Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
// Input: word1 = “makes”, word2 = “coding”
// Output: 1
// Input: word1 = "makes", word2 = "makes"
// Output: 3
// Note:
// You may assume word1 and word2 are both in the list.
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {

        // return mytry(words, word1, word2);

        // return mytry2(words, word1, word2);

        return method3(words, word1, word2);
    }

    private int method3(String[] words, String w1, String w2) {
        // modification on another method in I
        int n = words.length;
        int index1 = -1;
        int index2 = -1;
        int result = n + 1;
        if (!w1.equals(w2)) {
            // totally same as I
            for (int i = 0; i < n; i++) {
                if (words[i].equals(w1)) {
                    index1 = i;
                }
                if (words[i].equals(w2)) {
                    index2 = i;
                }
                if (index1 != -1 && index2 != -1) {
                    result = Math.min(result, Math.abs(index1 - index2));
                }
            }
        } else {
            // modification: index1 and index2 are like prev and curr pointer
            for (int i = 0; i < n; i++) {
                if (words[i].equals(w1)) {
                    index2 = index1; // record the prev index
                    index1 = i;
                }
                if (index1 != -1 && index2 != -1) {
                    result = Math.min(result, index1 - index2);
                }
            }
        }

        return result;
    }

    private int mytry2(String[] words, String w1, String w2) {
        // use HashMap to record the values and its index, similar to II
        Map<String, List<Integer>> map = new HashMap<>();; // <string, index list>
        for (int i = 0; i < words.length; i++) {
            // only record searching words
            if (words[i].equals(w1) || words[i].equals(w2)) {
                List<Integer> list = map.getOrDefault(words[i], new ArrayList<Integer>());
                list.add(i);
                map.put(words[i], list);
            }
        }

        int result = Integer.MAX_VALUE;
        if (w1.equals(w2)) {
            List<Integer> list = map.get(w1);
            for (int i = 0; i < list.size() - 1; i++) {
                result = Math.min(result, list.get(i + 1) - list.get(i));
            }
            return result;
        } else {
            return shortest(map, w1, w2);
        }

    }
    public int shortest(Map<String, List<Integer>> map, String word1, String word2) {
        int result = Integer.MAX_VALUE;
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int i = 0;
        int j = 0;
        while (i < list1.size() && j < list2.size()) {
            int index1 = list1.get(i);
            int index2 = list2.get(j);
            if (index1 < index2) {
                result = Math.min(result, index2 - index1);
                i++; // move i bigger to get closer to index2
            } else {
                result = Math.min(result, index1 - index2);
                j++; // move j bigger to get closer to index1
            }
        }
        return result;
    }

    private int mytry(String[] words, String w1, String w2) {
        // brute force: O(N ^ 2), same as I
        int n = words.length;
        int index = 0;
        int result = n + 1;
        for (int i = 0; i < n; i++) {
            if (words[i].equals(w1)) {
                int left = i - 1;
                int right = i + 1;
                int count = 1;
                while (left >= 0 || right < n) {
                    if (left >= 0 && words[left].equals(w2)) {
                        break;
                    }
                    if (right < n && words[right].equals(w2)) {
                        break;
                    }
                    left--;
                    right++;
                    count++;
                }
                result = Math.min(result, count);
            }
        }

        return result;
    }
}
