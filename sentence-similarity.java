// 734. Sentence Similarity
// User Accepted: 1141
// User Tried: 1293
// Total Accepted: 1163
// Total Submissions: 3283
// Difficulty: Easy
// Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.
//
// For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].
//
// Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.
//
// However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
//
// Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.
//
// Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
//
// Note:
//
// The length of words1 and words2 will not exceed 1000.
// The length of pairs will not exceed 2000.
// The length of each pairs[i] will be 2.
// The length of each words[i] and pairs[i][j] will be in the range [1, 20].


class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1 == null || words1.length == 0 || words2 == null || words2.length == 0) {
            return Arrays.equals(words1, words2);
        }
        if (Arrays.equals(words1, words2)) {
            return true;
        }
        int n = words1.length;
        int m = words2.length;
        if (n != m) {
            return false;
        }
        Map<String, Set<String>> map = new HashMap<>();
        for (List<String> pair : pairs) {
            String ori = pair.get(0);
            String to = pair.get(1);
            Set<String> set = map.getOrDefault(ori, new HashSet());
            set.add(to);
            map.put(ori, set);
        }
        for (int i = 0; i < n; i++) {
            String str1 = words1[i];
            String str2 = words2[i];
            if (str1.equals(str2)) {
                continue;
            }
            if (contains(map, str1, str2) || contains(map, str2, str1)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
    private boolean contains(Map<String, Set<String>> map, String str1, String str2) {
        if (map.containsKey(str1) && map.get(str1).contains(str2)) {
                return true;
        }
        return false;
    }
}
