// 243. Shortest Word Distance
// DescriptionHintsSubmissionsDiscussSolution
// Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
//
// Example:
// Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
// Input: word1 = “coding”, word2 = “practice”
// Output: 3
// Input: word1 = "makes", word2 = "coding"
// Output: 1
// Note:
// You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.


class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {

        // return mytry(words, word1, word2);

        return method2(words, word1, word2);
    }

    private int method2(String[] words, String w1, String w2) {
        // 在 mytry 的基础上如何改进呢， 因为有重复并且和原 index 有关， 所以没办法做到 logN， 那么能不能在 O(N) 内查找到呢？ 关键点就在于要找最近的， 那么我们就总是记录最近一个遇到的 word1 和 word2， 那么我们就可以找到最近的距离
        // ref: https://leetcode.com/articles/shortest-word-distance/
        int n = words.length;
        int index1 = -1;
        int index2 = -1;
        int result = n + 1;
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
        return result;
    }

    private int mytry(String[] words, String w1, String w2) {
        // O(N ^ 2)
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
